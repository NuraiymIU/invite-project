package kg.megacom.inviteproject.services;

import kg.megacom.inviteproject.models.dto.InviteDto;
import kg.megacom.inviteproject.models.entity.Subscriber;

import java.util.Date;

public interface InviteService {
    InviteDto send(InviteDto inviteDto);
    void acceptInvite(Long subsId);
    Long countInvitesForTodayBySender(Subscriber sender, Date date);
    Long countInvitesForMonthBySender(Subscriber sender, Date date);
    Long countInvitesForTodayBySenderAndReceiver(Subscriber sender, Subscriber receiver, Date date);
}
