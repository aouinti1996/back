package com.springheroes.wellco.services;


import com.springheroes.wellco.dto.SmsRequest;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);
}
