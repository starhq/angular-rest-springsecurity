package net.dontdrinkandroot.example.angularrestspringsecurity.transfer;

import java.util.Map;

/**
 * Created by win7 on 2016/10/2.
 */
public class UserTransfer {

    private final String name;

    private final Map<String, Boolean> roles;

    public UserTransfer(String name, Map<String, Boolean> roles) {
        this.name = name;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public Map<String, Boolean> getRoles() {
        return roles;
    }
}
