package kg.megacom.inviteproject.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SubscriberDto {
    private Long subsId;
    private String phone;
    private boolean active;
    private Date editDate;
}
