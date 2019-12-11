package rmi;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;

import javax.swing.table.DefaultTableModel;

public class ListePersonnes extends JFrame implements ActionListener
{
	private JTable table;
	private ArrayList<Personne> liste;
	private JScrollPane sc;
	private JPanel panneau1,panneau2;
	private JButton qt;
	private DataInterface objetDistant;
	
	public ListePersonnes(ArrayList <Personne> liste,DataInterface objetDistant)
	{
	
		this.objetDistant=objetDistant;
		panneau1=new JPanel();
		panneau2=new JPanel();
		qt = new JButton("Quitter");
		 this.liste=liste;
		  sc  = new JScrollPane();
		  table = new JTable();
		  sc.setViewportView(table);
		  DefaultTableModel modele = (DefaultTableModel)table.getModel();
		  modele.addColumn("Numero Personne");
		  modele.addColumn("Nom");
		  modele.addColumn("Prenom");
		  modele.addColumn("Telephone");
		  		
		  int ligne=0;
		  for (Personne emp : liste)
		  {
			  modele.addRow( new Object[0]);
			  modele.setValueAt(String.valueOf(emp.getId()),ligne,0);
			  modele.setValueAt(emp.getNom(), ligne, 1);
			  modele.setValueAt(emp.getPrenom(),ligne,2);
			  modele.setValueAt(emp.getTel(),ligne, 3);
			  ligne++;
		  }
		 
		  setTitle("Client RMI:liste des personnes");
		  setSize(550,500);
		  qt.addActionListener(this); 
		  panneau1.add(sc);
		  panneau2.add(qt);
		  add(panneau1,BorderLayout.NORTH);
		  add(panneau2,BorderLayout.SOUTH);
		  setLocationRelativeTo(null);
		  setVisible(true);
	}
    public void actionPerformed(ActionEvent e)
    {
    	if (e.getSource()==qt)
    	{
    		dispose();
    		new Client(objetDistant);
    	}
    }
}
