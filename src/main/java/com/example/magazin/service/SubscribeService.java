package com.example.magazin.service;

import com.example.magazin.dto.subscribe.SubscribeDto;
import com.example.magazin.entity.subscribe.Subscribe;
import com.example.magazin.repository.subscribe.SubscribeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface SubscribeService {
    List<SubscribeDto> getAllSubscribes();
    SubscribeDto getSubscribeById(Integer id);
    SubscribeDto getSubscribeByEmail(String email);
    boolean deleteSubscribeById(Integer id);
    SubscribeDto saveSubscribe(String email);
}
