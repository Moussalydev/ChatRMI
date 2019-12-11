package rmi;

import java.rmi.*;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class Client extends JFrame implements ActionListener
{
 private DataInterface objetDistant;
 private JLabel lid,lnom,lprenom,ltel;
 private JLabel logo;
 private JTextField chid,chnom,chprenom,chtel;
 private JButton aj,qt,aff,creerg,envoyer;
 private JPanel pan1,pan2,pan3;
 public Client(DataInterface objetServeur)
	{
	    objetDistant = objetServeur;
	    logo= new JLabel("");
	    logo.setIcon(new javax.swing.ImageIcon("C:\\Users\\lyaissata\\Desktop\\photos\\dialogue.jpg"));
	    
		lid =new JLabel("Matricule:");
		lnom= new JLabel("Nom:");
		lprenom= new JLabel("Prenom:");
		ltel= new JLabel("Telephone");
		chid= new JTextField();
		chnom=new JTextField();
		chprenom = new JTextField();
		chtel = new JTextField();
		aj = new JButton("Enregistrer");
		qt = new JButton("Quitter");
		aff= new JButton("Afficher");
		creerg = new JButton("Creer groupe");
		envoyer = new JButton("Send a message");
		
		aj.addActionListener(this);
		aff.addActionListener(this);
		qt.addActionListener(this);
		creerg.addActionListener(this);
		envoyer.addActionListener(this);
	
		pan1=new JPanel();
		pan2=new JPanel();
		pan3= new JPanel();
		pan1.setLayout(new GridLayout(4,2));
		
		pan3.add(logo);
		pan1.add(lid);
		pan1.add(chid);
		pan1.add(lnom);
		pan1.add(chnom);
		pan1.add(lprenom);
		pan1.add(chprenom);
		pan1.add(ltel);
		pan1.add(chtel);
		pan2.add(aj);
		pan2.add(aff);
		pan2.add(qt);
		pan2.add(creerg);
		pan2.add(envoyer);
		add(pan3,BorderLayout.NORTH);
		add(pan1,BorderLayout.CENTER);
		add(pan2,BorderLayout.SOUTH);
		setTitle("Enregistrer un Abonné");
		setSize(650,400);
   	    setLocationRelativeTo(null);
		setVisible(true);
  }

   public void actionPerformed(ActionEvent e)
   {
	   if (e.getSource()==aj)
		{
		   
			    Personne p; 
			    String sid=chid.getText();
	    		int id = Integer.parseInt(sid);
	    		String nom=chnom.getText();
	    		String prenom=chprenom.getText();
	    		String tel=chtel.getText();
	    	    p = new Personne();
	    		p.setId(id);
	    		p.setNom(nom);
	    		p.setPrenom(prenom);
	    		p.setTel(tel);
	    		try 
	    		{
	    			
				objetDistant.ajouter(p);
				chid.setText("");
				chnom.setText("");
				chprenom.setText("");
				chtel.setText("");
				}
	    		catch (RemoteException ex)
	    		{
				 	System.out.println(ex.getMessage());
				}
		}
	   else
		   if (e.getSource()==aff)
			{
				try
				{
					
			         ArrayList<Personne> liste=objetDistant.afficher();
			         dispose();
			         new ListePersonnes(liste,objetDistant);
			        
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
			

           }
		   else
				if (e.getSource()==qt)
				{
			       dispose();
			       System.exit(0);
   
                 }
				else if(e.getSource()==creerg){
					new Mongroupe(objetDistant).setVisible(true);
					
				}
				else if(e.getSource()==envoyer){
					new Fenetre_Message(objetDistant).setVisible(true);
					
				}
 }
      
public static void main(String args[]) throws Exception
 {
	  
	   try
	   {
		   //!!!mettre l'adresse IP du serveur à la place localhost 
		   DataInterface objetServeur = (DataInterface) Naming.lookup("rmi://localhost:1099/BK");   
	       new Client(objetServeur);
	   }
	   catch (Exception ex)
	   {
		   System.out.println("impossible de se connecter");
	       System.out.println(ex.getMessage());	   
	   }
        
   }
}