package stable;

import flexible.MemoryBasedAuthorizer;

public class User {

    private final int id = 440730;

    private final String name = "Sejoon Lim";

    private final String title = "CTO";

    private final MemoryBasedAuthorizer authorizer;

    public User(MemoryBasedAuthorizer authorizer) {
        this.authorizer = authorizer;
    }

    public void doSomething() {
        if (authorizer.isMemoryLoaded() && !authorizer.isAuthorized(id)) return;

        // Do something
    }

}
