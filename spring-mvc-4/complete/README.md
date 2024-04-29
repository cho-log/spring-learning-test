# 1. MVC Configuration

<br>

스프링은 `WebMvcConfigurer` 라는 인터페이스 제공하여, 애플리케이션 개발자가 쉽게 MVC 설정을 커스터마이징할 수 있도록 합니다.
`WebMvcConfigurer` 의 메서드를 확인해보면 어떤 항목을 설정할 수 있는지 확인할 수 있습니다.

이 문서에서는 뷰 컨트롤러 매핑, 인터셉터 추가, `ArgumentResolver` 추가를 학습합니다.

<br>

### 참조

- [Spring - MVC Config](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config.html)
- [Be careful when using @Configuration classes with @EnableWebMvc in Spring Boot](https://dev.to/xterm/be-careful-when-using-configuration-classes-with-enablewebmvc-in-spring-boot-2n32)

<br>

# 2. View Controller

<br>

`WebMvcConfigurer` 가 제공하는 `addViewControllers` 메서드를 통해 특정 요청에 대해 뷰를 응답하도록 설정할 수 있습니다.
`addViewControllers` 메서드를 사용하면 컨트롤러를 작성하지 않고도 뷰를 응답할 수 있습니다.
<br>

### 학습 테스트
- 테스트 메서드: `cholog.WebMvcConfigurationTest.addViewControllers`
- 수행 방법
  - `cholog.config.WebMvcConfiguration.addViewControllers` 을 이용하여 학습 테스트를 성공시키세요.

<br>

### 참조
- [Spring - View Controller](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config/view-controller.html)

<br>

### 더 생각해보기
- `WebMvcConfigurer` 를 사용하는 방식과 직접 Controller 를 작성하는 방식 중 어떤 방식이 더 좋을까요? 그렇게 생각하는 이유는 무엇인가요?

<br>

# 3. Interceptor

<br>

Spring Framework에서 Interceptor는 주로 HTTP 요청의 사전 처리와 사후 처리를 관리하는 데 사용되는 컴포넌트입니다. Interceptor를 사용해 컨트롤러로 요청을 전달하기 전이나 후에 특정 로직을 실행할 수 있습니다.

`WebMvcConfigurer` 가 제공하는 `addInterceptor` 메서드를 통해 특정 패턴에 대해 인터셉터가 동작하도록 설정할 수 있습니다.

<br>

### 학습 테스트
- 테스트 메서드: `cholog.WebMvcConfigurationTest.addInterceptors`
- 수행 방법
  - `cholog.config.WebMvcConfiguration.addInterceptors` 을 이용하여 학습 테스트를 성공시키세요.
    - 회원이 아닌 사람이 회원 목록 조회 요청을 하면 권한이 없다는 응답이 나와야 합니다.
    - `CheckLoginInterceptor` 를 사용하여 요청 규약을 지킨 요청에만 응답하도록 설정하세요.
  - `CheckLoginInterceptor`, `MemberController` 에 breakpoint 를 설정하여 디버깅을 진행하며 작동 순서를 확인해 보세요.

<br>

### 참조
- [Spring - DispatcherServlet > Interception](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-servlet/handlermapping-interceptor.html)
- [Spring - MVC Config > Interceptors](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config/interceptors.html)

<br>

### 더 생각해보기
- 스프링이라는 프레임워크가 `HandlerInterceptor` 라는 인터페이스를 제공하고, 이런 행위를 지원하는 이유는 무엇일까요?
- 어떤 상황에서 Interceptor 를 사용할 수 있을까요?

<br>

# 4. Argument Resolver

<br>

Spring Framework에서 `HandlerMethodArgumentResolver`는 요청 데이터를 메서드의 매개변수로 변환할 때 사용하는 전략 인터페이스입니다. 컨트롤러의 메서드가 호출될 때 매개변수에 전달할 객체를 생성하거나 조작하는 로직을 구현할 수 있습니다.

예를 들어, HTTP 요청의 특정 헤더를 객체로 변환하거나, 세션에서 사용자 정보를 가져와 매개변수에 주입하는 등의 작업을 수행할 수 있습니다.

`WebMvcConfigurer` 가 제공하는 `addArgumentResolvers` 메서드를 통해 커스텀 ArgumentResolver를 추가할 수 있습니다.

<br>

### 학습 테스트
- 테스트 메서드: `cholog.WebMvcConfigurationTest.addArgumentResolvers`
- 수행 방법
  - `cholog.config.WebMvcConfiguration.addArgumentResolvers` 을 이용하여 학습 테스트를 성공시키세요.
    - `GET /favorites` 요청 시, 컨트롤러에서 `LoginMember`를 인자로 받을 수 있도록 설정하세요.

<br>

### 참조
- [Handler Method Argument Resolver](https://www.baeldung.com/spring-mvc-custom-data-binder#1-custom-argument-resolver)

<br>

### 더 생각해보기
- 스프링이라는 프레임워크가 `HandlerMethodArgumentResolver` 라는 인터페이스를 제공하고, 이런 행위를 지원하는 이유는 무엇일까요?
- 어떤 상황에서 ArgumentResolver 를 사용할 수 있을까요?

<br>
