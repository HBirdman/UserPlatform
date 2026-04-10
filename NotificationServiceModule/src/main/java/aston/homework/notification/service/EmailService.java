package aston.homework.notification.service;

import aston.homework.notification.dto.EmailRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.from:noreply@localhost}")
    private String fromEmail;

    public void sendMail(EmailRequestDTO request) {
        log.info("Отправка email на адрес: {}", request.getTo());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getBody());

        try {
            mailSender.send(message);
            log.info("Email успешно отправлен на {}", request.getTo());
        } catch (Exception e) {
            log.error("Ошибка при отправке email: {}", e.getMessage(), e);
            throw new RuntimeException("Не удалось отправить email", e);
        }
    }

    public void sendUserCreatedNotification(String email) {
        EmailRequestDTO request = new EmailRequestDTO();
        request.setTo(email);
        request.setSubject("Добро пожаловать!");
        request.setBody("Ваш аккаунт в UserService успешно создан!");
        sendMail(request);
    }

    public void sendUserDeletedNotification(String email) {
        EmailRequestDTO request = new EmailRequestDTO();
        request.setTo(email);
        request.setSubject("Уведомление об удалении аккаунта");
        request.setBody("Ваш аккаунт успешно удален из UserService");
        sendMail(request);
    }
}
