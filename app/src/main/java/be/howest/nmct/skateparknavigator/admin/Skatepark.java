package be.howest.nmct.skateparknavigator.admin;

import java.net.URI;
import java.net.URL;
import java.util.List;

/**
 * Created by Jonathan on 17/04/2015.
 */
public class Skatepark {
    public enum PROVINCE {
        WESTVLAANDEREN("West-Vlaanderen", 51.038354, 3.03412, 9),
        OOSTVLAANDEREN("Oost-Vlaanderen", 51.036895, 3.8306751, 10),
        ANTWERPEN("Antwerpen", 51.2603851, 4.3577201, 11),
        BRUSSEL("Brussel", 50.854975, 4.3753899, 12),
        VLAAMSBRABANT("Vlaams-Brabant", 50.86862, 4.538795, 11),
        LIMBURG("Limburg", 50.99784, 5.4456651, 10);

        private String name;
        private Double lattitude;
        private Double longitude;
        private int zoomlevel;

        PROVINCE(String name, Double lattitude, Double longitude, int zoomlevel) {
            this.name = name;
            this.lattitude = lattitude;
            this.longitude = longitude;
            this.zoomlevel = zoomlevel;
        }

        public int getZoomlevel() {
            return zoomlevel;
        }

        public String getName() {
            return name;
        }

        public Double getLattitude() {
            return lattitude;
        }

        public Double getLongitude() {
            return longitude;
        }
    }

    private String name;
    private String description;
    private String street;
    private String city;
    private String postcode;
    private String province;
    private String website;
    private int capacity;
    private boolean indoor;
    private boolean free;
    private double lattitude;
    private double longitude;

    public Skatepark(String name, String description, String street, String city, String postcode, String province, String website, int capacity, boolean indoor, boolean free, double lattitude, double longitude) {
        this.name = name;
        this.description = description;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.province = province;
        this.website = website;
        this.capacity = capacity;
        this.indoor = indoor;
        this.free = free;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public static Skatepark getSkateparkFromName(String sName, List<Skatepark> skateparks) {
        for (Skatepark skatepark : skateparks) {
            if (skatepark.getName().equals(sName)) return skatepark;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getProvince() {
        return province;
    }

    public String getWebsite() {
        return website;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public boolean isFree() {
        return free;
    }

    public double getLattitude() {
        return lattitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
