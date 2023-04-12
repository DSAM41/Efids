package efids.efids.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import efids.efids.model.Contingency_dep;

@Repository
public interface DepRepository extends JpaRepository<Contingency_dep, Integer> {
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE TABLE contingency_dep", nativeQuery = true)
	void truncateTable();
}
