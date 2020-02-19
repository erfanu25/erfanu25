package org.erfan.appcentric.services;

import org.erfan.appcentric.domain.Enum.StatusType;
import org.erfan.appcentric.domain.Order;
import org.erfan.appcentric.entity.OrderEntity;
import org.erfan.appcentric.mapper.OrderMapper;
import org.erfan.appcentric.repository.CustomerRepository;
import org.erfan.appcentric.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.customerRepository = customerRepository;
    }

    public List<Order> getOrderList() {
        return orderRepository.findAll()
                .stream()
                .map(orderEntity -> orderMapper
                                        .entityToDomainMapping()
                                        .map(orderEntity))
                .collect(Collectors.toList());
    }

    public List<Order> getOrdersByCustomer(long customerId) {
        return orderRepository.findAllByCustomerEntity(customerRepository.findById(customerId).get())
                .stream()
                .map(orderEntity -> orderMapper
                        .entityToDomainMapping()
                        .map(orderEntity))
                .collect(Collectors.toList());
    }

    public Long saveOrder(Order order) {
        return orderRepository.save(orderMapper.domainToEntityMapping().map(order)).getId();
    }

    public String changeStatus(StatusType statusType, Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        orderEntity.setStatus(statusType);
        Long id = orderRepository.save(orderEntity).getId();
        if (id != null) {
           return "Status Changed";
        } else {
            return "Status Not Changed";
        }
    }

}