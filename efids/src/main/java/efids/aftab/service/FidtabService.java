package efids.aftab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efids.aftab.model.Fidtab;
import efids.aftab.repository.FidtabRepository;

@Service
public class FidtabService {

	
	@Autowired
	FidtabRepository fidtabRepo;

	public List<Fidtab> showFidtab() {
		return fidtabRepo.findAllByOrderByBeme();
    }

}
