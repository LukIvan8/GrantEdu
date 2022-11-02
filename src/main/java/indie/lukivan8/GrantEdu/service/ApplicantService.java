package indie.lukivan8.GrantEdu.service;

import indie.lukivan8.GrantEdu.model.dto.RegisterDTO;
import indie.lukivan8.GrantEdu.model.entity.Applicant;
import indie.lukivan8.GrantEdu.model.entity.Role;
import indie.lukivan8.GrantEdu.model.repository.ApplicantRepo;
import indie.lukivan8.GrantEdu.model.repository.RoleRepo;
import indie.lukivan8.GrantEdu.security.JwtTokenUtil;
import indie.lukivan8.GrantEdu.utils.MappingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
public class ApplicantService {
    private final ApplicantRepo applicantRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MappingUtils mapping;
    private final RoleRepo roleRepo;
    private final DaoAuthenticationProvider manager;
    private final JwtTokenUtil jwtTokenUtil;
    private final ApplicantDetailsService applicantDetailsService;

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

    public String createToken(String uid, String password) throws Exception {

        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(uid, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails = applicantDetailsService.loadUserByUsername(uid);

        return jwtTokenUtil.generateToken(userDetails);
    }



}

