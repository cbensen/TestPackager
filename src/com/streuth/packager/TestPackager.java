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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

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

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setBounds(0, 0, dim.width / 4, dim.height / 4);


        long v = Runtime.getRuntime().maxMemory();
        Long value = v / 1048576;
        long t = Runtime.getRuntime().totalMemory();
        Long total = t / 1048576;

        JLabel label = new JLabel("Max" + value.toString() + "  Total"
                + total.toString());

        RuntimeMXBean RuntimemxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = new ArrayList<>(RuntimemxBean.getInputArguments());
        addJVMUserArgs(arguments);

        JList<String> list = new JList<>(arguments.toArray(new String[arguments.size()]));
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

        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);

        //Display the window.
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public static void addJVMUserArgs(List<String> list) {
        String property = System.getProperty("app.preferences.id");
        if (property != null) {
            Preferences node = Preferences.userRoot().node(property);
            Preferences node1 = node.node("JVMUserOptions");
            try {
                String[] keys = node1.keys();
                for (String key: keys) {
                     list.add("Key:" + key + node1.get(key, ""));
                }
            } catch (BackingStoreException ex) {
                Logger.getLogger(TestPackager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
