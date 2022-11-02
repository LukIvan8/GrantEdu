package indie.lukivan8.GrantEdu.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "grants")

public class Grant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "grant_id")
    private Long grantId;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "file_type")
    private String fileType;
    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
    private Applicant author;
    //@ManyToOne
    // private Project project;
    @JsonIgnore
    @Lob
    private byte[] data;
    @Column(name = "scored", columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean scored = false;
    @Column(name = "idea_score")
    private Short ideaScore;
    @Column(name = "plan_score")
    private Short planScore;
    @Column(name = "actuality_score")
    private Short actualityScore;
    @OneToOne
    @JoinColumn(name = "scorer_id", referencedColumnName = "user_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
    private Applicant scorer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grant grant = (Grant) o;
        return Objects.equals(getGrantId(), grant.getGrantId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGrantId());
    }
}
