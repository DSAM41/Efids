package efids.efids.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import efids.efids.model.Airport;

@Repository
public interface FidsAirportRepository extends JpaRepository<Airport, String> {
	List<Airport> findAllByOrderByApcthree();
}
