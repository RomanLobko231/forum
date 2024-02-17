package com.roman.forum.service;

import com.roman.forum.model.ForumUser;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;

@Service
public class MailService {

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
            helper.setFrom("noreply@vatra.com", "Vatra Forum");
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendPasswordResetEmail(String email) {

    }
}
