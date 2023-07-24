package com.example.ladiadminservice.service.Impl;


import com.example.ladiadminservice.config.jwt.CustomUserDetails;
import com.example.ladiadminservice.config.jwt.JwtTokenProvider;
import com.example.ladiadminservice.model.*;
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

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UnitService unitService;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleUserService roleUserService;

    @Autowired
    RoleFunctionService roleFunctionService;

    @Autowired
    FunctionService functionService;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    protected BaseRepository<User> getRepository() {
        return userRepository;
    }

    public BaseResponse createUser(CreateUserRequest createUserRequest) throws NoSuchAlgorithmException {
        Unit unit = unitService.getById(createUserRequest.getUnitId());
        User user = modelMapper.map(createUserRequest, User.class);
        user.setUnit(unit);
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user = userRepository.save(user);

        mapUserRole(user, createUserRequest.getRoleId(), unit);

        return new BaseResponse(200, "OK", user);
    }

    private void mapUserRole(User user, Long roleId, Unit unit) {
        Role role = roleService.getById(roleId);
        RoleUser roleUser = new RoleUser();
        roleUser.setCode(unit.getName());
        roleUser.setName(unit.getName() + "-" + role.getName());
        roleUser.setUserId(user.getId());
        roleUser.setRoleId(role.getId());
        roleUserService.create(roleUser);
    }

    @Override
    public BaseResponse login(LoginRequest loginRequest, Long unitId) throws NoSuchAlgorithmException {
        Unit unit = unitService.getById(unitId);
        User user = userRepository.findAllByUserNameAndUnit(loginRequest.getUserName(), unit);
        if (user == null) {
            return new BaseResponse(500, "Account không tồn tại", null);
        }

        if (!isValidPassword(user.getPassword(), loginRequest.getPassword())) {
            return new BaseResponse(500, "Mật khẩu không chính xác", null);
        }

        LoginResponse loginResponse = new LoginResponse();
        List<RoleUser> roleUserList = roleUserService.getAllByUserId(user.getId());
        List<Long> roleIdList = roleUserList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());
        loginResponse.setRoleList(roleService.getAllByInId(roleIdList));
        List<RoleFunction> roleFunctionList = roleFunctionService.getAllByInRoleId(roleIdList);
        List<Long> functionIdList = roleFunctionList.stream().map(item -> item.getFunctionId()).collect(Collectors.toList());
        loginResponse.setFunctionList(functionService.getAllByInId(functionIdList));
        loginResponse.setToken(jwtTokenProvider.generateToken(user.getUserName()));
        return new BaseResponse(200, "OK", loginResponse);
    }

    private boolean isValidPassword(String userPass, String reqPass) {
        return !StringUtils.isEmpty(reqPass) && passwordEncoder.matches(reqPass, userPass);
    }
}

