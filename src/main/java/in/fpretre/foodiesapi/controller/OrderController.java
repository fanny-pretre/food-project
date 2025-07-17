package in.fpretre.foodiesapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.fpretre.foodiesapi.io.OrderRequest;
import in.fpretre.foodiesapi.io.OrderResponse;
import in.fpretre.foodiesapi.service.OrderService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService; 

    @PostMapping("/create")
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
       OrderResponse response = orderService.createOrder(request); 
       return response; 
    }

}
