package org.gestion;

import static org.gestion.Employe.compareParSalaire;

public class Main {
    public static void main(String[] args) {

        Employe employe1 = new Employe(1, "John Doe", "Developer", 70000);
        Employe employe2 = new Employe(2, "Jane Smith", "Manager", 60000);

        System.out.println(compareParSalaire(employe1 , employe2));


    }
}