package efids.efids.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
//@RequestMapping(value = "/")
public class EfidsController {
	@RequestMapping(value = "efids/admin")
	public String indexHomeAdmin() {
		return "efids_admin";
	}
	@RequestMapping(value = "/")
	public RedirectView indexAdmin() {
		return new RedirectView("efids/admin");
	}
	@RequestMapping(value = "efids/user")
	public String indexHomeUser() {
		return "efids_user";
	}
	@RequestMapping(value = "efids/login")
	public String logIn() {
		return "login";
	}
}