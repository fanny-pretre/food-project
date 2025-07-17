package in.fpretre.foodiesapi.service;

import in.fpretre.foodiesapi.io.OrderRequest;
import in.fpretre.foodiesapi.io.OrderResponse;

public interface OrderService {
OrderResponse createOrder(OrderRequest request);
}
