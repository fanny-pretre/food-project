package in.fpretre.foodiesapi.service;

import java.util.List;

import in.fpretre.foodiesapi.io.OrderRequest;
import in.fpretre.foodiesapi.io.OrderResponse;

public interface OrderService {
OrderResponse createOrder(OrderRequest request);

List<OrderResponse> getUserOrders();

void removeOrder(String orderId);

List<OrderResponse>  getOrdersOfAllUsers();

void updateOrderStatus(String orderId, String status);
}
