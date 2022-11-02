package indie.lukivan8.GrantEdu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GrantResponse {
    private String fileName;
    private String fileType;
    private byte[] data;
    private Boolean scored;
    private Short ideaScore;
    private Short planScore;
    private Short actualityScore;
}
