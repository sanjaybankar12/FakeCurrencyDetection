/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygmailserver;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;
/**
 *
 * @author Sanjay
 */
public class MyGMailServer extends JFrame
{
    JLabel g,o,o2,g2,l1,e,glogo;
    
    MainPanel mp;
    //google logo;
    public MyGMailServer()
    {
        mp=new MainPanel();
        add(mp);
    }
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                MyGMailServer mygmail=new MyGMailServer();
                mygmail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mygmail.setLocation(300,50);
                mygmail.setSize(new Dimension(700,600));
                mygmail.setResizable(false);
                mygmail.setTitle("My GMail Application");
                mygmail.setVisible(true);
            }
        });
    }
        
 }

class MainPanel extends JPanel implements ActionListener
{
    JLabel luser,lpass,glogo,connect;
    JTextField useremail;
    JPasswordField password;
    MainHeaderPanel header;
    ImageIcon im;
    google logo;
    FooterPanel footer;
    JButton login;
    String user,pass;
    JLabel uf,pf;
    public MainPanel()
    {
        setLayout(null);
       // setBackground(Color.white);
        header=new MainHeaderPanel("Welcome to Gmail");
        add(header);
        footer=new FooterPanel("Designed & Developed by Sanjay Bankar.                         10 Apr 2014");
        add(footer);
        logo=new google();
        add(logo);
              
        uf=new JLabel();
        pf=new JLabel();
        uf.setForeground(Color.red);
        pf.setForeground(Color.red);
        add(uf);
        add(pf);
       
        /*im=new ImageIcon(getClass().getResource("/images/gglogo.png"));
        glogo=new JLabel();
        glogo.setIcon(im);
        add(glogo);*/
        connect=new JLabel("Sign in to connect to Gmail");
        connect.setFont(new Font("Times New Roman",Font.BOLD,19));
        add(connect);
        
        luser=new JLabel("Email Id    : ");
        luser.setFont(new Font("Times New Roman",Font.BOLD,16));
        lpass=new JLabel("Password   : ");
        lpass.setFont(new Font("Times New Roman",Font.BOLD,16));
        add(luser);
        add(lpass);
        useremail=new JTextField();
        password=new JPasswordField();
        add(useremail);
        add(password);
        
        login=new JButton("Sign in");
        login.setFont(new Font("Times New Roman",Font.BOLD,17));
        add(login);
        
        header.setBounds(50,20,600,50);
        logo.setBounds(50,100,600,100);
        //glogo.setBounds(50,80,300,120);
        
        connect.setBounds(240,220,250,25);
        
        luser.setBounds(200,270,90,28);
        useremail.setBounds(290,270,210,28);
        uf.setBounds(290,290,210,28);
        
        lpass.setBounds(200,320,90,28);
        password.setBounds(290,320,210,28);
        pf.setBounds(290,340,210,28);
        
        login.setBounds(310,380,110,35);
        
        footer.setBounds(50,500,600,50);
        login.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e)
    {
        
        if(e.getSource()==login && !useremail.getText().trim().isEmpty() && !password.getText().trim().isEmpty())
        {
            try
            {
                uf.setText("");
                pf.setText("");
                URL url=new URL("https://www.google.co.in");
                URLConnection uc=url.openConnection();
                
                String ctype=uc.getContentType();
                if(ctype!=null)
                {
                    try
                    {
                        VerifyPassword vp=new VerifyPassword(useremail.getText(),password.getText());
                        MailFrame mf=new MailFrame(useremail.getText(),password.getText());
                        useremail.setText("");
                        password.setText("");
                    }
                    catch(Exception ae)
                    {
                        JOptionPane.showMessageDialog(this,"Invalid email or password..!! or if your email & password is correct, then goto your Gmail you will \nget security message & change the security of your gmail by Turn on lesssecured service...!! ","Error Message",ERROR_MESSAGE);
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this,"Internet must be connected to procceed...!!","Error Message",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(IOException me)
            {
                JOptionPane.showMessageDialog(this,"Internet must be connected to procceed...!!","Error Message",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(useremail.getText().trim().isEmpty() && !password.getText().trim().isEmpty())
        {
            uf.setText("* Please enter your valid email.");
            pf.setText("");
        }
        else if(password.getText().trim().isEmpty() && !useremail.getText().trim().isEmpty())
        {
            pf.setText("* Please enter your password.");
            uf.setText("");
        }
        else
        {
            uf.setText("* Please enter your valid email.");
            pf.setText("* Please enter your password.");
        }
    }
    
}

class google extends JPanel
    {
        JLabel g,o,o2,g2,l1,e;
        public google()
        {
            g=new JLabel("G");
            g.setForeground(Color.blue);
            o=new JLabel("o");
            o.setForeground(Color.red);
            o2=new JLabel("o");
            o2.setForeground(Color.yellow);
            g2=new JLabel("g");
            g2.setForeground(Color.blue);
            l1=new JLabel("l");
            l1.setForeground(Color.GREEN);       
            e=new JLabel("e");
            e.setForeground(Color.red);
        
            g.setFont(new Font("Times New Roman",Font.BOLD,60));
            o.setFont(new Font("Times New Roman",Font.BOLD,60));
            o2.setFont(new Font("Times New Roman",Font.BOLD,60));
            g2.setFont(new Font("Times New Roman",Font.BOLD,60));
            l1.setFont(new Font("Times New Roman",Font.BOLD,60));
            e.setFont(new Font("Times New Roman",Font.BOLD,60));

            add(g);
            add(o);
            add(o2);
            add(g2);
            add(l1);
            add(e);

        }
    }

class MainHeaderPanel extends JPanel
{
    JLabel l;
    public MainHeaderPanel(String title)
    {
        l=new JLabel(title);
        add(l);
        l.setFont(new Font("Times New Roman",Font.BOLD,28));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));          
    }
}

class FooterPanel extends JPanel
{
    JLabel l;
    public FooterPanel(String title)
    {
        l=new JLabel(title);
        add(l);
        setLayout(null);
        l.setBounds(20,10,580,25);
        l.setFont(new Font("Times New Roman",Font.BOLD,18));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));          
    }
}

