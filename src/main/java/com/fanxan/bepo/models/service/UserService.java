package com.fanxan.bepo.models.service;

import com.fanxan.bepo.models.entity.Users;
import com.fanxan.bepo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class.getName());

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Integer id) {
        logger.info("Fetching user with id: {}" + id);
        return userRepository.findById(id);
    }

    public Users createUser(Users user) {
        Users savedUser = userRepository.save(user);
        logger.info("User created with id: " + savedUser.getId());
        return savedUser;
    }

    public Optional<Users> updateUser(Integer id, Users userDetails) {
        logger.info("Updating user with id: " + id);
        return userRepository.findById(id).map(user -> {
            BeanUtils.copyProperties(userDetails, user, getNullPropertyNames(userDetails));
            logger.info("User updated with id: {}", id);
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(Integer id) {
        logger.info("Deleting user with id: " + id);
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            logger.info("User deleted with id: " + id);
            return true;
        }).orElse(false);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper srcWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = srcWrapper.getPropertyDescriptors();

        Set<String> nullProperties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            Object srcValue = srcWrapper.getPropertyValue(propertyName);
            if (srcValue == null) {
                nullProperties.add(propertyName);
            }
        }

        return nullProperties.toArray(new String[0]);
    }
}