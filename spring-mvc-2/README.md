# CRUD API

## 1. create
- create 요청을 보내기 위해 POST 메서드를 사용합니다.
- 생성 시 포함될 값을 json형태로 body에 담아 보냅니다.
- 요청에 성공하면 201 응답코드와 함께 생성된 리소스의 location 값이 헤더에 담겨서 응답 옵니다.

### 학습 테스트 - create
- 테스트 메서드: `cholog.CRUDTest.create`
- `cholog.MemberController.create` 을 이용하여 학습 테스트를 성공시키세요.

### 참조
- [Spring - ResponseEntity](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/responseentity.html)
- [Spring - @RequestBody](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/requestbody.html)

## 2. read
- read 요청을 보내기 위해 GET 메서드를 사용합니다.
- 요청에 대한 응답으로 json 형태의 값을 받기를 희망한다는 의미로 헤더에 accept 값을 포함합니다.
- 요청에 성공하면 200 응답코드와 함께 요청한 데이터를 json 형태로 body에 담아 보냅니다.

### 학습 테스트 - read
- 테스트 메서드: `cholog.CRUDTest.read`
- `cholog.MemberController.read` 을 이용하여 학습 테스트를 성공시키세요.

## 3. update
- update 요청을 보내기 위해 PUT 메서드를 사용합니다. PATCH 를 활용할 수 있으나 전체 값을 수정하기 위해 PUT을 사용합니다
- 수정할 리소스의 식별자를 url path에 포함해서 요청을 보냅니다.
- body 값에는 수정할 정보를 담아서 보냅니다.
- 요청에 성공하면 200 응답코드를 응답 받습니다.

### 학습 테스트 -update
- 테스트 메서드: `cholog.CRUDTest.update`
- `cholog.MemberController.update` 메서드를 작성하여 학습 테스트를 성공시키세요.

### 참조
- [Spring - Method Arguments > @PathVariable](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/arguments.html)

### 4. delete
- delete 요청을 보내기 위해 DELETE 메서드를 사용합니다.
- 삭제할 리소스의 식별자를 url path에 포함해서 요청을 보냅니다.
- 요청에 성공하면 204 응답코드를 응답 받습니다.

### 학습 테스트 - delete
- 테스트 메서드: `cholog.CRUDTest.delete`
- `cholog.MemberController.delete` 메서드를 작성하여 학습 테스트를 성공시키세요.