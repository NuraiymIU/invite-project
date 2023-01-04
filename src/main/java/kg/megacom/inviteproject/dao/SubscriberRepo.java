package kg.megacom.inviteproject.dao;

import kg.megacom.inviteproject.models.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriberRepo extends JpaRepository<Subscriber, Long> {
}
