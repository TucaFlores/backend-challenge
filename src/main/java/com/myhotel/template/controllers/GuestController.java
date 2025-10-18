package com.myhotel.template.controllers;

import com.myhotel.template.models.GuestsEmailDomainCount;
import com.myhotel.template.services.GuestServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/guests")
public class GuestController {

    private final GuestServices guestServices;

    public GuestController(GuestServices guestServices) {
        this.guestServices = guestServices;
    }

    @GetMapping("/email/domain_count")
    public ResponseEntity<List<GuestsEmailDomainCount>> getGuestsByDomain() {
        List<GuestsEmailDomainCount> domains = guestServices.getGuestsEmailDomainCount();
        return ResponseEntity.ok(domains);
    }
}
