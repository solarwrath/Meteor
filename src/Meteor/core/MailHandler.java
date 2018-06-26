package Meteor.core;

import org.aeonbits.owner.ConfigFactory;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailHandler {
    //TODO pbbly make it singleton
    public void sendEmailTest(String givenTo, String givenSubject, String givenMessage) {

        //Config and necessary variables declaration

        PropertiesConfig config = ConfigFactory.create(PropertiesConfig.class);

        Properties props = new Properties();
        props.put("mail.smtp.auth", config.mailAuth());
        props.put("mail.smtp.starttls.enable", config.mailStartTLSEnable());
        props.put("mail.smtp.host", config.mailHost());
        props.put("mail.smtp.port", config.mailPort());

        //Getting session
        //TODO pbbly gotta make it singleton

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(config.mailUsername(), config.mailPassword());
                    }
                });

        try {
            //Creating message and passing it given recipient/subject/text
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(config.mailFrom()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(givenTo));
            message.setSubject(givenSubject);
            message.setText(givenMessage);

            // Sending message
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
