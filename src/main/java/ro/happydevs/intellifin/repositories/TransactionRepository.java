package ro.happydevs.intellifin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.happydevs.intellifin.models.business.Transaction;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t where t.accountId = :accountId and t.deleted = false")
    List<Transaction> findAllForAccount(@Param("accountId") Long accountId);

    @Query("select t from Transaction t where t.accountId = :accountId and t.createdAt between :start and :end")
    List<Transaction> findAllForAccountBetweenStartEndDates(@Param("accountId") Long accountId,
                                                            @Param("start") Date start,
                                                            @Param("end") Date end);
}
