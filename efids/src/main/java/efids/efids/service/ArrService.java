package efids.efids.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efids.efids.model.Contingency_arr;
import efids.efids.repository.ArrRepository;



@Service
public class ArrService {

	@Autowired
	ArrRepository arrRepo;

	// READ
	public List<Contingency_arr> getArr() {
		return arrRepo.findAllByOrderByTimeAsc();
//		return arrRepo.findAll();
	}

	// CREATE
	public Contingency_arr createArr(Contingency_arr model) {
		return arrRepo.save(model);
	}
	
	// CREATEALL
	public List<Contingency_arr> create_allArr(List<Contingency_arr> model) {
		arrRepo.truncateTable();
		return arrRepo.saveAll(model);
    }

	// UPDATE
	public Contingency_arr updateArr(Contingency_arr model) {
		return arrRepo.save(model);
	}

	// DELETE
	public void deleteArr(Contingency_arr model) {
		arrRepo.delete(model);
	}

}
