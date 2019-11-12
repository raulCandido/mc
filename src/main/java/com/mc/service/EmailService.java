package com.mc.service;

import org.springframework.mail.SimpleMailMessage;

import com.mc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmation(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
