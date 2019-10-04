package service;

import stable.User;

public class UserService {

    private final UserRepository dao = null;

    public void save(User user) {
        // do business logics

        // save in the database
        dao.save(user);
    }

}
