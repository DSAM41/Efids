package efids.aftab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import efids.aftab.model.Fidtab;
import efids.aftab.service.FidtabService;

@RestController
public class FidtabController {


    
    @Autowired
    FidtabService fidtabService;
	

    @GetMapping("fidtab/all")
	public List<Fidtab> showFidtab() {
		return fidtabService.showFidtab();
	}    
}

