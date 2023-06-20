package efids.efids.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import efids.efids.model.Contingency_arr;
import efids.efids.service.ArrService;


@RestController
public class ArrController {
	@Autowired
	private ArrService arrService;
	
//	@GetMapping("/import")
//	public void mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
//	    System.out.println(reapExcelDataFile);
//	}

	@GetMapping("arr/all")
	public List<Contingency_arr> showArr() {
		return arrService.getArr();
	}
	
	@PostMapping("arr/create")
	public Contingency_arr createArr(@RequestBody Contingency_arr model) {
		return arrService.createArr(model);
	}
	
	@PostMapping("arr/createall")
    public List<Contingency_arr> create_allArr(@RequestBody List<Contingency_arr> model) {
		return arrService.create_allArr(model);
    }
	
	@PutMapping("arr/update")
	public Contingency_arr updateArr(@RequestBody Contingency_arr model) {
		return arrService.updateArr(model);
	}
	
	@DeleteMapping("arr/delete")
	public void deleteArr(@RequestBody Contingency_arr model) {
		arrService.deleteArr(model);
	}
}