package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.model.User;

public interface UserRepository extends BaseRepository<User>{
    User findByUserName(String userName);
}
