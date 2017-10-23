package socialmediaandjava;

import DBConnection.JDBC;
import facebook4j.FacebookException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.TwitterException;

public class SocialMediaAndJava {

    public static void main(String[] args) throws FacebookException, TwitterException {

        // ask user for access tokens 
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter your name : ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your extended Facebook access token : ");
        String fb_access_token = scanner.nextLine();

        System.out.print("Enter your twitter access token : ");
        String tw_access_token = scanner.nextLine();

        System.out.print("Enter your google access token : ");
        String gg_access_token = scanner.nextLine();
        
//        String name = "ola";
//        String fb_access_token = "EAAbpziPuZA9IBABqWtBzXIgpCPTbLpTbP7v611H1Pkdy4ZCDgXZAEE22GdgO7qyhT9ZBa93p9nt9mZCx6lqCZCfrghK8bhU4JEkjeasx8JJu2OP3OCQdl2ZBLbET7dcL2nBL3Kt6MVCZCZB3v4ZCUxWSFXZCOTsjtKvt6AZD";
//        String tw_access_token = "424494168-OgHyvWSxq8Zy8SKBDQqlLDSiGPDEfxXupu6eqKPz";
//        String gg_access_token = "ya29.GlvtBKTLZEpnNJ810wQhH5S2X8NIaYbddb0tX-oDBH3Sp8abPYHpCi7qv7mc3c6wANzke1CN6BNhO0WrZzHV0YbFMlMju6IvilPQxEU5Xoa8h--MUwnSRqpQnRTo";

        //connect to mysql
        Connection connection = JDBC.getConnection();

        //get now date
        Date CreatedDate = new Date(System.currentTimeMillis());

        // the mysql check user if login for first time or not 
        String query = " SELECT * FROM Users where fb_access_token = ? and tw_access_token = ?";
        PreparedStatement preparedStmt = null;
        try {
            Statement statement = connection.createStatement();
            preparedStmt = connection.prepareStatement(query);

            preparedStmt.setString(1, fb_access_token);
            preparedStmt.setString(2, tw_access_token);
            ResultSet rs = preparedStmt.executeQuery();

            //if user is logged in update login date
            if (rs.next()) {
                int id = rs.getInt("id");
//                System.out.println("id is"+id);
                // if user is logged in update last_login parameter 

                String update_sql = "UPDATE Users SET last_login = ? where id = ?";
                PreparedStatement stmt = connection.prepareStatement(update_sql);

                stmt.setDate(1, CreatedDate);
                stmt.setInt(2, id);
                stmt.executeUpdate();
            } else {
//
                //login by facebook
                FacebookConf fb_config = new FacebookConf(fb_access_token);
                //retreive data from fb
                System.out.println("user_id :" + fb_config.getMe("id"));
                System.out.println("username :" + fb_config.getMe("username"));
                System.out.println("email :" + fb_config.getUserEmail());
                System.out.print("Birthday :" + fb_config.getBirthday());

                System.out.println("News Feed :" + fb_config.getNewsFeed());

                //login by twitter
                TwitterConf tw_config = new TwitterConf(tw_access_token);
                //retreive data from twitter
                tw_config.getTwitterUserName();
                tw_config.getTwitterNewFeeds();
                tw_config.getTwitterUserId();

                //login to google
                GoogleConf google = new GoogleConf();
                //retrieve data to google
                System.out.println("google email"+google.getgEmail());
                // if login for first time insert his information in db 
                // send welcome msg to user 
                System.out.println("Welcome " + name);
                String q = " insert into Users (name,fb_access_token,tw_access_token,gg_access_token,last_login)"
                        + " values (?,?,?,?,?)";
                // create the mysql insert preparedstatement

                preparedStmt = connection.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
                preparedStmt.setString(1, name);
                preparedStmt.setString(2, fb_access_token);
                preparedStmt.setString(3, tw_access_token);
                preparedStmt.setString(4, gg_access_token);
                preparedStmt.setDate(5, CreatedDate);
                preparedStmt.execute();
                ResultSet rst = preparedStmt.getGeneratedKeys();
                
                
                int id=rst.next()?id= rst.getInt(1):0;  //user id
                
                //insert social information to social media table 
                // note that: twitter not allow to retrieve email
                
                String insert_social = " insert into social_media (fb_username, fb_email, fb_birthday, gg_email, user_id)"
                        + " values (?,?,?,?,?)";
                
                // create the mysql insert preparedstatement

                PreparedStatement preparedStmt_insert = connection.prepareStatement(insert_social);
                preparedStmt_insert.setString(1, fb_config.getMe("username"));
                preparedStmt_insert.setString(2, fb_config.getUserEmail());
                preparedStmt_insert.setString(3, fb_config.getBirthday());
                preparedStmt_insert.setString(4, "ooo");
                preparedStmt_insert.setString(4, google.getgEmail());
                preparedStmt_insert.setInt(5, id);
                System.out.println(preparedStmt_insert);
//                execute the preparedstatement
                preparedStmt_insert.execute();

            }
        } catch (SQLException ex) {
            Logger.getLogger(SocialMediaAndJava.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
