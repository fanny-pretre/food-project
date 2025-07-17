package in.fpretre.foodiesapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.fpretre.foodiesapi.entity.OrderEntity;
import in.fpretre.foodiesapi.io.OrderRequest;
import in.fpretre.foodiesapi.io.OrderResponse;
import in.fpretre.foodiesapi.repository.OrderRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

@Autowired
private  OrderRepository orderRepository;

@Autowired
private  UserService  userService; 
    
    @Override
    public OrderResponse createOrder(OrderRequest request) {
        OrderEntity newOrder = convertToEntity(request);
        newOrder = orderRepository.save(newOrder);
        
        String loggedInUserId = userService.findByUserId(); 
        newOrder.setUserId(loggedInUserId);
        newOrder = orderRepository.save(newOrder);

        return convertToResponse(newOrder);


    }

    private OrderEntity convertToEntity(OrderRequest request) {
      return OrderEntity.builder()
        .userAddress(request.getUserAddress())
        .amount(request.getAmount())
        .orderedItems(request.getOrderedItems())
        .email(request.getEmail())
        .phoneNumber(request.getPhoneNumber())
        .orderStatus(request.getOrderStatus())
        .build();
    }

    private OrderResponse convertToResponse (OrderEntity newOrder) {
      return OrderResponse.builder()
        .id(newOrder.getId())
        .amount(newOrder.getAmount())
        .userAddress(newOrder.getUserAddress())
        .userId(newOrder.getUserId())
        .paymentStatus(newOrder.getPaymentStatus())
        .orderStatus(newOrder.getOrderStatus())
        .email(newOrder.getEmail())
        .phoneNumber(newOrder.getPhoneNumber())
        .build();
    }

}
