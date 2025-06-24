// Définit le format du body de la request, c'est à dire que c'est ces données que je vais devoir envoyer en front. Quand je vais faire mon POST, il faudra que ces données et uniquement ces données soient présentes pour la création. 

package in.fpretre.foodiesapi.io;

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
