package indie.lukivan8.GrantEdu.controller;

import indie.lukivan8.GrantEdu.model.dto.GrantResponse;
import indie.lukivan8.GrantEdu.model.dto.ScoreDTO;
import indie.lukivan8.GrantEdu.model.entity.Grant;
import indie.lukivan8.GrantEdu.service.GrantService;
import indie.lukivan8.GrantEdu.service.ProjectService;
import indie.lukivan8.GrantEdu.utils.MappingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grant")
public class GrantController {
    private final GrantService grantService;
    private final ProjectService projectService;
    private final MappingUtils mappingUtils;

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
        Grant dbFile = grantService.getGrant(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

    @PostMapping("/upload/{project_id}")
    public Grant uploadFile(@RequestBody MultipartFile multipartFile, Principal principal, @PathVariable Long project_id) throws IOException {
        return grantService.uploadGrant(multipartFile, principal, project_id);
    }

    @GetMapping("/list")
    public List<GrantResponse> displayModGrants() {
        return grantService.getModGrants();
    }

    @PostMapping("/score/{project_id}/{id}")
    public ResponseEntity<GrantResponse> scoreGrant(@PathVariable Long id, @PathVariable Long project_id, Principal principal, ScoreDTO scoreDTO) {
        if(!projectService.checkModList(project_id, principal)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(mappingUtils.grantToModSafe(grantService.setScore(id, scoreDTO, principal)));
    }
}
