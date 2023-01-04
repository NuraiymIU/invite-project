package kg.megacom.inviteproject.controllers;

import kg.megacom.inviteproject.models.dto.InvitesDto;
import kg.megacom.inviteproject.services.InvitesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invite")
public class InvitesController {

    @Autowired
    private InvitesService invitesService;

    @PostMapping("/send")
    public InvitesDto send(@RequestBody InvitesDto invitesDto) {
        return invitesService.send(invitesDto);
    }

}
