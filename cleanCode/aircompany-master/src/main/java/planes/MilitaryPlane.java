package planes;

import typesOfPlanes.MilitaryTypes;
import java.util.Objects;

public class MilitaryPlane extends Plane{

    private MilitaryTypes type;

    public MilitaryPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, MilitaryTypes type) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.type = type;
    }

    public MilitaryTypes getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", String.format(", type=%s}",type));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MilitaryPlane && super.equals(obj)) {
            MilitaryPlane plane = (MilitaryPlane) obj;
            return type == plane.getType();}
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}
