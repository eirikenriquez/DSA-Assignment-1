package Question2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Eirik Mykel Navarro Enriquez
 */
public class Controller implements ActionListener {

    public Panel panel;

    public Controller(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();

        switch (action) {
            case "Synchronized":
                panel.changeSyncSetting(true);
                break;
            case "Unsynchronized":
                panel.changeSyncSetting(false);
                break;
            default:
                break;
        }
    }

}
