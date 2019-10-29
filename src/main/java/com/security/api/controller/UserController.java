package com.security.api.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//권한체크의 다양한 방법
@RestController
public class UserController {
    @Secured("ROLE_A")
    @GetMapping("/check/a")
    public String checkAuthA() {
        return "A is pass";
    }

    @Secured("ROLE_B")
    @GetMapping("/check/b")
    public String checkAuthB() {
        return "B is pass";
    }

    @GetMapping("/check/c")
    public String checkAuthC() {
        return "C is pass (No Secured)";
    }

    @PreAuthorize("isAuthenticated() and (principal.games.contains(#game) and hasRole('ROLE_A'))")
    @GetMapping("/check/a-pre")
    public String checkAuthApre(@RequestParam String game) {
        System.out.println(game);
        return "A is pass";
    }
}
