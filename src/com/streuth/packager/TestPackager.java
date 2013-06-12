/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.streuth.packager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author mhowe
 */
public class TestPackager {
    /* Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Display Parameters");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(0, 0, dim.width / 4, dim.height / 4);


        long v = Runtime.getRuntime().maxMemory();
        Long value = v / 1048576;
        long t = Runtime.getRuntime().totalMemory();
        Long total = t / 1048576;

        JLabel label = new JLabel("Max" + value.toString() + "  Total"
                + total.toString());

        RuntimeMXBean RuntimemxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = RuntimemxBean.getInputArguments();
        String property = System.getProperty("test1");
        property = System.getProperty("test2");

        JList list = new JList(arguments.toArray(new String[arguments.size()]));
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(400, 200));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 30));
        panel.add(label, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);

        frame.getContentPane().add(panel);
        frame.setLocationRelativeTo(null);

        //Display the window.
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
