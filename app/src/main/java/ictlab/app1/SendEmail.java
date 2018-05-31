package ictlab.app1;

import android.os.AsyncTask;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail extends AsyncTask<Void,Void,Void> {

    public static final String USER_KEY = "mail.smtp.user";
    public static final String HOST_KEY = "mail.smtp.host";
    public static final String HOST_VALUE = "smtp-mail.outlook.com";
    public static final String PORT_KEY = "mail.smtp.port";
    public static final String PORT_VALUE = "587";
    public static final String STARTTLS_KEY = "mail.smtp.starttls.enable";
    public static final String STARTTLS_VALUE = "true";
    public static final String AUTH_KEY = "mail.smtp.auth";
    public static final String AUTH_VALUE = "true";
    public static final String PWD_KEY = "true";


    //TODO: Add username (email) & password from which account the emails will be sent.
    public static final String USERNAME = "0909391@hr.nl";
    public static final String PASSWORD = "zinaronaldo85";

    private Session session;

    private String email;
    private String subject;
    private String message;

    public SendEmail(String email, String subject, String message){
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }

    @Override
    protected Void doInBackground(Void... params) {
        Properties props = new Properties();
        props.put(USER_KEY, USERNAME);
        props.put(HOST_KEY, HOST_VALUE);
        props.put(PORT_KEY, PORT_VALUE);
        props.put(STARTTLS_KEY,STARTTLS_VALUE);
        props.put(AUTH_KEY, AUTH_VALUE);
        props.put(PWD_KEY, PASSWORD);

        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        try {
            MimeMessage mm = new MimeMessage(session);
            mm.setFrom(new InternetAddress(USERNAME));
            mm.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mm.setSubject(subject);
            mm.setText(message);
            Transport.send(mm);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}