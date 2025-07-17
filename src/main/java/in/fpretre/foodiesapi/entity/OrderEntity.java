package in.fpretre.foodiesapi.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import in.fpretre.foodiesapi.io.OrderItem;
import lombok.Builder;
import lombok.Data;

@Document(collection="orders")
@Data
@Builder
public class OrderEntity {
    @Id
    private String id;
    private String userId; 
    private String userAddress; 
    private String phoneNumber; 
    private String email; 
    private List<OrderItem> orderedItems; 
    private double amount; 
    private String paymentStatus; 
    private String orderStatus; 
}
