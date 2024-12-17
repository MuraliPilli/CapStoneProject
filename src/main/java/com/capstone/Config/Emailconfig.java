package com.capstone.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class Emailconfig {
	@Autowired
	JavaMailSender javamailsender;
	@Value("${spring.mail.username}")
	private String from;
	public void sendmail(String to,String body,String subject) {
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		mailMessage.setTo(to);
		mailMessage.setText(body);
		mailMessage.setSubject(subject);
		mailMessage.setFrom(from);
		
		javamailsender.send(mailMessage);
	}
}
