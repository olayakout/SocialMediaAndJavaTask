package socialmediaandjava;

import facebook4j.BatchRequest;
import facebook4j.BatchRequests;
import facebook4j.BatchResponse;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.RawAPIResponse;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.User;
import facebook4j.auth.AccessToken;
import facebook4j.internal.http.RequestMethod;
import facebook4j.internal.org.json.JSONException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



class FacebookConf {

    //configration parameters
    private final String appId = "1945921435625426";
    private final String appSecret = "5216b5b88203e093b53c0444c0dde7f1";
    private String commaSeparetedPermissions = " public_profile,";
//    private final String accessToken = "EAAbpziPuZA9IBABqWtBzXIgpCPTbLpTbP7v611H1Pkdy4ZCDgXZAEE22GdgO7qyhT9ZBa93p9nt9mZCx6lqCZCfrghK8bhU4JEkjeasx8JJu2OP3OCQdl2ZBLbET7dcL2nBL3Kt6MVCZCZB3v4ZCUxWSFXZCOTsjtKvt6AZD";
    private static Facebook facebook;

    FacebookConf(String fb_access_token){

        facebook = new FacebookFactory().getInstance();
        facebook.setOAuthAppId(appId, appSecret);
        facebook.setOAuthPermissions(commaSeparetedPermissions);
        facebook.setOAuthAccessToken(new AccessToken(fb_access_token));

    }

    public String getMe(String required) {
        BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
        batch.add(new BatchRequest(RequestMethod.GET, "me"));
        List<BatchResponse> results = null;
        String id, username = null;
        try {
            results = facebook.executeBatch(batch);
            switch (required) {
                case "id":
                    id = results.get(0).asJSONObject().getString("id");
                    return id;
                case "username":
                    username = results.get(0).asJSONObject().getString("name");
                    return username;
            }

        } catch (FacebookException | JSONException ex) {
            System.out.println(ex);
        }
        return null;

    }

    public String getUserEmail() {
        try {
            // add permission to access posts of user
            commaSeparetedPermissions += "email";
            facebook.setOAuthPermissions(commaSeparetedPermissions);
            User user = facebook.getUser(getMe("id"), new Reading().fields("email"));
            return user.getEmail();
        } catch (FacebookException ex) {
            System.out.println(ex);
        }
        return null;

    }
    
    
    public ResponseList getNewsFeed() {
        try {
            // add permission to access posts of user
            commaSeparetedPermissions += "user_posts";
            facebook.setOAuthPermissions(commaSeparetedPermissions);

            return facebook.getFeed();
        } catch (FacebookException ex) {
            System.out.println(ex);
        }
        return null;

    }

    public String getEmail() {
        // add permission to access posts of user
        commaSeparetedPermissions += "email";
        facebook.setOAuthPermissions(commaSeparetedPermissions);
        try {
            return facebook.getMe().getEmail();

        } catch (FacebookException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public String getBirthday(){
        try {
            RawAPIResponse res = facebook.callGetAPI("me?fields=birthday");
            return res.asJSONObject().optString("birthday");
        } catch (FacebookException ex) {
            Logger.getLogger(FacebookConf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
