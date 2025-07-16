package in.fpretre.foodiesapi.service;

import in.fpretre.foodiesapi.io.CartRequest;
import in.fpretre.foodiesapi.io.CartResponse;

public interface CartService {

    CartResponse addtoCart(CartRequest request);
    
    CartResponse getCart();

    void clearCart();

    CartResponse removeFromCart(CartRequest cartRequest);

}
