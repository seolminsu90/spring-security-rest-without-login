# spring-security-rest-without-login
- spring + security 참조용
- Rest 방식의 인증 필터를 구현
- Security Login을 제거하고 외부 헥사 토큰으로만 사용할 때 사용할법 함
- @PreAuthorize 활용해봄. 복잡한 조건의 권한 필요시 좋을 듯
- 어디에 바로 쓸 수 있는 프로젝트 X, 부분적으로 활용할 만한 요소 기억용도(security... aop... jpa..)
- 이 기반으로 별도 Login 구현 / Security 적용한 건 cs-homework 참조하기

## 기타 로그인시 부가 정보 필요 시
- WebAuthenticationDetails, AuthenticationDetailsSource 를 커스텀 구현해서 security config에 설정해주면 됨.
- spring-security-rest < 프로젝트의 ExtraParam 참고 *개인적으로 별도 로그인 구현하고 Auth만 가져다 쓰는게 편하긴함..

## update
- 실무에서 하려는 유저권한에 맞게 유저 -> 권한 유저 -> 게임보유 시에 처리 가능하도록 aop 설정추가
- 실무에서 하려는 3계층 권한 구현 시도를 위해 3계층 JPA join Test
  - 또는 AccessDecisionManager 구현해서 @Secured가 아닌 방식으로 직접구현 해야할 듯
