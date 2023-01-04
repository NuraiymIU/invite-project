package kg.megacom.inviteproject.models.dto;

import kg.megacom.inviteproject.models.entity.Status;
import lombok.Data;

import java.util.Date;

@Data
public class InvitesDto {
    private Long id;
    private SubscribersDto sender;
    private SubscribersDto receiver;
    private Date startDate;
    private Date endDate;
    private Status status;
}
