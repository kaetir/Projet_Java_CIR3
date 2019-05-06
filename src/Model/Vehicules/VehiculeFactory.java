package Model.Vehicules;

import Model.Vehicules.Exception.VehiculeCreationException;

public class VehiculeFactory {

    public static Vehicule create(String vehType) throws VehiculeCreationException {
        try {
            if(vehType.equals("car")) {
                return new Car();
            } else if(vehType.equals("motorBike")) {
                return new MotorBike();
            } else if(vehType.equals("truck")) {
                return new Truck();
            }
            throw new VehiculeCreationException("ERROR");
        } catch (VehiculeCreationException e){
            e.printStackTrace();
            return null;
        }


    }

}
