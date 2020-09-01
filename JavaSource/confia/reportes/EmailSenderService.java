package confia.reportes;

//import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSenderService {
	private final Properties properties = new Properties();

	private String Texto;
	// private List<String> receptor;
	private String receptor;
	private String subject;
	private Session session;

	private void init() {

		// CONFIA
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.mail.sender", "sistema@grupoconfia.com");
		properties.put("mail.smtp.user", "sistema@grupoconfia.com");
		properties.put("mail.smtp.auth", "true");
		session = Session.getDefaultInstance(properties);

	}

	public void sendEmail() {
		init();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties
					.get("mail.smtp.mail.sender")));
			// message.addRecipient(Message.RecipientType.TO, new
			// InternetAddress(
			// receptor));
			message.addRecipients(Message.RecipientType.TO,
					InternetAddress.parse(receptor));
			message.setSubject(subject);
			message.setText(Texto, "ISO-8859-1", "html");
			Transport t = session.getTransport("smtp");
			
			System.out.println("---------------------------------------------------");
			System.out.println("host:"+(String) properties.get("mail.smtp.host"));
			System.out.println("Usuario:"+(String) properties.get("mail.smtp.user"));
			System.out.println("PAsswd:"+"Sistema19$$");
			System.out.println("---------------------------------------------------");
			t.connect((String) properties.get("mail.smtp.user"), "Sistema19$$");
			
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			
			
			
		} catch (MessagingException me) {
			// Aqui se deberia o mostrar un mensaje de error o en lugar
			// de no hacer nada con la excepcion, lanzarla para que el modulo
			// superior la capture y avise al usuario con un popup, por ejemplo.
			System.out
					.println("Error de envio de mail: -------->");
			me.printStackTrace();
			return;
		}
	}

	public String getTexto() {
		return Texto;
	}

	public void setTexto(String texto) {
		Texto = texto;
	}

	// public List<String> getReceptor() {
	// return receptor;
	// }
	//
	// public void setReceptor(List<String> receptor) {
	// this.receptor = receptor;
	// }

	public String getSubject() {
		return subject;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
