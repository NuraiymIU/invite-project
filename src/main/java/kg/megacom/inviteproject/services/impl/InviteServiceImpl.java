package kg.megacom.inviteproject.services.impl;

import kg.megacom.inviteproject.dao.InviteRepo;
import kg.megacom.inviteproject.exceptions.NotFoundException;
import kg.megacom.inviteproject.exceptions.ValidationException;
import kg.megacom.inviteproject.mappers.InviteMapper;
import kg.megacom.inviteproject.mappers.SubscriberMapper;
import kg.megacom.inviteproject.models.dto.InviteDto;
import kg.megacom.inviteproject.models.dto.SubscriberDto;
import kg.megacom.inviteproject.models.entity.Invite;
import kg.megacom.inviteproject.models.enums.InviteStatus;
import kg.megacom.inviteproject.models.entity.Subscriber;
import kg.megacom.inviteproject.services.InviteService;
import kg.megacom.inviteproject.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InviteServiceImpl implements InviteService {

    @Autowired
    private InviteRepo inviteRepo;

    @Autowired
    private SubscriberService subscriberService;

    private final Integer DAY_MAX_COUNT_INVITE = 5;
    private final Integer DAY_RECEIVER_INVITE = 1;
    private final Integer MONTH_MAX_COUNT_INVITE = 30;

    @Override
    public InviteDto send(InviteDto inviteDto) {
        SubscriberDto sender = subscriberService.saveIfNotExists(inviteDto.getSender());
        SubscriberDto receiver = subscriberService.saveIfNotExists(inviteDto.getReceiver());

        inviteDto.setSender(sender);
        inviteDto.setReceiver(receiver);

        Invite invite = new Invite();

        if (!inviteDto.getReceiver().isActive()) {
            throw new ValidationException("Абонент заблокировал отправку приглашений");
        }

        if(validate(inviteDto)) {
            invite = InviteMapper.INSTANCE.toInvite(inviteDto);
        }
        List<Invite> inviteList = inviteRepo.findAllByReceiver(SubscriberMapper.INSTANCE.toSubscriber(receiver));

        inviteList = inviteList.stream()
                .peek(i -> {
                    if(i.getInviteStatus().equals(InviteStatus.ACTIVE)) {
                        i.setInviteStatus(InviteStatus.NOT_ACTIVE);
                    }
                })
                .collect(Collectors.toList());

        inviteRepo.saveAll(inviteList);

        invite = inviteRepo.save(invite);
        return InviteMapper.INSTANCE.toInviteDto(invite);

    }

    @Override
    public void acceptInvite(Long subsId) {
        Subscriber subscriber = subscriberService.findById(subsId);

        Invite invite = inviteRepo.findByReceiverAndStatus(subscriber, InviteStatus.ACTIVE)
                .orElseThrow(() -> new NotFoundException("У абонента нет активного приглашения"));

        invite.setInviteStatus(InviteStatus.ACCEPTED);
        inviteRepo.save(invite);
    }

    @Override
    public Long countInvitesForTodayBySender(Subscriber sender, Date date) {
        return inviteRepo.countAllBySenderAndStartDateAfter(sender, date);
    }

    @Override
    public Long countInvitesForMonthBySender(Subscriber sender, Date date) {
        return inviteRepo.countAllBySenderAndStartDateAfter(sender, date);
    }

    @Override
    public Long countInvitesForTodayBySenderAndReceiver(Subscriber sender, Subscriber receiver, Date date) {
        return inviteRepo.countAllBySenderAndReceiverAndEndDateAfter(sender, receiver, date);
    }

    private boolean validate(InviteDto inviteDto) {
        Subscriber sender = SubscriberMapper.INSTANCE.toSubscriber(inviteDto.getSender());
        Subscriber receiver = SubscriberMapper.INSTANCE.toSubscriber(inviteDto.getReceiver());

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

        if(countInvitesForTodayBySenderAndReceiver(sender, receiver, date) > DAY_RECEIVER_INVITE) {
            throw new ValidationException("Вы можете приглашать одного и того же абонента только 1 раз за один день");
        }

        calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();

        if(countInvitesForMonthBySender(sender, date1) > MONTH_MAX_COUNT_INVITE) {
            throw new ValidationException("Вы можете отправлять только 30 приглашений за один месяц");
        }

        List<Invite> inviteList = inviteRepo.findAllByReceiver(receiver);

        inviteList.forEach(i -> {
            if(i.getInviteStatus().equals(InviteStatus.ACCEPTED)) {
                throw new ValidationException("Абонент уже принимал приглашение");
            }
        });
        return true;
    }

}
