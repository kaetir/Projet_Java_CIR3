package Model.Roads.Exception;

public class RoadCreationException extends Throwable {
    public RoadCreationException() {
        System.out.println("ERROR: Can't create \'ROAD\'.  Can only create \'path\', \'way\' or \'highway\'. Must specified number of ways between 1 and 3.");
    }
}
