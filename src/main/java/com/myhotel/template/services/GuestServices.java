package com.myhotel.template.services;

import com.myhotel.template.models.GuestsEmailDomainCount;
import com.myhotel.template.repositories.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestServices {

    private final GuestRepository guestRepository;

    public GuestServices(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<GuestsEmailDomainCount> getGuestsEmailDomainCount() {
        return guestRepository.countGuestsByEmailDomain().stream()
                .map(row -> new GuestsEmailDomainCount(
                        (String) row[0], ((Number) row[1]).longValue()))
                .collect(Collectors.toList());
    }
}
