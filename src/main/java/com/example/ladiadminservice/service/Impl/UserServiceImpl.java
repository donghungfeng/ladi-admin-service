package com.example.ladiadminservice.service.Impl;


import com.example.ladiadminservice.config.jwt.CustomUserDetails;
import com.example.ladiadminservice.config.jwt.JwtTokenProvider;
import com.example.ladiadminservice.model.*;
import com.example.ladiadminservice.query.CustomRsqlVisitor;
import com.example.ladiadminservice.reponse.BaseResponse;
import com.example.ladiadminservice.reponse.LoginReponse;
import com.example.ladiadminservice.repository.*;
import com.example.ladiadminservice.request.CreateUserRequest;
import com.example.ladiadminservice.request.LoginRequest;
import com.example.ladiadminservice.service.UserService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UnitRepository unitRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleUserRepository roleUserRepository;

    @Autowired
    RoleFunctionRepository roleFunctionRepository;
    @Autowired
    FunctionRepository functionRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    protected BaseRepository<User> getRepository() {
        return userRepository;
    }

    public BaseResponse createUser(CreateUserRequest createUserRequest) throws NoSuchAlgorithmException {
        Unit unit = unitRepository.findAllById(createUserRequest.getUnitId());
        User user = modelMapper.map(createUserRequest, User.class);
        user.setUnit(unit);
        user.setPassword(enCode(user.getPassword()));
        User user1 = userRepository.save(user);
        Role role = roleRepository.findAllById(createUserRequest.getRoleId());
        RoleUser roleUser = new RoleUser();
        roleUser.setCode(unit.getName());
        roleUser.setName(unit.getName()+"-"+role.getName());
        roleUser.setUserId(user1.getId());
        roleUser.setRoleId(role.getId());
        roleUserRepository.save(roleUser);
        return new BaseResponse(200, "OK", user);
    }

    @Override
    public BaseResponse login(LoginRequest loginRequest, String unit) throws NoSuchAlgorithmException {
        Node userRootNode = new RSQLParser().parse("unit.code=="+unit+";userName=="+loginRequest.getUserName());
        Specification<User> userSpec = userRootNode.accept(new CustomRsqlVisitor<User>());
        User user = userRepository.findAll(userSpec).get(0);
        if (user == null){
            return new BaseResponse(500, "Account không tồn tại", null);
        }
        String password = enCode(loginRequest.getPassword());
        if (!user.getPassword().equals(password)){
            return new BaseResponse(500, "Mật khẩu không chính xác", null);
        }
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        List<RoleUser> roleUserList = roleUserRepository.findAllByUserId(user.getId());
        List<RoleFunction> roleFunctionList = new ArrayList<>();
        List<Long> roleIdList = new ArrayList<>();
        for (RoleUser item : roleUserList){
            roleFunctionList.addAll(roleFunctionRepository.findAllByRoleId(item.getRoleId()));
            roleIdList.add(item.getRoleId());
        }

        List<Long> functionIdList = new ArrayList<>();
        for (RoleFunction item : roleFunctionList){
            functionIdList.add(item.getFunctionId());
        }
        String roleFilter = "";
        String functionFilter = "";
        if (roleIdList.size() == 1){
            roleFilter = "(" + roleIdList.get(0) + ")";
        }else {
            for (int i = 0; i<roleIdList.size(); i++){
                if (i==0){
                    roleFilter = roleFilter + "(" + roleIdList.get(i);
                }else if (i==roleUserList.size()-1){
                    roleFilter = roleFilter + "," + roleIdList.get(i) + ")";
                }else {
                    roleFilter = roleFilter + "," + roleIdList.get(i);
                }
            }
        }
        if (functionIdList.size() == 1){
            functionFilter = "(" + functionIdList.get(0) + ")";
        }else {
            for (int i = 0; i<functionIdList.size(); i++){
                if (i==0){
                    functionFilter = functionFilter + "(" + functionIdList.get(i);
                }else if (i==roleUserList.size()-1){
                    functionFilter = functionFilter + "," + functionIdList.get(i) + ")";
                }else {
                    functionFilter = functionFilter + "," + functionIdList.get(i);
                }
            }
        }


        Node roleRootNode = new RSQLParser().parse("id=in="+roleFilter);
        Specification<Role> roleSpec = roleRootNode.accept(new CustomRsqlVisitor<Role>());

        List<String> roleList = roleRepository.findAll(roleSpec).stream().map(item -> item.getName()).collect(Collectors.toList());
        Node functionRootNode = new RSQLParser().parse("id=in="+functionFilter);
        Specification<Function> functionSpec = functionRootNode.accept(new CustomRsqlVisitor<Function>());
        List<String> urlList =  functionRepository.findAll(functionSpec).stream().map(item -> item.getUrl()).collect(Collectors.toList());

        LoginReponse loginReponse = new LoginReponse(jwtTokenProvider.generateToken(new CustomUserDetails(roleUserRepository, roleRepository, user)), roleList, urlList );
        return new BaseResponse(200, "OK", loginReponse);
    }


    public String enCode(String string) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(string.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter
                .printHexBinary(digest).toUpperCase();
    }


}

