package kg.megacom.inviteproject.services.impl;

import kg.megacom.inviteproject.dao.SubscriberRepo;
import kg.megacom.inviteproject.exceptions.NotFoundException;
import kg.megacom.inviteproject.mappers.SubscriberMapper;
import kg.megacom.inviteproject.models.dto.RequestDto;
import kg.megacom.inviteproject.models.dto.SubscriberDto;
import kg.megacom.inviteproject.models.entity.Subscriber;
import kg.megacom.inviteproject.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    private SubscriberRepo subscriberRepo;

    @Override
    public SubscriberDto saveIfNotExists(SubscriberDto subscriberDto) {
        Subscriber subscriber = SubscriberMapper.INSTANCE.toSubscriber(subscriberDto);

        if (subscriberRepo.existsById(subscriber.getSubsId())) {
            subscriber = subscriberRepo.findById(subscriber.getSubsId()).get();
        } else {
            subscriber.setActive(true);
            subscriber.setEditDate((new Date()));
            subscriber = subscriberRepo.save(subscriber);
        }
        return SubscriberMapper.INSTANCE.toSubscriberDto(subscriber);
    }

    @Override
    public void changeSubscriberStatus(RequestDto requestDto, Boolean status) {
        Optional<Subscriber> existSubscriber = subscriberRepo.findById(requestDto.getSubsId());

        Subscriber subscriber = new Subscriber();

        if(existSubscriber.isPresent()) {
            subscriber = existSubscriber.get();
            subscriber.setEditDate(new Date());
            subscriber.setActive(status);
        } else {
            subscriber.setSubsId(requestDto.getSubsId());
            subscriber.setPhone(requestDto.getPhone());
            subscriber.setActive(status);
        }
        subscriberRepo.save(subscriber);
    }

    @Override
    public Subscriber findById(Long subsId) {
        return subscriberRepo.findById(subsId)
                .orElseThrow(() -> new NotFoundException("Абонент не найден"));
    }
}
