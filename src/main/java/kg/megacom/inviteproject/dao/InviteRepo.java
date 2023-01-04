package kg.megacom.inviteproject.dao;

import kg.megacom.inviteproject.models.entity.Invite;
import kg.megacom.inviteproject.models.enums.InviteStatus;
import kg.megacom.inviteproject.models.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InviteRepo extends JpaRepository<Invite, Long> {

    long countAllBySenderAndStartDateAfter(Subscriber sender, Date startDate);
    long countAllBySenderAndReceiverAndEndDateAfter(Subscriber sender, Subscriber receiver, Date startDate);
    List<Invite> findAllByReceiver(Subscriber receiver);
    Optional<Invite> findByReceiverAndStatus(Subscriber receiver, InviteStatus inviteStatus);

}
