package efids.aftab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import efids.aftab.model.FidsAirport;

@Repository
public interface FidsAirportRepository extends JpaRepository<FidsAirport, String> {
	List<FidsAirport> findAllByOrderByApcthree();
}
