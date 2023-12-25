package peaksoft.repo;

import peaksoft.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    Optional<User> getUserByEmail(String email);

//    Optional<UserResponse> getUserById(Long id);

}
