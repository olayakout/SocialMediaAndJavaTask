package socialmediaandjava;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GoogleConf {

    private final static String clientId = "230420548476-6c615vqbv73a72mfhuq98h8orltrc685.apps.googleusercontent.com";
    private final static String clientSecret = "92Hb6ce5gHMxdxTSDlOJfX-D";
    private final static String accessToken = "ya29.GlvtBKTLZEpnNJ810wQhH5S2X8NIaYbddb0tX-oDBH3Sp8abPYHpCi7qv7mc3c6wANzke1CN6BNhO0WrZzHV0YbFMlMju6IvilPQxEU5Xoa8h--MUwnSRqpQnRTo";
    private final static String refreshToken = "1/qNrEfAXcXZvjbQ6vGl5Ejr-IJ_PebDUaGTZY5p8TQnc";
    private final static String refreshListener = "4/Cy9Rne4C1CLo2lrr1bdnSK9wSZd2dhPBwrjtN8q2MPg";
    GoogleCredential credential;
    String userinfo;

    public GoogleConf() {
        credential = new GoogleCredential().setAccessToken(accessToken);
    }

    public String getgEmail() {
        Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential).setApplicationName(
                "Oauth2").build();
        try {
            userinfo = oauth2.userinfo().get().execute().getEmail();
            return userinfo;

        } catch (IOException ex) {
            Logger.getLogger(GoogleConf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
