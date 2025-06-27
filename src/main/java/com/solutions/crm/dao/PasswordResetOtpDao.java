package com.solutions.crm.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solutions.crm.beans.ResetPasswordOtp;
import com.solutions.crm.repository.ResetPasswordOtpRepository;
import com.solutions.crm.service.PasswordResetOtpService;



@Service
public class PasswordResetOtpDao implements PasswordResetOtpService {

	@Autowired
	ResetPasswordOtpRepository otpRepository;

	@Override
	public ResetPasswordOtp findByEmail(String email) {

		return otpRepository.findByEmail(email);
	}

	@Override
	public int updateStatusByEmail(String email) {

		return otpRepository.updateStatusByEmail(email);
	}

	@Override
	public int updateStatusByEmailAndResetToken(String email, String reset_token) {

		return otpRepository.updateStatusByEmailAndResetToken(email, reset_token);
	}

	@Override
	public ResetPasswordOtp findByResetToken(String token) {

		return otpRepository.findByResetToken(token);
	}

	@Override
	public int updateStatusByResetToken(String reset_token) {

		return otpRepository.updateStatusByResetToken(reset_token);
	}

	@Override
	public boolean savepasswordResetOtp(ResetPasswordOtp resetPasswordOtp) {
		try {
			otpRepository.save(resetPasswordOtp);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updatepasswordResetOtp(ResetPasswordOtp resetPasswordOtp, int otp_id) {
		try {
			resetPasswordOtp.setId(otp_id);
			otpRepository.save(resetPasswordOtp);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public ResetPasswordOtp findAndMailResetTokenByCompanyNameAndEmail(int company_id, String company_email) {
		
		return otpRepository.findResetTokenByCompanyNameAndEmail(company_id, company_email);
	}

}
