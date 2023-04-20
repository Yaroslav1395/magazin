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

    List<String> getAllSubscribesEmail();

    Optional<Subscribe> getSubscribeById(Integer id);

    Optional<String> getSubscribeNameById(Integer id);

    String deleteSubscribeById(Integer id);
    SubscribeDto getByEmail(String email);

    SubscribeDto createSubscribe(String email);
}
