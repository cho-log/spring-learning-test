package cholog;

import org.springframework.web.client.RestTemplate;

public class TodoClientWithRestTemplate {
    private final RestTemplate restTemplate;

    public TodoClientWithRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Todo getTodoById(Long id) {
        // TODO: restTemplate을 사용하여 요청을 보내고 결과를 Todo로 변환하여 반환
        // TODO: 존재하지 않는 id로 요청을 보낼 경우 TodoException.NotFound 예외를 던짐
        return new Todo();
    }
}
