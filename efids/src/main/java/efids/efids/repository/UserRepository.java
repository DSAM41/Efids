package efids.efids.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import efids.efids.model.User_login;



@Repository
public interface UserRepository extends JpaRepository<User_login, Integer> {
	User_login findByUsernameAndPassword(String username, String password);
}
