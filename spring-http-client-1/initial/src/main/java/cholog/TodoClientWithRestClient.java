package cholog;

import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;

public class TodoClientWithRestClient {
    private final RestClient restClient;

    public TodoClientWithRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Todo> getTodos() {
        // TODO: restClient의 get 메서드를 사용하여 요청을 보내고 결과를 Todo 리스트로 변환하여 반환
        return Collections.emptyList();
    }

    public Todo getTodoById(Long id) {
        // TODO: restClient의 get 메서드를 사용하여 요청을 보내고 결과를 Todo로 변환하여 반환
        // TODO: 존재하지 않는 id로 요청을 보낼 경우 TodoException.NotFound 예외를 던짐
        return new Todo();
    }
}
