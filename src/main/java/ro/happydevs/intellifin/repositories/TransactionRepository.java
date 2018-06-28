package ro.happydevs.intellifin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.happydevs.intellifin.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}
