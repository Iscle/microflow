package org.daniel.microflow.view;

import org.daniel.microflow.controller.CursorDetail;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolBar extends JPanel {

    private final JToolBar jtbIcons;

    /* Files */
    private JButton jbNewFile;
    private JButton jbOpenFile;
    private JButton jbSaveFile;
    private JButton jbPrint;

    /* Main Options */
    private JButton jbCursor;
    private JButton jbDelete;
    private JButton jbUndo;
    private JButton jbRedo;

    /* TADs diagram */
    private JButton jbTAD;
    private JButton jbVariable;
    private JButton jbPeripheral;
    private JButton jbIface;
    private JButton jbOperation;
    private JButton jbInterrupt;

    /* States diagram */
    private JButton jbState;
    private JButton jbTransition;
    private JButton jbAction;

    /* Extra buttons */
    private JButton jbText;

    private static final int MIN_WIDTH = 640;
    private static final int MIN_HEIGHT = 40;
    private static final int BUTTON_SIZE = 27;

    private static final Dimension jbDimension = new Dimension(BUTTON_SIZE, BUTTON_SIZE);

    /* Icon paths */
    private static final String NEW_FILE_ICON = "/image/toolbar/new_file.png";
    private static final String OPEN_FILE_ICON = "/image/toolbar/open_file.png";
    private static final String SAVE_FILE_ICON = "/image/toolbar/save_file.png";
    private static final String PRINT_FILE_ICON = "/image/toolbar/print_file.png";
    private static final String CURSOR_ICON = "/image/toolbar/cursor.png";
    public static final String DELETE_ICON = "/image/toolbar/delete.png";
    private static final String UNDO_ICON = "/image/toolbar/undo.png";
    private static final String REDO_ICON = "/image/toolbar/redo.png";

    private static final String TAD_ICON = "/image/toolbar/TAD.png";
    private static final String VAR_ICON = "/image/toolbar/var.png";
    private static final String PERIPHERAL_ICON = "/image/toolbar/peripheral.png";
    private static final String INTERFACE_ICON = "/image/toolbar/interface.png";
    private static final String OPERATION_ICON = "/image/toolbar/operation.png";
    private static final String INTERRUPT_ICON = "/image/toolbar/interrupt.png";
    private static final String STATE_ICON = "/image/toolbar/state.png";
    private static final String TRANSITION_CURSOR = "/image/toolbar/transition.png";
    private static final String ACTION_CURSOR = "/image/toolbar/action.png";

    public static final String TAD_ICON_C = "/image/cursor/TAD_c.png";
    public static final String VAR_ICON_C = "/image/cursor/var_c.png";
    public static final String PERIPHERAL_ICON_C = "/image/cursor/peripheral_c.png";
    public static final String INTERFACE_ICON_C = "/image/cursor/interface_c.png";
    public static final String OPERATION_ICON_C = "/image/cursor/operation_c.png";
    public static final String INTERRUPT_ICON_C = "/image/cursor/interrupt_c.png";
    public static final String STATE_ICON_C = "/image/cursor/state_c.png";
    public static final String TRANSITION_CURSOR_C = "/image/cursor/transition_c.png";
    public static final String ACTION_CURSOR_C = "/image/cursor/action_c.png";

    public static final String TEXT_ICON = "/image/toolbar/text.png";
    public static final String CLOSE_ICON = "/image/toolbar/close.png";

    public ToolBar() {

        this.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        this.setPreferredSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));

        jtbIcons = new JToolBar("Icons ToolBar");
        jtbIcons.setFloatable(false);
        jtbIcons.setRollover(true);

        /* Add all buttons with separators */
        this.addFilesButtons();
        jtbIcons.addSeparator();
        this.addMainOpButtons();
        jtbIcons.addSeparator();
        this.addTADButtons();
        jtbIcons.addSeparator();
        this.addStatesButtons();
        jtbIcons.addSeparator();
        this.addExtraButtons();

        this.add(jtbIcons);

    }

    private void addFilesButtons() {
        JPanel jpButtons = new JPanel();

        jbNewFile = makeCustomButton("Create a new file", NEW_FILE_ICON);
        jbOpenFile = makeCustomButton("Open an existing file", OPEN_FILE_ICON);
        jbSaveFile = makeCustomButton("Save actual file", SAVE_FILE_ICON);
        jbPrint = makeCustomButton("Print actual file", PRINT_FILE_ICON);

        jpButtons.add(jbNewFile);
        jpButtons.add(jbOpenFile);
        jpButtons.add(jbSaveFile);
        jpButtons.add(jbPrint);

        jtbIcons.add(jpButtons);
    }

    private void addMainOpButtons() {
        JPanel jpButtons = new JPanel();

        jbCursor = makeCustomButton("Select cursor", CURSOR_ICON);
        jbDelete = makeCustomButton("Delete element", DELETE_ICON);
        jbUndo = makeCustomButton("Undo", UNDO_ICON);
        jbRedo = makeCustomButton("Redo", REDO_ICON);

        jpButtons.add(jbCursor);
        jpButtons.add(jbDelete);
        jpButtons.add(jbUndo);
        jpButtons.add(jbRedo);

        jtbIcons.add(jpButtons);
    }

    private void addTADButtons() {
        JPanel jpButtons = new JPanel();

        jbTAD = makeCustomButton("Create TAD", TAD_ICON);
        jbVariable = makeCustomButton("Create variable", VAR_ICON);
        jbPeripheral = makeCustomButton("Create peripheral", PERIPHERAL_ICON);
        jbIface = makeCustomButton("Interface cursor", INTERFACE_ICON);
        jbOperation = makeCustomButton("Operation cursor", OPERATION_ICON);
        jbInterrupt = makeCustomButton("Interrupt cursor", INTERRUPT_ICON);

        jpButtons.add(jbTAD);
        jpButtons.add(jbVariable);
        jpButtons.add(jbPeripheral);
        jpButtons.add(jbIface);
        jpButtons.add(jbOperation);
        jpButtons.add(jbInterrupt);

        jtbIcons.add(jpButtons);
    }

    private void addStatesButtons() {
        JPanel jpButtons = new JPanel();

        jbState = makeCustomButton("Create state", STATE_ICON);
        jbTransition = makeCustomButton("Transition cursor", TRANSITION_CURSOR);
        jbAction = makeCustomButton("Add action", ACTION_CURSOR);

        jpButtons.add(jbState);
        jpButtons.add(jbTransition);
        jpButtons.add(jbAction);

        jtbIcons.add(jpButtons);
    }

    private void addExtraButtons() {
        JPanel panel = new JPanel();

        jbText = makeCustomButton("Add floating text", TEXT_ICON);

        panel.add(jbText);

        jtbIcons.add(panel);
    }

    private static JButton makeCustomButton(String tipText, String iconPath) {
        return makeCustomButton(tipText, iconPath, BUTTON_SIZE);
    }

    public static JButton makeCustomButton(String tipText, String iconPath, int size) {
        JButton jb = new JButton();

        jb.setPreferredSize(jbDimension);
        jb.setToolTipText(tipText);
        jb.setBorder(BorderFactory.createEmptyBorder());
        jb.setContentAreaFilled(false);                     //For Windows
        jb.addMouseListener(new MouseAdapter() {

            private final Border hovered = BorderFactory.createBevelBorder(BevelBorder.RAISED);
            private final Border notHovered = BorderFactory.createEmptyBorder();

            @Override
            public void mouseEntered(MouseEvent e) {
                jb.setBorder(hovered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jb.setBorder(notHovered);
            }

        });

        try {
            Image img = new ImageIcon(ToolBar.class.getResource(iconPath)).getImage()
                    .getScaledInstance(size, size, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(img);
            jb.setIcon(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jb;
    }

    public void addButtonListener(ActionListener c) {
        jbNewFile.addActionListener(c);
        jbNewFile.setActionCommand(CursorDetail.NEW_FILE.name());
        jbOpenFile.addActionListener(c);
        jbOpenFile.setActionCommand(CursorDetail.OPEN_FILE.name());
        jbSaveFile.addActionListener(c);
        jbSaveFile.setActionCommand(CursorDetail.SAVE_FILE.name());
        jbPrint.addActionListener(c);
        jbPrint.setActionCommand(CursorDetail.PRINT_FILE.name());

        jbCursor.addActionListener(c);
        jbCursor.setActionCommand(CursorDetail.SELECTING.name());
        jbDelete.addActionListener(c);
        jbDelete.setActionCommand(CursorDetail.DELETING.name());
        jbUndo.addActionListener(c);
        jbUndo.setActionCommand(CursorDetail.UNDO.name());
        jbRedo.addActionListener(c);
        jbRedo.setActionCommand(CursorDetail.REDO.name());

        jbTAD.addActionListener(c);
        jbTAD.setActionCommand(CursorDetail.ADD_TAD.name());
        jbVariable.addActionListener(c);
        jbVariable.setActionCommand(CursorDetail.ADD_VARIABLE.name());
        jbPeripheral.addActionListener(c);
        jbPeripheral.setActionCommand(CursorDetail.ADD_PERIPHERAL.name());
        jbIface.addActionListener(c);
        jbIface.setActionCommand(CursorDetail.ADD_INTERFACE.name());
        jbOperation.addActionListener(c);
        jbOperation.setActionCommand(CursorDetail.ADD_OPERATION.name());
        jbInterrupt.addActionListener(c);
        jbInterrupt.setActionCommand(CursorDetail.ADD_INTERRUPT.name());

        jbState.addActionListener(c);
        jbState.setActionCommand(CursorDetail.ADD_STATE.name());
        jbTransition.addActionListener(c);
        jbTransition.setActionCommand(CursorDetail.ADD_TRANSITION.name());
        jbAction.addActionListener(c);
        jbAction.setActionCommand(CursorDetail.ADD_ACTION.name());

        jbText.addActionListener(c);
        jbText.setActionCommand(CursorDetail.ADD_TEXT.name());

    }
}
