package kg.megacom.inviteproject.mappers;

import kg.megacom.inviteproject.models.dto.SubscribersDto;
import kg.megacom.inviteproject.models.entity.Subscribers;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscribersMapper {

    SubscribersMapper INSTANCE = Mappers.getMapper(SubscribersMapper.class);

    Subscribers toSubscriber(SubscribersDto subscribersDto);

    SubscribersDto toSubscriberDto(Subscribers subscribers);
}
