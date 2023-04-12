package efids.aftab.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efids.aftab.model.FidsAirport;
import efids.aftab.repository.FidsAirportRepository;

@Service
public class FidsAirportService {

	
	@Autowired
	FidsAirportRepository fidsAirportRepo;
	
	public Optional<FidsAirport> selectFidsAirport(String model) {
		return fidsAirportRepo.findById(model);
	}

	public List<FidsAirport> showFidsAirport() {
		return fidsAirportRepo.findAllByOrderByApcthree();
    }

}
