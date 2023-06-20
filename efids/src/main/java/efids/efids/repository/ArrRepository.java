package efids.efids.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import efids.efids.model.Contingency_arr;



@Repository
public interface ArrRepository extends JpaRepository<Contingency_arr, Integer> {
	@Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE contingency_arr", nativeQuery = true)
    void truncateTable();
	
	 List<Contingency_arr> findAllByOrderByTimeAsc();
}
