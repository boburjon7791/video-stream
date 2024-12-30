package com.example.demo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@SpringBootApplication
public class DemoApplication {
    String rootFolder="G:/Videoder";
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @ResponseBody
    @GetMapping(value = "/video/{fileName}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> video(@PathVariable String fileName) throws IOException {
        String path=rootFolder+"/"+fileName;
        File file=new File(path);
        UrlResource resource = UrlResource.from(file.toURI());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+resource.getFilename()+"\"")
                .header(HttpHeaders.ACCEPT_RANGES,"bytes")
                .body(resource);
    }

    @GetMapping
    public String home(@RequestParam(required = false, defaultValue = "") String fileName, Model model){
        model.addAttribute("fileName", fileName);
        return "index";
    }
}
