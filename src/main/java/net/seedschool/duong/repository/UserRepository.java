package net.seedschool.duong.repository;

import net.seedschool.duong.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);

}
