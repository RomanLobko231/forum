package com.roman.forum.service;

import com.roman.forum.model.ForumUser;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;

@Service
public class MailService {


    @Value("#{environment.EMAIL_USERNAME}")
    String from;

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    public MailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmailVerification(ForumUser user){
        Context context = new Context();

        context.setVariable("name", user.getUsername());
        context.setVariable("token", user.getVerificationToken());

        String content = templateEngine.process("verification-template", context);
        String subject = "Email Verification";
        sendSimpleHtmlEmail(user.getEmail(), subject, content);
    }

    public void sendSimpleHtmlEmail(String to, String subject, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MailSendException("An error sending an email", e);
        }
    }


    public void sendPasswordResetEmail(ForumUser user, String resetToken) {
            Context context = new Context();
            context.setVariable("token", resetToken);
            context.setVariable("username", user.getUsername());

            String content = templateEngine.process("password-reset-template", context);
            String subject = "Password Reset";

            sendSimpleHtmlEmail(user.getEmail(), subject, content);
    }
}
