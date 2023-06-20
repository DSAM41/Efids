package efids.efids.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import efids.efids.model.Contingency_dep;
import efids.efids.service.DepService;


@RestController
public class DepController {
	@Autowired
	private DepService depService;

	@GetMapping("dep/all")
	public List<Contingency_dep> showArr() {
		return depService.getDep();
	}
	
	@PostMapping("dep/create")
	public Contingency_dep createArr(@RequestBody Contingency_dep model) {
		return depService.createDep(model);
	}
	
	@PostMapping("dep/createall")
    public List<Contingency_dep> create_allDep(@RequestBody List<Contingency_dep> model) {
		return depService.create_allDep(model);
    }
	
	@PutMapping("dep/update")
	public Contingency_dep updateArr(@RequestBody Contingency_dep model) {
		return depService.updateDep(model);
	}
	
	@DeleteMapping("dep/delete")
	public void deleteArr(@RequestBody Contingency_dep model) {
		depService.deleteDep(model);
	}
}