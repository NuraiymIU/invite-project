package kg.megacom.inviteproject.dao;

import kg.megacom.inviteproject.models.entity.Invites;
import kg.megacom.inviteproject.models.entity.Subscribers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SubscribersRepo extends JpaRepository<Subscribers, Long> {
}
