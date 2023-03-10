package kg.megacom.inviteproject.mappers;

import kg.megacom.inviteproject.models.dto.SubscriberDto;
import kg.megacom.inviteproject.models.entity.Subscriber;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriberMapper {

    SubscriberMapper INSTANCE = Mappers.getMapper(SubscriberMapper.class);

    Subscriber toSubscriber(SubscriberDto subscriberDto);

    SubscriberDto toSubscriberDto(Subscriber subscriber);
}
