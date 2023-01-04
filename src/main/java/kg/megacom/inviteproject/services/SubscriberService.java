package kg.megacom.inviteproject.services;

import kg.megacom.inviteproject.models.dto.RequestDto;
import kg.megacom.inviteproject.models.dto.SubscriberDto;
import kg.megacom.inviteproject.models.entity.Subscriber;

public interface SubscriberService {

    SubscriberDto saveIfNotExists(SubscriberDto subscriberDto);
    void changeSubscriberStatus(RequestDto requestDto, Boolean status);
    Subscriber findById(Long subsId);
}
