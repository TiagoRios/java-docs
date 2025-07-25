package com.revisoes.gui.swing.components.models;

import java.awt.Color;
import java.awt.Dimension;

/*
 * A application that requires the following files:
 *   ConversionPanel.java
 *   ConverterRangeModel.java
 *   FollowerRangeModel.java
 *   Unit.java
 */
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Converter {
    ConversionPanel metricPanel, usaPanel;
    Unit[] metricDistances = new Unit[3];
    Unit[] usaDistances = new Unit[4];
    final static boolean MULTICOLORED = false;

    // Specify the look and feel to use. Valid values:
    // null (use the default), "Metal", "System", "Motif", "GTK+"
    final static String LOOKANDFEEL = null;

    ConverterRangeModel dataModel = new ConverterRangeModel();
    JPanel mainPane;

    /**
     * Create the ConversionPanels (one for metric, another for U.S.).
     * I used "U.S." because although Imperial and U.S. distance
     * measurements are the same, this program could be extended to
     * include volume measurements, which aren't the same.
     */
    public Converter() {
        // Create Unit objects for metric distances, and then
        // instantiate a ConversionPanel with these Units.
        metricDistances[0] = new Unit("Centimeters", 0.01);
        metricDistances[1] = new Unit("Meters", 1.0);
        metricDistances[2] = new Unit("Kilometers", 1000.0);
        metricPanel = new ConversionPanel(this, "Metric System",
                metricDistances,
                dataModel);

        // Create Unit objects for U.S. distances, and then
        // instantiate a ConversionPanel with these Units.
        usaDistances[0] = new Unit("Inches", 0.0254);
        usaDistances[1] = new Unit("Feet", 0.305);
        usaDistances[2] = new Unit("Yards", 0.914);
        usaDistances[3] = new Unit("Miles", 1613.0);
        usaPanel = new ConversionPanel(this, "U.S. System",
                usaDistances,
                new FollowerRangeModel(dataModel));

        // Create a JPanel, and add the ConversionPanels to it.
        mainPane = new JPanel();
        mainPane.setLayout(new BoxLayout(mainPane, BoxLayout.PAGE_AXIS));
        if (MULTICOLORED) {
            mainPane.setOpaque(true);
            mainPane.setBackground(new Color(255, 0, 0));
        }
        mainPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mainPane.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPane.add(metricPanel);
        mainPane.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPane.add(usaPanel);
        mainPane.add(Box.createGlue());
        resetMaxValues(true);
    }

    public void resetMaxValues(boolean resetCurrentValues) {
        double metricMultiplier = metricPanel.getMultiplier();
        double usaMultiplier = usaPanel.getMultiplier();
        int maximum = ConversionPanel.MAX;

        if (metricMultiplier > usaMultiplier) {
            maximum = (int) (ConversionPanel.MAX *
                    (usaMultiplier / metricMultiplier));
        }

        dataModel.setMaximum(maximum);

        if (resetCurrentValues) {
            dataModel.setDoubleValue(maximum);
        }
    }

    private static void initLookAndFeel() {
        String lookAndFeel = null;

        if (LOOKANDFEEL != null) {
            if (LOOKANDFEEL.equals("Metal")) {
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            } else if (LOOKANDFEEL.equals("System")) {
                lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            } else if (LOOKANDFEEL.equals("Motif")) {
                lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            } else if (LOOKANDFEEL.equals("GTK+")) { // new in 1.4.2
                lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            } else {
                System.err.println("Unexpected value of LOOKANDFEEL specified: "
                        + LOOKANDFEEL);
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }

            try {
                UIManager.setLookAndFeel(lookAndFeel);
            } catch (ClassNotFoundException e) {
                System.err.println("Couldn't find class for specified look and feel:"
                        + lookAndFeel);
                System.err.println("Did you include the L&F library in the class path?");
                System.err.println("Using the default look and feel.");
            } catch (UnsupportedLookAndFeelException e) {
                System.err.println("Can't use the specified look and feel ("
                        + lookAndFeel
                        + ") on this platform.");
                System.err.println("Using the default look and feel.");
            } catch (Exception e) {
                System.err.println("Couldn't get specified look and feel ("
                        + lookAndFeel
                        + "), for some reason.");
                System.err.println("Using the default look and feel.");
                e.printStackTrace();
            }
        }
    }

    /**
     * Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Set the look and feel.
        initLookAndFeel();

        // Create and set up the window.
        JFrame frame = new JFrame("Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        Converter converter = new Converter();
        converter.mainPane.setOpaque(true); // content panes must be opaque
        frame.setContentPane(converter.mainPane);

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
