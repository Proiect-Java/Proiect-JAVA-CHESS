package main;


import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel(new BorderLayout());



        frame.add(mainPanel);
        frame.getContentPane().setBackground(new Color(49, 46, 39));
        frame.setLayout(new GridBagLayout());
        frame.setMinimumSize(new Dimension(800, 800));
        frame.setLocationRelativeTo(null);
        frame.setTitle("Hai să jucăm șah!");

        Board board = new Board();
        mainPanel.add(board, BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
