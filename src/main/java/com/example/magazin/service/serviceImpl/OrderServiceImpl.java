package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.mappers.ProductForOrderMapper;
import com.example.magazin.dto.mappers.UserMapper;
import com.example.magazin.dto.order.OrderDto;
import com.example.magazin.dto.order.OrderForSave;
import com.example.magazin.dto.product.ProductForOrderDto;
import com.example.magazin.dto.review.ReviewDto;
import com.example.magazin.entity.order.Order;
import com.example.magazin.entity.product.Product;
import com.example.magazin.entity.review.Review;
import com.example.magazin.entity.user.User;
import com.example.magazin.exceptions.ResourceNotFoundException;
import com.example.magazin.repository.order.OrderRepository;
import com.example.magazin.repository.product.ProductRepository;
import com.example.magazin.repository.user.UserRepository;
import com.example.magazin.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private ProductForOrderMapper productForOrderMapper;
    private UserMapper userMapper;
    @Override
    public OrderDto getOrderById(Integer id) {
        Order order;
        try {
            order = orderRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
        List<Product> productList = order.getProducts();
        List<ProductForOrderDto> productForOrderDtoList = productList.stream()
                .map(product -> productForOrderMapper.toDto(product))
                .toList();

        return OrderDto.builder()
                .id(order.getId())
                .total(order.getTotal())
                .products(productForOrderDtoList)
                .user(userMapper.toDto(order.getUser()))
                .build();
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> OrderDto.builder()
                    .id(order.getId())
                    .total(order.getTotal())
                    .products(order.getProducts().stream()
                            .map(product -> productForOrderMapper.toDto(product))
                            .toList())
                .user(userMapper.toDto(order.getUser()))
                .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAllOrders(Sort sort) {
        List<Order> orders = orderRepository.findAll(sort);
        return orders.stream()
                .map(order -> OrderDto.builder()
                        .id(order.getId())
                        .total(order.getTotal())
                        .products(order.getProducts().stream()
                                .map(product -> productForOrderMapper.toDto(product))
                                .toList())
                        .user(userMapper.toDto(order.getUser()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepository.findAll(pageable);
        return orders.stream()
                .map(order -> OrderDto.builder()
                        .id(order.getId())
                        .total(order.getTotal())
                        .products(order.getProducts().stream()
                                .map(product -> productForOrderMapper.toDto(product))
                                .toList())
                        .user(userMapper.toDto(order.getUser()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto saveOrder(OrderForSave orderForSave) {
        List<Integer> productIds = orderForSave.getProducts().stream()
                .map(ProductForOrderDto::getId)
                .toList();
        User user;
        try {
            user = userRepository.findById(orderForSave.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
        List<Product> products = productRepository.findAllById(productIds);
        Order order = Order.builder()
                .id(orderForSave.getId())
                .dateTime(orderForSave.getDateTime())
                .total(orderForSave.getTotal())
                .products(products)
                .user(user)
                .build();
        Order saveOrder = orderRepository.save(order);

        return OrderDto.builder()
                .id(saveOrder.getId())
                .total(saveOrder.getTotal())
                .products(saveOrder.getProducts().stream()
                        .map(product -> productForOrderMapper.toDto(product))
                        .toList())
                .user(userMapper.toDto(saveOrder.getUser()))
                .build();
    }

    @Override
    public boolean deleteOrderById(Integer id) {
        Order order;
        try {
            order = orderRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
            orderRepository.deleteById(order.getId());
            return true;
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public OrderDto updateOrder(Integer orderId, OrderDto orderDto) {
        Order order;
        User user;
        List<Integer> productIds = orderDto.getProducts().stream()
                .map(ProductForOrderDto::getId)
                .toList();
        List<Product> products = productRepository.findAllById(productIds);
        try {
            order = orderRepository.findById(orderId)
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

            user = userRepository.findById(orderDto.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));

            orderRepository.deleteById(order.getId());

            Order orderForSave = Order.builder()
                    .id(order.getId())
                    .total(orderDto.getTotal())
                    .dateTime(orderDto.getDateTime())
                    .user(user)
                    .products(products)
                    .build();

            Order saveOrder = orderRepository.save(orderForSave);

            return OrderDto.builder()
                    .id(saveOrder.getId())
                    .total(saveOrder.getTotal())
                    .products(saveOrder.getProducts().stream()
                            .map(product -> productForOrderMapper.toDto(product))
                            .toList())
                    .user(userMapper.toDto(saveOrder.getUser()))
                    .build();
        }catch (ResourceNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long countOrders() {
        return orderRepository.count();
    }
}
