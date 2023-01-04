package kg.megacom.inviteproject.services;

import kg.megacom.inviteproject.models.dto.InvitesDto;
import kg.megacom.inviteproject.models.entity.Subscribers;

import java.util.Date;

public interface InvitesService {
    InvitesDto send(InvitesDto invitesDto);
    Long countInvitesForTodayBySender(Subscribers sender, Date date);
    Long countInvitesForMonthBySender(Subscribers sender, Date date);
}
