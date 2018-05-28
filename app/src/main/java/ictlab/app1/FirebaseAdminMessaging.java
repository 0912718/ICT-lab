package ictlab.app1;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseAdminMessaging {

    public static void sendMessage(){
        try{
            FileInputStream serviceAccount = new FileInputStream("firebase_props.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

            //String registrationToken = "YOUR_REGISTRATION_TOKEN";

            Message message = Message.builder().setTopic("TEST TOPIC")
                    .putData("score", "850")
                    .putData("time", "2:45")
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);

        }catch(IOException ioe){
            ioe.printStackTrace();
        }catch(FirebaseMessagingException fme){
            fme.printStackTrace();
        }

    }
}
