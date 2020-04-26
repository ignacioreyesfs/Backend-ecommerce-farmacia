package MailSender;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import org.junit.Test;
import org.mockito.Mockito;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

public class MailSenderTest {
    private static final String APPLICATION_NAME = "Gmail test";

    @Test
    public void TestingSendingRandomEmail() throws MessagingException, IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        // spy gmailSender
        GmailSender gmailSender = Mockito.spy(GmailSender.class);
        Gmail service = new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, gmailSender.getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        // Create a message
        MimeMessage mimeMessage = gmailSender.createEmail("farmacialibertadreceiver@gmail.com",
                "farmacialibertadtesting@gmail.com", "Testing", "This is a testing email, thanks google for the account");

        // Send the email
        Message message = gmailSender.createMessageWithEmail(mimeMessage);

        // set the spy return
        doReturn(message).when(gmailSender).sendMessage(any(), any(), any());

        assertEquals(message, gmailSender.sendMessage(service, "me", mimeMessage));
    }
}