package in.fpretre.foodiesapi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import in.fpretre.foodiesapi.io.FoodRequest;
import in.fpretre.foodiesapi.io.FoodResponse;



public interface FoodService {

   String uploadFile(MultipartFile file);

    FoodResponse addFood(FoodRequest request, MultipartFile file);

    List<FoodResponse> readFoods();

    FoodResponse readFood(String id);

    boolean deleteFile(String filename);

    void deleteFood(String id); 

}
