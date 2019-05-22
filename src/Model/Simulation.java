package Model;

import Model.Roads.Road;
import Model.Vehicules.Vehicule;
import javafx.util.Pair;

import java.util.Iterator;

public abstract class Simulation {

    //Simulation
    public static void start(){
        System.out.println(System.getProperty("line.separator") + "*** Starting simulation ***");
        System.out.println(System.getProperty("line.separator") + "*~ init from cities ~*");
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
            Pair<Integer, Integer> paire = chooseIndexRoad(c, v);
            int i = paire.getKey();
            if(i != -1) {
                //Enlèvement du véhicule v à la ville c
                iterator.remove();
                //Ajout de la ville de destination au véhicule
                if(Model.getRoads(c).get(i).getCityA().equals(c)) v.setDestination(Model.getRoads(c).get(i).getCityB());
                else v.setDestination(Model.getRoads(c).get(i).getCityA());
                System.out.println("    by the " + Model.getRoads(c).get(i).getName() + " at the index " + i + " of city " + c.getStringId());
                //Ajout du véhicule v à la route d'index i
                Model.getRoads(c).get(i).add(v);
                Model.getRoads(c).get(i).printVehicules();
                //Initialisation de la voie du véhicule
                v.setWay(paire.getValue());
                //Affichage de la liste des véhicules restants dans la ville c
                c.printVehicules();
            } else {
                //Cas où aucune route n'est disponible
                System.out.println("No road available for this " + v.getType());
            }

        }
    }

    //Choix de l'index d'une des routes rattachées à la ville c
    public static Pair<Integer, Integer> chooseIndexRoad(City c, Vehicule v){

        Pair<Boolean, Integer> paire;   //La paire va prendre comme première valeur un boolean indiquant si la route est libre
        //Et comme deuxième valeur la voie disponible

        for(Road r : Model.getRoads(c)){
            paire = r.isFree(c.getX(), c.getY(), v);
            if(paire.getKey() && !(v.getDestination().equals(c))) return new Pair<>(Model.getRoads().indexOf(r), paire.getValue());    //Si la route est libre, renvoi l'index 0
        }

        return new Pair<>(-1, -1);  //Si aucune route n'est libre, renvoie -1

        /*
        //Si une seule route est disponible, vérification de cette route. Sinon choix aléatoire
        if(Model.getRoads(c).size() == 1){
            paire = Model.getRoads(c).get(0).isFree(c.getX(), c.getY(), v);
            if(paire.getKey()) return new Pair<>(0, paire.getValue());    //Si la route est libre, renvoi l'index 0
        } else {
            //Choix aléatoire
            int i = (int) (Math.random() * (Model.getRoads(c).size()));
            int j = i - 1;
            if (j < 0) j = Model.getRoads(c).size() - 1;

            //Vérification
            while (i != j) {
                paire = Model.getRoads(c).get(i).isFree(c.getX(), c.getY(), v);
                if (paire.getKey()) return new Pair<>(i, paire.getValue());    //Si la route est libre, renvoi de l'index
                else i = ++i;                    //Sinon incrémentation de l'index
                if (i >= Model.getRoads(c).size())
                    i = 0;            //Si l'index dépasse la taille, réinitialisation de l'index à 0
            }
        }

        return new Pair<>(-1, -1);  //Si aucune route n'est libre, renvoie -1*/

    }

    public static void step(){

        int acc = 10;   //Coefficient d'accélération

        System.out.println(System.getProperty("line.separator") + "*~ step ~*");

        //Parcours de toutes les routes r de la map
        for(Road r : Model.getRoads()) {
            //Affichage console
            System.out.println(System.getProperty("line.separator") + "-> " + r.getName() + " (" + r.getNbWay() +
                    " ways) between city " + r.getCityA().getStringId() + " and " + r.getCityB().getStringId() + " :");
            r.printVehicules();

            double distanceRoute = Math.sqrt(Math.pow((r.getCityB().getX() - r.getCityA().getX()), 2) + Math.pow((r.getCityB().getY() - r.getCityA().getY()), 2));
            double cote;
            double angle;

            //Affichage de la vitesse
            System.out.println("Speed limit on " + r.getName() + " : " + r.getSpeedLimit() + " km/h");

            //Augmentation de la vitesse pour chaque véhicule v
            Iterator<Vehicule> iterator = r.getVehicules().iterator();
            while (iterator.hasNext()) {
                Vehicule v = iterator.next();

                if((v.getCurrentSpeed() + acc) <= v.getMaxSpeed() && (v.getCurrentSpeed() + acc) <= r.getSpeedLimit()) v.setCurrentSpeed(v.getCurrentSpeed() + acc);
                else v.setCurrentSpeed(r.getSpeedLimit());

                double ajoutX = 0;
                double ajoutY = 0;

                if(r.getCityB().getY() > r.getCityA().getY()){
                    cote = Math.abs(r.getCityB().getX() - r.getCityA().getX());
                    angle = Math.asin(cote/distanceRoute);
                    if(r.getCityB().getX() > r.getCityA().getX()){
                        ajoutX = v.getCurrentSpeed()*Math.sin(angle);
                        ajoutY = v.getCurrentSpeed()*Math.cos(angle);
                        if(v.getX()+ajoutX > v.getDestination().getX()) ajoutX = v.getDestination().getX()-v.getX();
                        if(v.getY()+ajoutY > v.getDestination().getY()) ajoutY = v.getDestination().getY()-v.getY();
                    } else {
                        ajoutX = - v.getCurrentSpeed()*Math.sin(angle);
                        ajoutY = v.getCurrentSpeed()*Math.cos(angle);
                        if(v.getX()+ajoutX < v.getDestination().getX()) ajoutX = v.getDestination().getX()-v.getX();
                        if(v.getY()+ajoutY > v.getDestination().getY()) ajoutY = v.getDestination().getY()-v.getY();
                    }
                } else {
                    cote = Math.abs(r.getCityA().getY() - r.getCityB().getY());
                    angle = Math.asin(cote/distanceRoute);
                    if(r.getCityB().getX() > r.getCityA().getX()){
                        ajoutY = - v.getCurrentSpeed()*Math.sin(angle);
                        ajoutX = v.getCurrentSpeed()*Math.cos(angle);
                        if(v.getX()+ajoutX > v.getDestination().getX()) ajoutX = v.getDestination().getX()-v.getX();
                        if(v.getY()+ajoutY < v.getDestination().getY()) ajoutY = v.getDestination().getY()-v.getY();
                    } else {
                        ajoutY = - v.getCurrentSpeed()*Math.sin(angle);
                        ajoutX = - v.getCurrentSpeed()*Math.cos(angle);
                        if(v.getX()+ajoutX < v.getDestination().getX()) ajoutX = v.getDestination().getX()-v.getX();
                        if(v.getY()+ajoutY < v.getDestination().getY()) ajoutY = v.getDestination().getY()-v.getY();
                    }
                }

                ajoutX = ajoutX/2;
                ajoutY = ajoutY/2;


                if(v.getDestination().getX() == (v.getX()+ajoutX) && v.getDestination().getY() == (v.getY()+ajoutY)){
                    v.getDestination().add(v);
                    iterator.remove();
                    System.out.println(v.getType() + " arrived in city " + v.getDestination().getStringId());
                } else {
                    v.updateVehicule(v.getX() + ajoutX, v.getY() + ajoutY, r);
                }
            }
        }
    }

    public static boolean isFinish(){
        System.out.println(System.getProperty("line.separator") + "*** ? Finishing Simulation ? ***");

        for(Road r : Model.getRoads()){
            if(!(r.getVehicules().isEmpty())){
                System.out.println("Some vehicles are still on roads");
                return false;
            }
            System.out.println("No more vehicles are rolling on " + r.getName() + " between city " + r.getCityA().getStringId() + "and city " + r.getCityB().getStringId());
        }

        for(City c : Model.getCities()){
            if(!(c.getVehicules().isEmpty())){
                for(Vehicule v : c.getVehicules()){
                    if(!(v.getDestination().equals(c))) return false;
                    System.out.println(v.getType() + " is at its destination in city " + c.getStringId());
                }
            }
        }
        System.out.println("Every vehicles are at their destination");
        System.out.println("Shutdown simulation...");
        return true;
    }
    
}
