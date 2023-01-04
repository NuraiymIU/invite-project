package kg.megacom.inviteproject.controllers;

import kg.megacom.inviteproject.models.dto.InviteDto;
import kg.megacom.inviteproject.services.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/invite")
public class InviteController {

    @Autowired
    private InviteService inviteService;

    @PostMapping("/send")
    public InviteDto send(@RequestBody InviteDto inviteDto) {
        return inviteService.send(inviteDto);
    }

    @PostMapping("/accept")
    public void accept(@RequestParam Long subsId) {
        inviteService.acceptInvite(subsId);
    }

}
