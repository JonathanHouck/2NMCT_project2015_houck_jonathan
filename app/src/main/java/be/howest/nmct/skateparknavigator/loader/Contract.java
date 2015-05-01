package be.howest.nmct.skateparknavigator.loader;

import android.provider.BaseColumns;

/**
 * Created by Jonathan on 22/04/2015.
 */
public class Contract {
    public interface SkateparkColumns extends BaseColumns {
        public static final String COLUMN_SKATEPARK_NAME = "skatepark_name";
        public static final String COLUMN_SKATEPARK_DESCRIPTION = "skatepark_description";
        public static final String COLUMN_SKATEPARK_STREET = "skatepark_street";
        public static final String COLUMN_SKATEPARK_CITY = "skatepark_city";
        public static final String COLUMN_SKATEPARK_POSTCODE = "skatepark_postcode";
        public static final String COLUMN_SKATEPARK_PROVINCE = "skatepark_province";
        public static final String COLUMN_SKATEPARK_WEBSITE = "skatepark_website";
        public static final String COLUMN_SKATEPARK_CAPACITY = "skatepark_capacity";
        public static final String COLUMN_SKATEPARK_INDOOR = "skatepark_indoor";
        public static final String COLUMN_SKATEPARK_FREE = "skatepark_free";
        public static final String COLUMN_SKATEPARK_LATTITUDE = "skatepark_lattitude";
        public static final String COLUMN_SKATEPARK_LONGITUDE = "skatepark_longitude";
        public static final String COLUMN_SKATEPARK_ZOOMLEVEL = "skatepark_zoomlevel";
    }

    public interface ProvinceColumns extends BaseColumns {
        public static final String COLUMN_PROVINCE_NAME = "province_name";
        public static final String COLUMN_PROVINCE_LATTITUDE = "province_lattitude";
        public static final String COLUMN_PROVINCE_LONGITUDE = "province_longitude";
        public static final String COLUMN_PROVINCE_ZOOMLEVEL = "province_zoomlevel";
    }
}
