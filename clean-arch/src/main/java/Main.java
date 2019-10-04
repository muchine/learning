import flexible.MemoryBasedAuthorizer;
import stable.User;

public class Main {

    public static void main(String[] args) {
        User user = new User(new MemoryBasedAuthorizer());
    }

}
