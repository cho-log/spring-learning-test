# 1. Basic Auth

<br>

<img src="src/main/resources/image/basic.png">

기본 인증(Basic Authentication) 방식은 사용자의 아이디와 비밀번호를 웹 사이트에 알려주는 간단한 방법입니다.
사용자 이름과 비밀번호를 웹 브라우저에서 서버로 보낼 때는 `Authorization: Basic <credentials>`라는 형태로 요청 헤더에 정보를 실어 보냅니다.
여기서 `<credentials>` 부분에는 사용자의 아이디와 비밀번호를 콜론 하나로 이어붙인 문자열(`{{userName}}:{{password}}`)을 Base64라는 방식으로 인코딩합니다.
실제로 보내지는 정보는 Base64로 인코딩된 문자열 형태로 전송되어, 직접 눈으로 봤을 때는 사용자 이름이나 비밀번호를 쉽게 알아볼 수 없습니다.

```http request
Headers: Authorization=Basic ZW1haWxAZW1haWwuY29tOjEyMzQ=
```

<br>

RestAssured를 사용해 Basic Auth를 적용하려면,
아래와 같이 테스트 코드에 인증 정보를 포함해 Authorization 헤더를 자동으로 설정하도록 작성합니다.

```java
.auth().preemptive().basic(EMAIL, PASSWORD)
```

<br>

Authorization 헤더에 담긴 정보를 추출하는 방법은 아래와 같습니다.

```java
AuthInfo authInfo = authorizationExtractor.extract(request);
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.AuthTest.basicLogin`
- 수행 방법
    - `cholog.BasicLoginController` 을 이용하여 학습 테스트를 성공시키세요.
    - `.auth().preemptive().basic()`과 `.auth().basic()`의 차이를 학습해보세요.

<br>

### 참조
- [`@RequestParam` or `HttpServletRequest`](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-arguments)

<br>

# 2. Session Login

<br>

<img src="src/main/resources/image/session.png">

Basic Authentication 방식의 경우 매 요청마다 아이디와 패스워드를 전송하는데, 보안상으로 문제가 있을 수 있고 효율적이지 않습니다.
세션 기반 로그인 방식은 사용자가 첫 로그인 시에만 신원 정보를 제출하고, 서버는 이를 확인한 뒤 세션 ID를 발급하여 응답합니다.
사용자는 이후의 요청에서 이 세션 ID를 사용하여 자신을 인증하고, 서버는 이를 통해 사용자의 로그인 상태를 식별하고 유지합니다.
세션 ID를 이용함으로써 매 요청시마다 신원을 증명하는 부담을 줄일 수 있습니다.

<br>

세션 ID 응답은 Set-Cookie 헤더에 담겨서 응답됩니다.

```http request
HTTP/1.1 200
Set-Cookie: JSESSIONID=89EA9B9B9F00EAC2B8D2208649EA6260; Path=/; HttpOnly
```

<br>

이후 요청에는 Cookie 헤더에 세션 ID를 담아서 요청합니다.

```http request
Headers: Cookie=JSESSIONID=89EA9B9B9F00EAC2B8D2208649EA6260
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.AuthTest.sessionLogin`
- 수행 방법
    - `cholog.SessionLoginController` 이용하여 학습 테스트를 성공시키세요.

<br>

### 참조
- [HttpSession](https://www.baeldung.com/spring-security-session#2-injecting-the-raw-session-into-a-controller)

# 3. Token Login

<br>

<img src="src/main/resources/image/token.png">

토큰 기반 로그인 방식에서 사용자는 최초 로그인 때 아이디와 패스워드 같은 신원 정보를 포함해서 요청합니다.
서버는 이 정보를 검증한 후, 암호화된 접근 토큰(Access Token, 예: JWT)을 발급하여 응답합니다.
사용자는 이후의 요청에 이 토큰을 포함시켜 자신을 인증하게 되며,
서버는 요청이 들어올 때마다 헤더에 포함된 토큰을 검증하여 사용자의 신원을 확인하고 접근 권한을 제어합니다.

<br>

토큰 발급 이후, 서버로 보내는 요청에는 토큰값은 담아서 보내는데 Authorization 헤더에 담아서 보냅니다.

```http request
Headers: Authorization=Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbEBlbWFpbC5jb20iLCJpYXQiOjE3MDU4NDU2NzIsImV4cCI6MTcwNTg0OTI3Mn0.YibLSi-PenIMc0LJUW50A_hq98uZmQu7OAdIxIvF4MY
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.AuthTest.tokenLogin`
- 수행 방법
    - `cholog.TokenLoginController` 이용하여 학습 테스트를 성공시키세요.
