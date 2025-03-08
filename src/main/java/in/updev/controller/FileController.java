package in.updev.controller;

import in.updev.edastavej.model.File;
import com.updevlogics.edastavej.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @GetMapping("/files/view/{id}")
    public String viewFileDetails(@PathVariable("id") Long id, Model model) {
        Optional<File> file = fileService.getFileById(id);
        if (file.isPresent()) {
            model.addAttribute("file", file.get());
            return "file_details";
        } else {
            return "redirect:/user/dashboard";
        }
    }

    @GetMapping("/files/open/{id}")
    public ResponseEntity<Resource> viewFile(@PathVariable("id") Long id) {
        Resource file = fileService.getFileAsResource(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("/files/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") Long id) {
        Resource file = fileService.getFileAsResource(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}