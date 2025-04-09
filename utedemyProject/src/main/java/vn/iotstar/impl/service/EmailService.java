package vn.iotstar.impl.service;

import java.util.Properties;
import java.util.Random;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;



public class EmailService {
    private static final int DEFAULT_OTP_LENGTH = 6;
    
    public String generateOTP() {
        return generateOTP(DEFAULT_OTP_LENGTH);
    }
    
    public String generateOTP(int length) {
  
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        
        // Tạo OTP chỉ chứa số từ 0-9
        for (int i = 0; i < length; i++) {
            otp.append(random.nextInt(10));
        }
        
        return otp.toString();
    }
    
    public boolean sendOtp(String email, String otpCode) {
        // Thiết lập cấu hình email server
        String host = "smtp.gmail.com";
        String port = "587";
        String senderEmail = "phihiep31012004@gmail.com";
        String senderPassword = "ocjsgypnecgrwmcy";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Thiết lập nội dung email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Mã OTP kích hoạt tài khoản");
            message.setText("Xin chào, mã OTP của bạn là: " + otpCode);

            // Gửi email
            Transport.send(message);
            
            // Trả về true nếu gửi email thành công
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            // Trả về false nếu có lỗi xảy ra
            return false;
        }
    }
}
