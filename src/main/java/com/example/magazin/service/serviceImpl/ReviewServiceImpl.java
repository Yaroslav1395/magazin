package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.mappers.UserMapper;
import com.example.magazin.dto.review.ReviewDto;
import com.example.magazin.dto.review.ReviewForSaveDto;
import com.example.magazin.entity.product.Product;
import com.example.magazin.entity.review.Review;
import com.example.magazin.entity.user.User;
import com.example.magazin.exceptions.ResourceNotFoundException;
import com.example.magazin.repository.product.ProductRepository;
import com.example.magazin.repository.review.ReviewRepository;
import com.example.magazin.repository.user.UserRepository;
import com.example.magazin.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private ReviewRepository reviewRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private UserMapper userMapper;
    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(review -> ReviewDto.builder()
                    .id(review.getId())
                    .message(review.getMessage())
                    .dateTime(review.getDateTime())
                    .userDto(userMapper.toDto(review.getUser()))
                    .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllReviews(Sort sort) {
        return reviewRepository.findAll(sort).stream()
                .map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .message(review.getMessage())
                        .dateTime(review.getDateTime())
                        .userDto(userMapper.toDto(review.getUser()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewDto> getAllReviews(Pageable pageable) {
        return reviewRepository.findAll(pageable).stream()
                .map(review -> ReviewDto.builder()
                        .id(review.getId())
                        .message(review.getMessage())
                        .dateTime(review.getDateTime())
                        .userDto(userMapper.toDto(review.getUser()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto saveReview(ReviewForSaveDto reviewForSaveDto) {
        User user;
        Product product;
        try {
            user = userRepository.findById(reviewForSaveDto.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            product = productRepository.findById(reviewForSaveDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }

        Review review = Review.builder()
                .message(reviewForSaveDto.getMessage())
                .dateTime(reviewForSaveDto.getDateTime())
                .product(product)
                .user(user)
                .build();

        Review saveReview = reviewRepository.save(review);

        return ReviewDto.builder()
                .id(saveReview.getId())
                .message(saveReview.getMessage())
                .dateTime(saveReview.getDateTime())
                .userDto(userMapper.toDto(user)).build();
    }

    @Override
    public boolean deleteReviewById(Integer id) {
        Review review;
        try {
            review = reviewRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return false;
        }
        reviewRepository.deleteById(review.getId());
        return true;
    }

    @Override
    public ReviewDto updateReviews(Integer reviewId, ReviewDto reviewDto) {
        Review review;
        try {
            review = reviewRepository.findById(reviewId)
                    .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
            reviewRepository.deleteById(review.getId());

            Review updateReview = reviewRepository.save(Review.builder()
                            .dateTime(reviewDto.getDateTime())
                            .message(reviewDto.getMessage())
                            .user(review.getUser())
                            .product(review.getProduct())
                    .build());
            return ReviewDto.builder()
                    .id(review.getId())
                    .message(review.getMessage())
                    .dateTime(review.getDateTime())
                    .userDto(userMapper.toDto(review.getUser()))
                    .build();
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long countReviews() {
        return reviewRepository.count();
    }
}
