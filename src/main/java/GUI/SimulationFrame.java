package GUI;

import BusinessLogic.SimulationManager;

import javax.swing.*;
import java.awt.*;

public class SimulationFrame {
    private JFrame frame = new JFrame();
    private JLabel name = new JLabel("Queue Simulation");
    private JLabel nrClient = new JLabel("Number of Clients: ");
    private JLabel nrServer = new JLabel("Number of Servers: ");
    private JLabel timeLImit = new JLabel("Time Limit: ");
    private JLabel minArrTime = new JLabel("Min Arrival Time: ");
    private JLabel maxArrTime = new JLabel("Max Arrival Time: ");
    private JLabel minSerTime = new JLabel("Min Service Time: ");
    private JLabel maxSerTime = new JLabel("Min Service Time: ");

    private static JTextField nrClientText = new JTextField(10);
    private static JTextField nrServerText = new JTextField(10);
    private static JTextField timeLimitText = new JTextField(5);
    private static JTextField minArrTimeText = new JTextField(6);
    private static JTextField maxArrTimeText = new JTextField(6);
    private static JTextField minSerTimeText = new JTextField(6);
    private static JTextField maxSerTimeText = new JTextField(6);

    private JLabel q1 = new JLabel("Queue1: ");
    private JLabel q2 = new JLabel("Queue2: ");
    private JLabel q3 = new JLabel("Queue3: ");
    private JLabel q4 = new JLabel("Queue4: ");
    private JLabel q5 = new JLabel("Queue5: ");

    private JTextField q1Text = new JTextField(15);
    private JTextField q2Text = new JTextField(15);
    private JTextField q3Text = new JTextField(15);
    private JTextField q4Text = new JTextField(15);
    private JTextField q5Text = new JTextField(15);


    private JButton startButton = new JButton("Start");

    public SimulationFrame(){

        frame.setTitle("Queue Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1000, 700);
        frame.getContentPane().setBackground(new Color(203, 195, 227));
        frame.setResizable(false);
        frame.setLayout(null);

        name.setBounds(20, 5, 300, 50);
        name.setFont(new Font("Calibri", Font.PLAIN, 28));
        frame.add(name);

        nrClient.setBounds(30, 55, 200, 45);
        nrClient.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(nrClient);
        nrClientText.setBounds(220, 60, 130, 30);
        nrClientText.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(nrClientText);

        nrServer.setBounds(30, 105, 200, 45);
        nrServer.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(nrServer);
        nrServerText.setBounds(220, 105, 130, 30);
        nrServerText.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(nrServerText);

        timeLImit.setBounds(30, 160, 200, 45);
        timeLImit.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(timeLImit);
        timeLimitText.setBounds(220, 160, 130, 30);
        timeLimitText.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(timeLimitText);

        minArrTime.setBounds(30, 210, 200, 45);
        minArrTime.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(minArrTime);
        minArrTimeText.setBounds(220, 210, 130, 30);
        minArrTime.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(minArrTimeText);

        maxArrTime.setBounds(30, 260, 200, 45);
        maxArrTime.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(maxArrTime);
        maxArrTimeText.setBounds(220, 260, 130, 30);
        maxArrTimeText.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(maxArrTimeText);

        minSerTime.setBounds(30, 305, 200, 45);
        minSerTime.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(minSerTime);
        minSerTimeText.setBounds(220, 305, 130, 30);
        minSerTimeText.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(minSerTimeText);

        maxSerTime.setBounds(30, 355, 200, 45);
        maxSerTime.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(maxSerTime);
        maxSerTimeText.setBounds(220, 352, 130, 30);
        maxSerTimeText.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(maxSerTimeText);


        q1.setBounds(500, 90, 200, 45);
        q1.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(q1);
        q1Text.setBounds(600, 90, 350, 30);
        q1Text.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(q1Text);

        q2.setBounds(500, 145, 200, 45);
        q2.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(q2);
        q2Text.setBounds(600, 145, 350, 30);
        q2Text.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(q2Text);


        q3.setBounds(500, 200, 200, 45);
        q3.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(q3);
        q3Text.setBounds(600, 200, 350, 30);
        q3Text.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(q3Text);


        q4.setBounds(500, 255, 200, 45);
        q4.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(q4);
        q4Text.setBounds(600, 255, 350, 30);
        q4Text.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(q4Text);

        q5.setBounds(500, 310, 200, 45);
        q5.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(q5);
        q5Text.setBounds(600, 310, 350, 30);
        q5Text.setFont(new Font("Calibri", Font.PLAIN, 22));
        frame.add(q5Text);

        startButton.setBounds(800, 400, 100, 70);
        startButton.setFont(new Font("Calibri", Font.PLAIN, 22));
        startButton.setHorizontalAlignment(JTextField.CENTER);
        startButton.setBackground(new Color(221, 160, 221));
        startButton.setForeground(new Color(127, 0, 255));
        frame.add(startButton);

    }

    public static String getnrClients(){
        return nrClientText.getText();
    }

    public static String getnrServers(){
        return nrServerText.getText();
    }

    public static String getTimeLimit(){
        return timeLimitText.getText();
    }

    public static String getMinArrTime(){
        return minArrTimeText.getText();
    }

    public static String getMaxArrTime(){
        return maxArrTimeText.getText();
    }

    public static String getMinSerTime(){
        return minSerTimeText.getText();
    }

    public static String getMazSerTime(){
        return maxSerTimeText.getText();
    }

}
