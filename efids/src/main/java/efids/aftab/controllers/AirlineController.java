package efids.aftab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import efids.aftab.model.Airline;
import efids.aftab.service.AirlineService;

@RestController
public class AirlineController {
	@Autowired
	private AirlineService airlineService;

	@GetMapping("airline/all")
	public List<Airline> showAirport() {
		return airlineService.getAirline();
	}
}
