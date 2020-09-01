package confia.reportes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MeetingSenderService {
	private final Properties prop = new Properties();
	private Session session;
	private String receptor;
	private String subject;
	private String cuerpo;
	private String titulo;
	private String ubicacion;
	private Date start;
	private Date end;

	private void init() {
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");

		final String username = "sistema@grupoconfia.com";
		final String password = "Sistema19$$";

		session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

	}

	public void sendMeeting() {
		init();

		// Define message
		String from = "buxis@ec.aseyco.com";

		MimeMessage message = new MimeMessage(session);
		try {
			message.addHeaderLine("method=REQUEST");
			message.addHeaderLine("charset=UTF-8");
			message.addHeaderLine("component=VEVENT");
			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(receptor));
			// Create an alternative Multipart
			Multipart multipart = new MimeMultipart("alternative");
			// part 1, html text
			BodyPart messageBodyPart = buildHtmlTextPart();
			multipart.addBodyPart(messageBodyPart);

			// Add part two, the calendar
			BodyPart calendarPart = buildCalendarPart();
			multipart.addBodyPart(calendarPart);

			// Put parts in message
			message.setContent(multipart);

			// send message

			Transport.send(message);

			System.out.println("Success");
		} catch (MessagingException me) {
			me.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private BodyPart buildHtmlTextPart() throws MessagingException {
		MimeBodyPart descriptionPart = new MimeBodyPart();

		// Note: even if the content is spcified as being text/html, outlook
		// won't read correctly tables at all
		// and only some properties from div:s. Thus, try to avoid too fancy
		// content
		String content = cuerpo;
		descriptionPart.setContent(content, "text/html; charset=utf-8");

		return descriptionPart;
	}

	// define somewhere the icalendar date format
	private static SimpleDateFormat iCalendarDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm'00'");

	private BodyPart buildCalendarPart() throws Exception {

		BodyPart calendarPart = new MimeBodyPart();

		String calendarContent = 
				"BEGIN:VCALENDAR\n" 
				+ "METHOD:REQUEST\n"
				+ "PRODID:-//Microsoft Corporation//Outlook 9.0 MIMEDIR//EN\n" 
				+ "VERSION:2.0\n" + "BEGIN:VEVENT\n"
				+ "DTSTART:" + iCalendarDateFormat.format(start) 
				+ "\n" + "DTEND:" + iCalendarDateFormat.format(end)
				+ "\n" + "SUMMARY:" 
				+ titulo + "\n" 
				+ "UID:324\n" 
				+ "ATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;RSVP=TRUE:MAILTO"
				+ receptor.trim() 
				+ "\n" + "ORGANIZER:MAILTO:buxis@ec.aseyco.com\n" 
				+ "LOCATION:" + ubicacion + " \n"
				+ "SEQUENCE:0\n" + "PRIORITY:5\n" 
				+ "CLASS:PUBLIC\n" + "STATUS:CONFIRMED\n" 
				+ "TRANSP:OPAQUE\n"
				+ "BEGIN:VALARM\n" + "ACTION:DISPLAY\n" 
				+ "DESCRIPTION:" 
				+ subject 
				+ ".\n\n" 
				+ "SUMMARY:" 
				+ subject
				+ "\n" + "TRIGGER;RELATED=START:-PT00H15M00S\n" 
				+ "END:VALARM\n" 
				+ "END:VEVENT\n" + "END:VCALENDAR";
		
	
		

		calendarPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
		calendarPart.setContent(calendarContent, "text/calendar;method=CANCEL");

		return calendarPart;
	}

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
		this.receptor = receptor;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

}
