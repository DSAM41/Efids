package efids.efids.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping(value = "/")
public class EfidsController {
	@RequestMapping(value = "efids")
	public String indexHome() {
		return "efids";
	}
	@RequestMapping(value = "login")
	public String logIn() {
		return "login";
	}
}