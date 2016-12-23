package com.paolorizzo.predictor.utils;

//File Name SendEmail.java

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailManager {

	public static final void send(String to, String subject, String text)
			throws Exception {

		final String username = "betmailinfodeliverysystem@gmail.com";
		final String password = "betmailinfodeliverysystem1";
		final String host = "smtp.gmail.com";
		final String port = "465";

		Properties props = new Properties();
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("paolorizzo5@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(text);

			Transport.send(message);

			System.out.println("Mail sent succesfully!");
		} catch (MessagingException e) {
			throw new Exception(e);
		}
	}

	public static void sendMail(String to, String subject, String text) {

		final String username = "betmailinfodeliverysystem@gmail.com";
		final String password = "betmailinfodeliverysystem1";
		final String host = "smtp.gmail.com";
		final String port = "465";

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject(subject);

			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(text);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			// messageBodyPart = new MimeBodyPart();
			// DataSource source = new FileDataSource(new File(""));
			// messageBodyPart.setDataHandler(new DataHandler(source));
			// messageBodyPart.setFileName("MyFile.pdf");
			// multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			message.setContent(multipart);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
