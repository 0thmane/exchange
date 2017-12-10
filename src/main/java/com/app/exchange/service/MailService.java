package com.app.exchange.service;

import com.app.exchange.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    @Autowired
    private JavaMailSender sender;

    private static String MSG_SIGNUP = "Bonjour\n\nVotre inscription a été prise en compte. Nous allons vous envoyer un mail dés que votre compte sera activé\n\nBonne journée";

    private static String MSG_ENALBED = "Bonjour\n\nNous vous informons que votre compte a été activer \n\nBonne journée";

    public void notifyUserSignup(User user) {
        try {

            sendEmail(user, MSG_SIGNUP);

        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void notifyUserEnabled(User user) {
        try {

            sendEmail(user, MSG_ENALBED);

        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendEmail(User user, String msg) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setTo(user.getEmail());
        helper.setText(msg);
        helper.setSubject("EXCHANGE");

        sender.send(message);
    }
}
