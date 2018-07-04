package ro.happydevs.intellifin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.happydevs.intellifin.models.reporting.LogLine;

@Repository
public interface LogLineRepository extends JpaRepository<LogLine, Long> {

}
