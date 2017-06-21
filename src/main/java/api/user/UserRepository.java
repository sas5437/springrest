package api.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;
import api.user.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {

  @Query("select u from User u where u.email = :email")
  User findByEmail(@Param("email") String email);
}
