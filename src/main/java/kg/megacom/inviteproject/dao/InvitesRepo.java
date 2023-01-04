package kg.megacom.inviteproject.dao;

import kg.megacom.inviteproject.models.entity.Invites;
import kg.megacom.inviteproject.models.entity.Subscribers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InvitesRepo extends JpaRepository<Invites, Long> {

    long countAllBySenderAndStartDateAfter(Subscribers sender, Date startDate);

}
