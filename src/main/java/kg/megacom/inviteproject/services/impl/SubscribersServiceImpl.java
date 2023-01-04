package kg.megacom.inviteproject.services.impl;

import kg.megacom.inviteproject.dao.SubscribersRepo;
import kg.megacom.inviteproject.mappers.SubscribersMapper;
import kg.megacom.inviteproject.models.dto.SubscribersDto;
import kg.megacom.inviteproject.models.entity.Subscribers;
import kg.megacom.inviteproject.services.SubscribersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SubscribersServiceImpl implements SubscribersService {

    @Autowired
    private SubscribersRepo subscribersRepo;

    @Override
    public SubscribersDto saveIfNotExists(SubscribersDto subscribersDto) {

        Subscribers subscribers = SubscribersMapper.INSTANCE.toSubscriber(subscribersDto);

        if (subscribersRepo.existsById(subscribers.getSubsId())) {

            subscribers = subscribersRepo.findById(subscribers.getSubsId()).get();
        } else {
            subscribers.setActive(true);
            subscribers.setEditDate((new Date()));
            subscribers = subscribersRepo.save(subscribers);
        }
        return SubscribersMapper.INSTANCE.toSubscriberDto(subscribers);

    }
}
