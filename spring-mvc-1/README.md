# 1. 페이지 응답
## 1.1 Welcome Page
- 스프링 부트는 정적 페이지와 템플릿 시작 페이지를 모두 지원합니다. 
- 먼저 구성된 정적 콘텐츠 위치에서 index.html 파일을 찾습니다. 
- 하나라도 없으면 index 템플릿을 찾습니다. 
- 둘 중 하나라도 찾으면 자동으로 응용 프로그램 시작 페이지로 사용됩니다.

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

### 학습 테스트 - welcome page
- 테스트 메서드: `cholog.ResponseStaticTest.responseIndexPage`
- `resources/static/hi.html` 을 이용하여 학습 테스트를 성공시키세요.

### 참조
- [Spring Boot - Welcome Page](https://docs.spring.io/spring-boot/docs/3.1.2/reference/htmlsingle/#web.servlet.spring-mvc.welcome-page)


## 1.2 Static Page
- /resources/static 아래의 경로에 위치한 파일은 접근이 가능합니다.

### 학습 테스트 - static page
- 테스트 메서드: `cholog.ResponseStaticTest.responseStaticPage`
- `resources/static/hello.html` 을 이용하여 학습 테스트를 성공시키세요.

### 참조
- [Spring - Static Resources](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-config/static-resources.html#page-title)

## 1.3 Template Engine
- 동적으로 페이지 처리를 하기 위해서는 template engine을 활용할 수 있습니다.
- 이번 학습 테스트에서는 Thymeleaf를 활용하여 동적 처리를 합니다.
- request 시 parameter로 전달된 name 값을 @RequestParam을 활용하여 주입 받습니다.
- 메서드 파라미터로 주입받은 Model 객체를 활용하여 template engine에 전달할 값을 설정합니다.

### 학습 테스트 - template engine
- 테스트 메서드: `cholog.ResponseTemplatesTest.responseTemplatesPage`
- `cholog.ResponseController.world` 메서드를 작성하여 학습 테스트를 성공시키세요.

### 참조
- [Spring - Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Spring - @RequestParam](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/requestparam.html)
- [Spring - Method Arguments > Model](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/arguments.html)
- [Baeldung - Introduction to Using Thymeleaf in Spring](https://www.baeldung.com/thymeleaf-in-spring-mvc)

# 2. 객체 응답

### 2.1 Json 응답
- 컨트롤러 메서드의 리턴타입을 그대로 body에 담아 응답을 하기 위해서는 `@ResponseBody`를 활용합니다.

### 학습 테스트 - json response
- 테스트 메서드: `cholog.ResponseJsonTest.responseJson`
- `cholog.ResponseController.json` 메서드를 작성하여 학습 테스트를 성공시키세요.

### 참조
- [Spring - @ResponseBody](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/responsebody.html#page-title)
- [Spring - Return Values > Other return values](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/return-types.html)