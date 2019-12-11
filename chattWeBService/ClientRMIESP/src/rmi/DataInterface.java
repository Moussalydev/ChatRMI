package rmi;

import java.rmi.*;
import java.util.*;
public interface DataInterface extends Remote 
{

 public void ajouter(Personne p) throws RemoteException;
 public ArrayList <Personne> afficher() throws RemoteException;
 public void CreerGroupe(Groupe g) throws RemoteException;
 public void PosterMessage(Messageg m) throws RemoteException;
 
 

}