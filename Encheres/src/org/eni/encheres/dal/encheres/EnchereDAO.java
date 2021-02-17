/**
 * 
 */
package org.eni.encheres.dal.encheres;

import org.eni.encheres.bo.Enchere;
import org.eni.encheres.dal.exceptions.DALException;

/**
 * 
 * Cette classe permet de centraliser les méthodes d'intéractions avec le
 * système de persistance
 *
 * @author Catherine Créé le: 11 févr. 2021 Modifié le: 11 févr. 2021
 */
public interface EnchereDAO {

	/**
	 * 
	 * Méthode permettant de créer une nouvelle enchère en base de données et de la
	 * mettre à jour
	 * 
	 * @param nouvelleEnchere
	 *            Enchere à ajouter en base
	 * @throws DALException
	 *             si un problème survient lors de l'insertion en base
	 */
	void create(Enchere nouvelleEnchere) throws DALException;

}
