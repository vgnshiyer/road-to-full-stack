package com.lab6.surveyapi.Controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab6.surveyapi.Services.MarkdownService;

@RestController
public class ApiDocsController {
    
    @Autowired
    private final MarkdownService markdownService;

    public ApiDocsController(MarkdownService markdownService) {
        this.markdownService = markdownService;
    }

    @GetMapping(value = "/api-docs", produces = {"text/html"})
    public String getApiDocs() {
        try {
            Resource resource = new ClassPathResource("static/api-docs.md");
            return markdownService.parse(new String(resource.getInputStream().readAllBytes()));
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_INTERNAL_SERVER_ERROR).body(e.getMessage()).toString();
        }
    }
}
