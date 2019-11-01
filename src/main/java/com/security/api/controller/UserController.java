package com.security.api.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.security.config.aop.ResourceGameSelector;

//권한체크의 다양한 방법
@RestController
public class UserController {
    @GetMapping("/check/me")
    public Object checkMe() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getPrincipal();
    }

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

    @PreAuthorize("isAuthenticated() and ((principal.games != null and principal.games.contains(#game)) and hasAuthority('ROLE_A'))")
    @GetMapping("/check/pre-a/hasgames")
    public String checkAuthPreAHasGames(@RequestParam String game) {
        return "A is pass";
    }

    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_A')")
    @GetMapping("/check/pre-a/nogames")
    public String checkAuthPreANoHasGames() {
        return "A is pass";
    }

    // 실무에서 같은 Role인데도 못쓸 수 있는 상위권한(보유게임)이 잇는 부분이 있어서 테스트
    @ResourceGameSelector
    @GetMapping("/inject/aop")
    public String injectAopBeforeCustomAnnotation() {
        return "inject";
    }

    @ResourceGameSelector
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_SELECT') and hasAuthority(#game)")
    @GetMapping("/inject/aop/test")
    public String injectAopTest(@RequestAttribute(required = false, name = "selected") String game) {
        return "Hello world !!!";
    }

    @ResourceGameSelector
    @Secured("ROLE_SELECT")
    @GetMapping("/inject/aop/test2")
    public String injectAopTest() {
        return "Hello world !!!";
    }
}
