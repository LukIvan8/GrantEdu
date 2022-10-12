package indie.lukivan8.GrantEdu.controller;

import indie.lukivan8.GrantEdu.model.entity.Grant;
import indie.lukivan8.GrantEdu.service.GrantService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
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

    @GetMapping("/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long id) {
        Grant dbFile = grantService.getGrant(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(dbFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
                .body(new ByteArrayResource(dbFile.getData()));
    }

    @PostMapping("/upload")
    public Grant uploadFile(@RequestBody MultipartFile multipartFile, Principal principal) throws IOException {
        return grantService.uploadGrant(multipartFile, principal);
    }

    @GetMapping("/display")
    public List<Grant> displayGrants(){
        return grantService.getAllGrants();
    }
}
