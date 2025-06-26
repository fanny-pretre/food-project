

package in.fpretre.foodiesapi.service;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import in.fpretre.foodiesapi.entity.FoodEntity;
import in.fpretre.foodiesapi.io.FoodRequest;
import in.fpretre.foodiesapi.io.FoodResponse;
import in.fpretre.foodiesapi.repository.FoodRepository;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Service

public class FoodServiceImpl implements FoodService{

  @Autowired
    private  S3Client s3Client;

  @Autowired
    private  FoodRepository foodRepository;

    @Value("${aws.s3.bucketname}")
    private String bucketName; 


// Construction d'une méthode responsable de l'upload de fichier sur le S3 bucket
@Override 
    public String uploadFile(MultipartFile file) {
      String filenameExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
      String key =  UUID.randomUUID().toString()+"."+filenameExtension;
      try {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(key)
        .acl("public-read")
        .contentType(file.getContentType())
        .build();

        PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));
      
        if (response.sdkHttpResponse().isSuccessful()) {
            return "https://"+bucketName+".s3.amazonaws.com/"+key;
        }
        else {
           throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload failed");
        }

    
    } catch (IOException ex) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An error occured while loading the file");

      }
    }

    // Ici on vient récupérer la request que l'on transforme en entity mais avant de sauvegarder on ajoute l'image et une fois ok on la transforme en response
    @Override
     public FoodResponse addFood(FoodRequest request, MultipartFile file) {
      FoodEntity newFoodEntity = convertToEntity(request);
      String imageUrl = uploadFile(file);
      newFoodEntity.setImageUrl(imageUrl);
      newFoodEntity = foodRepository.save(newFoodEntity);
      return convertToResponse(newFoodEntity);

     }

     // Méthode qui retourne la liste de tous les foods items. 
     @Override 
public List<FoodResponse> readFoods() {
    List<FoodEntity> databaseEntries =  foodRepository.findAll(); 
    return databaseEntries.stream()
        .map(object -> convertToResponse(object)) // transforme chaque FoodEntity en FoodResponse
        .collect(Collectors.toList()); // rassemble tout dans une List
}

    // Méthode qui retourne un seul food item sinon si pas trouvé alors runtime exception
    @Override
    public  FoodResponse readFood(String id) {
     FoodEntity existingFood = foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Food not found for the id"+id));
     return convertToResponse(existingFood);
    }

     // Ici on vient transformer une food request en entity
     private FoodEntity convertToEntity(FoodRequest request) {
      return FoodEntity.builder()
      .name(request.getName())
      .description(request.getDescription())
      .category(request.getCategory())
      .price(request.getPrice())
      .build();
     }

     // Cette méthode transforme un objet FoodEntity (venant de la base de données) en un objet FoodResponse (destiné à être envoyé au client via l’API, par exemple en JSON).
     private FoodResponse convertToResponse(FoodEntity entity) {
      return FoodResponse.builder()
      .id(entity.getId())
      .name(entity.getName())
      .description(entity.getDescription())
      .category(entity.getCategory())
      .price(entity.getPrice())
      .imageUrl(entity.getImageUrl())
      .build();
     }

}
