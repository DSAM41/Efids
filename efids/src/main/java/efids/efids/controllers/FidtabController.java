package efids.efids.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import efids.efids.model.Fidtab;
import efids.efids.service.FidtabService;

@RestController
public class FidtabController {


    
    @Autowired
    FidtabService fidtabService;
	

    @GetMapping("fidtab/all")
	public List<Fidtab> showFidtab() {
		return fidtabService.showFidtab();
	}    
}

