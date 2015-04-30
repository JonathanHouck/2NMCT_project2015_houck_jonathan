package be.howest.nmct.skateparknavigator.admin;

import java.net.URI;
import java.net.URL;

/**
 * Created by Jonathan on 17/04/2015.
 */
public class Skatepark {
    public enum CAPACITY {
        SMALL("Klein", 1),
        MEDIUM("Gemiddeld", 2),
        BIG("Groot", 3);

        private String name;
        private int level;

        CAPACITY(String name, int level) {
            this.name = name;
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }
    }

    public enum PROVINCE {
        WESTVLAANDEREN("West-Vlaanderen", 51.038354, 3.03412, 10),
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getLattitude() {
            return lattitude;
        }

        public void setLattitude(Double lattitude) {
            this.lattitude = lattitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public int getZoomlevel() {
            return zoomlevel;
        }

        public void setZoomlevel(int zoomlevel) {
            this.zoomlevel = zoomlevel;
        }
    }

    private String name;
    private String description;
    private String street;
    private String city;
    private String postcode;
    private PROVINCE province;
    private String website;
    private CAPACITY capacity;
    private boolean indoor;
    private boolean free;
    private double lattitude;
    private double longitude;
    private int zoomlevel;

    public Skatepark(String name, String city, CAPACITY capacity) {
        this.name = name;
        this.city = city;
        this.capacity = capacity;
    }

    public Skatepark(String name, String description, String street, String city, String postcode, PROVINCE province, String website, CAPACITY capacity, boolean indoor, boolean free, double lattitude, double longitude, int zoomlevel) {
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
        this.zoomlevel = zoomlevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public PROVINCE getProvince() {
        return province;
    }

    public void setProvince(PROVINCE province) {
        this.province = province;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public CAPACITY getCapacity() {
        return capacity;
    }

    public void setCapacity(CAPACITY capacity) {
        this.capacity = capacity;
    }

    public boolean isIndoor() {
        return indoor;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getZoomlevel() {
        return zoomlevel;
    }

    public void setZoomlevel(int zoomlevel) {
        this.zoomlevel = zoomlevel;
    }
}
