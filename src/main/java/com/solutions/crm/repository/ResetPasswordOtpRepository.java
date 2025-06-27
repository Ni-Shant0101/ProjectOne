package com.solutions.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.solutions.crm.beans.ResetPasswordOtp;



public interface ResetPasswordOtpRepository extends JpaRepository<ResetPasswordOtp, Integer> {

	@Query(value = "SELECT * FROM reset_password_otp WHERE user_email =:email and status=1", nativeQuery = true)
	ResetPasswordOtp findByEmail(@Param("email") String email);

	@Modifying
	@Query(value = "UPDATE reset_password_otp otp1 SET otp1.status=0 WHERE otp1.user_email =:email", nativeQuery = true)
	int updateStatusByEmail(@Param("email") String email);

	@Modifying
	@Query(value = "UPDATE reset_password_otp otp1 SET otp1.status=0, otp1.reset_token =:reset_token  WHERE otp1.user_email =:email and otp1.status=1", nativeQuery = true)
	int updateStatusByEmailAndResetToken(@Param("email") String email, @Param("reset_token") String reset_token);

	@Query(value = "SELECT * FROM reset_password_otp WHERE reset_token = :token AND status = 1", nativeQuery = true)
	ResetPasswordOtp findByResetToken(@Param("token") String token);

	@Modifying
	@Query(value = "UPDATE reset_password_otp otp1 SET otp1.status=0 WHERE otp1.reset_token =:reset_token", nativeQuery = true)
	int updateStatusByResetToken(@Param("reset_token") String reset_token);

	@Query(value = "SELECT * FROM reset_password_otp WHERE user_id =:user_id AND user_email =:user_email", nativeQuery = true)
	ResetPasswordOtp findResetTokenByCompanyNameAndEmail(@Param("user_id") int user_id,
			@Param("user_email") String user_email);  // flag 1
}
