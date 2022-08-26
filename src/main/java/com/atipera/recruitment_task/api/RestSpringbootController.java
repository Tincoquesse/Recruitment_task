package com.atipera.recruitment_task.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class RestSpringbootController {

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/user/{username}")
    public Object[] getUserData(@PathVariable String username) {
        String uri = "https://api.github.com/users/" + username + "/repos";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object[]> response = restTemplate.getForEntity(uri, Object[].class);
        Object[] objects = response.getBody();

        return objects;

    }

}
