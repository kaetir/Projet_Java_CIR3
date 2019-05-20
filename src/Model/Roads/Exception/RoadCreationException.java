package Model.Roads.Exception;

public class RoadCreationException extends Throwable {
    //Envoie une erreur en cas de mauvais paramétrage du nombre de voies
    public RoadCreationException() {
        System.out.println("ERROR: Can't create \'ROAD\'.  Can only create \'path\', \'way\' or \'highway\'. Must specified number of ways between 1 and 3");
    }
}
