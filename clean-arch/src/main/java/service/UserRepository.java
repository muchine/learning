package service;

import stable.User;

import java.util.List;

public interface UserRepository {

    void save(User user);

    User findById(int id);

    List<User> searchByName(String name);

}
