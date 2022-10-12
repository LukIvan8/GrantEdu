package indie.lukivan8.GrantEdu.service;

import indie.lukivan8.GrantEdu.model.entity.Applicant;
import indie.lukivan8.GrantEdu.model.repository.ApplicantRepo;
import indie.lukivan8.GrantEdu.model.dto.RegisterDTO;
import indie.lukivan8.GrantEdu.model.entity.Role;
import indie.lukivan8.GrantEdu.model.repository.RoleRepo;
import indie.lukivan8.GrantEdu.utils.MappingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicantService implements UserDetailsService {
    private final ApplicantRepo applicantRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MappingUtils mapping;
    private final RoleRepo roleRepo;

    public List<Applicant> getListOfUsers() {
        return applicantRepo.findAll();
    }

    public Applicant createUser(RegisterDTO applicantDTO) {
        Applicant applicant = mapping.mapToApplicant(applicantDTO);
        applicant.setPassword(passwordEncoder.encode(applicant.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roles.add(roleRepo.findByName("USER_ROLE"));
        applicant.setRoles(roles);
        return applicantRepo.save(applicant);
    }

    public Boolean checkIfUserExists(RegisterDTO dto) {
        return applicantRepo.findByUid(dto.getUid()) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        Applicant applicant = applicantRepo.findByUid(uid);
        Collection<GrantedAuthority> authorities = new ArrayList<>(applicant.getRoles());
        return new User(applicant.getUsername(), applicant.getPassword(), authorities);
    }
}

