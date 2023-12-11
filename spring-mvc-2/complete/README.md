# 1. CRUD API

<br>

CRUD는 대부분의 소프트웨어가 가지는 기본적인 데이터 처리 기능으로, Create(생성), Read(읽기), Update(갱신), Delete(삭제)를 의미하는 말입니다.
리소스를 관리하는 일반적인 API를 만들 때 역시 CRUD 기능을 구현합니다.

<br>

# 2. create
<br>

리소스 생성을 요청하는 API입니다.
서버에 데이터를 제출하기 위해 POST 메서드를 사용합니다.
리소스 생성 시 필요한 데이터를 json 형태로 body에 담아 요청을 보냅니다.

<br>

```http request
POST /members HTTP/1.1
content-type: application/json

{
    "name": "브라운",
    "age": 20
}
```

<br>

요청에 성공하면 201 응답코드를 응답 받습니다.
그리고 Location 헤더에 생성된 리소스의 위치를 담아 응답을 받습니다.
생성된 리소스를 확인할 수 있도록 body에 생성된 리소스를 담아 응답할 수 있습니다.

<br>

```http request
HTTP/1.1 201 
Location: /members/1
Content-Type: application/json

{
    "id": 1,
    "name": "브라운",
    "age": 20
}
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.CRUDTest.create`
- 수행 방법
  - `cholog.MemberController.create` 을 이용하여 학습 테스트를 성공시키세요.

<br>

### 참조
- [Spring - ResponseEntity](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/responseentity.html)
- [Spring - @RequestBody](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/requestbody.html)

<br>

# 3. read

<br>

리소스 조회를 요청하는 API입니다.
서버에 리소스의 정보를 검색하기 위해 GET 메서드를 사용합니다.

<br>

```http request
GET /members HTTP/1.1
```

<br>

요청에 성공하면 200 응답코드를 응답 받습니다.
조회된 정보를 body에 담아 응답할 수 있습니다.

<br>

```http request
HTTP/1.1 200 
Content-Type: application/json

[
    {
        "id": 1,
        "name": "브라운",
        "age": 20
    },
    {
        "id": 2,
        "name": "브리",
        "age": 10
    }
]
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.CRUDTest.read`
- 수행 방법
  - `cholog.MemberController.read` 을 이용하여 학습 테스트를 성공시키세요.

<br>

# 4. update

<br>

리소스 수정을 요청하는 API입니다.
리소스를 대체하기 위해 PUT 메서드를 사용합니다.
PATCH를 이용할 수 있으나 전체 리소스를 대체하기 위해 PUT을 사용합니다.
수정할 리소스의 식별자를 url path에 포함해서 요청을 보냅니다.
body 값에는 수정할 정보를 담아서 보냅니다.

<br>

```http request

PUT /members/1 HTTP/1.1

{
    "name": "브라운",
    "age": 30
}
```

<br>

요청에 성공하면 200 응답코드를 응답 받습니다.

<br>

```http request
HTTP/1.1 200
```

<br>


### 학습 테스트
- 테스트 메서드: `cholog.CRUDTest.update`
- 수행 방법
  - `cholog.MemberController.update` 메서드를 작성하여 학습 테스트를 성공시키세요.

<br>

### 참조
- [Spring - Method Arguments > @PathVariable](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/arguments.html)

<br>


# 5. delete

<br>

리소스 삭제를 요청하는 API입니다.
리소스를 삭제하기 위해 DELETE 메서드를 사용합니다.
삭제할 리소스의 식별자를 url path에 포함해서 요청을 보냅니다.

<br>

```http request
DELETE /members/1 HTTP/1.1
```

<br>

요청에 성공하면 204 응답코드를 응답 받습니다.

<br>

```http request
HTTP/1.1 204
```

<br>

### 학습 테스트
- 테스트 메서드: `cholog.CRUDTest.delete`
- 수행 방법
  - `cholog.MemberController.delete` 메서드를 작성하여 학습 테스트를 성공시키세요.
