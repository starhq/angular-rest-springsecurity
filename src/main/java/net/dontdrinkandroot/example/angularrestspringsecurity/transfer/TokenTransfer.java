package net.dontdrinkandroot.example.angularrestspringsecurity.transfer;

/**
 * Created by win7 on 2016/10/2.
 */
public class TokenTransfer {

    private final String token;

    public TokenTransfer(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
