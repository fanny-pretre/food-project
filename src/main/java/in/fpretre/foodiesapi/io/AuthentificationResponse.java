package in.fpretre.foodiesapi.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public class AuthentificationResponse {
    private String email; 
    private String token; 

}
