package com.example.magazin.dto.mappers;

import com.example.magazin.dto.subscribe.SubscribeDto;
import com.example.magazin.entity.subscribe.Subscribe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscribeMapper {
    Subscribe toEntity(SubscribeDto subscribeDto);
    SubscribeDto toDto(Subscribe subscribe);
}
