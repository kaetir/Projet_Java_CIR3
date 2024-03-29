package Model;

import Model.Intersections.Intersection;
import Model.Roads.Road;
import Model.Vehicules.Vehicule;
import javafx.util.Pair;

import java.util.Iterator;
import java.util.Vector;

public abstract class Simulation {

    //Simulation
    public static void vehiculesFromCitiesToRoads(){
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
        int i;

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
            i = paire.getKey();
            if(i != -1) {
                //Enlèvement du véhicule v à la ville c
                System.out.println(v.getType() + " left city " + c.getStringId());
                iterator.remove();
                //Ajout de la ville de destination au véhicule
                if(Model.getRoads(c).get(i).getCityA().equals(c)) v.setDestination(Model.getRoads(c).get(i).getCityB(), paire.getValue());
                else v.setDestination(Model.getRoads(c).get(i).getCityA(), paire.getValue());
                System.out.println("    by the " + Model.getRoads(c).get(i).getName() + " at the index " + i + " of city " + c.getStringId());
                //Ajout du véhicule v à la route d'index i
                Model.getRoads(c).get(i).addVehicule(v);
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

        if(!(v.getDestination().equals(c))){

            Pair<Boolean, Integer> paire;   //La paire va prendre comme première valeur un boolean indiquant si la route est libre
            //Et comme deuxième valeur la voie disponible

            for(Road r : Model.getRoads(c)){
                paire = r.isFree(c.getX(), c.getY(), v);

                City a;
                City b;

                if(v.getDestination().equals(r.getCityB())){
                    a = r.getCityA();
                    b = r.getCityB();
                } else {
                    a = r.getCityB();
                    b = r.getCityA();
                }


                if(a.equals(v.getDestination()) || b.equals(v.getDestination()) && paire.getKey())
                    return new Pair<>(Model.getRoads(c).indexOf(r), paire.getValue());    //Si la route est libre, renvoi l'index 0
            }
            
        }

        return new Pair<>(-1, -1);  //Si aucune route n'est libre, renvoie -1
    }


    public static void step(){

        double coeff = 50;

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

            City A = r.getCityA();
            City B = r.getCityB();

            //Affichage de la vitesse
            System.out.println("Speed limit on " + r.getName() + " : " + r.getSpeedLimit() + " km/h");

            //Augmentation de la vitesse pour chaque véhicule v
            Iterator<Vehicule> iterator = r.getVehicules().iterator();
            while (iterator.hasNext()) {
                Vehicule v = iterator.next();

                v.setCurrentSpeed(calculAcc(v, r));
                System.out.println("Current speed set at : " + v.getCurrentSpeed());

                City a;
                City b;

                if(v.getDestination().equals(B)){
                    a = A;
                    b = B;
                } else {
                    a = B;
                    b = A;
                }

                double ajoutX;
                double ajoutY;
                double speed = v.getCurrentSpeed()/coeff;

                if(b.getY() > a.getY()){
                    cote = Math.abs(b.getX() - a.getX());
                    angle = Math.asin(cote/distanceRoute);
                    if(b.getX() > a.getX()){
                        ajoutX = speed*Math.sin(angle);
                        ajoutY = speed*Math.cos(angle);
                        if(v.getX()+ajoutX >= b.getX()) ajoutX = b.getX()-v.getX();
                        if(v.getY()+ajoutY >= b.getY()) ajoutY = b.getY()-v.getY();
                    } else {
                        ajoutX = - speed*Math.sin(angle);
                        ajoutY = speed*Math.cos(angle);
                        if(v.getX()+ajoutX < b.getX()) ajoutX = b.getX()-v.getX();
                        if(v.getY()+ajoutY >= b.getY()) ajoutY = b.getY()-v.getY();
                    }
                } else {
                    cote = Math.abs(a.getY() - b.getY());
                    angle = Math.asin(cote/distanceRoute);
                    if(b.getX() > a.getX()){
                        ajoutY = - speed*Math.sin(angle);
                        ajoutX = speed*Math.cos(angle);
                        if(v.getX()+ajoutX >= b.getX()) ajoutX = b.getX()-v.getX();
                        if(v.getY()+ajoutY < b.getY()) ajoutY = b.getY()-v.getY();
                    } else {
                        ajoutY = - speed*Math.sin(angle);
                        ajoutX = - speed*Math.cos(angle);
                        if(v.getX()+ajoutX < b.getX()) ajoutX = b.getX()-v.getX();
                        if(v.getY()+ajoutY < b.getY()) ajoutY = b.getY()-v.getY();
                    }
                }

                if(b.getX() == (v.getX()+ajoutX) && b.getY() == (v.getY()+ajoutY)){
                    b.add(v);
                    iterator.remove();
                    System.out.println(v.getType() + " arrived in city " + b.getStringId());
                } else {
                    v.updateVehicule(v.getX() + ajoutX, v.getY() + ajoutY, r);
                }
            }
        }
    }

    private static void updateWays(Vehicule vehicule, Road r){
        Vector<Vehicule> vehLine;
        for(int i = 3; i > 1; i--){
            vehLine = vehicule.onLineAroundVehicule(r, 30, i-1);
            for(Vehicule v : vehLine){
                if(v.getCurrentSpeed() > vehicule.getCurrentSpeed()){
                    if(vehicule.onLineAroundVehicule(r, 10, i-1).isEmpty())
                        vehicule.setWay(i-1);
                }
            }
        }
        vehLine = vehicule.onLineAroundVehicule(r, 30, vehicule.getWay());
        for(Vehicule v : vehLine){
            //du code
        }

    }

    private static double calculAcc(Vehicule v, Road r){

        double acc = 0;   //Coefficient d'accélération
        double coeff = 1;

        System.out.println("+Speed of this " + v.getType() + " : " + v.getCurrentSpeed());
        System.out.println(isThereAnIntersecNearInFront(v, r));
        if(isThereACityNear(v, r) || isThereAnIntersecNearInFront(v, r).getValue()){
            if(isThereAnIntersecNearInFront(v, r).getValue()){
                System.out.println("Intersection detected : incomming");
                if(isThereAnIntersecNearInFront(v, r).getKey().isOpen(v, r)) {
                    return 40;
                } else {
                    return 0;
                }
            }
            acc = -0.5;
            if(r.getNbWay() == 3) acc = -1;
            if((v.getCurrentSpeed() + acc) <= 0) return 20;
            else return (v.getCurrentSpeed() + acc);
        }

        if(isThereAnIntersecNearBehind(v, r).getValue()) {
            System.out.println("Intersection detected : leaving");
            coeff = 40;
        }
        if(r.getNbWay() == 1) acc = 0.5*coeff;
        if(r.getNbWay() == 2) acc = 1*coeff;
        if(r.getNbWay() == 3) acc = 2*coeff;

        if((v.getCurrentSpeed() + acc) <= v.getMaxSpeed() && (v.getCurrentSpeed() + acc) <= r.getSpeedLimit()) return (v.getCurrentSpeed() + acc);
        else return r.getSpeedLimit();
    }

    private static Pair<Intersection, Boolean> isThereAnIntersecNearBehind(Vehicule v, Road r){
        System.out.println("        behind");
        return isThereAnIntersecNear(v, r, true);
    }

    private static Pair<Intersection, Boolean> isThereAnIntersecNearInFront(Vehicule v, Road r){
        System.out.println("        front");
        return isThereAnIntersecNear(v, r, false);
    }

    private static Pair<Intersection, Boolean> isThereAnIntersecNear(Vehicule v, Road r, boolean i){
        double dist;
        double dist2;
        Pair<Intersection, Boolean> retour;
        for(Intersection in : Model.getIntersex()){
            if(in.getA().equals(r) || in.getB().equals(r)){
                dist = Math.sqrt(Math.pow((in.getX() - v.getX()), 2) + Math.pow((in.getY() - v.getY()), 2));
                System.out.println("    approaching intersection distant of " + dist);
                dist2 = Math.sqrt(Math.pow((v.getX() - v.getDestination().getX()), 2) + Math.pow((v.getY() - v.getDestination().getY()), 2));
                if(i){
                    retour = new Pair<>(in, true);
                    if (whatToSendInter(dist) && (dist > dist2)) return retour;
                } else {
                    retour = new Pair<>(in, true);
                    if (whatToSendInter(dist) && (dist < dist2)) return retour;
                }
            }
        }
        retour = new Pair<>(null, false);
        return retour;
    }

    private static boolean whatToSendInter(double dist) {
        if(dist < 25) return true;
        return false;
    }

    private static boolean whatToSend(Road r, double dist) {
        if(r.getNbWay() == 1 && dist < 130) return true;
        if(r.getNbWay() == 2 && dist < 170) return true;
        if(r.getNbWay() == 3 && dist < 150) return true;
        return false;
    }

    private static boolean isThereACityNear(Vehicule v, Road r){
        double dist;

        dist = Math.sqrt(Math.pow((v.getDestination().getX() - v.getX()), 2) + Math.pow((v.getDestination().getY() - v.getY()), 2));
        System.out.println("    approaching destination distant of " + dist);

        if (whatToSend(r, dist)) return true;

        return false;
    }

    public static boolean isFinish(){
        System.out.println(System.getProperty("line.separator") + "*** ? Finishing Simulation ? ***" + System.getProperty("line.separator"));


        for(City c : Model.getCities()){
            if(!(c.getVehicules().isEmpty())){
                Iterator<Vehicule> iterator = c.getVehicules().iterator();
                while (iterator.hasNext()) {
                    Vehicule v = iterator.next();
                    if(!(v.getDestination().equals(c))){
                        System.out.println("At least one " + v.getType() + " is not arrived yet at its destination");
                        System.out.println("Simulation continuing...");
                        return false;
                    } else {
                        System.out.println(v.getType() + " is at its destination in city " + c.getStringId());
                        Model.getVehicules().removeElement(v);
                        iterator.remove();
                        Model.getVehicules();
                        System.out.println("    and has been erased");
                    }
                }
            }
        }
        for(Road r : Model.getRoads()){
            if(!(r.getVehicules().isEmpty())){
                System.out.println("Some vehicles are still on roads");
                return false;
            }
            System.out.println("No more vehicles are rolling on " + r.getName() + " between city " + r.getCityA().getStringId() + " and city " + r.getCityB().getStringId());
        }
        System.out.println("Every vehicles are at their destination");
        System.out.println("Shutdown simulation...");
        return true;
    }

}
