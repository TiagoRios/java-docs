package com.revisoes.gui.swing.components.component.button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/* 
 * ButtonDemo.java requires the following files:
 *   images/right.gif
 *   images/middle.gif
 *   images/left.gif
 */
public class ButtonDemo extends JPanel
        implements ActionListener {
    protected JButton leftButton, middleButton, rightButton;

    public ButtonDemo() {
        ImageIcon leftButtonIcon = createImageIcon("images/right.gif");
        ImageIcon middleButtonIcon = createImageIcon("images/middle.gif");
        ImageIcon rightButtonIcon = createImageIcon("images/left.gif");

        leftButton = new JButton("Disable middle button", leftButtonIcon);
        leftButton.setVerticalTextPosition(AbstractButton.CENTER);
        leftButton.setHorizontalTextPosition(AbstractButton.LEADING); // aka LEFT, for left-to-right locales
        leftButton.setMnemonic(KeyEvent.VK_D);
        leftButton.setActionCommand("disable");

        middleButton = new JButton("Middle button", middleButtonIcon);
        middleButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        middleButton.setHorizontalTextPosition(AbstractButton.CENTER);
        middleButton.setMnemonic(KeyEvent.VK_M);

        rightButton = new JButton("Enable middle button", rightButtonIcon);
        // Use the default text position of CENTER, TRAILING (RIGHT).
        rightButton.setMnemonic(KeyEvent.VK_E);
        rightButton.setActionCommand("enable");
        rightButton.setEnabled(false);

        // Listen for actions on buttons 1 and 3.
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);

        leftButton.setToolTipText("Click this button to disable the middle button.");
        middleButton.setToolTipText("This middle button does nothing when you click it.");
        rightButton.setToolTipText("Click this button to enable the middle button.");

        // Add Components to this container, using the default FlowLayout.
        add(leftButton);
        add(middleButton);
        add(rightButton);
    }

    public void actionPerformed(ActionEvent e) {
        if ("disable".equals(e.getActionCommand())) {
            middleButton.setEnabled(false);
            leftButton.setEnabled(false);
            rightButton.setEnabled(true);
        } else {
            middleButton.setEnabled(true);
            leftButton.setEnabled(true);
            rightButton.setEnabled(false);
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ButtonDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {

        // Create and set up the window.
        JFrame frame = new JFrame("ButtonDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        ButtonDemo newContentPane = new ButtonDemo();
        newContentPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(newContentPane);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}