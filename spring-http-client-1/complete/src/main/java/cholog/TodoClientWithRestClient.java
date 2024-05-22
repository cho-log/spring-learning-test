package cholog;

import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

public class TodoClientWithRestClient {
    private final RestClient restClient;

    public TodoClientWithRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<Todo> getTodos() {
        Todo[] todoBody = restClient.get()
                .uri("/todos")
                .retrieve()
                .body(Todo[].class);

        return Arrays.asList(todoBody);
    }

    public Todo getTodoById(Long id) {
        return restClient.get()
                .uri("/todos/{id}", id)
                .retrieve()
                .onStatus(status -> status.value() == 404, (req, res) -> {
                  throw new TodoException.NotFound(id);
                })
                .body(Todo.class);
    }
}
