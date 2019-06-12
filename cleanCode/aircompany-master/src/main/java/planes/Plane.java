package planes;

import java.util.Objects;

abstract public class Plane {
    private String model;
    private int maxSpeed;
    private int maxFlightDistance;
    private int maxLoadCapacity;

    Plane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.maxFlightDistance = maxFlightDistance;
        this.maxLoadCapacity = maxLoadCapacity;
    }

    String getModel() {
        return model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMaxFlightDistance() {
        return maxFlightDistance;
    }

    public int getMaxLoadCapacity() {
        return maxLoadCapacity;
    }

    @Override
    public String toString() {
        return String.format("Plane{model='%s', maxSpeed=%d, maxFlightDistance=%d, maxLoadCapacity=%d}",model,maxSpeed,maxFlightDistance,maxLoadCapacity);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Plane){
            Plane plane = (Plane) obj;
            return maxSpeed == plane.getMaxSpeed() &&
                maxFlightDistance == plane.getMaxFlightDistance() &&
                maxLoadCapacity == plane.getMaxLoadCapacity() &&
                Objects.equals(model, plane.getModel()); }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
    }
}
