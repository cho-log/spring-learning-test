# 1. Spring MVC 에서의 예외처리

<br>

Spring MVC 에서는 애플리케이션에서의 예외를 효과적으로 관리할 수 있도록 @ExceptionHandler, @ControllerAdvice 등의 어노테이션을 제공합니다.

이 문서에서는 각 어노테이션의 사용법을 설명하고, 실습할 수 있는 학습 테스트를 안내합니다.

<br>

# 2. @ExceptionHandler
<br>

@ExceptionHandler는 특정 컨트롤러 내에서 발생할 수 있는 예외를 처리하기 위한 메서드에 적용되는 어노테이션입니다. 
이 어노테이션이 적용된 메서드는 해당 컨트롤러에서 처리되지 않은 예외를 캐치하고, 그 예외에 대한 사용자 정의 처리 로직을 실행합니다. 

메서드는 예외 객체를 파라미터로 받을 수 있으며, 적절한 응답을 반환할 수 있습니다.

<br>

```java
@Controller
public class MyController {
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return new ResponseEntity<>("Null Pointer Exception occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.ExceptionTest.handleExceptionUsingExceptionHandler`
- 수행 방법
  - `cholog.controller.ProductController` 에 예외 처리 로직을 추가하여 학습 테스트를 성공시키세요.

<br>

### 참조
- [Spring - Exceptions](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-exceptionhandler.html)

<br>

# 3. @ControllerAdvice

<br>

@ControllerAdvice는 애플리케이션 전역에서 발생하는 예외를 처리하기 위한 클래스에 적용되는 어노테이션입니다. 
이 어노테이션을 사용하면 여러 컨트롤러에 걸쳐 공통적으로 발생할 수 있는 예외를 한 곳에서 처리할 수 있습니다. 

@ExceptionHandler와 같은 다른 어노테이션과 결합하여 사용되며, 특정 패키지 내의 컨트롤러 또는 특정 타입의 컨트롤러에 대해서만 적용할 수도 있습니다.

<br>

```java
@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<String> handleException(Exception ex) {
    return new ResponseEntity<>("An error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.ExceptionTest.handleExceptionUsingControllerAdvice`
- 수행 방법
  - `cholog.controller.ProductController`, `cholog.controller.MemberController` 를 수정하지 않고 학습 테스트를 성공시키세요.

<br>
