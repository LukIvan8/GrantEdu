package indie.lukivan8.GrantEdu.controller;

import indie.lukivan8.GrantEdu.model.entity.Applicant;
import indie.lukivan8.GrantEdu.service.ApplicantService;
import indie.lukivan8.GrantEdu.model.dto.RegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class ApplicantController {
    private final ApplicantService applicantService;


    @GetMapping("/list")
    public List<Applicant> getAllUsers() {
        return applicantService.getListOfUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<Applicant> registerUser(@Valid @RequestBody RegisterDTO dto) {
        if (applicantService.checkIfUserExists(dto)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(applicantService.createUser(dto));
    }

}
