package Model.Roads.Exception;

public class RoadCreationException extends Throwable {
    public RoadCreationException(String error) {
        System.out.println("ERROR: Can't create \'" + error + "\'.  \'RoadFactory\' can only create \'path\', \'way\' or \'highway\'.");
    }
}
