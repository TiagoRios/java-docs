package com.revisoes.gui.swing.listeners;

/*
 * Swing version
 */

import javax.swing.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MultiListener extends JPanel
        implements ActionListener {
    JTextArea topTextArea;
    JTextArea bottomTextArea;
    JButton button1, button2;
    final static String newline = "\n";

    public MultiListener() {
        super(new GridBagLayout());
        GridBagLayout gridBagLayout = (GridBagLayout) getLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        Dimension preferredSize = new Dimension(200, 75);

        JLabel jLabel = null;
        JScrollPane bottomScrollPane = null;
        JScrollPane topScrollPane = null;

        jLabel = new JLabel("What MultiListener hears:");

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

        gridBagLayout.setConstraints(jLabel, gridBagConstraints);

        add(jLabel);

        gridBagConstraints.weighty = 1.0;

        topTextArea = new JTextArea();
        topTextArea.setEditable(false);
        topScrollPane = new JScrollPane(topTextArea);

        topScrollPane.setPreferredSize(preferredSize);
        gridBagLayout.setConstraints(topScrollPane, gridBagConstraints);

        add(topScrollPane);

        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;

        jLabel = new JLabel("What Eavesdropper hears:");
        gridBagLayout.setConstraints(jLabel, gridBagConstraints);

        add(jLabel);

        gridBagConstraints.weighty = 1.0;

        bottomTextArea = new JTextArea();
        bottomTextArea.setEditable(false);
        bottomScrollPane = new JScrollPane(bottomTextArea);
        bottomScrollPane.setPreferredSize(preferredSize);

        gridBagLayout.setConstraints(bottomScrollPane, gridBagConstraints);

        add(bottomScrollPane);

        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.insets = new Insets(10, 10, 0, 10);

        button1 = new JButton("Blah blah blah");
        gridBagLayout.setConstraints(button1, gridBagConstraints);

        add(button1);

        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;

        button2 = new JButton("You don't say!");
        gridBagLayout.setConstraints(button2, gridBagConstraints);

        add(button2);

        button1.addActionListener(this);
        button1.setActionCommand("outroNomeActionCommand");

        button2.addActionListener(this);
        button2.addActionListener(new Eavesdropper(bottomTextArea));

        // Tamanho do painel
        setPreferredSize(new Dimension(450, 450));
        setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(1, 1, 2, 2, Color.black),
                        BorderFactory.createEmptyBorder(5, 5, 5, 5)));

    }

    public void actionPerformed(ActionEvent e) {
        topTextArea.append(e.getActionCommand() + newline); // método pega o nome do botao
        topTextArea.setCaretPosition(topTextArea.getDocument().getLength());
    }

    /**
     * Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("MultiListener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        JComponent newContentPane = new MultiListener();
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

class Eavesdropper implements ActionListener {
    JTextArea myTextArea;

    public Eavesdropper(JTextArea textArea) {
        myTextArea = textArea;
    }

    public void actionPerformed(ActionEvent e) {
        myTextArea.append(e.getActionCommand() + MultiListener.newline);
        myTextArea.setCaretPosition(myTextArea.getDocument().getLength());
    }
}
