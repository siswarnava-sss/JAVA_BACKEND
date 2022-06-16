package com.example.deep.enpensetracker.Service;

import com.example.deep.enpensetracker.Entity.User;
import com.example.deep.enpensetracker.Entity.UserModel;

public interface UserService {
    User createUser(UserModel user);
    User read(Long id);
    User update(User user,Long id);
    void delete(Long id);

    User getLoggedInUser();
}
