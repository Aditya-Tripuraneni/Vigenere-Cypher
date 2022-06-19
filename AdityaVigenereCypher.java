package com.Aditya;

/*Name: Aditya Tripuraneni
Description: Encrypts a message that is passed into the program using the viegenere technique
Date: 03/30/2021 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class AdityaVigenereCypher {

    public static void main(String[] args) {
        VigenereCypher frame = new VigenereCypher();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
@SuppressWarnings("serial")
class VigenereCypher extends JFrame implements ActionListener
{
    Font monoSpacedFont = new Font("Monospaced", Font.PLAIN, 20); // Font
    char[] letters = new char[26];
    JButton encryptBtn;
    JTextArea plainMsg, encryptedMsg;
    String key = "COMPUTERS"; // key for encrypting the msg

    public VigenereCypher()
    {
        super("Aditya's Vigenere Cypher");
        JPanel pane = (JPanel) getContentPane();

        pane.setDoubleBuffered(true);
        pane.setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(2,1,5,5));
        leftPanel.add(new JLabel("Message: "));
        leftPanel.add(new JLabel("Encrypted"));
        leftPanel.setBackground(Color.orange);

        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new GridLayout(2,1,5,5));
        plainMsg = new JTextArea(); // User inputs message here
        encryptedMsg = new JTextArea(); // Encrypted msg will show here
        encryptedMsg.setEditable(false); // Done so we can't edit the encrypted msg
        centrePanel.add(plainMsg);
        centrePanel.add(encryptedMsg);

        encryptBtn = new JButton("Encrypt");
        encryptBtn.setBackground(Color.green);
        encryptBtn.addActionListener(this);

        pane.add(centrePanel, BorderLayout.CENTER);
        pane.add(encryptBtn, BorderLayout.SOUTH);
        pane.add(leftPanel, BorderLayout.WEST);

        for(int i = 65; i<=90; i++) // Loop based of ASCII table for letters only
            letters[i - 65] = (char)i;

        setSize(600, 300);
        setVisible(true); // Makes visible
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == encryptBtn)
        {
            System.out.println("Encrypt the button");
            encryptedMsg.setText(encryptMsg()); // Sets text to encrypted msg
        }
    }

    public String encryptMsg(){ // Encrypts the message
        String message = plainMsg.getText().toUpperCase();
        String encryptedMessage = "";
        int msgLetterIndex, keyLetterIndex;
        for (int i = 0, j =0;  i < message.length(); i ++)
        {
            msgLetterIndex = getIndex(message.charAt(i)); // Getting index from message
            keyLetterIndex = getIndex(key.charAt(j % key.length())); // Getting index from key
            if (msgLetterIndex == -1) // Ignoring spaces, and anything that is not a letter
            {
                encryptedMessage = encryptedMessage + String.valueOf(message.charAt(i));
            }
            else
                {
                encryptedMessage = encryptedMessage + String.valueOf(letters[(keyLetterIndex + msgLetterIndex) % letters.length]); // Concatenates and continues to loop again from beginning if exceed letter length
                j++; // Moves through the keys if there is a letter
                }
        }
        return encryptedMessage;

    }

    public int getIndex(char letter){ // Gets index of letter
        for (int i = 0; i < letters.length; i++) //loops over the letters
        {
            if (letters[i] == letter) // Checks if letter is equal to param and returns index
                return i;
        }
        return -1; // If nothing is found returns -1
    }


}

