package efids.aftab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efids.aftab.model.AftabArrival;
import efids.aftab.repository.AftabArrivalRepository;

@Service
public class AftabArrivalService {

	@Autowired
	AftabArrivalRepository aftabArrivalRepo;
	
	public List<AftabArrival> showArrival() {
		return aftabArrivalRepo.tableArrival();
    }
}
