package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.Unit;
import com.example.ladiadminservice.repository.entity.User;

public interface UserRepository extends BaseRepository<User>{
    User findByUserName(String userName);

    User findAllByUserNameAndUnit(String userName, Unit unit);

}
