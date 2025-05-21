package vn.iotstar.utils;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;

public class EmailUtil {
	public static String HOST = "smtp.gmail.com";
	public static String PORT = "587";
    public static String SENDER_EMAIL = "phihiep31012004@gmail.com";
    public static String SENDER_PASSWORD = "ocjsgypnecgrwmcy";

    public static Session createMailSession() {
    	Properties props = new Properties();
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return Session.getInstance(props, new Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
		});

    }
}
