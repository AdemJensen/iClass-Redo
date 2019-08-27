package top.chorg.kernel.api.auth;

public class LoginRequest {

    public String username, passwordHash;

    public LoginRequest(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }
}
