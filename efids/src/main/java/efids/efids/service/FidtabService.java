package efids.efids.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efids.efids.model.Fidtab;
import efids.efids.repository.FidtabRepository;

@Service
public class FidtabService {

	
	@Autowired
	FidtabRepository fidtabRepo;

	public List<Fidtab> showFidtab() {
		return fidtabRepo.findAllByOrderByBeme();
    }

}
