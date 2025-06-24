package in.bushansirgur.foodiesapi.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 

@Data

// Méthode de Lombok pour créer un constructeur avec tous les arguments
@AllArgsConstructor
// Méthode de Lombok pour créer un constructeur vide 
@NoArgsConstructor

public class FoodRequest {

    private String name; 
    private String description;
    private double price; 
    private String category; 

}
