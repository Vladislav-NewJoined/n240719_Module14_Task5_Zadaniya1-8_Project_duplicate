package task14_5_1.zadanye2_7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import task14_5_1.zadanye2_7.models.Chat;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    Optional<Chat> findByChatIdEquals(long chatId);

}
