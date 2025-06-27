package com.solutions.crm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.solutions.crm.beans.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {

	Users findByEmail(String email);

	@Query(value = "SELECT * FROM users WHERE username =:username AND status =1", nativeQuery = true)
	Users findByUsername(@Param("username") String username);

	Optional<Users> getByUsername(String username);

	@Query(value = "SELECT * FROM users WHERE status=1", nativeQuery = true)
	List<Users> findAllUsers();

	@Query(value = "SELECT * FROM users WHERE id =:id", nativeQuery = true)
	Optional<Users> findUserById(@Param("id") int id);

	@Query(value = "SELECT * FROM users WHERE status=0 ", nativeQuery = true)
	List<Users> deletedUsers();

	@Modifying
	@Query(value = "UPDATE users user1 SET user1.password =:password, user1.updated_at =:updated WHERE user1.email =:email and user1.id =:id", nativeQuery = true)
	int updatePasswordByEmailAndId(@Param("password") String password, @Param("updated") String updated,
			@Param("email") String email, @Param("id") int id);

	@Modifying
	@Query(value = "UPDATE users u SET u.status = 0, u.updated_at =:updated WHERE u.id =:id", nativeQuery = true)
	int deleteByUserId(@Param("updated") String updated, @Param("id") int id);

	@Modifying
	@Query(value = "UPDATE users SET status = 1, updated_at =:updated WHERE id =:id ", nativeQuery = true)
	int activeByUserId(@Param("updated") String updated, @Param("id") int id);

}
