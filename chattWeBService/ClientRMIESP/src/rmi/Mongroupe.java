package rmi;

import java.rmi.*;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Mongroupe extends JFrame implements ActionListener
{
 private DataInterface objetDistant;
 private JLabel lnomgroupe;
 private JTextField chnomgroupe;
 private JButton poster;
 private JPanel pan1,pan2;
 public Mongroupe(DataInterface objetDistant)
	{
	    this.objetDistant = objetDistant;
		lnomgroupe =new JLabel("Nom du groupe:");
		chnomgroupe= new JTextField(10);
		
		
		poster = new JButton("Poster");
		
		poster.addActionListener(this);
		
	    
		pan1=new JPanel();
		pan2=new JPanel();
		pan1.setLayout(new FlowLayout());
		pan1.add(lnomgroupe);
	
		pan1.add(chnomgroupe);
		
		
		pan2.add(poster);
		
		
		add(pan1,BorderLayout.CENTER);
		add(pan2,BorderLayout.SOUTH);
		setTitle("Création d'un groupe");
		setSize(500,200);
   	    setLocationRelativeTo(null);
   	    
		setVisible(true);
		
		
  }

   public void actionPerformed(ActionEvent e)
   {
	   if (e.getSource()==poster)
		{
		   
			    Groupe g; 
			    String sgroupe=chnomgroupe.getText();
	    		
	    	    g = new Groupe();
	    		g.setNomgroupe(sgroupe);
	    		
	    		try 
	    		{
	    			
				objetDistant.CreerGroupe(g);
				chnomgroupe.setText("");
				}
	    		catch (Exception ex)
	    		{
				 	System.out.println(ex.getMessage());
				}
		    }
	  				
       }
   
      

}