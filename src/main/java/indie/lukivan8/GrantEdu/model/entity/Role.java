package indie.lukivan8.GrantEdu.model.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Setter @Getter
@NoArgsConstructor
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id")
    private Long roleId;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<Applicant> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return getRoleId().equals(role.getRoleId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleId());
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, HashSet<Applicant> users) {
        this.name = name;
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}
