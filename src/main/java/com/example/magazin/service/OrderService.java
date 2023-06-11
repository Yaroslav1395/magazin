package com.example.magazin.service;

import com.example.magazin.dto.order.OrderDto;
import com.example.magazin.dto.order.OrderForSave;
import com.example.magazin.dto.product.ProductForOrderDto;
import com.example.magazin.dto.review.ReviewDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface OrderService {
    OrderDto getOrderById(Integer id);
    List<OrderDto> getAllOrders();
    List<OrderDto> getOrdersByUserEmail(String userEmail);
    List<OrderDto> getOrderByDate(LocalDate startDate, LocalDate endDate);
    List<OrderDto> getAllOrders(Sort sort);
    List<OrderDto> getAllOrders(Pageable pageable);
    OrderDto saveOrder(Set<ProductForOrderDto> productForOrderDtoList, String userEmail, BigDecimal total);
    boolean deleteOrderById(Integer id);
    OrderDto updateOrder(Integer orderId, OrderDto orderDto);
    Long countOrders();
}
