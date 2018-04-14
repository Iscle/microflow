package view;

import controller.CursorDetail;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JMenuBar {

    private JMenu jmFile;
    private JMenu jmTools;
    private JMenu jmWindow;
    private JMenu jmHelp;

    private View view;
    private JPanel jpToolBar;

    private static final String ABOUT_MSG = "Main\nDaniel Ortiz & Joan Gómez\n" +
            "Inspired by the original of F. Escudero\nCopyright (C) 2018";

    /* Menu File Items */
    private JMenuItem jmiNewFile;
    private JMenuItem jmiOpenFile;
    private JMenuItem jmiSave;
    private JMenuItem jmiSavePNG;
    private JMenuItem jmiPrint;
    private JMenuItem jmiGenFiles;
    private JMenuItem jmiGenMotor;

    /* Tools Items */
    private JMenuItem jmiCursor;
    private JMenuItem jmiUndo;
    private JMenuItem jmiDelete;
    private JMenuItem jmiTAD;
    private JMenuItem jmiVar;
    private JMenuItem jmiPeri;
    private JMenuItem jmiIfaceC;
    private JMenuItem jmiOpC;
    private JMenuItem jmiIntC;
    private JMenuItem jmiState;
    private JMenuItem jmiTransC;
    private JMenuItem jmiAddAction;


    public MenuBar(View view, JPanel jpToolBar) {
        this.view = view;
        this.jpToolBar = jpToolBar;

        this.setName("Options menu");

        jmFile = new JMenu("File");
        this.addFileOptions();

        jmTools = new JMenu("Tools");
        this.addToolsOptions();

        jmWindow = new JMenu("Window");
        this.addWindowOptions();

        jmHelp = new JMenu("Help");
        this.addHelpOptions();

        this.add(jmFile);
        this.add(jmTools);
        this.add(jmWindow);
        this.add(jmHelp);
    }

    private void addFileOptions() {

        /* File related options */
        jmiNewFile = new JMenuItem("New file");
        jmiOpenFile = new JMenuItem("Open file");
        jmiSave = new JMenuItem("Save");
        jmiSavePNG = new JMenuItem("Save as PNG");
        jmiPrint = new JMenuItem("Print");


        JMenuItem jmiExit = new JMenuItem("Exit");
        jmiExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jmFile.add(jmiNewFile);
        jmFile.add(jmiOpenFile);
        jmFile.add(jmiSave);
        jmFile.add(jmiSavePNG);
        jmFile.add(jmiPrint);
        jmFile.addSeparator();

        /* Generation options */
        jmiGenFiles = new JMenuItem("Create .c .h files");
        jmiGenMotor = new JMenuItem("Create motor");

        jmFile.add(jmiGenFiles);
        jmFile.add(jmiGenMotor);
        jmFile.addSeparator();

        /* Exit */
        jmFile.add(jmiExit);
    }

    private void addToolsOptions() {
        /* Basic Options */
        jmiCursor = new JMenuItem("Cursor");
        jmiUndo = new JMenuItem("Undo");
        jmiDelete = new JMenuItem("Delete");

        jmTools.add(jmiCursor);
        jmTools.add(jmiUndo);
        jmTools.add(jmiDelete);
        jmTools.addSeparator();

        /* TAD Tools */
        jmiTAD = new JMenuItem("TAD");
        jmiVar = new JMenuItem("Variable");
        jmiPeri = new JMenuItem("Peripheral");
        jmiIfaceC = new JMenuItem("Interface cursor");
        jmiOpC = new JMenuItem("Operation cursor");
        jmiIntC = new JMenuItem("Interrupt cursor");

        jmTools.add(jmiTAD);
        jmTools.add(jmiVar);
        jmTools.add(jmiPeri);
        jmTools.add(jmiIfaceC);
        jmTools.add(jmiOpC);
        jmTools.add(jmiIntC);
        jmTools.addSeparator();

        /* States Tools */
        jmiState = new JMenuItem("State");
        jmiTransC = new JMenuItem("Transition cursor");
        jmiAddAction = new JMenuItem("Add action");

        jmTools.add(jmiState);
        jmTools.add(jmiTransC);
        jmTools.add(jmiAddAction);
    }

    private void addWindowOptions() {
        JMenuItem jmiMinimize = new JMenuItem("Minimize");
        JMenuItem jmiMaximize = new JMenuItem("Maximize");
        JMenuItem jmiToolBar = new JMenuItem("Show/Hide Toolbar");

        /* Add actions */
        jmiMinimize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setExtendedState(JFrame.ICONIFIED);
            }
        });

        jmiMaximize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setExtendedState(JFrame.MAXIMIZED_BOTH);
            }
        });

        jmiToolBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jpToolBar.isVisible()) {
                    jpToolBar.setVisible(false);
                } else {
                    jpToolBar.setVisible(true);
                }
            }
        });

        jmWindow.add(jmiMinimize);
        jmWindow.add(jmiMaximize);
        jmWindow.add(jmiToolBar);
    }


    private void addHelpOptions() {
        JMenuItem jmiAbout = new JMenuItem("About");
        jmiAbout.addActionListener(e -> {
            //TODO: pasar el icono del bubblewizard
            JOptionPane.showMessageDialog(null, ABOUT_MSG, "Credits", JOptionPane.INFORMATION_MESSAGE, null);
        });

        jmHelp.add(jmiAbout);
    }


    public void addButtonListener(ActionListener c) {
        jmiNewFile.addActionListener(c);
        jmiNewFile.setActionCommand(CursorDetail.NEW_FILE.name());
        jmiOpenFile.addActionListener(c);
        jmiOpenFile.setActionCommand(CursorDetail.OPEN_FILE.name());
        jmiSave.addActionListener(c);
        jmiSave.setActionCommand(CursorDetail.SAVE_FILE.name());
        jmiSavePNG.addActionListener(c);
        jmiSavePNG.setActionCommand(CursorDetail.SAVE_FILE_PNG.name());
        jmiPrint.addActionListener(c);
        jmiPrint.setActionCommand(CursorDetail.PRINT_FILE.name());
        jmiGenFiles.addActionListener(c);
        jmiGenFiles.setActionCommand(CursorDetail.GEN_FILES.name());
        jmiGenMotor.addActionListener(c);
        jmiGenMotor.setActionCommand(CursorDetail.GEN_MOTOR.name());

        jmiCursor.addActionListener(c);
        jmiCursor.setActionCommand(CursorDetail.SELECTING.name());
        jmiUndo.addActionListener(c);
        jmiUndo.setActionCommand(CursorDetail.UNDO.name());
        jmiDelete.addActionListener(c);
        jmiDelete.setActionCommand(CursorDetail.DELETING.name());

        jmiTAD.addActionListener(c);
        jmiTAD.setActionCommand(CursorDetail.ADD_TAD.name());
        jmiVar.addActionListener(c);
        jmiVar.setActionCommand(CursorDetail.ADD_VARIABLE.name());
        jmiPeri.addActionListener(c);
        jmiPeri.setActionCommand(CursorDetail.ADD_PERIPHERAL.name());
        jmiIfaceC.addActionListener(c);
        jmiIfaceC.setActionCommand(CursorDetail.ADD_INTERFACE.name());
        jmiOpC.addActionListener(c);
        jmiOpC.setActionCommand(CursorDetail.ADD_OPERATION.name());
        jmiIntC.addActionListener(c);
        jmiIntC.setActionCommand(CursorDetail.ADD_INTERRUPT.name());

        jmiState.addActionListener(c);
        jmiState.setActionCommand(CursorDetail.ADD_STATE.name());
        jmiTransC.addActionListener(c);
        jmiTransC.setActionCommand(CursorDetail.ADD_TRANSITION.name());
        jmiAddAction.addActionListener(c);
        jmiAddAction.setActionCommand(CursorDetail.ADD_ACTION.name());

    }

}

