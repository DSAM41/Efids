package efids.efids.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import efids.efids.model.Contingency_arr;
import efids.efids.model.Sync;
import efids.efids.service.SyncService;

@RestController
public class syncController {

	@Autowired
	SyncService syncService;

	// CREATE
	@GetMapping("syncStatus")
	public String getSync() throws JsonProcessingException {
		return syncService.getData();
	}

	// CREATE
	@PostMapping("syncScreen")
	public Sync syncData(@RequestBody Sync model) {
		return syncService.syncData(model);
	}

}
