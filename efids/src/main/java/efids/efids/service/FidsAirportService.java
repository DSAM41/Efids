package efids.efids.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efids.efids.model.Airport;
import efids.efids.repository.FidsAirportRepository;

@Service
public class FidsAirportService {

	
	@Autowired
	FidsAirportRepository fidsAirportRepo;
	
	public Optional<Airport> selectFidsAirport(String model) {
		return fidsAirportRepo.findById(model);
	}

	public List<Airport> showFidsAirport() {
		return fidsAirportRepo.findAllByOrderByApcthree();
    }

}
