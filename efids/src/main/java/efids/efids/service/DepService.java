package efids.efids.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efids.efids.model.Contingency_dep;
import efids.efids.repository.DepRepository;

@Service
public class DepService {

	@Autowired
	DepRepository depRepo;

	// READ
	public List<Contingency_dep> getDep() {
		return depRepo.findAll();
	}

	// CREATE
	public Contingency_dep createDep(Contingency_dep model) {
		return depRepo.save(model);
	}

	// CREATEALL
	public List<Contingency_dep> create_allDep(List<Contingency_dep> model) {
		depRepo.truncateTable();
		return depRepo.saveAll(model);
	}

	// UPDATE
	public Contingency_dep updateDep(Contingency_dep model) {
		return depRepo.save(model);
	}

	// DELETE
	public void deleteDep(Contingency_dep model) {
		depRepo.delete(model);
	}

}
