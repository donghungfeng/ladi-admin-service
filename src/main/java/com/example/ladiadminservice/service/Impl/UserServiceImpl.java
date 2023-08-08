package com.example.ladiadminservice.service.Impl;


import com.example.ladiadminservice.config.jwt.JwtTokenProvider;
import com.example.ladiadminservice.constants.Status;
import com.example.ladiadminservice.model.req.AssignUserRoleReq;
import com.example.ladiadminservice.repository.entity.*;
import com.example.ladiadminservice.model.BaseResponse;
import com.example.ladiadminservice.model.LoginResponse;
import com.example.ladiadminservice.repository.*;
import com.example.ladiadminservice.model.req.LoginRequest;
import com.example.ladiadminservice.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
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
    private UserRoleService userRoleService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected BaseRepository<User> getRepository() {
        return userRepository;
    }

    @Override
    public User create(User user) {
        user.setUnit(unitService.getById(user.getUnit().getId()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return super.create(user);
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

    @Transactional
    @Override
    public void assignRole(AssignUserRoleReq req) {
        User user = this.getById(req.getUserId());
        userRoleService.deleteByUser(user.getId());
        if (CollectionUtils.isEmpty(req.getRoleIds())) return;
        
        List<Role> roleList = roleService.getAllByInId(req.getRoleIds());
        if (CollectionUtils.isEmpty(roleList)) return;

        List<UserRole> userRoleList = roleList.stream()
                .map(e -> UserRole.builder()
                        .user(user)
                        .role(e)
                        .build())
                .collect(Collectors.toList());
        userRoleService.createAll(userRoleList);
    }

    private boolean isValidPassword(String userPass, String reqPass) {
        return !StringUtils.isEmpty(reqPass) && passwordEncoder.matches(reqPass, userPass);
    }
}

