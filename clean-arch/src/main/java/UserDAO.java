import service.UserRepository;
import stable.User;

import java.util.List;

public class UserDAO implements UserRepository {

    public void startTransaction() {

    }

    public void commitTransaction() {

    }

    @Override
    public void save(User user) {
        startTransaction();
        String sql = "INSERT INTO USERS ()";
        commitTransaction();
    }

    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM USERS..";
        return null;
    }

    @Override
    public List<User> searchByName(String name) {
        return null;
    }

}
