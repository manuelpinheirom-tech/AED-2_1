package StationsProject;

import java.io.Serializable;

record Stop(Journey journey, Station station, int position, String arrival, String departure) implements Serializable {


}