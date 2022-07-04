// Retaj Tayib Alansari
// GH
// 1911692
package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.InputMismatchException;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GH_1911692 extends JFrame implements ActionListener {

    private static final String[] days = {"Friday", "Saturday", "Sunday", "Monday", "Tuseday", "Wednesday", "Thursday"};
    private static final String[] times = {"8:00 AM", "9:00 AM", "10:00 AM", "3:00 PM", "4:00 PM",
        "5:00 PM", "10:00 PM", "11:00 PM", "12:00 AM"};
    public static int count = 0;
    public static int count2 = 0;
    private static Formatter output;
    private static Scanner input;

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.com)$";
    private static final String NAME_PATTERN = "[a-zA-Z]{3,30}";
    private static final String PHONE_PATTERN = "(05)\\d{8}";

    // Method to validate first name and last name
    public boolean validate(final String hex) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }

    // Method to validate email
    public boolean validate2(final String hex) {
        Pattern pattern2 = Pattern.compile(EMAIL_PATTERN);

        Matcher matcher = pattern2.matcher(hex);
        return matcher.matches();
    }

    // Method to validate phone number
    public boolean validate3(final String hex) {
        Pattern pattern3 = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern3.matcher(hex);
        return matcher.matches();
    }

    // Method to write to file and append 
    private static void appendUsingBufferedWriter(String filePath, String text, int noOfLines) {
        File file = new File(filePath);
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            // to append to file, you need to initialize FileWriter using below constructor
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            for (int i = 0; i < noOfLines; i++) {
                br.newLine();
                br.write(text);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to read from file
    static void readUsingFileReader(String fn, JTextArea pane) {
        try {
            FileReader fr = new FileReader(fn);
            pane.read(fr, null);
            fr.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        JFrame home = new JFrame(); // Home frame 
        JFrame info = new JFrame(); // User information frame
        JFrame list = new JFrame(); // List of reservation frame
        JFrame resturant2 = new JFrame(); // Details of user reservation
        JFrame resturant3 = new JFrame(); // Reservation completed frame
        JFrame resturant4 = new JFrame(); // Review reservation of user

        // to read from file deatils of user reservation
        JScrollPane scrl = new JScrollPane();
        JTextArea toread1 = new JTextArea();
        scrl.setViewportView(toread1);
        toread1.setEditable(false);
        toread1.setBackground(new Color(204, 204, 204));

        // to read from file all users reservation 
        JScrollPane scrl2 = new JScrollPane();
        JTextArea toread2 = new JTextArea();
        scrl2.setViewportView(toread2);
        toread2.setEditable(false);
        toread2.setBackground(new Color(204, 204, 204));

        // Home frame
        // create a new panel to hold the component
        JPanel Panel = new JPanel();
        Panel.setLayout(null); // set panel Layout

        // add icons to label
        Icon bug = new ImageIcon(("burg.png"));
        JLabel bur = new JLabel(bug);
        Icon bug3 = new ImageIcon(("tom.png"));
        JLabel tom = new JLabel(bug3);
        Icon bug4 = new ImageIcon(("tom2.png"));
        JLabel tom2 = new JLabel(bug4);
        JLabel tom3 = new JLabel(bug4);
        Icon bug5 = new ImageIcon(("frok.png"));
        JLabel frok = new JLabel(bug5);

        // Resturant Title
        JLabel resName = new JLabel();
        resName.setFont(new Font("PT Mono", 1, 24));
        resName.setText("TEASTY BURGER RESTURANT");
        Panel.add(resName);
        resName.setBounds(130, 30, 350, 50);

        JButton newRes = new JButton();
        newRes.setFont(new Font("Lucida Bright", 0, 14));
        newRes.setText("NEW RESERVATION");

        // anonymous inner class listener for button
        newRes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                info.setVisible(true);
                home.setVisible(false);
            }
        });
        Panel.add(newRes);
        newRes.setBounds(160, 170, 240, 29);

        JButton listRes = new JButton();
        listRes.setFont(new Font("Lucida Bright", 0, 14));
        listRes.setText("RESERVATION LIST");

        // anonymous inner class listener for button
        listRes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try { // read from ReservationInformation.txt
                    readUsingFileReader("ReservationInformation.text", toread2);
                } catch (IllegalStateException stateException) {
                    System.err.println("Error reading from file. Terminating.");
                }
                list.setVisible(true); // open frame
                home.setVisible(false); // close frame
            }
        });
        Panel.add(listRes);
        listRes.setBounds(160, 200, 240, 29);

        JButton exit = new JButton();
        exit.setFont(new Font("Lucida Bright", 0, 14));
        exit.setText("EXIT");

        // anonymous inner class listener for button
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });

        Panel.add(exit);
        exit.setBounds(160, 260, 240, 29);

        JButton changeColorJButton = new JButton();
        changeColorJButton.setFont(new Font("Lucida Bright", 0, 14));
        changeColorJButton.setText("CHANGE COLOR");
        Panel.setBackground(Color.WHITE); // set background for panel
        home.setBackground(Color.WHITE); // set background for frame

        // anonymous inner class listener for button
        changeColorJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // JColorChooser
                Color color = JColorChooser.showDialog(home, "Choose a color", Color.WHITE);

                if (color == null) {
                    color = Color.WHITE;
                }

                Panel.setBackground(color);
                home.setBackground(color);

            }
        }
        );
        Panel.add(changeColorJButton);
        changeColorJButton.setBounds(160, 230, 240, 29);

        home.add(bur);
        bur.setBounds(320, 80, 370, 240);
        home.add(tom);
        tom.setBounds(-10, 180, 199, 150);
        home.add(tom2);
        tom2.setBounds(0, 100, 170, 80);
        home.add(tom3);
        tom3.setBounds(60, 100, 199, 150);
        home.add(frok);
        frok.setBounds(150, -20, 330, 180);

        home.add(Panel);

        // Frame 2
        // create a new panel to hold the component
        JPanel Panel1 = new JPanel();
        Panel1.setLayout(null); // set panel Layout
        Panel1.setBackground(Color.WHITE); // set panel background

        JLabel FirstName = new JLabel("FULL NAME: *");
        Panel1.add(FirstName);
        FirstName.setBounds(40, 80, 90, 16);

        JLabel Email = new JLabel("EMAIL: *");
        Panel1.add(Email);
        Email.setBounds(40, 140, 60, 16);

        JLabel PhoneNumber = new JLabel("PHONE NUMBER *");
        Panel1.add(PhoneNumber);
        PhoneNumber.setBounds(40, 190, 120, 16);

        JLabel Sex = new JLabel();
        Sex.setText("GENDER: *");
        Panel1.add(Sex);
        Sex.setBounds(40, 250, 70, 16);

        JTextField FirsNametf = new JTextField();
        Panel1.add(FirsNametf);
        FirsNametf.setBounds(190, 70, 160, 30);

        FirsNametf.setForeground(Color.LIGHT_GRAY);
        FirsNametf.setText("Enter your first name");

        // anonymous inner class listener for mouseAdapter
        FirsNametf.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                FirsNametf.setText(""); // reset text field
                FirsNametf.setForeground(Color.BLACK);

            }

        }
        );

        JTextField LastNametf = new JTextField();
        Panel1.add(LastNametf);
        LastNametf.setBounds(370, 70, 160, 30);

        LastNametf.setForeground(Color.LIGHT_GRAY);
        LastNametf.setText("Enter your last name");

        // anonymous inner class listener for mouseAdapter
        LastNametf.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                LastNametf.setText("");  // reset text field
                LastNametf.setForeground(Color.BLACK);

            }

        }
        );

        JTextField Emailtf = new JTextField();
        Panel1.add(Emailtf);
        Emailtf.setBounds(190, 130, 160, 30);

        Emailtf.setForeground(Color.LIGHT_GRAY);
        Emailtf.setText("Example@example.com");

        // anonymous inner class listener for mouseAdapter
        Emailtf.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Emailtf.setText(""); // reset text field
                Emailtf.setForeground(Color.BLACK);

            }

        }
        );

        JTextField PhoneNumbertf = new JTextField(20);
        Panel1.add(PhoneNumbertf);
        PhoneNumbertf.setBounds(190, 180, 160, 30);

        PhoneNumbertf.setForeground(Color.LIGHT_GRAY);
        PhoneNumbertf.setText("05xxxxxxxx");

        // anonymous inner class listener for mouseAdapter
        PhoneNumbertf.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                PhoneNumbertf.setText("");  // reset text field
                PhoneNumbertf.setForeground(Color.BLACK);

            }

        }
        );

        JRadioButton male = new JRadioButton("Male");
        Panel1.add(male);
        male.setBounds(200, 250, 61, 23);

        JRadioButton female = new JRadioButton("Female");
        Panel1.add(female);
        female.setBounds(260, 250, 76, 23);

        // create radio button group to group two button
        ButtonGroup group = new ButtonGroup();
        group.add(male);
        group.add(female);

        JButton next1 = new JButton("->>");

        // anonymous inner class listener for button
        next1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                GH_1911692 nameValidator = new GH_1911692(); // objet from GH_1911692
                GH_1911692 emailValidator = new GH_1911692();
                GH_1911692 phoneValidator = new GH_1911692();
                boolean flag = true;
                String selection;

                if (male.isSelected()) {
                    selection = "Male";
                } else {
                    selection = "Female";
                }

                try { // throw an exception and immedatle catch it
                    if (!nameValidator.validate(FirsNametf.getText().trim())) {
                        flag = false;

                        throw new InputMismatchException(); // generate exception
                    }
                } catch (InputMismatchException e) { // catch exception thrown in try
                    JOptionPane.showMessageDialog(null, "You must inter letters. Please try again.",
                            "Incorrect First Name.", JOptionPane.ERROR_MESSAGE);
                }

                try { // throw an exception and immedatle catch it
                    if (!nameValidator.validate(LastNametf.getText().trim())) {
                        flag = false;
                        throw new InputMismatchException(); // generate exception

                    }
                } catch (InputMismatchException e) { // catch exception thrown in try
                    JOptionPane.showMessageDialog(null, "You must inter letters. Please try again.",
                            "Incorrect Last Name.", JOptionPane.ERROR_MESSAGE);
                }

                try { // throw an exception and immedatle catch it

                    if (!emailValidator.validate2(Emailtf.getText().trim())) {
                        flag = false;
                        throw new Exception(); // generate exception
                    }
                } catch (Exception ex) { // catch exception thrown in try
                    if (!Emailtf.getText().contains("@")) {
                        JOptionPane.showMessageDialog(null, "You must include '@' in your email.",
                                "Incorrect Email.", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!Emailtf.getText().endsWith(".com")) {
                        JOptionPane.showMessageDialog(null, "Your email must end with '.com'.",
                                "Incorrect Email.", JOptionPane.ERROR_MESSAGE);

                    }

                }

                try { // throw an exception and immedatle catch it
                    if (!phoneValidator.validate3(PhoneNumbertf.getText().trim())) {
                        flag = false;
                        throw new Exception(); // generate exception
                    }
                } catch (Exception ex) { // catch exception thrown in try
                    if (!PhoneNumbertf.getText().startsWith("05")) {
                        JOptionPane.showMessageDialog(null, "You must start with 05",
                                "Incorrect Phone Number.", JOptionPane.ERROR_MESSAGE);
                    }
                    if (PhoneNumbertf.getText().length() < 10 || PhoneNumbertf.getText().length() > 10) {
                        JOptionPane.showMessageDialog(null, "You must enter 10 diget",
                                "Incorrect Phone Number.", JOptionPane.ERROR_MESSAGE);
                    }
                }

                try { // open file  file.txt
                    output = new Formatter("file.text");
                } catch (SecurityException SecurityException) {
                    System.err.println("Write permission denied. Terminating");
                    System.exit(1); // terminate the program
                } catch (FileNotFoundException FileNotFoundException) {
                    System.err.println("Error opening file. Terminating");
                    System.exit(1); // terminate the program
                }

                try { // throw an exception and immedatle catch it
                    if (group.getSelection() == null) {
                        flag = false;
                        throw new NullPointerException(); // generate exception

                    }

                } catch (NullPointerException np) { // catch exception thrown in try
                    JOptionPane.showMessageDialog(null, "You must specify the gender.",
                            "No choice selected", JOptionPane.ERROR_MESSAGE);

                }

                try {
                    if (flag == true) {
                        resturant2.setVisible(true); // open frame
                        info.setVisible(false); // close frame

                        String content = "Name: " + FirsNametf.getText() + " " + LastNametf.getText()
                                + "\nEmail: " + Emailtf.getText() + "\nPhone Number: "
                                + PhoneNumbertf.getText() + "\nGender: "
                                + selection + "\n";

                        // open file ReservationInformation.txt to write 
                        appendUsingBufferedWriter("ReservationInformation.text", content, 1);
                        output.format("%s%n", content);
                    }
                } catch (SecurityException SecurityException) {
                    System.err.println("Write permission denied. Terminating");
                    System.exit(1); // terminate the program
                } catch (FormatterClosedException FormatterClosedException) {
                    System.err.println("Error writing to file. Terminating ");
                }

            }
        }
        );
        Panel1.add(next1);

        next1.setBounds(510, 0, 60, 29);

        JButton pre1 = new JButton("<<-");

        // anonymous inner class listener for ++counter button 
        pre1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                info.setVisible(false); // close frame
                home.setVisible(true); // open frame
            }
        });
        Panel1.add(pre1);
        pre1.setBounds(0, 0, 60, 29);

        Icon bug1 = new ImageIcon(("Star.png")); // add icon to label
        JLabel fill = new JLabel(bug1);
        Panel1.add(fill);
        fill.setBounds(400, 120, 150, 170);

        info.add(Panel1);

        // Frmae 3
        // create a new panel to hold the component
        JPanel Panel2 = new JPanel();
        Panel2.setLayout(null); // set panel Layout
        Panel2.setBackground(Color.WHITE); // set panel background

        JLabel title2 = new JLabel("RESERVATION DETAILS");
        title2.setFont(new Font("Lucida Bright", 0, 24));
        Panel2.add(title2);
        title2.setBounds(162, 30, 380, 29);

        JLabel adults = new JLabel("ADULTS:");
        Panel2.add(adults);
        adults.setBounds(40, 100, 60, 16);

        JButton in = new JButton("+");
        in.setBounds(180, 90, 40, 29);
        Panel2.add(in);

        JTextField adultstf = new JTextField();
        Panel2.add(adultstf);
        adultstf.setBounds(150, 90, 30, 30);
        adultstf.setEditable(false);
        JButton de = new JButton("-");
        de.setBounds(110, 90, 40, 29);
        Panel2.add(de);

        // anonymous inner class listener for ++counter button 
        in.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ++count;
                adultstf.setText(count + "");

            }
        });
        // anonymous inner class listener for --counter button 
        de.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                --count;
                adultstf.setText(count + "");

            }
        });

        JLabel children = new JLabel("CHILDREN's:");
        Panel2.add(children);
        children.setBounds(20, 140, 79, 16);

        JButton in2 = new JButton("+");
        in2.setBounds(180, 130, 40, 29);
        Panel2.add(in2);
        JTextField childrentf = new JTextField();
        Panel2.add(childrentf);
        childrentf.setBounds(150, 130, 30, 30);
        childrentf.setEditable(false);
        JButton de2 = new JButton("-");
        de2.setBounds(110, 130, 40, 29);
        Panel2.add(de2);

        // anonymous inner class listener for ++counter button 
        in2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ++count2;
                childrentf.setText(count2 + "");

            }
        });

        // anonymous inner class listener for --counter button 
        de2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                --count2;
                childrentf.setText(count2 + "");

            }
        });
        JLabel time = new JLabel("TIME:");
        Panel2.add(time);
        time.setBounds(60, 200, 34, 16);

        JComboBox<String> timecomb = new JComboBox(times);
        Panel2.add(timecomb);
        timecomb.setBounds(100, 240, 110, 27);

        JLabel day = new JLabel("DAY:");
        Panel2.add(day);
        day.setBounds(60, 240, 40, 16);

        JComboBox<String> daycomb = new JComboBox(days);
        Panel2.add(daycomb);
        daycomb.setBounds(100, 200, 110, 27);

        JLabel view = new JLabel("VIEW:");
        Panel2.add(view);
        view.setBounds(280, 100, 50, 16);

        JRadioButton sea = new JRadioButton("SEA");
        Panel2.add(sea);
        sea.setBounds(350, 100, 55, 20);

        JRadioButton downtown = new JRadioButton("DOWNTOWN");
        Panel2.add(downtown);
        downtown.setBounds(440, 100, 112, 20);

        // create radio button group to group two button
        ButtonGroup group1 = new ButtonGroup();
        group1.add(sea);
        group1.add(downtown);

        JLabel tableForm = new JLabel("TABLE FORM:");
        Panel2.add(tableForm);
        tableForm.setBounds(230, 140, 90, 16);

        JRadioButton square = new JRadioButton("SQUARE");
        Panel2.add(square);
        square.setBounds(350, 140, 82, 23);

        JRadioButton circular = new JRadioButton("CIRCULAR");
        Panel2.add(circular);
        circular.setBounds(440, 140, 95, 23);

        // create radio button group to group two button
        ButtonGroup group2 = new ButtonGroup();
        group2.add(square);
        group2.add(circular);

        JLabel seat = new JLabel("SEAT:");
        Panel2.add(seat);
        seat.setBounds(280, 200, 50, 16);

        JRadioButton indor = new JRadioButton("INDOOR");
        Panel2.add(indor);
        indor.setBounds(350, 200, 84, 23);

        JRadioButton outdoor = new JRadioButton("OUTDOOR");
        Panel2.add(outdoor);
        outdoor.setBounds(440, 200, 97, 23);

        // create radio button group to group two button
        ButtonGroup group3 = new ButtonGroup();
        group3.add(indor);
        group3.add(outdoor);

        JLabel optional = new JLabel("OPTIONAL:");
        Panel2.add(optional);
        optional.setBounds(250, 240, 70, 16);

        JCheckBox music = new JCheckBox("Music");
        Panel2.add(music);
        music.setBounds(350, 240, 69, 23);

        JButton next2 = new JButton();
        next2.setText("->>");

        // anonymous inner class listener for button 
        next2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                boolean flag2 = true;
                String selection;

                if (indor.isSelected()) {
                    selection = "Indoor";
                } else {
                    selection = "Outdoor";
                }

                try {
                    if (group3.getSelection() == null) {
                        flag2 = false;

                        throw new NullPointerException();
                    }
                } catch (NullPointerException np) {
                    JOptionPane.showMessageDialog(null, "You must specify seat choice.",
                            "No choice selected", JOptionPane.ERROR_MESSAGE);

                }

                try {

                    if (flag2 == true) {

                        resturant3.setVisible(true); // open frame
                        resturant2.setVisible(false); // close frame
                        String content = "Number of Adults: " + adultstf.getText()
                                + "\nNumber of Childrens: " + childrentf.getText() + "\nTime: "
                                + timecomb.getSelectedItem() + "\nDay: " + daycomb.getSelectedItem()
                                + "\nSeat: " + selection + "\n";

                        // open file ReservatIoninformation.txt to append
                        appendUsingBufferedWriter("ReservationInformation.text", content, 1);

                        // write to file.txt
                        output.format("%s%n", content);

                        // close file.txt 
                        if (output != null) {
                            output.close();
                        }

                    }
                } catch (SecurityException SecurityException) {
                    System.err.println("Write permission denied. Terminating");
                    System.exit(1); // terminate the program
                } catch (FormatterClosedException FormatterClosedException) {
                    System.err.println("Error writing to file. Terminating ");
                } catch (NullPointerException np) {
                    JOptionPane.showMessageDialog(null, "You must specify seat choice.",
                            "No choice selected", JOptionPane.ERROR_MESSAGE);

                }

            }
        });
        Panel2.add(next2);
        next2.setBounds(510, 0, 60, 29);

        JButton pre = new JButton();
        pre.setText("<<-");

        // anonymous inner class listener for button 
        pre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resturant2.setVisible(false);
                info.setVisible(true);
            }
        });
        Panel2.add(pre);
        pre.setBounds(0, 0, 60, 29);

        resturant2.add(Panel2);

        // Frame 4
        // create a new panel to hold the component
        JPanel Panel3 = new JPanel();
        Panel3.setLayout(null); // set panel Layout
        Panel3.setBackground(Color.WHITE); // set panel background

        JLabel title3 = new JLabel("RESERVATION COMPLETED");
        title3.setFont(new Font("Lucida Bright", 0, 24));
        Panel3.add(title3);
        title3.setBounds(130, 40, 330, 29);

        Icon bug2 = new ImageIcon(("qr.png"));
        JLabel qr = new JLabel(bug2);
        qr.setBounds(170, 80, 240, 220);
        Panel3.add(qr);

        JButton next3 = new JButton();
        next3.setText("->>");

        // anonymous inner class listener for button 
        next3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                FirsNametf.setText(""); // reset the text feilds
                LastNametf.setText("");
                Emailtf.setText("");
                PhoneNumbertf.setText("");
                group.clearSelection(); // reset the button
                adultstf.setText("0");
                childrentf.setText("0");
                group1.clearSelection();
                group2.clearSelection();
                group3.clearSelection();
                music.setSelected(false); // reset checkbox
                count = 0; // reset the counter
                count2 = 0;

                try { // read from file file.txt
                    readUsingFileReader("file.text", toread1);
                } catch (IllegalStateException stateException) {
                    System.err.println("Error reading from file. Terminating.");
                }
                resturant3.setVisible(false); // close frame
                resturant4.setVisible(true); // open frame
            }
        });
        Panel3.add(next3);
        next3.setBounds(510, 0, 60, 29);

        resturant3.add(Panel3);

        // Frmae 5
        // create a new panel to hold the component
        JPanel Panel4 = new JPanel();
        Panel4.setLayout(null); // set panel Layout
        Panel4.setBackground(Color.WHITE); // set panel background

        JLabel title4 = new JLabel();
        title4.setFont(new Font("Lucida Bright", 0, 24));
        title4.setText("RESERVATION REVIEW");
        Panel4.add(title4);
        title4.setBounds(160, 40, 330, 29);

        Panel4.add(scrl);
        scrl.setBounds(110, 100, 360, 150);

        JButton next4 = new JButton();
        next4.setText("->>");

        // anonymous inner class listener for button 
        next4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                resturant4.setVisible(false); // close frame
                home.setVisible(true); // open frame
            }
        });
        Panel4.add(next4);
        next4.setBounds(510, 0, 60, 29);

        resturant4.add(Panel4); // add panel to frame

        // Frmae 6
        // create a new panel to hold the component
        JPanel Panel5 = new JPanel();
        Panel5.setLayout(null); // set panel Leyout
        Panel5.setBackground(Color.WHITE); // set panel background

        JLabel title5 = new JLabel();
        title5.setFont(new Font("Lucida Bright", 0, 24));
        title5.setText("RESERVATION LIST");
        Panel5.add(title5);
        title5.setBounds(170, 40, 330, 29);

        Panel5.add(scrl2);
        scrl2.setBounds(40, 100, 490, 190);

        JButton next5 = new JButton();
        next5.setText("->>");

        // anonymous inner class listener for button 
        next5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

                list.setVisible(false); // colse frame 
                home.setVisible(true); // open frame
            }
        });
        Panel5.add(next5);
        next5.setBounds(510, 0, 60, 29);

        list.add(Panel5); // add the panel to frame

        // Frame 1
        home.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        home.setLocationRelativeTo(null); // center the frame
        home.setSize(575, 320); // set frame size
        home.setVisible(true);

        // Frame 2
        info.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        info.setLocationRelativeTo(null);  // center the frame
        info.setSize(575, 320); // set frame size

        // Frame 3
        resturant2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resturant2.setLocationRelativeTo(null);  // center the frame
        resturant2.setSize(575, 320); // set frame size

        // Frame 4
        resturant3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resturant3.setLocationRelativeTo(null);  // center the frame
        resturant3.setSize(575, 320); // set frame size

        //Frame 5
        resturant4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resturant4.setLocationRelativeTo(null);  // center the frame
        resturant4.setSize(575, 320); // set frame size

        // Frame 6
        list.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        list.setLocationRelativeTo(null);  // center the frame
        list.setSize(575, 320); // set frame size

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
