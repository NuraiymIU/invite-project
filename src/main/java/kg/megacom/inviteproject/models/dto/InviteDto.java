package kg.megacom.inviteproject.models.dto;

import kg.megacom.inviteproject.models.enums.InviteStatus;
import lombok.Data;

import java.util.Date;

@Data
public class InviteDto {
    private Long id;
    private SubscriberDto sender;
    private SubscriberDto receiver;
    private Date startDate;
    private Date endDate;
    private InviteStatus inviteStatus;
}
