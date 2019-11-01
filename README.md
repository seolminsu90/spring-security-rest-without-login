# spring-security-rest
- spring + security 참조용
- Rest 방식의 인증 필터를 구현
- Security Login을 제거하고 외부 헥사 토큰으로만 사용할 때 사용할법 함
- @PreAuthorize 활용해봄. 복잡한 조건의 권한 필요시 좋을 듯
- 어디에 바로 쓸 수 있는 프로젝트 X, 부분적으로 활용할 만한 요소 기억용도(security... aop... jpa..)

## update
- 실무에서 하려는 유저권한에 맞게 유저 -> 권한 유저 -> 게임보유 시에 처리 가능하도록 aop 설정추가
- 실무에서 하려는 3계층 권한 구현 시도를 위해 3계층 JPA join Test
