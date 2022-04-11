/**
 * This program will ask the user information about a person and store the information
 * Author: Maciek Stuczyk
 * Class: CPSC-24500-002
 * Assignment: HW11
 */

// Importing necessary function 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

// Defining variables 
class Person implements Serializable {
    String name;
    String number;
    String DOB;
    String email;

    // Constructor with all fields
    public Person(String name, String number, String DOB, String email) {
        this.name = name;
        this.number = number;
        this.DOB = DOB;
        this.email = email;
    }

    // Creating toString
    @Override
    public String toString() {
        return "Name = " + name + "\n" +
                "Phone Number = " + number + "\n" +
                "DOB = " + DOB + "\n" +
                "Email = " + email + "\n";
    }

}

// Creating GUI and placing buttons
public class Data extends JFrame {

    public Data() {
        JButton add = new JButton("Add Information to a file");
        setLayout(new FlowLayout());
        add(add);

        JButton retrieve = new JButton("Retrieve information from a file and display it");
        setLayout(new FlowLayout());
        add(retrieve);

        JButton delete = new JButton("Delete Information");
        setLayout(new FlowLayout());
        add(delete);

        JButton update = new JButton("Update Information");
        setLayout(new FlowLayout());
        add(update);

        JButton exit = new JButton("Exit");
        setLayout(new FlowLayout());
        add(exit);

        ActionListener listener = new addListener();
        add.addActionListener(listener);

        ActionListener listener2 = new retrieveListener();
        retrieve.addActionListener(listener2);

        ActionListener listener3 = new deleteListener();
        delete.addActionListener(listener3);

        ActionListener listener4 = new updateListener();
        update.addActionListener(listener4);

        ActionListener listener5 = new exitListener();
        exit.addActionListener(listener5);
    }

    // Adding function to add button
    public class addListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("Please enter the person's name: ");
            String num = JOptionPane.showInputDialog("Please enter the person's phone number: ");
            String DOB = JOptionPane.showInputDialog("Please enter the person's date of birth (mm/dd/yyyy): ");
            String email = JOptionPane.showInputDialog("Please enter the person's email: ");

            Person p = new Person(name, num, DOB, email);
            try {
                writeToFile(p);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }

        }
    }

    // Adding function to retrieve button
    public class retrieveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                readFile();
            } catch (ClassNotFoundException | IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    // Adding function to delete button
    public class deleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            Person p = new Person(" ", " ", " ", " ");

            try {
                writeToFile(p);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    // Adding function to update information button
    public class updateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("Please enter the person's updated name: ");
            String num = JOptionPane.showInputDialog("Please enter the person's updated phone number: ");
            String DOB = JOptionPane.showInputDialog("Please enter the person's updated date of birth (mm/dd/yyyy): ");
            String email = JOptionPane.showInputDialog("Please enter the person's updated email: ");

            Person p = new Person(name, num, DOB, email);

            try {
                writeToFile(p);
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    // Adding function to exit button
    public class exitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    // Main function and creating the GUI window
    public static void main(String[] args) {
        JFrame frame = new Data();
        frame.setTitle("Person Database");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 150);
        frame.setVisible(true);
    }

    // Creating Serialization file
    public static void writeToFile(Person p) throws IOException {
        String file = JOptionPane.showInputDialog("Please enter the desired name of the file: ");
        String f = file + ".bin";
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(f));

        objectOutputStream.writeObject(p);
        objectOutputStream.close();
    }

    // Reading Serialization file
    public static void readFile() throws IOException, ClassNotFoundException {
        String file = JOptionPane.showInputDialog("Please enter the name of the file: ");
        String f = file + ".bin";
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(f));

        Person name = (Person) objectInputStream.readObject();
        JOptionPane.showMessageDialog(null, name);

        objectInputStream.close();

    }
}