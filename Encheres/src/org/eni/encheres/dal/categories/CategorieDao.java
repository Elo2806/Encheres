package org.eni.encheres.dal.categories;

import java.util.Map;

import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.exceptions.DALException;

/**
 * 
 * Interface regroupant les méthodes permettant d'interagir avec le systeme de
 * persistance sur les objets métiers liés aux Articles.
 * 
 * @author Taharqa Créé le: 18 févr. 2021
 * 
 */
public interface CategorieDao {
	
	/**
	 *  
	 * Méthode permettant de récupérer la map de l'ensemble des catégories en fonction de leur identifiant dans le systeme de persistance. 
	 * @return la map de l'ensemble des catégories en fonction de leur identifiant
	 * @throws DALException si un probleme survient lors de la recherche dans le systeme de persistance
	 */
	Map<Integer,Categorie> findAll() throws DALException;

}
