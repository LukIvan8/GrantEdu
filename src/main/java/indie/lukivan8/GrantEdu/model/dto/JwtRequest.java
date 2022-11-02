package indie.lukivan8.GrantEdu.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class JwtRequest {
    @NotEmpty(message = "pls provide uid")
    private String uid;
    @NotEmpty(message = "pls, provide password")
    private String password;

    public JwtRequest(String uid, String password) {
        this.setUid(uid);
        this.setPassword(password);
    }
}