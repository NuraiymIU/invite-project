package kg.megacom.inviteproject.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SubscribersDto {
    private Long subsId;
    private String phone;
    private boolean active;
    private Date editDate;
}
