package rmi;

import java.io.*;

public class Personne implements Serializable
{
   private int id;
	

	private String nom;
	private String prenom;
	private String tel;
	

public int getId()
{
return id;
}

public String getNom()
{
return nom;
}

public String getPrenom()
{
return prenom;
}

public String getTel()
{
return tel;
}

public void setId(int id)
{
  this.id=id;
}

public void setNom(String nom)
{
this.nom=nom;
}

public void setPrenom(String prenom)
{
this.prenom=prenom;
}

public void setTel(String tel)
{
this.tel=tel;
}
}