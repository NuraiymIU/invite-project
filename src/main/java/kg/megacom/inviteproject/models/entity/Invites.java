package kg.megacom.inviteproject.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Invites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "senderSubsId")
    private Subscribers sender;

    @ManyToOne
    @JoinColumn(name = "receiverSubsId")
    private Subscribers receiver;

    private Date startDate;
    private Date endDate;

    @Enumerated(EnumType.STRING)
    private Status status;
}
