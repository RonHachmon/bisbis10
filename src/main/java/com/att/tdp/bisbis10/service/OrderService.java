package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.DishOrderDTO;
import com.att.tdp.bisbis10.dto.OrderDTO;
import com.att.tdp.bisbis10.dto.OrderIdDTO;
import com.att.tdp.bisbis10.entitys.Dish;
import com.att.tdp.bisbis10.entitys.DishOrder;
import com.att.tdp.bisbis10.entitys.Order;
import com.att.tdp.bisbis10.entitys.Restaurant;
import com.att.tdp.bisbis10.repository.DishOrderRepository;
import com.att.tdp.bisbis10.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    private final DishOrderRepository dishOrderRepository;
    private final OrderRepository orderRepository;
    private final RestaurantService restaurantService;
    private final DishService dishService;

    @Autowired
    public OrderService(DishOrderRepository dishOrderRepository, OrderRepository orderRepository, RestaurantService restaurantService, DishService dishService) {
        this.dishOrderRepository = dishOrderRepository;
        this.orderRepository = orderRepository;
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }

    public OrderIdDTO createOrder(OrderDTO orderDTO)
    {
        Restaurant restaurant = restaurantService.getRestaurantIfExist(orderDTO.getRestaurantId());
        Order order = new Order();
        order.setRestaurant(restaurant);
        orderRepository.save(order);
        addDishes(order, orderDTO.getOrderItems());
        return new OrderIdDTO(order.getId());
    }

    private void addDishes(Order order, DishOrderDTO[] orderItems) {
        for (DishOrderDTO dishOrderDTO : orderItems) {
            Dish dish= dishService.getDishIfExist(dishOrderDTO.getDishId());
            DishOrder dishOrder=new DishOrder(dishOrderDTO.getAmount(),dish,order);
            dishOrderRepository.save(dishOrder);
            order.addDishOrder(dishOrder);
        }
        orderRepository.save(order);
    }


}
