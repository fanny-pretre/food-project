package in.fpretre.foodiesapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.fpretre.foodiesapi.io.FoodRequest;
import in.fpretre.foodiesapi.io.FoodResponse;
import in.fpretre.foodiesapi.service.FoodService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/foods")
@AllArgsConstructor

public class FoodController /* Création de la handler method*/ {


// Injection d’un service métier (FoodService) qui s’occupera de la vraie logique : enregistrement en base de données, sauvegarde de fichier, etc.
    private final FoodService foodService;

// Cette méthode est déclenchée quand on appelle l’API en POST
     @PostMapping
public FoodResponse addFood(@RequestPart("food") String foodString, @RequestPart("file") MultipartFile file) {
    
//On crée un ObjectMapper (de Jackson) pour convertir la chaîne JSON foodString en un objet Java FoodRequest.
    ObjectMapper objectMapper = new ObjectMapper();
    FoodRequest request = null;

// On tente de convertir le JSON reçu dans la variable foodString en un objet FoodRequest. Si le JSON est mal formé → on renvoie une erreur 400 avec un message d’erreur personnalisé.
    try {
request = objectMapper.readValue(foodString, FoodRequest.class); 

    } catch(JsonProcessingException ex) {
 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalide JSON for format");
        

    }
FoodResponse response = foodService.addFood(request, file); 
return response;
    }

    @GetMapping
    public List<FoodResponse> readFoods() {
        return foodService.readFoods();
    }

    @GetMapping("/{id}")
    public FoodResponse readFood(@PathVariable String id) {
       return foodService.readFood(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFood(@PathVariable String id) {
        foodService.deleteFood(id);
    }
}