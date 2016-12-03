/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygmailserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
/**
 *
 * @author Sanjay
 */
public class MailFrame
{
    FramePanel fp;
    public MailFrame(String usermail,String password)
    {
        JFrame jf=new JFrame("Welcome to your Gmail Account");
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLocation(300,50);
        jf.setSize(new Dimension(700,600));
        jf.setResizable(false);
        jf.setVisible(true);
        fp=new FramePanel(usermail,password,jf);
        jf.add(fp);
    }
}

class FramePanel extends JPanel implements ActionListener
{   
    HeadPanel hp;
    FootPanel fp;
    JLabel info,lreci,lsub,lmsg;
    JTextField reci,sub;
    JTextArea jta;
    JScrollPane jsp;
    JButton send,attach,signout;
    google2 logo;
    JLabel er,es,em,lattach,latt;
    String recipient,subject,message;
    String user,pass,filepath="",filename="";
    JFrame jf;
    public FramePanel(String usermail,String password,JFrame jf)
    {
        setLayout(null);
        user=usermail;
        pass=password;
        this.jf=jf;
        hp=new HeadPanel(usermail);
        add(hp);
        hp.setBounds(50,20,600,40);
        fp=new FootPanel();
        add(fp);
        fp.setBounds(50,510,600,40);

        logo=new google2();
        add(logo);
        logo.setBounds(50,60,600,60);
        
        info=new JLabel("Send mails by providing following details  ");
        info.setFont(new Font("Times New Roman",Font.BOLD,20));       
        add(info);
        info.setBounds(180,120,350,25);
        
        er=new JLabel();
        add(er);
        es=new JLabel();
        add(es);
        em=new JLabel();
        add(em);
        er.setForeground(Color.red);
        es.setForeground(Color.red);
        em.setForeground(Color.red);
        er.setBounds(280,182,200,25);
        es.setBounds(280,234,150,25);
        em.setBounds(280,405,200,25);
        
        lreci=new JLabel("Recipient Email : ");
        lreci.setFont(new Font("Times New Roman",Font.BOLD,15));
        add(lreci);
        lsub=new JLabel("Subject  : ",JLabel.RIGHT);
        lsub.setFont(new Font("Times New Roman",Font.BOLD,15));        
        add(lsub);
        lreci.setBounds(160,160,120,28);
        lsub.setBounds(160,210,120,28);
        reci=new JTextField();
        add(reci);
        sub=new JTextField();
        add(sub);
        reci.setBounds(280,160,250,28);
        sub.setBounds(280,210,250,28);
        lmsg=new JLabel("Message :",JLabel.RIGHT);
        lmsg.setFont(new Font("Times New Roman",Font.BOLD,15));               
        add(lmsg);
        lmsg.setBounds(160,260,110,28);
        jta=new JTextArea();
        jta.setColumns(12);
        jsp=new JScrollPane(jta);
        add(jsp);
        jsp.setBounds(280,260,250,150);
        
        send=new JButton("Send Mail");
        send.setFont(new Font("Times New Roman",Font.BOLD,15));
        add(send);
        send.setBounds(420,450,110,30);
        attach=new JButton("Attach File");
        attach.setFont(new Font("Times New Roman",Font.BOLD,15));
        add(attach);
        attach.setBounds(280,450,120,30);
        
        latt=new JLabel("",JLabel.RIGHT);
        add(latt);
        latt.setFont(new Font("Times New Roman",Font.BOLD,15));                       
        latt.setBounds(160,420,120,28);
        lattach=new JLabel();
        add(lattach);
        lattach.setBounds(280,420,200,28);
        send.addActionListener(this);
        attach.addActionListener(this);
        
        signout=new JButton("Sign out");
        signout.setFont(new Font("Times New Roman",Font.BOLD,15));
        add(signout);
        signout.addActionListener(this);
        signout.setBounds(550,450,110,30);
    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==send && !reci.getText().trim().isEmpty() && !sub.getText().trim().isEmpty() && !jta.getText().trim().isEmpty())
        {
            er.setText("");
            es.setText("");
            em.setText("");
            
            recipient=reci.getText();
            subject=sub.getText();
            message=jta.getText();
            
            try
            {
                SendMail sm=new SendMail(user,pass,recipient,subject,message,filepath,filename);
                JOptionPane.showMessageDialog(this,"Your mail is sent Successfully...!!");
                reci.setText("");
                sub.setText("");
                jta.setText("");
                latt.setText("");
                lattach.setText("");
                filepath="";
                filename="";
            }
            catch(Exception ae)
            {
                ae.printStackTrace();
                JOptionPane.showMessageDialog(this,"Internet Connection is lost...!!"+ae,"Error Message",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource()==signout)
        {
            jf.dispose();
        }
        else if(reci.getText().trim().isEmpty() && !sub.getText().trim().isEmpty() && !jta.getText().trim().isEmpty())
        {
            er.setText("* Please enter recipient email address.");
            es.setText("");
            em.setText("");
        }
        else if(reci.getText().trim().isEmpty() && !sub.getText().trim().isEmpty() && jta.getText().trim().isEmpty())
        {
            er.setText("* Please enter recipient email address.");
            es.setText("");
            em.setText("* Please enter message to sent.");
        }
        else if(reci.getText().trim().isEmpty() && sub.getText().trim().isEmpty() && !jta.getText().trim().isEmpty())
        {
            er.setText("* Please enter recipient email address.");
            es.setText("* Please enter subject.");
            em.setText("");
        }
        else if(!reci.getText().trim().isEmpty() && sub.getText().trim().isEmpty() && !jta.getText().trim().isEmpty())
        {
            er.setText("");
            es.setText("* Please enter subject.");
            em.setText("");
        }
        else if(!reci.getText().trim().isEmpty() && sub.getText().trim().isEmpty() && jta.getText().trim().isEmpty())
        {
            er.setText("");
            es.setText("* Please enter subject.");
            em.setText("* Please enter message to sent.");
        }
        else if(!reci.getText().trim().isEmpty() && !sub.getText().trim().isEmpty() && jta.getText().trim().isEmpty())
        {
            er.setText("");
            es.setText("");
            em.setText("* Please enter message to sent.");
        }
        else if(reci.getText().trim().isEmpty() && sub.getText().trim().isEmpty() && jta.getText().trim().isEmpty())
        {
            er.setText("* Please enter recipient email address.");
            es.setText("* Please enter subject.");
            em.setText("* Please enter message to sent.");
        }
        else if(e.getSource()==attach)
        {
            JFileChooser jfc=new JFileChooser();
            jfc.showSaveDialog(this);
            File file=jfc.getSelectedFile();
            
            String path="",fname="";
            if(file!=null)
            {
                path=file.getPath();
                fname=file.getName();
                latt.setText("Attachment  : ");
            }
            filepath=path;
            filename=fname;
            lattach.setText(filename);
            er.setText("");
            es.setText("");
            em.setText("");
        }
    }
}

class google2 extends JPanel
    {
        JLabel g,o,o2,g2,l1,e;
        public google2()
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
        
            g.setFont(new Font("Times New Roman",Font.BOLD,40));
            o.setFont(new Font("Times New Roman",Font.BOLD,40));
            o2.setFont(new Font("Times New Roman",Font.BOLD,40));
            g2.setFont(new Font("Times New Roman",Font.BOLD,40));
            l1.setFont(new Font("Times New Roman",Font.BOLD,40));
            e.setFont(new Font("Times New Roman",Font.BOLD,40));

            add(g);
            add(o);
            add(o2);
            add(g2);
            add(l1);
            add(e);

        }
    }


class HeadPanel extends JPanel
{
    JLabel l;
    public HeadPanel(String title)
    {
        l=new JLabel("Welcome, Your Gmail             "+title);
        add(l);
        l.setFont(new Font("Times New Roman",Font.BOLD,19));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }
}
class FootPanel extends JPanel
{
    JLabel l;
    public FootPanel()
    {
        l=new JLabel("Note : This Application uses Gmail SMTP Server for sending mails.  Thank you..!!");
        add(l);
        l.setFont(new Font("Times New Roman",Font.BOLD,16));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }
}