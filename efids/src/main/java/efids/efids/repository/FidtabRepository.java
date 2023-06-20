package efids.efids.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import efids.efids.model.Fidtab;

@Repository
public interface FidtabRepository extends JpaRepository<Fidtab, String> {
	List<Fidtab> findAllByOrderByBeme();
}
