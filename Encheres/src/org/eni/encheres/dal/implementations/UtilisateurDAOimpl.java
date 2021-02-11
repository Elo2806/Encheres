package org.eni.encheres.dal.implementations;

import java.sql.PreparedStatement;

import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.interfaces.UtilisateurDAO;

public class UtilisateurDAOimpl implements UtilisateurDAO {

	@Override
	public Utilisateur create(Utilisateur newUtilisateur) {
		Utilisateur updatedUtilisateur;
		PreparedStatement prepare;
		

		"Insert into Utilisateurs (no_utilisateur,pseudo,nom,prenom,email,telephone,rue,co) "
		
		
		updatedUtilisateur = newUtilisateur;
		
		return updatedUtilisateur;
	}

}
