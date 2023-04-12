package efids.aftab.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import efids.aftab.model.FidsAirport;
import efids.aftab.service.FidsAirportService;

@RestController
public class FidsAirportController {

//    @Autowired
//    private FidsAirportService fidsAirportService;
    
//    @GetMapping("fidsairport/a")
//	public FidsAirport selectFidsAirport(@RequestBody FidsAirport model) {
//		return fidsAirportService.selectFidsAirport(model);
//	}    
    
//    @GetMapping("fidsairport/a")
//    public FidsAirport selectFidsAirport(@RequestParam("id") String id) {
//		return fidsAirportService.selectFidsAirport(id);
//	}
    
//    @GetMapping("fidsairport/a")
//	public FidsAirport create(@RequestBody FidsAirport model) {
//		return fidsAirportService.selectFidsAirport(model);
//	}
    
    @Autowired
	FidsAirportService fidsAirportService;
	
	@GetMapping("fidsairport/{apcthree}")
	public ResponseEntity<FidsAirport> getFidsAirport(@PathVariable String apcthree) {
		String id = new String(apcthree);
		Optional<FidsAirport> fidsAirport = fidsAirportService.selectFidsAirport(id);
		if (fidsAirport.isPresent()) {
			return ResponseEntity.ok(fidsAirport.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
  
    @GetMapping("fidsairport/all")
	public List<FidsAirport> showFidsAirport() {
		return fidsAirportService.showFidsAirport();
	}    
}

