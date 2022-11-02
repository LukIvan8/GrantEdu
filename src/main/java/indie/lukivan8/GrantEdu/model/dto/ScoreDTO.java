package indie.lukivan8.GrantEdu.model.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class ScoreDTO {
    @Min(1)@Max(10)
    private Short actuality;
    @Min(1)@Max(10)
    private Short idea;
    @Min(1)@Max(10)
    private Short plan;
}
