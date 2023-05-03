package efids.efids.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import efids.efids.model.Airport;
import efids.efids.service.FidsAirportService;

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
	public ResponseEntity<Airport> getFidsAirport(@PathVariable String apcthree) {
		String id = new String(apcthree);
		Optional<Airport> fidsAirport = fidsAirportService.selectFidsAirport(id);
		if (fidsAirport.isPresent()) {
			return ResponseEntity.ok(fidsAirport.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
  
    @GetMapping("fidsairport/all")
	public List<Airport> showFidsAirport() {
		return fidsAirportService.showFidsAirport();
	}    
}

