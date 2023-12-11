# 1. Welcome Page

<br>

스프링 부트는 정적 페이지와 템플릿 시작 페이지를 모두 지원합니다.
먼저 구성된 정적 콘텐츠 위치에서 index.html 파일을 찾습니다.
하나라도 없으면 index 템플릿을 찾습니다.
둘 중 하나라도 찾으면 자동으로 응용 프로그램 시작 페이지로 사용됩니다.

<br> 

### 파일 경로
```
resources
ㄴ static 
  ㄴ index.html
  
or
  
resources
ㄴ templates
  ㄴ index.html
```

<br>

### 학습 테스트
welcome page 설정을 연습하는 학습 테스트 입니다.

- 테스트 메서드: `cholog.ResponseStaticTest.responseIndexPage`
- 수행 방법
  - `resources/static/hi.html` 을 이용하여 학습 테스트를 성공시키세요.
  - welcome page 설정을 위해 적절한 위치에 이동을 하거나 파일명을 변경해보세요.

<br>

### 참고자료
- [Spring Boot - Welcome Page](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/#web.servlet.spring-mvc.welcome-page)

<br>

# 2 Static Page

<br>

resources/static 아래의 경로에 위치한 파일은 접근이 가능합니다.
서비스에서 필요한 정적 자원들을 해당 경로에 위치시킨 후 활용할 수 있습니다.

<br>

### 학습 테스트
정적 페이지 설정을 연습하는 학습 테스트 입니다.
- 테스트 메서드: `cholog.ResponseStaticTest.responseStaticPage`
- 수행 방법
  - `resources/templates/static.html` 을 이용하여 학습 테스트를 성공시키세요.
  - 정적 페이지 설정을 위해 적절한 위치에 이동을 하거나 파일명을 변경해보세요.

<br>

### 참고자료
- [Spring - Static Resources](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config/static-resources.html#page-title)

<br>

# 3. Template Engine

<br>

동적으로 페이지 처리를 하기 위해서는 템플릿 엔진을 활용할 수 있습니다.
이번 학습 테스트에서는 `Thymeleaf`를 활용하여 요청에 대한 동적 처리를 합니다.
쿼리 스트링(?name=brown)으로 전달된 name 값을 `@RequestParam`을 활용하여 컨트롤러 메서드의 파라미터로 주입 받습니다.
컨트롤러 메서드 내에서 뷰로 값을 전달하기 위해서 `Model` 객체를 활용합니다.
Model 객체는 컨트롤러 메서드의 파라미터로 주입 받을 수 있고, addAttribute 메서드를 통해 값을 전달할 수 있습니다.

<br>

### 학습 테스트
- 테스트 메서드: `cholog.ResponseTemplatesTest.responseTemplatesPage`
- 수행 방법
  - `cholog.MemberController.world` 메서드를 작성하여 학습 테스트를 성공시키세요.
  - `/template` 요청 시 `resources/templates/hello.html` 페이지가 응답할 수 있도록 설정하세요.

<br>

### 참고자료
- [Spring - Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Spring - @RequestParam](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/requestparam.html)
- [Spring - Method Arguments > Model](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/arguments.html)
- [Baeldung - Introduction to Using Thymeleaf in Spring](https://www.baeldung.com/thymeleaf-in-spring-mvc)

<br>

# 4. Json 응답

<br>

컨트롤러 메서드의 리턴타입을 그대로 body에 담아 응답을 하기 위해서는 `@ResponseBody`를 활용할 수 있습니다.

<br>

### 학습 테스트
- 테스트 메서드: `cholog.ResponseJsonTest.responseJson`
- 수행 방법
  - `cholog.MemberController.json` 메서드를 작성하여 학습 테스트를 성공시키세요.
  - `/json` 요청 시 `{"name": "brown", "age": 20}` 응답할 수 있도록 설정하세요.

<br>

### 참고자료
- [Spring - @ResponseBody](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/responsebody.html#page-title)
- [Spring - Return Values > Other return values](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/return-types.html)
