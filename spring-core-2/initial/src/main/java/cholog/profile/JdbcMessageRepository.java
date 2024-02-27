package cholog.profile;

import java.util.List;

public class JdbcMessageRepository implements MessageRepository {
    @Override
    public List<String> findMessages() {
        return List.of("Production Profile");
    }
}
