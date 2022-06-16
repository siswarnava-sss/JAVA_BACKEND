package com.example.deep.enpensetracker.Service;

import com.example.deep.enpensetracker.Entity.User;
import com.example.deep.enpensetracker.Entity.UserModel;
//import com.example.deep.enpensetracker.oldUserRepository.UserRepository;
import com.example.deep.enpensetracker.ExpenseRepository.UserRepository;
import com.example.deep.enpensetracker.exception.ItemAlreadyExistsException;
import com.example.deep.enpensetracker.exception.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(UserModel user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new ItemAlreadyExistsException("Email already registered");
        }
        User newUser = new User();
        BeanUtils.copyProperties(user,newUser);
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }
    public User read(Long id) throws ResourceNotFoundException{
        return userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found for the id: "+id));
    }

    @Override
    public User update(User user, Long id) {
        User oldUser = read(id);
        oldUser.setName(user.getName() != null ? user.getName(): oldUser.getName());
        oldUser.setEmail(user.getEmail() != null ? user.getEmail(): oldUser.getEmail());
        oldUser.setPassword(user.getPassword() != null ? bcryptEncoder.encode(user.getPassword()): oldUser.getPassword());
        oldUser.setAge(user.getAge()!=null ? user.getAge() : oldUser.getAge());
        userRepository.save(oldUser);
        return oldUser;
    }

    @Override
    public void delete(Long id) {
        User user = read(id);
        userRepository.delete(user);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Email not registered"));
    }
}
