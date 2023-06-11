package com.example.magazin.service.serviceImpl;

import com.example.magazin.dto.mappers.OrderMapper;
import com.example.magazin.dto.mappers.ProductForOrderMapper;
import com.example.magazin.dto.mappers.UserMapper;
import com.example.magazin.dto.order.OrderDto;
import com.example.magazin.dto.product.ProductForOrderDto;
import com.example.magazin.dto.productImageDto.ProductImageDto;
import com.example.magazin.entity.order.Order;
import com.example.magazin.entity.product.Product;
import com.example.magazin.entity.user.User;
import com.example.magazin.exceptions.ResourceNotFoundException;
import com.example.magazin.repository.category.CategoryRepository;
import com.example.magazin.repository.order.OrderRepository;
import com.example.magazin.repository.product.ProductRepository;
import com.example.magazin.repository.user.UserRepository;
import com.example.magazin.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;
    private CategoryRepository categoryRepository;
    private ProductForOrderMapper productForOrderMapper;
    private UserMapper userMapper;
    private OrderMapper orderMapper;
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
    public List<OrderDto> getOrdersByUserEmail(String userEmail) {
        List<Order> orders = orderRepository.findByUserEmail(userEmail);
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
    public List<OrderDto> getOrderByDate(LocalDate startDate, LocalDate endDate) {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .filter(order ->
                        order.getDateTime().toLocalDate().isBefore(endDate)
                                && order.getDateTime().toLocalDate().isAfter(startDate))
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
    @Transactional
    public OrderDto saveOrder(Set<ProductForOrderDto> productsForOrderDtoList, String userEmail, BigDecimal total) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Product> products = productsForOrderDtoList.stream()
                .map(productForOrderDtoList -> {
                    return productRepository.findById(productForOrderDtoList.getId())
                                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
                })
                .toList();

        products.forEach(product ->
                productRepository.updateProductAmountById(product.getId(), product.getAmount()));
        List<Product> productsChangeAmount = new ArrayList<>();

        productsForOrderDtoList.forEach(productForOrderDto ->{
            for (Product product : products) {
                if (product.getId().equals(productForOrderDto.getId())) {
                    product.setAmount(productForOrderDto.getAmount());
                    productsChangeAmount.add(product);
                }
            }
        });

        Order order = Order.builder()
                .products(productsChangeAmount)
                .user(user)
                .dateTime(LocalDateTime.now())
                .total(total)
                .build();

        Order saveOrder = orderRepository.save(order);

        return OrderDto.builder()
                .id(saveOrder.getId())
                .total(saveOrder.getTotal())
                .user(userMapper.toDto(user))
                .products(saveOrder.getProducts().stream().map(product -> ProductForOrderDto.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .price(product.getPrice())
                                .productImages(product.getProductImages().stream()
                                        .map(productImage -> ProductImageDto.builder()
                                                .src(productImage.getSrc())
                                                .filePath(productImage.getFilePath())
                                                .build())
                                        .collect(Collectors.toList()))
                                .amount(product.getAmount())
                                .build()).collect(Collectors.toList()))
                .dateTime(saveOrder.getDateTime())
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
