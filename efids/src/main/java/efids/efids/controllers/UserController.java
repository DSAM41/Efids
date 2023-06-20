package efids.efids.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import efids.efids.model.User_login;
import efids.efids.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("user/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session) {
		User_login user = userService.userLogin(username, password);
		if (user != null) {
			System.out.println("login : " + user.getUsername() + " | rule : " + user.getRule());
			session.setAttribute("user", user);
			if (user.getRule().equals("admin")) {
				return "redirect:/efids/admin";
			} else {
				return "redirect:/efids/user";
			}
		} else {
			return "redirect:/efids/login?error";
		}
	}

	@GetMapping("user/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/efids/login";
	}
}
