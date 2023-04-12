package efids.aftab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efids.aftab.model.Airline;
import efids.aftab.repository.AirlineRepository;

@Service
public class AirlineService {
	
	@Autowired
	AirlineRepository airlineRepo;
	
	// READ
//		public List<Airline> getAirline() {
//			return airlineRepo.findByAlc2Not("  ");
//		}
	
	public List<Airline> getAirline() {
		return airlineRepo.findAllByOrderByAlc3();
	}

}
