package org.gestion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class GestionEmployes {

    // Constante pour définir le nombre maximum d'employés pouvant être gérés.
    static final int MAX_EMPLOYES = 50;
    // Tableau statique pour stocker les employés. La taille maximale est définie par MAX_EMPLOYES.
    static Employe[] employes = new Employe[MAX_EMPLOYES];
    // Variable statique pour suivre le nombre actuel d'employés dans le tableau.
    private static int nombreEmployes = 0;
    // Scanner statique pour lire les entrées de l'utilisateur depuis la console.
    private static final Scanner scanner = new Scanner(System.in);

    // Affiche le menu principal de l'application de gestion des employés.
    // Propose différentes options à l'utilisateur pour interagir avec le système.
    public static void printMenu() {
        System.out.println("\n--- Menu Gestion des Employés ---");
        System.out.println("1. Ajouter un employé");
        System.out.println("2. Modifier un employé");
        System.out.println("3. Supprimer un employé");
        System.out.println("4. Afficher la liste des employés");
        System.out.println("5. Rechercher un employé");
        System.out.println("6. Calculer la masse salariale");
        System.out.println("7. Trier les employés par salaire");
        System.out.println("0. Quitter");
        System.out.print("Choisissez une option : ");
    }

    // Ajoute un employé au tableau d'employés.
    public static void ajouterEmploye(Employe employe) {
        // Vérifie d'abord s'il y a de la place dans le tableau avant d'ajouter l'employé.
        if (nombreEmployes < MAX_EMPLOYES) {
            employes[nombreEmployes] = employe;
            nombreEmployes++; // Incrémente le compteur du nombre d'employés
            System.out.println("Employé ajouté avec succès.");
        } else {
            System.out.println("Impossible d'ajouter un employé. Le tableau est plein.");
        }
    }

    // Modifie les informations d'un employé existant dans le tableau.
    public static void modifierEmploye(int id, String nouveauNom, String nouveauPoste, double nouveauSalaire) {

        // Recherche l'index de l'employé dans le tableau
        int indexEmploye = trouverEmployeIndex(id);
        if (indexEmploye != -1) {
            if (nouveauNom != null && !nouveauNom.isEmpty()) {
                employes[indexEmploye].setNom(nouveauNom);
            }
            if (nouveauPoste != null && !nouveauPoste.isEmpty()) {
                employes[indexEmploye].setPoste(nouveauPoste);
            }
            if (nouveauSalaire != -1) {
                employes[indexEmploye].setSalaire(nouveauSalaire);
            }
            System.out.println("Employé modifié avec succès.");
        } else {
            System.out.println("Employé non trouvé avec l'ID : " + id);
        }
    }

    // Supprime un employé du tableau en utilisant son ID.
    public static void supprimerEmploye(int id) {
        int indexEmploye = trouverEmployeIndex(id);
        if (indexEmploye != -1) {
            for (int i = indexEmploye; i < nombreEmployes - 1; i++) {
                employes[i] = employes[i + 1]; // Déplace chaque employé suivant d'une position vers la gauche pour supprimer l'employé à l'index 'indexEmploye'
            }
            employes[nombreEmployes - 1] = null; // Pour éviter de garder une référence à l'ancien dernier élément
            nombreEmployes--;
            System.out.println("Employé supprimé avec succès.");
        } else {
            System.out.println("Employé non trouvé avec l'ID : " + id);
        }
    }

    // Affiche la liste de tous les employés actuellement enregistrés.
    public static void afficherEmployes() {
        System.out.println("\n--- Liste des employés ---");
        if (nombreEmployes == 0) {
            System.out.println("Aucun employé à afficher.");
        } else {
            for (int i = 0; i < nombreEmployes; i++) {
                System.out.println(employes[i]);
            }
        }
    }

    // Recherche des employés par nom ou poste.
    public static void rechercherEmploye(String critere) {
        System.out.println("\n--- Recherche employé par critère : " + critere + " ---");
        boolean trouve = false;
        for (int i = 0; i < nombreEmployes; i++) {
            //  // Vérifie si le nom ou le poste contient le critère (en ignorant la casse)
            if (employes[i].getNom().toLowerCase().contains(critere.toLowerCase()) || employes[i].getPoste().toLowerCase().contains(critere.toLowerCase())) {
                System.out.println(employes[i]);
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun employé trouvé correspondant au critère : " + critere);
        }
    }

    // Calcule et affiche la masse salariale totale de tous les employés.
    public static void calculerMasseSalariale() {
        double masseSalariale = 0;
        for (int i = 0; i < nombreEmployes; i++) {
            masseSalariale += employes[i].getSalaire();
        }
        System.out.println("\n--- Masse salariale ---");
        System.out.println("La masse salariale totale est de : " + masseSalariale);
    }


    // Trie les employés par salaire en ordre croissant ou décroissant.
    public static void trierEmployesParSalaire(boolean ordreCroissant) {
        System.out.println("\n--- Trier les employés par salaire ---");

        if (nombreEmployes > 0) {
            // Crée une copie du tableau d'employés pour le tri, en excluant les éléments nulls.
            Employe[] employesTries = Arrays.copyOfRange(employes, 0, nombreEmployes); // Copie pour éviter de trier les nulls
            // Trie le tableau copié en utilisant un comparateur basé sur la méthode compareParSalaire de la classe Employe
            Arrays.sort(employesTries, (e1, e2) -> Employe.compareParSalaire(e1, e2));

            if (!ordreCroissant) {
                // Inverser l'ordre pour le tri décroissant
                for (int i = 0; i < employesTries.length / 2; i++) {
                    Employe temp = employesTries[i];
                    employesTries[i] = employesTries[employesTries.length - 1 - i];
                    employesTries[employesTries.length - 1 - i] = temp;
                }
            }

            System.out.println("\n--- Liste des employés triés par salaire (" + (ordreCroissant ? "croissant" : "décroissant") + ") ---");
            for (Employe employe : employesTries) {
                System.out.println(employe);
            }
        } else {
            System.out.println("Aucun employé à trier.");
        }
    }

    // Recherche l'index d'un employé dans le tableau en utilisant son ID.
    private static int trouverEmployeIndex(int code) {
        for (int i = 0; i < nombreEmployes; i++) {
            if (employes[i].getId() == code) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int choix;
        do {
            printMenu();
            choix = scanner.nextInt(); // Lit le choix de l'utilisateur
            scanner.nextLine(); // Consomme la nouvelle ligne après nextInt() pour éviter des problèmes avec nextLine() suivant

            switch (choix) { // switch pour gérer les différentes options du menu
                case 1: {
                    System.out.println("\n--- Ajouter un employé ---");
                    System.out.print("ID de l'employé : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nom de l'employé : ");
                    String nom = scanner.nextLine();
                    System.out.print("Poste de l'employé : ");
                    String poste = scanner.nextLine();
                    System.out.print("Salaire mensuel de l'employé : ");
                    double salaire = scanner.nextDouble();
                    scanner.nextLine();

                    Employe nouvelEmploye = new Employe(id, nom, poste, salaire);
                    ajouterEmploye(nouvelEmploye);
                    break;
                }
                case 2: {
                    System.out.println("\n--- Modifier un employé ---");
                    System.out.print("Entrez l'ID de l'employé à modifier : ");
                    int idModifier = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nouveau nom (laisser vide pour ne pas modifier) : ");
                    String nouveauNom = scanner.nextLine();
                    System.out.print("Nouveau poste (laisser vide pour ne pas modifier) : ");
                    String nouveauPoste = scanner.nextLine();
                    System.out.print("Nouveau salaire (laisser -1 pour ne pas modifier) : ");
                    double nouveauSalaire = scanner.nextDouble();
                    scanner.nextLine();

                    // Modifie l'employé avec les nouvelles informations
                    modifierEmploye(idModifier, nouveauNom, nouveauPoste, nouveauSalaire);
                    break;
                }
                case 3: {
                    System.out.println("\n--- Supprimer un employé ---");
                    System.out.print("Entrez l'ID de l'employé à supprimer : ");
                    int idSupprimer = scanner.nextInt();
                    scanner.nextLine();
                    supprimerEmploye(idSupprimer); // Supprime l'employé en utilisant son
                    break;
                }
                case 4:
                    afficherEmployes(); // Affiche la liste de tous les employés
                    break;
                case 5: {
                    System.out.println("\n--- Rechercher un employé ---");
                    System.out.print("Entrez un nom ou un poste pour la recherche : ");
                    String critere = scanner.nextLine();
                    rechercherEmploye(critere); // Recherche et affiche les employés correspondant au critère
                    break;
                }
                case 6:
                    calculerMasseSalariale(); // Calcule et affiche la masse salariale totale
                    break;
                case 7: {
                    System.out.println("\n--- Trier les employés par salaire ---");
                    System.out.print("Ordre croissant (true) ou décroissant (false) ? : ");
                    boolean ordreCroissant = scanner.nextBoolean();
                    scanner.nextLine();
                    trierEmployesParSalaire(ordreCroissant); // Trie et affiche les employés par salaire selon l'ordre choisi
                    break;
                }
                case 0:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        } while (choix != 0); // Boucle do-while qui continue tant que l'utilisateur ne choisit pas l'option 0 (quitter)

        scanner.close(); // Ferme le scanner pour libérer les ressources
    }
}
