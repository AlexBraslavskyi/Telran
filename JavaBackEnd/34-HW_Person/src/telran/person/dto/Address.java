package telran.person.dto;

import java.io.Serializable;

public class Address implements Serializable {

    String city;
    String street;
    int building;
    int aprt;

    public Address() {

    }

    public Address(String city, String street, int building, int aprt) {
        super();
        this.city = city;
        this.street = street;
        this.building = building;
        this.aprt = aprt;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "[city=" + city + ", street=" + street + ", building=" + building + ", aprt=" + aprt + "]";
    }

    public String getStreet() {
        return street;
    }

    public int getBuilding() {
        return building;
    }

    public int getAprt() {
        return aprt;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

}
