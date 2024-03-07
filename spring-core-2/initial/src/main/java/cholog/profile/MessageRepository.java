package cholog.profile;

import java.util.List;

public interface MessageRepository {
    List<String> findMessages();
}
