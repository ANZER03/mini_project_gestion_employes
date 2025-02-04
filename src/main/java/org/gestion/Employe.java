package org.gestion;

public class Employe {
    private int id;
    private String nom;
    private String poste;
    private double salaire;

    // constrecteur vide
    public Employe() {

    }

    // constrecteur avec les arguments
    public Employe(int id, String nom, String poste, double salaire) {
        this.id = id;
        this.nom = nom;
        this.poste = poste;
        this.salaire = salaire;
    }

    // Getters & Setters

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPoste() {
        return poste;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    // re-implementation de methode toString
    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", poste='" + poste + '\'' +
                ", salaire=" + salaire +
                '}';
    }

    // comparer par deux employes par salaire, c'est emp1.salaire > em2.salaire donc la fonction return 1, sinon -1
    public static int compareParSalaire(Employe emp1, Employe emp2) {
        return Double.compare(emp1.getSalaire(), emp2.getSalaire());
    }

}
