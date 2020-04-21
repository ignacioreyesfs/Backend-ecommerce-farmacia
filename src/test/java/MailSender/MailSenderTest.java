package MailSender;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.junit.Test;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.GeneralSecurityException;

import static MailSender.GmailSender.getCredentials;

public class MailSenderTest {
    private static final String APPLICATION_NAME = "Gmail test";

    @Test
    public void TestingSendingRandomEmail() throws MessagingException, IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, GmailSender.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        // Create a message
        MimeMessage mimeMessage = GmailSender.createEmail("farmacialibertadreceiver@gmail.com",
                "farmacialibertadtesting@gmail.com", "Testing", "This is a testing email, thanks google for the account");

        // Send the email
        Message message = GmailSender.sendMessage(service, "me", mimeMessage);

        // Print the response
        System.out.println("Message id: " + message.getId());
        System.out.println(message.toPrettyString());
    }
}
