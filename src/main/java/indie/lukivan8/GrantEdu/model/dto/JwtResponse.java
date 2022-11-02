package indie.lukivan8.GrantEdu.model.dto;


public class JwtResponse {
    private final String jwttoken;

    public JwtResponse(String jwtToken) {
        this.jwttoken = jwtToken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}