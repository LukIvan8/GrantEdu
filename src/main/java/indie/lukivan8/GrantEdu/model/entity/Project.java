package indie.lukivan8.GrantEdu.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private Applicant creator;
    @OneToMany
    //Check grant list if user already posted his one
    private Set<Grant> grantList = new HashSet<>();
    private Set<Applicant> moderators = new HashSet<>();
    public Project(String name){
        this.name = name;
    }

}
