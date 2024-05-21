package cholog;

public class Todo {
    private Long id;
    private Long userId;
    private String title;
    private boolean completed;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }
}
