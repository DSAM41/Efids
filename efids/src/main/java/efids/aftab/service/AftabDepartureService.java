package efids.aftab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efids.aftab.model.AftabDeparture;
import efids.aftab.repository.AftabDepartureRepository;

@Service
public class AftabDepartureService {

	@Autowired
	AftabDepartureRepository aftabDepartureRepo;
	
	public List<AftabDeparture> showDeparture() {
		return aftabDepartureRepo.tableDeparture();
    }
}
