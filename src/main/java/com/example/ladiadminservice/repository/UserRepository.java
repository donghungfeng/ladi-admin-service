package com.example.ladiadminservice.repository;

import com.example.ladiadminservice.repository.entity.Unit;
import com.example.ladiadminservice.repository.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
    Optional<User> findByUserName(String userName);

}
