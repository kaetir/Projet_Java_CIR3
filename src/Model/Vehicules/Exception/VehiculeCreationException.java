package Model.Vehicules.Exception;

public class VehiculeCreationException extends Throwable {
    public VehiculeCreationException(String error) {
        System.out.println("ERROR: \'VehiculeFactory\' can only create motorBike, car or truck.");
    }
}
