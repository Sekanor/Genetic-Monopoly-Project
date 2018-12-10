package monopoly.modele;

import monopoly.controleur.ControleurDeplacementPion;
import monopoly.modele.cases.Case;
import monopoly.modele.cases.ECase;

import java.util.Objects;

/**
 * Représente le pion du joueur, donc sa position sur la map.
 */
public class Pion {
    /**
     * Case sur laquelle est situé le pion.
     */
    private Case position;

    /**
     * Nom du pion.
     */
    private String nom;

    /**
     * Joueur à qui le pion appartient.
     */
    private Joueur joueur;

    /**
     * Permet de savoir si on vient de passer par la case départ.
     */
    private boolean caseDepartLast;

    /**
     * Contrôleur gérant le déplacement du pion.
     */
    private ControleurDeplacementPion controleurDeplacement;

    /**
     * Permet de créer un nouveau pion
     * @param position Position de base du pion.
     * @param nom Nom du pion.
     */
    public Pion(Case position, String nom) {
        this.position = position;
        this.nom = nom;
        caseDepartLast = false;
    }

    /**
     * Permet de créer un nouveau pion, par défaut, sur la case départ.
     * @param nom Nom du pion.
     */
    public Pion(String nom) {
        this.nom = nom;
        this.position = Jeu.getInstance().getPlateau().getCaseDepart();
    }

    /**
     * Permet de déplacer le pion d'un certain nombre de cases.
     * @param nbCases Nombre de cases dont on veut avancer.
     */
    public void deplacer(int nbCases) {
        System.out.println(this);
        caseDepartLast = false;
        for(int i = 0; i < nbCases; ++i) {
            avancerUneCase(i+1 < nbCases);
        }

        position.action(getJoueur());
        this.controleurDeplacement.deplacer();
    }

    /**
     * Permet de déplacer le pion jusqu'à une case
     * @param objectif Nom de la case (ATTENTION : Doit exister sur le plateau, sinon boucle infinie).
     */
    public void deplacer(String objectif) {
        caseDepartLast = false;
        while(!position.getNom().equals(objectif)) {
            avancerUneCase(!objectif.equals("Case départ"));
        }

        position.action(getJoueur());
        this.controleurDeplacement.deplacer();
    }

    /**
     * Permet de reculer d'un certain nombre de cases.
     * @param nbCases Nombre de cases dont on veut reculer.
     */
    public void reculer(int nbCases) {
        caseDepartLast = false;
        for(int i = 0; i < nbCases; ++i) {
            reculerUneCase(false);
        }

        position.action(getJoueur());
        this.controleurDeplacement.deplacer();
    }

    /**
     * Permet de reculer jusqu'à une case.
     * @param objectif Nom de la case (ATTENTION : doit exister sur le plateau, sinon boucle infinie).
     */
    public void reculer(String objectif) {
        caseDepartLast = false;
        while(!position.getNom().equals(objectif)) {
            reculerUneCase(false);
        }

        position.action(getJoueur());
        this.controleurDeplacement.deplacer();
    }

    /**
     * Permet d'avancer d'une case, sans déclencher son effet (sauf sur la case départ)
     * @param declencheDepart Permet de savoir si on déclenche la case départ si l'on passe dessus
     */
    private void avancerUneCase(boolean declencheDepart) {
        position = position.getSuivante();
        if(declencheDepart && position.getType() == ECase.Depart) {
            position.action(getJoueur());
        }
        this.controleurDeplacement.deplacer();
    }

    /**
     * Permet de reculer d'une case, sans déclencher son effet (sauf sur la case départ)
     * @param declencheDepart Permet de savoir si on doit déclencher la case départ.
     */
    private void reculerUneCase(boolean declencheDepart) {
        position = position.getPrecedente();
        if(declencheDepart && position.getType() == ECase.Depart) {
            position.action(getJoueur());
        }
        this.controleurDeplacement.deplacer();
    }

    /**
     * Permet de récupérer la case sur laquelle est situé le pion.
     * @return La case sur laquelle le pion se situe.
     */
    public Case getPosition() {
        return position;
    }

    /**
     * Permet de mettre le pion sur une case définie.
     * @param position Case sur laquelle on souhaite poser le pion.
     */
    public void setPosition(Case position) {
        this.position = position;
        this.controleurDeplacement.deplacer();
    }

    /**
     * Permet de savoir si le dernier déplacement est passé par la case départ (pour l'affichage)
     * @return Vrai si on est passé par la case départ.
     */
    public boolean isCaseDepartLast() {
        return caseDepartLast;
    }

    /**
     * Permet d'indiquer qu'on vient de passer par la case départ.
     */
    public void passeCaseDepart() {
        caseDepartLast = true;
    }

    /**
     * Permet de récupérer le nom du pion.
     * @return Nom du pion.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de récupérer le joueur associé au pion.
     * @return Joueur associé.
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Permet de changer le joueur associé au pion.
     * @param joueur Joueur nouvellement associé.
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    /**
     * Getter du contrôleur de déplacement.
     * @return Contrôleur de déplacement.
     */
    public ControleurDeplacementPion getControleur() {
        return controleurDeplacement;
    }

    /**
     * Setter du contrôleur de déplacement.
     * @param controleurDeplacement Contrôleur de déplacement.
     */
    public void setControleur(ControleurDeplacementPion controleurDeplacement) {
        this.controleurDeplacement = controleurDeplacement;
    }

    @Override
    public String toString(){
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pion pion = (Pion) o;
        return caseDepartLast == pion.caseDepartLast &&
                Objects.equals(position, pion.position) &&
                Objects.equals(nom, pion.nom) &&
                Objects.equals(joueur, pion.joueur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, nom, joueur, caseDepartLast);
    }
}
