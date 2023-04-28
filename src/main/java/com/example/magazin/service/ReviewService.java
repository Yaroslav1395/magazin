package com.example.magazin.service;

import com.example.magazin.dto.review.ReviewDto;
import com.example.magazin.dto.review.ReviewForSaveDto;
import com.example.magazin.dto.user.UserDto;
import com.example.magazin.dto.user.UserRegistrationDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAllReviews();
    List<ReviewDto> getAllReviews(Sort sort);
    List<ReviewDto> getAllReviews(Pageable pageable);
    ReviewDto saveReview(ReviewForSaveDto reviewForSaveDto);
    boolean deleteReviewById(Integer id);
    ReviewDto updateReviews(Integer reviewId, ReviewDto reviewDto);
    Long countReviews();
}
