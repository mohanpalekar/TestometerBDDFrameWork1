package Utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

/**
 * SendEmail class to send an email
 */
public class SendEmail {

	/**
	 * sendEmailNotification method
	 */
	public static void main(String[] args) throws AddressException, MessagingException, IOException {
		
		Logs.getLog().getLogger().info("{SendEmail} Initiating sendEmailNotification");

		final Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", System.getProperty("emailHost"));
		prop.put("mail.smtp.port", System.getProperty("emailPort"));
		prop.put("mail.smtp.ssl.trust", System.getProperty("emailHost"));

		final Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(System.getProperty("toEmail"), System.getProperty("emailApp"));
			}
		});

		final Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(System.getProperty("toEmail")));
		message.setRecipients(
				Message.RecipientType.TO, InternetAddress.parse(System.getProperty("fromEmail")));
		message.setSubject("Automation - Report");
		
		File file = new File(System.getProperty("user.dir")+"/testResults.properties");

		FileReader fileReader = new FileReader(file);

		Properties properties =  new Properties();

		properties.load(fileReader);
		
		long totalScenarios = Long.parseLong(properties.getProperty("totalScenarios"));
		long totalFailedScenarios = Long.parseLong(properties.getProperty("totalFailedScenarios"));
		double timeTaken = Double.parseDouble(properties.getProperty("timeTaken"));
		
		final String results = "Total Scenarios Count: "+totalScenarios+"\n Scenarios Passed : "+(totalScenarios-totalFailedScenarios)+"\n Scenarios Failed : "+totalFailedScenarios+"\n Execution Time: "+timeTaken;

		final String msg = "Hi Team, \n Please find the attached report."
				+ "\n Please review the failures and raise the bugs.\n "+results;

		final MimeBodyPart mimeBodyPart = new MimeBodyPart();
		mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

		final StringBuffer attachPath = new StringBuffer(40);
		attachPath.append(System.getProperty("user.dir")).append(System.getProperty("emailPDF"));
		final MimeBodyPart attachBodyPart = new MimeBodyPart();
		attachBodyPart.attachFile(new File(attachPath.toString()));

		final MimeMultipart multipart = new MimeMultipart();
		multipart.addBodyPart(mimeBodyPart);
		multipart.addBodyPart(attachBodyPart);

		message.setContent(multipart);

		Transport.send(message);
		
		Logs.getLog().getLogger().info("{SendEmail} sendEmailNotification success");
	}
}

