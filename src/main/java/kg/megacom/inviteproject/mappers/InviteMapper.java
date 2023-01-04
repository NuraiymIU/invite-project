package kg.megacom.inviteproject.mappers;

import kg.megacom.inviteproject.models.dto.InviteDto;
import kg.megacom.inviteproject.models.entity.Invite;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InviteMapper {

    InviteMapper INSTANCE = Mappers.getMapper(InviteMapper.class);

    Invite toInvite(InviteDto inviteDto);

    InviteDto toInviteDto(Invite invite);

}
