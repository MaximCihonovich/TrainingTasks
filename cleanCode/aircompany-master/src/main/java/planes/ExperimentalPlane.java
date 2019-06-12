package planes;

import typesOfPlanes.ClassificationLevels;
import typesOfPlanes.ExperimentalTypes;
import java.util.Objects;

public class ExperimentalPlane extends Plane{

    private ExperimentalTypes type;
    private ClassificationLevels classificationLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, ExperimentalTypes type, ClassificationLevels classificationLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.type = type;
        this.classificationLevel = classificationLevel;
    }

    public ClassificationLevels getClassificationLevel(){
        return classificationLevel;
    }

    public ExperimentalTypes getType() {
        return type;
    }

    public void setClassificationLevel(ClassificationLevels classificationLevel){
        this.classificationLevel = classificationLevel;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExperimentalPlane && super.equals(obj)) {
            ExperimentalPlane plane = (ExperimentalPlane) obj;
            return type == plane.getType();}
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);}

    @Override
    public String toString() {
        return String.format("experimentalPlane{model='%s'}",super.getModel());
    }
}
