/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twitterComm;

import gui.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Charles Corner
 */
public class Refresher implements Runnable {

    private MainFrame parent;
    private boolean canContinue;

    public Refresher(MainFrame parent) {
        this.parent = parent;
        this.canContinue = true;
    }

    public void run() {
        while (canContinue) {
            parent.updateTL();
            try {
                // Thread will sleep for 6 minutes
                Thread.sleep(360000);
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }

    }

    public void setCanContinue(boolean canContinue) {
        this.canContinue = canContinue;
    }
}
