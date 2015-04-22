package be.howest.nmct.skateparknavigator.loader;

import android.provider.BaseColumns;

/**
 * Created by Jonathan on 22/04/2015.
 */
public class Contract {
    public interface SkateparkColumns extends BaseColumns {
        public static final String COLUMN_SKATEPARK_NAME = "skatepark_name";
        public static final String COLUMN_SKATEPARK_CAPACITY = "skatepark_capacity";
        //public static final String COLUMN_SKATEPARK_INDOOR = "skatepark_indoor";
        //public static final String COLUMN_SKATEPARK_FREE = "skatepark_free";
        public static final String COLUMN_SKATEPARK_CITY = "skatepark_city";
    }
}
