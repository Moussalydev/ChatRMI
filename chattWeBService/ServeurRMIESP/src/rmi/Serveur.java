package rmi;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.naming.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



public class Serveur extends JFrame implements ActionListener
{
   private JPanel p1,p2;	
   private JTextArea zone;
   private JButton bquit;

   public Serveur()
	{
		
		p1 = new JPanel();
		p2 = new JPanel();
		zone = new JTextArea(8,40);
		bquit = new JButton("Quitter");
		bquit.addActionListener(this);
		p1.add(zone);
		add(p1,BorderLayout.CENTER);
		p2.add(bquit);
		add(p2,BorderLayout.SOUTH);
		setTitle("Serveur RMI");
		zone.setText("Le serveur RMI a demarre");
		setSize(500,300);
		this.setLocationRelativeTo(null);
		setVisible (true);
		try
		{
			ServiceRMI s = new ServiceRMI();	
			LocateRegistry.createRegistry(1099);
			Naming.rebind("rmi://localhost/BK",s);
	    } 
		catch (Exception e)
		{
	       System.out.println(e.getMessage());
		
	    }
		
	}
	
// classe interne ServiceRMI
	class ServiceRMI extends UnicastRemoteObject implements DataInterface
	{
		private Connection con=null;
		private PreparedStatement st=null;
		private ResultSet rs=null; 
	
		public ServiceRMI() throws RemoteException
		{
			try
		    {
		       Class.forName("com.mysql.jdbc.Driver");
		 	   con=DriverManager.getConnection("jdbc:mysql://localhost:3306/chatdb","root","");
		    
		    }
		    catch(Exception e)
		    {
		      System.out.println(e.getMessage());
		    }

				
		}
					
		public void ajouter(Personne p)
		{
			try
			 {
				st = con.prepareStatement("insert into utilisateur values(?,?,?,?)");
				st.setInt(1,p.getId());
				st.setString(2,p.getNom());
				st.setString(3,p.getPrenom());
				st.setString(4,p.getTel());
				st.executeUpdate();
		  }

		  catch(Exception e)
		  {

		    System.out.println(e.getMessage());
		  }

	    }
		
		public ArrayList <Personne> afficher()
		{
			 ArrayList<Personne> liste= new ArrayList<Personne>();
		     try
		     {
		       st=con.prepareStatement("select * from utilisateur");
		       rs=st.executeQuery();
		       while(rs.next())
		         {
		           Personne p = new Personne();
		           p.setId(rs.getInt("matricule"));
		           p.setNom(rs.getString("nom"));
		           p.setPrenom(rs.getString("prenom"));
		           p.setTel(rs.getString("tel"));  
		           liste.add(p); 
		         }  
		     }
		     catch(Exception e)
		     {
		        System.out.println(e.getMessage());
		     }
		    return liste;

		}

		
		@Override
		public void CreerGroupe(Groupe g) throws RemoteException {
			
			try
			 {
				st = con.prepareStatement("insert into groupe values(?)");
				st.setString(1,g.getNomgroupe());
				st.executeUpdate();
		  }

		  catch(Exception e)
		  {

		    System.out.println(e.getMessage());
		  }
			
			
		}

		@Override
		public void PosterMessage(Messageg m) throws RemoteException {
			
			try
			 {
				st = con.prepareStatement("insert into messagegroupe values(?,?,?)");
				st.setString(1,m.getIntitule());
				st.setString(2,m.getNomgroupe());
				st.setInt(3,m.getIdutilisateur());
				st.executeUpdate();
		   }

		  catch(Exception e)
		  {

		    System.out.println(e.getMessage());
		  }
					
			
		}
		
	}
	 
	//fin classe interne
	
	public void actionPerformed (ActionEvent e)
	{
		dispose();
		System.exit(0);
	}
	
	public static void main(String args[])
	{
		new Serveur();
	}
	
}
