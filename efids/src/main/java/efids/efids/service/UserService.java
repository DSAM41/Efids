package efids.efids.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import efids.efids.model.User_login;
import efids.efids.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;

	public User_login userLogin(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password);
    }
}
