package kg.megacom.inviteproject.controllers;

import kg.megacom.inviteproject.models.dto.RequestDto;
import kg.megacom.inviteproject.models.dto.SubscriberDto;
import kg.megacom.inviteproject.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subscriber")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @PostMapping("/save")
    public SubscriberDto save(@RequestBody SubscriberDto subscriberDto) {
        return subscriberService.saveIfNotExists(subscriberDto);
    }

    @PutMapping("/block")
    public void changeSubscriberStatus(@RequestBody RequestDto requestDto, @RequestParam Boolean status) {
        subscriberService.changeSubscriberStatus(requestDto, status);
    }
}
