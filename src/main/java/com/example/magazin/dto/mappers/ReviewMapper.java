package com.example.magazin.dto.mappers;

import com.example.magazin.dto.review.ReviewDto;
import com.example.magazin.entity.review.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toEntity(ReviewDto reviewDto);
    ReviewDto toDto(Review review);
}
