package Model;

import Model.Vehicules.Vehicule;

import java.util.Iterator;

public abstract class Simulation {

    //Simulation
    public static void start(){
        System.out.println(System.getProperty("line.separator") + "*** Starting simulation ***");
        //Parcours de toutes les villes c de la map
        for(City c : Model.getCities()) {
            System.out.println(System.getProperty("line.separator") + "-> City " + c.getStringId() + " :");
            vehiculeFromCityToRoad(c);
        }
    }

    //Placement des différentes voiture d'une ville au début d'un route SI C'EST POSSIBLE
    public static void vehiculeFromCityToRoad(City c){
        //Affichage de la liste des routes rattahées à la ville c
        Model.printRoads(c);
        //Affichage de la liste des véhicules présents dans la ville c
        c.printVehicules();

        //Parcours de tous les véhicules v de la ville c
        Iterator<Vehicule> iterator = c.getVehicules().iterator();
        while (iterator.hasNext()) {
            Vehicule v = iterator.next();

            //Choix de l'index d'une des routes rattachées à la ville c
            int i = chooseIndexRoad(c);
            if(i != -1) {
                //Enlèvement du véhicule v à la ville c
                iterator.remove();
                //Ajout de la ville de destination au véhicule
                if(Model.getRoads(c).get(i).getCityA().equals(c)) v.setDestination(Model.getRoads(c).get(i).getCityB());
                else v.setDestination(Model.getRoads(c).get(i).getCityA());
                System.out.println("    by the road at the index " + i + " of city " + c.getStringId());
                //Ajout du véhicule v à la route d'index i
                Model.getRoads(c).get(i).add(v);
                Model.getRoads(c).get(i).printVehicules();
                //Affichage de la liste des véhicules restants dans la ville c
                c.printVehicules();
            } else {
                //Cas où aucune route n'est disponible
                System.out.println("No road available for this " + v.getType());
            }

        }
    }

    //Choix de l'index d'une des routes rattachées à la ville c
    public static int chooseIndexRoad(City c){


        //Si une seule route est disponible, vérification de cette route. Sinon choix aléatoire
        if(Model.getRoads(c).size() == 1){
            if(Model.getRoads(c).get(0).isFree(c)) return 0;    //Si la route est libre, renvoi l'index 0
        } else {
            //Choix aléatoire
            int i = (int) (Math.random() * (Model.getRoads(c).size()));
            int j = i - 1;
            if (j < 0) j = Model.getRoads(c).size() - 1;

            //Vérification
            while (i != j) {
                System.out.println("i = " + i + " ; j = " + j);
                if (Model.getRoads(c).get(i).isFree(c)) return i;    //Si la route est libre, renvoi de l'index
                else i = ++i;                                       //Sinon incrémentation de l'index
                if (i >= Model.getRoads(c).size())
                    i = 0;            //Si l'index dépasse la taille, réinitialisation de l'index à 0
            }
        }
        return -1;  //Si aucune route n'est libre, renvoie -1
    }
}
