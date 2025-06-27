package com.solutions.crm.service;

import com.solutions.crm.beans.ResetPasswordOtp;

public interface PasswordResetOtpService {

	boolean savepasswordResetOtp(ResetPasswordOtp resetPasswordOtp);

	boolean updatepasswordResetOtp(ResetPasswordOtp resetPasswordOtp, int otp_id);

	ResetPasswordOtp findByEmail(String email);

	int updateStatusByEmail(String email);

	int updateStatusByEmailAndResetToken(String email, String reset_token);

	ResetPasswordOtp findByResetToken(String token);

	int updateStatusByResetToken(String reset_token);

	ResetPasswordOtp findAndMailResetTokenByCompanyNameAndEmail(int company_id, String company_email); //flag 2
}
