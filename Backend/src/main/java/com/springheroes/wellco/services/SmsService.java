package com.springheroes.wellco.services;

import com.springheroes.wellco.dto.SmsRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SmsService {

    private final SmsSender smsSender;

    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }
}
