package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.mappers.SubscribeMapper;
import com.example.magazin.dto.subscribe.SubscribeDto;
import com.example.magazin.entity.subscribe.Subscribe;
import com.example.magazin.exceptions.EntryAlreadyExists;
import com.example.magazin.repository.subscribe.SubscribeRepository;
import com.example.magazin.service.SubscribeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {
    private SubscribeRepository subscribeRepository;
    private SubscribeMapper subscribeMapper;
    @Override
    public List<SubscribeDto> getAllSubscribes() {
        return null;
    }

    @Override
    public List<String> getAllSubscribesEmail() {
        return null;
    }

    @Override
    public Optional<Subscribe> getSubscribeById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getSubscribeNameById(Integer id) {
        return Optional.empty();
    }

    @Override
    public String deleteSubscribeById(Integer id) {
        return null;
    }

    @Override
    public SubscribeDto getByEmail(String email) {
        Subscribe subscribe = subscribeRepository.findByEmail(email)
                .orElseThrow(() -> new ResolutionException("Subscribe not found"));
        return  subscribeMapper.toDto(subscribe);
    }

    @Override
    public SubscribeDto createSubscribe(String email) {
        Subscribe subscribe = Subscribe.builder().email(email).build();
        try {
            Subscribe exist = subscribeRepository.findByEmail(email)
                    .orElseThrow(() -> new ResolutionException("Subscribe not found"));
            throw new EntryAlreadyExists("This email already exist");
        }catch (ResolutionException e){
            subscribeRepository.save(subscribe);
        }
        return getByEmail(email);
    }
}
