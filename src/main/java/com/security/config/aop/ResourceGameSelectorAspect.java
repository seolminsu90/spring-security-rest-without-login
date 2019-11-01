package com.security.config.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.security.api.repository.UserRepository;

/**
 * ResourceGameSelector 이 포함된 컨트롤러 메소드 실행 이전에 파라미터 작업
 * 
 * 실무 프로젝트에서 컨트롤러 타기 이전에 리소스별 게임 권한 설정해줘야 할만한 일이 잇어서 테스트해보았음.
 * 
 * 조작하려는 리소스의 원점 Root인 게임을 가져오고 그 게임 ROLE을 유저가 가지고 있는지 체크하는 AOP.. 로 활용..
 * 
 * @author ms.seol
 *
 */
@Aspect
@Component
public class ResourceGameSelectorAspect {
    @Autowired
    UserRepository userRepository;

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        // Example
        /**
         * 
         * 
         * 이 곳에서 리소스로 게임을 선택받음~
         * 
         * 
         */

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hasRole = false;
        if (authentication != null) {
            for (GrantedAuthority role : authentication.getAuthorities()) {
                if (role.getAuthority().equals("ROLE_선택된게임")) {
                    hasRole = true;
                    break;
                }
            }
        }

        if (!hasRole) {
            throw new AccessDeniedException("게임 권한이 없습니다.");
        }
    }

    @Pointcut("@annotation(com.security.config.aop.ResourceGameSelector)")
    public void pointcut() {
    }

}
