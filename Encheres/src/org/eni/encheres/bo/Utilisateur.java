package org.eni.encheres.bo;

/**
 * Classe modélisant un utilisateur
 * @author ElCaTar
 *
 */
public class Utilisateur {

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
	private boolean administrateur;
	private boolean actif;
	
	// Constructeurs
	/**
	 * Constructeur vide par défaut
	 */
	public Utilisateur() {
		actif = true;
	}
	
	/**
	 * Constructeur
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
		this();
		setPseudo(pseudo);
		setNom(prenom);
		setPrenom(prenom);
		setEmail(email);
		setTelephone(telephone);
		setRue(rue);
		setCodePostal(codePostal);
		setVille(ville);
		setMotDePasse(motDePasse);
		setCredit(credit);
		setAdministrateur(administrateur);
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
	private void setNoUtilisateur(Integer noUtilisateur) {
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
	
	
	
	
}
