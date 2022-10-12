package indie.lukivan8.GrantEdu.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class RegisterDTO {
    @NotEmpty(message = "Should include full name")
    private String fullName;
    @NotEmpty
    private String uid;
    @NotEmpty
    private String email;
    @NotEmpty
    private String phoneNum;
    @NotEmpty
    private String password;
}
