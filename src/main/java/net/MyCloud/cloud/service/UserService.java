package net.MyCloud.cloud.service;

import net.MyCloud.cloud.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
