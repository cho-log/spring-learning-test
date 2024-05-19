# 1. Spring의 HTTP 클라이언트

<br>

스프링 프레임워크는 다양한 HTTP 클라이언트를 제공하여 애플리케이션이 외부 API와 통신할 수 있도록 합니다. 
스프링이 제공하는 HTTP 클라이언트에는 `RestClient`, `WebClient`, `RestTemplate` 이 있습니다.

이 문서에서는 스프링 6.1 버전에 추가된 `RestClient` 에 대해 다룹니다.

<br>

### 참조

- [Spring - REST Clients](https://docs.spring.io/spring-framework/reference/integration/rest-clients.html)
- [Spring blog - New in Spring 6.1: RestClient](https://spring.io/blog/2023/07/13/new-in-spring-6-1-restclient)

<br>

# 2. GET 요청 하기

<br>

`RestClient` 의 `get()` 메서드를 사용해 GET 요청을 할 수 있습니다.

<br>

### 학습 테스트
- 테스트 메서드: `cholog.RestClientTest.testGetTodos`
- 수행 방법
  - `cholog.TodoRestClient.getTodos` 을 이용하여 학습 테스트를 성공시키세요.
    - `TodoRestClient` 의 restClient 를 사용하여 Todo 목록을 조회합니다.
      - Todo 목록 조회 url: http://jsonplaceholder.typicode.com/todos
    - 외부 API의 host는 `RestClientConfig` 에 설정되어 있습니다.

<br>

# 3. GET 요청의 응답 변환하기

<br>

`RestClient` 의 `body()` 메서드를 사용해 응답을 원하는 POJO로 변환할 수 있습니다.

<br>

### 학습 테스트
- 테스트 메서드: `cholog.RestClientTest.testGetTodoWithId`
- 수행 방법
  - `cholog.TodoRestClient.getTodoById` 을 이용하여 학습 테스트를 성공시키세요.
    - `TodoRestClient` 의 restClient 를 사용하여 특정 id를 가진 Todo를 조회합니다.
      - Todo 조회 url: http://jsonplaceholder.typicode.com/todos/1
    - 응답 Body로 받은 `userId`, `id`, `title`, `completed` 값이 잘 매핑되도록 `Todo` 를 수정합니다.

<br>

# 4. 예외 처리

<br>

`RestClient` 의 `onStatus()` 메서드를 사용해 HTTP 응답 코드에 따라 예외처리를 할 수 있습니다.

<br>

### 학습 테스트
- 테스트 메서드: `cholog.RestClientTest.testGetTodoWithNonExistentId`
- 수행 방법
  - `cholog.TodoRestClient.getTodoById` 을 이용하여 학습 테스트를 성공시키세요.
    - 존재하지 않는 id에 대한 조회 요청을 했을 때, `TodoException.NotFound` 예외가 발생하도록 합니다.

<br>

# 5. 더 생각해보기

- 스프링이 제공하는 HTTP 클라이언트에는 각각 다른 성격과 사용 방법을 가지고 있습니다. 
  - `RestClient` 외의 다른 클라이언트에 대한 학습 테스트를 작성해 보며 차이점을 느껴봅니다. 
  - 각 클라이언트의 특징을 찾아보고 어떤 상황에 어떤 클라이언트를 택하는게 좋을지 생각해 봅니다. 
