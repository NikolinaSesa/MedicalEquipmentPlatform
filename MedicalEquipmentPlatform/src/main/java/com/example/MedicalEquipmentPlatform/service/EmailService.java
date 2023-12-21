package com.example.MedicalEquipmentPlatform.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email){
        javaMailSender.send(email);
    }

    public void sendEmailWithQRCode(String to, String subject, String text, String pathToQRCode){
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare (MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(text);

                FileSystemResource file = new FileSystemResource(new File(pathToQRCode));
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.addAttachment("QRCode.jpg", file);
            }
        };

        try{
            javaMailSender.send(preparator);
        }catch(MailException e){
            e.printStackTrace();
        }
    }

}
