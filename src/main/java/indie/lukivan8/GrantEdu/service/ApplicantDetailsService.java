package indie.lukivan8.GrantEdu.service;

import indie.lukivan8.GrantEdu.model.entity.Applicant;
import indie.lukivan8.GrantEdu.model.repository.ApplicantRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class ApplicantDetailsService implements UserDetailsService {
    private final ApplicantRepo applicantRepo;
    @Override
    public UserDetails loadUserByUsername(String uid) throws UsernameNotFoundException {
        Applicant applicant = applicantRepo.findByUid(uid);
        Collection<GrantedAuthority> authorities = new ArrayList<>(applicant.getRoles());
        return new User(applicant.getUsername(), applicant.getPassword(), authorities);
    }
}
