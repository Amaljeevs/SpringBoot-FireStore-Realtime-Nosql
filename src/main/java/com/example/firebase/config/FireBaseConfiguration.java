/*
 * FireBase - FireStore Configuration File.
 * Author : Amal jeev s
 * Description : This is the configuration code for FireStore DataBase.
 * Date : 16-10-2023
 * 
 */
package com.example.firebase.config;

import java.io.FileInputStream;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FireBaseConfiguration {

	@SuppressWarnings("deprecation")
	@PostConstruct
	public void initialization() {

		FileInputStream serviceAccount;
		try {
			serviceAccount = new FileInputStream("./serviceAccountKey.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
