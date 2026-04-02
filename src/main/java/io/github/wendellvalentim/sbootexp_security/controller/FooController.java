package io.github.wendellvalentim.sbootexp_security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class FooController {

    @GetMapping("/public")
    public ResponseEntity<String> publicRoute(){
        return ResponseEntity.ok("Public Route, Ok!");
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateRoute(Authentication authentication){
        System.out.println(authentication.getClass());
        return ResponseEntity.ok("Private Route, Ok!, Usuario:" + authentication.getName());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole{'ADMIN'}")
    public ResponseEntity<String> adminRoute(){
        return ResponseEntity.ok("Admin Route, Ok!");
    }
}
