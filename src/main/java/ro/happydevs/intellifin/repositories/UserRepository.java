package ro.happydevs.intellifin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.happydevs.intellifin.models.business.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email and u.password = :password")
    User findByEmailAndPassword(@Param("email") String email,
                                @Param("password") String password);

    @Query("select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);
}
