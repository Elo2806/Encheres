/**
 * 
 */
package org.eni.encheres.bll;

import java.time.LocalDate;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.encheres.EnchereDAO;

/**
 * @author Catherine
 * Créé le: 11 févr. 2021
 * Modifié le: 11 févr. 2021
 */
public class EnchereManager {
 private static EnchereManager instance;
 private EnchereDAO enchereDao;
 
 
/**
 *Construteur
 */
private EnchereManager() {
	enchereDao = DAOFactory.getEnchereDAO();
}
 
 public static EnchereManager getInstance() {
		if(instance==null) {
			instance =  new EnchereManager();
		}
		
		return instance;
	}
 
 public void encherir(LocalDate dateEnchere, Integer montantEnchere, Utilisateur utilisateur, ArticleVendu article) {
 	//appeler la DAL
}

 public Enchere creerEnchere() {}