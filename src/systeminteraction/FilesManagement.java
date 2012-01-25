/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package systeminteraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Charles Corner
 */
public class FilesManagement {

    private File tichinerDir;
    private File userDir;
    private File accessToken;
    private File userConf;
    private String sysSeparator;
    private PrintWriter pw;
    private Scanner sc;
    private String accessTokArray[];
    private boolean newTokenReq;
    private String twitterUser;

    public FilesManagement() {
        userDir = new File(System.getProperty("user.home"));
        sysSeparator = System.getProperty("file.separator");
        accessTokArray = null;
        buildConfFiles();

    }

    private void buildConfFiles() {

        try {
            tichinerDir = new File(userDir.getCanonicalPath(), ".tichiner");
        } catch (IOException e) {
            System.err.println("The system didn't allowed the creation of the file");
            System.exit(102);
        }

        if (!tichinerDir.exists()) {
            boolean success = tichinerDir.mkdir();
        }


        accessToken = new File(tichinerDir, ".accessToken");
        if (accessToken.exists()) {
            try {
                sc = new Scanner(accessToken);
            } catch (FileNotFoundException ex) {
                System.err.println("The file wasn't found");
                System.exit(103);
            }

            accessTokArray = new String[2];
            accessTokArray[0] = sc.nextLine();
            accessTokArray[1] = sc.nextLine();
            sc.close();

        } else {
            try {
                accessToken.createNewFile();
                newTokenReq = true;
            } catch (IOException ex) {
                System.err.println("The system didn't allowed the creation of the file");
                System.exit(102);
            }
        }

        userConf = new File(tichinerDir, ".user");
        if (userConf.exists()) {
            try {
                sc = new Scanner(userConf);
            } catch (FileNotFoundException ex) {
                System.err.println("The file wasn't found");
                System.exit(103);
            }

            twitterUser = sc.nextLine();
            sc.close();

        } else {
            try {
                userConf.createNewFile();
            } catch (IOException ex) {
                System.err.println("The system didn't allowed the creation of the file");
                System.exit(102);
            }
        }

    }

    public boolean getNewTokenReq() {
        return newTokenReq;
    }

    public boolean saveAccessToken(String accToken[]) {
        try {
            pw = new PrintWriter(accessToken);
            pw.println(accToken[0]);
            pw.println(accToken[1]);
            pw.close();
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }
    }

    public boolean saveUserConf(String user) {
        try {
            pw = new PrintWriter(userConf);
            pw.println(user);
            pw.close();
            return true;
        } catch (FileNotFoundException ex) {
            return false;
        }
    }

    public String[] getAccessTokArray() {
        return accessTokArray;
    }

    public String getTwitterUser() {
        return twitterUser;
    }

}