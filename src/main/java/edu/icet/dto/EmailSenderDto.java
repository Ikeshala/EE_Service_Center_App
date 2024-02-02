package edu.icet.dto;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailSenderDto {
    private static final String PROPERTIES_FILE = "/email.properties";

    public static void sendEmail(String to, String subject, String body) {
        Properties emailProperties = loadProperties();

        Session session = Session.getInstance(emailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        emailProperties.getProperty("email.username"),
                        emailProperties.getProperty("email.password")
                );
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailProperties.getProperty("email.username")));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Failed to send the Email.");
        }
    }


    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = EmailSenderDto.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.out.println("Sorry, unable to find " + PROPERTIES_FILE);
                return properties;
            }

            properties.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}

