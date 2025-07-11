package in.fpretre.foodiesapi.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.fpretre.foodiesapi.service.CartService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/cart")
public class CartController {

    private final CartService cartService; 

    @PostMapping
    public ResponseEntity<?> addtoCart(@RequestBody Map<String, String> request) {
        String foodId = request.get("foodId");
        if (foodId == null || foodId.isEmpty()) {
            return ResponseEntity.badRequest().body("Food Id is required");
        }
        cartService.addtoCart(foodId);
        return ResponseEntity.ok().body(null);

    }
}
