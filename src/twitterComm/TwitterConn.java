/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterComm;

import javax.swing.JOptionPane;
import systeminteraction.FilesManagement;
import winterwell.jtwitter.OAuthSignpostClient;
import winterwell.jtwitter.Twitter;

/**
 *
 * @author Charles Corner
 * 
 */
public class TwitterConn {

    String user;
    OAuthSignpostClient oauthClient;
    Twitter twitter;    
    String accessTokArray[] = new String[2];
    FilesManagement fm;

    public TwitterConn() {      
        fm = new FilesManagement();
        accessTokArray = fm.getAccessTokArray();
        user = fm.getTwitterUser();
        connect2Twitter();
    }

    private void connect2Twitter() {
        if (user == null) {
            user = JOptionPane.showInputDialog("Tichiner - User", "What's your username?");
            fm.saveUserConf(user);
        }

        if (accessTokArray == null) {
            oauthClient = new OAuthSignpostClient("mqlHtxDoKtkbenmNMH7GA",
                    "sXCiC8ZdofjHk34erbI6IWI63yOuBBNGHglOsbU", "oob");
            oauthClient.authorizeDesktop();
            
            // get the pin
            String v = OAuthSignpostClient.askUser("Please enter the verification PIN from Twitter");
            oauthClient.setAuthorizationCode(v);
            accessTokArray = oauthClient.getAccessToken();

        } else {
            oauthClient = new OAuthSignpostClient("mqlHtxDoKtkbenmNMH7GA",
                    "sXCiC8ZdofjHk34erbI6IWI63yOuBBNGHglOsbU", accessTokArray[0], accessTokArray[1]);
        }        

        fm.saveAccessToken(accessTokArray);

        twitter = new Twitter(user,oauthClient);
    }

    public Twitter getTwitter(){
        return this.twitter;
    }

    public String getUser() {
        return user;
    }

    
}