package kg.megacom.inviteproject.models.entity;

import kg.megacom.inviteproject.models.enums.InviteStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Invite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_subs_id")
    private Subscriber sender;

    @ManyToOne
    @JoinColumn(name = "receiver_subs_id")
    private Subscriber receiver;

    private Date startDate;
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private InviteStatus inviteStatus;

}
