package in.fpretre.foodiesapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import in.fpretre.foodiesapi.entity.OrderEntity;

@Repository
public interface OrderRepository extends MongoRepository <OrderEntity, String>  {
List<OrderEntity> findByUserId(String userId);
}
