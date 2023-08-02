package com.example.ladiadminservice.service.Impl;


import com.example.ladiadminservice.config.jwt.JwtTokenProvider;
import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.model.UserDto;
import com.example.ladiadminservice.model.req.AddUserRoleReq;
import com.example.ladiadminservice.repository.entity.*;
import com.example.ladiadminservice.response.BaseResponse;
import com.example.ladiadminservice.response.LoginResponse;
import com.example.ladiadminservice.repository.*;
import com.example.ladiadminservice.request.CreateUserRequest;
import com.example.ladiadminservice.request.LoginRequest;
import com.example.ladiadminservice.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UnitService unitService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService roleUserService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected BaseRepository<User> getRepository() {
        return userRepository;
    }

    public BaseResponse createUser(CreateUserRequest createUserRequest) {
        Unit unit = unitService.getById(createUserRequest.getUnitId());
        User user = modelMapper.map(createUserRequest, User.class);
        user.setUnit(unit);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user = super.create(user);

        return new BaseResponse(200, "OK", this.toDto(user));
    }

    private UserDto toDto(User user) {
        return UserDto.builder()
                .userName(user.getUserName())
                .address(user.getAddress())
                .email(user.getEmail())
                .unit(user.getUnit())
                .fullName(user.getFullName())
                .build();
    }

    @Override
    public BaseResponse login(LoginRequest loginRequest) throws NoSuchAlgorithmException {

        Optional<User> userOptional = userRepository.findByUserName(loginRequest.getUserName());
        if (!userOptional.isPresent())
            return new BaseResponse(500, "Account không tồn tại", null);

        User user = userOptional.get();
        if (!Objects.equals(user.getStatus(), Status.ACTIVE))
            return new BaseResponse(500, "Account đã bị khóa", null);

        if (!isValidPassword(user.getPassword(), loginRequest.getPassword())) {
            return new BaseResponse(500, "Mật khẩu không chính xác", null);
        }

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtTokenProvider.generateToken(user.getUserName()));
        return new BaseResponse(200, "OK", loginResponse);
    }

    @Override
    public void addRole(AddUserRoleReq req) {

    }

    private boolean isValidPassword(String userPass, String reqPass) {
        return !StringUtils.isEmpty(reqPass) && passwordEncoder.matches(reqPass, userPass);
    }
}

