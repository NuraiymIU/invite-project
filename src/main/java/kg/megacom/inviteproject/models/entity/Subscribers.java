package kg.megacom.inviteproject.models.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Subscribers {
    @Id
    private Long subsId;
    private String phone;
    private boolean active;
    private Date editDate;
}
