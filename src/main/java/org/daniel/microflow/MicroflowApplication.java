package org.daniel.microflow;

import org.daniel.microflow.controller.Controller;
import org.daniel.microflow.view.View;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MicroflowApplication {

    public static final String VERSION = "1.6.0";

    public static void main(String[] args) {
        try {
            if (System.getProperty("os.name").startsWith("Mac")) {
                System.setProperty("apple.laf.useScreenMenuBar", "true");
                System.setProperty("apple.eawt.quitStrategy", "CLOSE_ALL_WINDOWS");
                System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Microflow");
            }
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            View view = new View();
            Controller controller = new Controller(view);
            view.registerController(controller);
            view.addActionListener(controller);
        });

        checkUpdates();

    }

    private static void checkUpdates() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/ortizdaniel/microflow/master/VERSION");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = reader.readLine();
            if (line != null && line.compareTo(VERSION) > 0) {
                int res = JOptionPane.showConfirmDialog(null,
                        "New update available. Would you like to be taken to the download page?", "Update",
                        JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    Desktop.getDesktop().browse(new URI("https://github.com/ortizdaniel/microflow/releases"));
                }
            }
            reader.close();
        } catch (URISyntaxException | IOException e) {
            System.err.println("Could not check for updates.");
        }

    }
}