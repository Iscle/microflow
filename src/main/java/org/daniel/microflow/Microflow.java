package org.daniel.microflow;

import org.daniel.microflow.controller.OuterController;
import org.daniel.microflow.view.OuterView;

import javax.swing.*;
import java.awt.*;

public class Microflow {

    public static final String VERSION = "1.6.7";

    public static void main(String[] args) {
        if (System.getProperty("os.name").startsWith("Mac")) {
            UIManager.put("TabbedPane.selected", Color.WHITE);
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Microflow");
            System.setProperty("apple.eawt.quitStrategy", "CLOSE_ALL_WINDOWS");
        }

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Failed to set UI look and feel: " + e.getLocalizedMessage());
        }

        SwingUtilities.invokeLater(() -> {
            OuterView view = new OuterView();
            OuterController controller = new OuterController(view);
            view.registerController(controller);
        });
    }
}
