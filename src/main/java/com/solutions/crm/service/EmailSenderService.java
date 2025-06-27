package com.solutions.crm.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendEmail(String toEmail, String subject, String body) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("omkarmagdum5890@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);

		mailSender.send(message);

		System.out.println("Mail Send Successfully...");

		return true;
	}

	public int sendHtmlEmail(String subject, String content, String to) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
			mimeMessage.setContent(content, "text/html;charset=utf-8");
			messageHelper.setFrom("omkarmagdum5890@gmail.com");
			messageHelper.setSubject(subject);
			messageHelper.setTo(to);
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			System.out.println("Exception Occured "+e);
			return -1;
		} finally {
		}
		return 1;
	}

}
