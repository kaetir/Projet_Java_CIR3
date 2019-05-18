package Model.Roads;

import Model.City;
import Model.Roads.Exception.RoadCreationException;

public class RoadFactory {

    //Fonction de création des routes
    public static Road create(String roadType, City start, City end) throws RoadCreationException {

        try {

            //Création du véhicule en fonction du paramètre fourni
            if(roadType.equals("path"))                 //Création d'une route
                return new Path(start, end);
            else if(roadType.equals("way"))             //Création d'une route nationale
                return new Way(start, end);
            else if(roadType.equals("highway"))         //Création d'une autoroute
                return new Highway(start, end);

            //Renvoi d'une erreur
            throw new RoadCreationException(roadType);

        } catch (RoadCreationException e){
            return null;
        }

    }

}