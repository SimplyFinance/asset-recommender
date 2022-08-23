package finance.simply.asset.recommender.repository;

import finance.simply.asset.recommender.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<Deal, Integer> {
  public List<Deal> findByStartDateLessThanAndEndDateGreaterThan(LocalDate date,LocalDate date1);
}
