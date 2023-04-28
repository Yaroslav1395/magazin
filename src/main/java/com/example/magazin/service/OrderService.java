package com.example.magazin.service;

import com.example.magazin.dto.order.OrderDto;
import com.example.magazin.dto.order.OrderForSave;
import com.example.magazin.dto.review.ReviewDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface OrderService {
    OrderDto getOrderById(Integer id);
    List<OrderDto> getAllOrders();
    List<OrderDto> getAllOrders(Sort sort);
    List<OrderDto> getAllOrders(Pageable pageable);
    OrderDto saveOrder(OrderForSave orderForSave);
    boolean deleteOrderById(Integer id);
    OrderDto updateOrder(Integer orderId, OrderDto orderDto);
    Long countOrders();
}
