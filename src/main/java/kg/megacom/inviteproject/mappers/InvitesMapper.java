package kg.megacom.inviteproject.mappers;

import kg.megacom.inviteproject.models.dto.InvitesDto;
import kg.megacom.inviteproject.models.entity.Invites;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvitesMapper {

    InvitesMapper INSTANCE = Mappers.getMapper(InvitesMapper.class);

    Invites toInvite(InvitesDto invitesDto);

    InvitesDto toInviteDto(Invites invites);

}
