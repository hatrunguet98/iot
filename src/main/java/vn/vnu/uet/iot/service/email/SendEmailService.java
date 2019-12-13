package vn.vnu.uet.iot.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailService {
    private static final Logger logger = LoggerFactory.getLogger(SendEmailService.class);

    @Value("${user.email.address}")
    private String emailAddress;
    @Autowired
    private JavaMailSender mailSender;

    public void senEmailReport() {
        String subject = "Invite User";
        sendEmail(this.createdContentReport(), subject);
    }

    private void sendEmail(String content, String subject) {
        try {
            MimeMessage messageEmail = mailSender.createMimeMessage();
            boolean multipart = true;
            MimeMessageHelper helper = new MimeMessageHelper(messageEmail, multipart, "utf-8");
            messageEmail.setContent(content, "text/html; charset=UTF-8");
            helper.setFrom("testw1605@gmail.com");
            helper.setTo(emailAddress);
            helper.setSubject(subject);
            mailSender.send(messageEmail);
        } catch (MessagingException e) {
            logger.error("Unable to email invitations to " + emailAddress);
            e.printStackTrace();

        }
    }

    private String createdContentReport() {
        String error1 = "";
        String error2 = "";

        String invitation = "\"<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\" />\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\" />\n" +
                "    <title>Cảnh báo hỏng thiết bị đo</title>    \n" +
                "</head>\n" +
                "<body>\n" +
                "    <p>Chào bạn,</p>\n" +
                "\n" +
                "\t<p>Đây là tin nhắn tự động cảnh báo các thiết bị đo môi trường lỗi:</p>\n" +
                "\n" +
                error1 + error2 +
                "\n" +
                "\t<p>Trân trọng!</p>\n" +
                "</body>\n" +
                "</html>\n";
        return invitation;
    }
}
