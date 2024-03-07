package cholog.profile;

import java.util.List;

public class InmemoryMessageRepository implements MessageRepository {
    @Override
    public List<String> findMessages() {
        return List.of("Development Profile");
    }
}
