/**
 * 
 */
package org.eni.encheres.dal.encheres;

import org.eni.encheres.bo.Enchere;
import org.eni.encheres.dal.exceptions.DALException;

/**
 * 
 * Interface regroupant les méthodes permettant d'interagir avec le systeme de
 * persistance sur les objets métiers liés aux Enchères.
 *
 * @author Catherine Créé le: 11 févr.
 */
public interface EnchereDAO {

	/**
	 * 
	 * Méthode permettant d'insérer un enchère dans le systeme de persistance.
	 * 
	 * @param nouvelleEnchere
	 *            Enchere à ajouter en base
	 * @throws DALException
	 *             si un problème survient lors de l'insertion en systeme
	 */
	void create(Enchere nouvelleEnchere) throws DALException;

	/**
	 * 
	 * Méthode permettant de mettre à jour le systeme en fonction de l'objet en parametre
	 * 
	 * @param enchereAModifier enchère à modifier en base
	 * @throws DALException si un problème survient lors de la modification en systeme
	 */
	void update(Enchere enchereAModifier) throws DALException;

}
