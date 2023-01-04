package kg.megacom.inviteproject.services;

import kg.megacom.inviteproject.models.dto.SubscribersDto;

public interface SubscribersService {

    SubscribersDto saveIfNotExists(SubscribersDto subscribersDto);
}
