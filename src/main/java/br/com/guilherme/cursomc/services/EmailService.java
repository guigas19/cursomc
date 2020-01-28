package br.com.guilherme.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.guilherme.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);

}
