package kg.megacom.inviteproject.services.impl;

import kg.megacom.inviteproject.dao.InvitesRepo;
import kg.megacom.inviteproject.exceptions.ValidationException;
import kg.megacom.inviteproject.mappers.InvitesMapper;
import kg.megacom.inviteproject.mappers.SubscribersMapper;
import kg.megacom.inviteproject.models.dto.InvitesDto;
import kg.megacom.inviteproject.models.entity.Invites;
import kg.megacom.inviteproject.models.entity.Subscribers;
import kg.megacom.inviteproject.services.InvitesService;
import kg.megacom.inviteproject.services.SubscribersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class InvitesServiceImpl implements InvitesService {

    @Autowired
    private InvitesRepo invitesRepo;

    @Autowired
    private SubscribersService subscribersService;

    private final Integer DAY_MAX_COUNT_INVITE = 5;
    private final Integer MONTH_MAX_COUNT_INVITE = 30;

    @Override
    public InvitesDto send(InvitesDto invitesDto) {

        invitesDto.setSender(subscribersService.saveIfNotExists(invitesDto.getSender()));
        invitesDto.setReceiver(subscribersService.saveIfNotExists(invitesDto.getReceiver()));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        //   01.06.2021   00:00:00.000
        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);


        if (!invitesDto.getReceiver().isActive()) {

        }

        Invites invites = InvitesMapper.INSTANCE.toInvite(invitesDto);
        return InvitesMapper.INSTANCE.toInviteDto(invites);

    }

    @Override
    public Long countInvitesForTodayBySender(Subscribers sender, Date date) {
        return invitesRepo.countAllBySenderAndStartDateAfter(sender, date);
    }

    @Override
    public Long countInvitesForMonthBySender(Subscribers sender, Date date) {
        return invitesRepo.countAllBySenderAndStartDateAfter(sender, date);
    }

    private boolean validateSubscriberInvite(InvitesDto invitesDto) {

        Subscribers sender = SubscribersMapper.INSTANCE.toSubscriber(invitesDto.getSender());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();

        if(countInvitesForTodayBySender(sender, date) > DAY_MAX_COUNT_INVITE) {
            throw new ValidationException("Вы можете отправлять только 5 приглашений за один день");
        }

        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar = Calendar.getInstance();

        if(countInvitesForTodayBySender(sender, new Date()) > MONTH_MAX_COUNT_INVITE) {
            throw new ValidationException("Вы можете отправлять только 30 приглашений за один месяц");
        }
        return true;
    }

}
