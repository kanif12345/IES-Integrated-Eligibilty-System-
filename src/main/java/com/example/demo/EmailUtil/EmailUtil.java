package com.example.demo.EmailUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.response.ApiResponse;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailUtil {
	
	
   @Autowired
    private  JavaMailSender javaMailSender ;

  
   

    public ApiResponse sendEmail(String to, String subject, String body) {

        try {
   
        	
        	System.out.println(to);
            

            if (to == null || to.trim().isEmpty()) {
                return ApiResponse.builder()
                        .message("Email is null")
                        .status(false)
                        .build();
            }

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            javaMailSender.send(mimeMessage);             

            return ApiResponse.builder()
                    .message("Email sent")
                    .status(true)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.builder()
                    .message("Email error: " + e.getMessage())
                    .status(false)
                    .build();
        }
    }
}
