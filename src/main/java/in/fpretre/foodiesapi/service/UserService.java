package in.fpretre.foodiesapi.service;

import in.fpretre.foodiesapi.io.UserResponse;
import in.fpretre.foodiesapi.io.UserRequest;

public interface UserService {
UserResponse registerUser(UserRequest request); 

String findByUserId(); 
}
