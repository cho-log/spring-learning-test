package cholog;

public class TodoException extends RuntimeException {
    public TodoException(String message) {
        super(message);
    }

    public static class NotFound extends TodoException {
        public NotFound(Long id) {
            super("Todo not found with id: " + id);
        }
    }
}
