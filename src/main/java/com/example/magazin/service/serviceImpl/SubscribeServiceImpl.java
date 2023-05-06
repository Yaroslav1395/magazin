package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.mappers.SubscribeMapper;
import com.example.magazin.dto.subscribe.SubscribeDto;
import com.example.magazin.entity.subscribe.Subscribe;
import com.example.magazin.exceptions.EntryAlreadyExists;
import com.example.magazin.exceptions.ResourceNotFoundException;
import com.example.magazin.repository.subscribe.SubscribeRepository;
import com.example.magazin.service.SubscribeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {
    private SubscribeRepository subscribeRepository;
    private SubscribeMapper subscribeMapper;


    @Override
    public List<SubscribeDto> getAllSubscribes() {
        return subscribeRepository.findAll().stream()
                .map(subscribe -> subscribeMapper.toDto(subscribe))
                .collect(Collectors.toList());
    }

    @Override
    public SubscribeDto getSubscribeById(Integer id) {
        Subscribe subscribe;
        try {
            subscribe = subscribeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Subscribe not found0"));
            return subscribeMapper.toDto(subscribe);
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public SubscribeDto getSubscribeByEmail(String email) {
        Subscribe subscribe;
        try {
            subscribe = subscribeRepository.findByEmail(email)
                    .orElseThrow(() -> new ResolutionException("Subscribe not found"));
            return subscribeMapper.toDto(subscribe);
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteSubscribeById(Integer id) {
        Subscribe subscribe;
        try {
            subscribe = subscribeRepository.findById(id)
                    .orElseThrow(() -> new ResolutionException("Subscribe not found"));
            subscribeRepository.deleteById(subscribe.getId());
            return true;
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public SubscribeDto saveSubscribe(String email) {
        return subscribeMapper.toDto(subscribeRepository.save(Subscribe.builder().email(email).build()));
    }

    @Override
    public boolean existByEmail(String email) {
        return subscribeRepository.existsByEmail(email);
    }

}
