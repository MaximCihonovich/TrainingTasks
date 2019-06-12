package javaCollections.main;

import java.util.ArrayList;
import java.util.Random;

public class Parking {

    private ArrayList<Boolean> parkingPlacesList = new ArrayList<>();

    Parking (int parkingAreaSize) {
        for ( byte i=0; i < parkingAreaSize; ++i ) {
            parkingPlacesList.add(new Random().nextBoolean());
        }
    }

    void addCarToParking () {
        int freeParkingPlace = parkingPlacesList.size()+1;
        for (byte i=0; i < parkingPlacesList.size(); ++i) {
            if (parkingPlacesList.get(i)) {
                freeParkingPlace = i;
                break;
            }
        }
        if ( freeParkingPlace <= parkingPlacesList.size()) {
            parkingPlacesList.set(freeParkingPlace,false);
        } else {
            System.out.println("Свободных мест нет." + '\n');
        }
    }

    void removeCarFromParking (int parkingPlace) {
        if (!(parkingPlacesList.get(parkingPlace-1))) {
            parkingPlacesList.set(parkingPlace-1,true);
        } else {
            System.out.println("Это место уже свободно." + '\n');
        }
    }

    @Override
    public String toString () {
        StringBuilder parkingPlacesListString = new StringBuilder();
        for (byte i=0; i < parkingPlacesList.size(); ++i) {
            if (parkingPlacesList.get(i)) {
                parkingPlacesListString.append(String.format("%-3d%s%n",i+1,"Свободно"));
            } else {
                parkingPlacesListString.append(String.format("%-3d%s%n",i+1,"Занято"));
            }
        }
        return parkingPlacesListString.toString();
    }
}
