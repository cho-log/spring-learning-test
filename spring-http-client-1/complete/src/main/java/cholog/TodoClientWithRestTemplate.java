package cholog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class TodoClientWithRestTemplate {
    private final RestTemplate restTemplate;

    public TodoClientWithRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Todo getTodoById(Long id) {
        try {
            ResponseEntity<Todo> response = restTemplate.getForEntity("http://jsonplaceholder.typicode.com/todos/{id}", Todo.class, id);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new TodoException.NotFound(id);
            }
            throw new TodoException("Failed to get todo with id: " + id);
        }
    }
}
