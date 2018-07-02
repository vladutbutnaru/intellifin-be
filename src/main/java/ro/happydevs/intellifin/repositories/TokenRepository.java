package ro.happydevs.intellifin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.happydevs.intellifin.models.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    @Query("select t from Token t where t.code = :code and t.valid = true")
    Token findByCode(@Param("code") String code);

}
