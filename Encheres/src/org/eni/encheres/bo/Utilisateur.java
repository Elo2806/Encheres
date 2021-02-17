package org.eni.encheres.bo;

import java.io.Serializable;
import java.util.Map;

/**
 * Classe modélisant un utilisateur
 * @author ElCaTar
 *
 */
public class Utilisateur implements Serializable {

	private Integer noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String motDePasse;
	private Integer credit;
	private Boolean administrateur;
	private Boolean actif;
	private Boolean supprime;
	private Map<Integer,Enchere> mapEncheres;
	
	// Constructeurs
	/**
	 * Constructeur vide par défaut
	 */
	public Utilisateur() {
		credit = 0;
		administrateur = false;
		actif = true;
		supprime = false;
	}
	
	/**
	 * 
	 * Constructeur pour la saisie utilisateur (création)
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) {
		this();
		setPseudo(pseudo);
		setNom(nom);
		setPrenom(prenom);
		setEmail(email);
		setTelephone(telephone);
		setRue(rue);
		setCodePostal(codePostal);
		setVille(ville);
		setMotDePasse(motDePasse);
	}
	
	/**
	 * 
	 * Constructeur pour la saisie utilisateur (modification)
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, Integer noUtilisateur) {
		this(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
		setNoUtilisateur(noUtilisateur);
	}
	
	/**
	 * Constructeur initial
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 * @param credit
	 * @param administrateur
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, Integer credit, boolean administrateur) {
		this(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
		setCredit(credit);
		setAdministrateur(administrateur);
	}
	
	/**
	 * 
	 * Constructeur de l'utilisateur a la sortie du systeme de persistance
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 * @param credit
	 * @param administrateur
	 * @param noUtilisateur
	 * @param actif
	 */
	public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, Integer credit, boolean administrateur, int noUtilisateur, boolean actif ) {
		this(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
		setNoUtilisateur(noUtilisateur);
		setActif(actif);
	}

	


	// Getters et Setters
	/**
	 * Méthode permettant de récupérer noUtilisateur
	 * @return le noUtilisateur
	 */
	public Integer getNoUtilisateur() {
		return noUtilisateur;
	}
	/**
	 * Méthode permettant de modifier noUtilisateur
	 * @param noUtilisateur le new noUtilisateur
	 */
	public void setNoUtilisateur(Integer noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	/**
	 * Méthode permettant de récupérer pseudo
	 * @return le pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	/**
	 * Méthode permettant de modifier pseudo
	 * @param pseudo le new pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	/**
	 * Méthode permettant de récupérer nom
	 * @return le nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Méthode permettant de modifier nom
	 * @param nom le new nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * Méthode permettant de récupérer prenom
	 * @return le prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * Méthode permettant de modifier prenom
	 * @param prenom le new prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * Méthode permettant de récupérer email
	 * @return le email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Méthode permettant de modifier email
	 * @param email le new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Méthode permettant de récupérer telephone
	 * @return le telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * Méthode permettant de modifier telephone
	 * @param telephone le new telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * Méthode permettant de récupérer rue
	 * @return le rue
	 */
	public String getRue() {
		return rue;
	}
	/**
	 * Méthode permettant de modifier rue
	 * @param rue le new rue
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}
	/**
	 * Méthode permettant de récupérer codePostal
	 * @return le codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}
	/**
	 * Méthode permettant de modifier codePostal
	 * @param codePostal le new codePostal
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	/**
	 * Méthode permettant de récupérer ville
	 * @return le ville
	 */
	public String getVille() {
		return ville;
	}
	/**
	 * Méthode permettant de modifier ville
	 * @param ville le new ville
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	/**
	 * Méthode permettant de récupérer motDePasse
	 * @return le motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}
	/**
	 * Méthode permettant de modifier motDePasse
	 * @param motDePasse le new motDePasse
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	/**
	 * Méthode permettant de récupérer credit
	 * @return le credit
	 */
	public Integer getCredit() {
		return credit;
	}
	/**
	 * Méthode permettant de modifier credit
	 * @param credit le new credit
	 */
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	/**
	 * Méthode permettant de récupérer administrateur
	 * @return le administrateur
	 */
	public boolean isAdministrateur() {
		return administrateur;
	}
	/**
	 * Méthode permettant de modifier administrateur
	 * @param administrateur le new administrateur
	 */
	private void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	/**
	 * Méthode permettant de récupérer actif
	 * @return le actif
	 */
	public boolean isActif() {
		return actif;
	}

	/**
	 * Méthode permettant de modifier actif
	 * @param actif le new actif
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
	}

	/**
	 * Méthode permettant de récupérer supprime
	 * @return le supprime
	 */
	public boolean isSupprime() {
		return supprime;
	}

	/**
	 * Méthode permettant de modifier supprime
	 * @param supprime le new supprime
	 */
	public void setSupprime(boolean supprime) {
		this.supprime = supprime;
	}

	/**
	 * Méthode permettant de récupérer mapEncheres
	 * @return le mapEncheres
	 */
	public Map<Integer, Enchere> getMapEncheres() {
		return mapEncheres;
	}

	/**
	 * Méthode permettant de modifier mapEncheres
	 * @param mapEncheres new mapEncheres
	 */
	public void setMapEncheres(Map<Integer, Enchere> mapEncheres) {
		this.mapEncheres = mapEncheres;
	}

	
}
