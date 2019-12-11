package rmi;

import java.rmi.*;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Fenetre_Message extends JFrame implements ActionListener
{
 private DataInterface objetDistant;
 private JLabel lmessage;
 private JTextField chmess;
 
 private JLabel lgroupe,logo;
 
 private JTextField chgroupe;
 
 private JLabel lmatricule;
 private JTextField chmatricule;
 
 private JButton poster,regarder;
 private JPanel pan1,pan2,pan3;
 public Fenetre_Message(DataInterface objetDistant)
	{
	    this.objetDistant = objetDistant;
	    logo= new JLabel("");
	    logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\lyaissata\\Desktop\\photos\\dialogue.jpg"));
	  
		lmessage =new JLabel("Message:");
		chmess= new JTextField(30);
		
		lgroupe =new JLabel("Groupe:");
		chgroupe= new JTextField(15);
		
		lmatricule =new JLabel("Matricule:");
		chmatricule= new JTextField(15);
		
		
		poster = new JButton("Poster");
		regarder = new JButton("Voire messages");
		
		poster.addActionListener(this);
		regarder.addActionListener(this);
		
	    
		pan1=new JPanel();
		
		pan2=new JPanel();
		pan3 = new JPanel();
		pan1.setLayout(new GridLayout(5,5));
		pan3.add(logo);
		
		pan1.add(lmatricule);
		pan1.add(chmatricule);
		pan1.add(lgroupe);
		pan1.add(chgroupe);
		pan1.add(lmessage);
		pan1.add(chmess);
		
		
		pan2.add(poster);
		pan2.add(regarder);
		
		add(pan3,BorderLayout.NORTH);
		add(pan1,BorderLayout.CENTER);
		add(pan2,BorderLayout.SOUTH);
		setTitle("Diffusion de message");
		setSize(500,500);
   	    setLocationRelativeTo(null);
   	    
		setVisible(true);
		
		
  }

   public void actionPerformed(ActionEvent e)
   {
	   if (e.getSource()==poster)
		{
		   
			    Messageg m; 
			    String smessage=chmess.getText();
			    String sgroupe =chgroupe.getText();
			    String smatricule = chmatricule.getText();
			    int matricule = Integer.parseInt(smatricule);
	    		
	    	    m = new Messageg();
	    		m.setNomgroupe(sgroupe);
	    		m.setIntitule(smessage);
	    		m.setIdutilisateur(matricule);
	    		
	    		try 
	    		{
	    			
				objetDistant.PosterMessage(m);
				chgroupe.setText("");
				chmess.setText("");
				chmatricule.setText("");
				}
	    		catch (Exception ex)
	    		{
				 	System.out.println(ex.getMessage());
				}
		    }
	   
	  				
       }
   
      

}