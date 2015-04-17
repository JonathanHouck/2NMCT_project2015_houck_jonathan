package be.howest.nmct.skateparknavigator.data;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import be.howest.nmct.skateparknavigator.admin.Skatepark;

/**
 * Created by Jonathan on 17/04/2015.
 */
public class SkateparkAdmin {

    private static List<Skatepark> skateparks;
    static {
        skateparks = new ArrayList<Skatepark>();

        Skatepark s1 = new Skatepark(
                "Luxaplast",
                "Het streetterrein aan de voormailige luxaplast is letterlijk een hemelsgeschenk: je kan er immers skaten als de regen of de sneeuw bij bakken uit de hemel valt. Hoewel het een outdoorpark is kunnen de weersomstandigheden je er niets maken omdat zeven meter boven jouw hoofd de ring rond Kortrijk loopt. Droog en geniaal...",
                "Markebekestraat 1",
                "Marke",
                "8510",
                "West-Vlaanderen",
                "http://www.kortrijk.be/vrije-tijd/jeugd/ruimte/skate-blade-bikespots/marke-luxaplast",
                1,
                false,
                true,
                50.8130482,
                3.2358383,
                17
        );

        Skatepark s2 = new Skatepark(
                "Skateplaza ODK",
                "Dit nieuw aangelegde skateplaza bestaat uit drie zones: een streetzone (met quarters, ledges, stairs en een wembely-gap) een technische zone (met banks, pyramids, curbs, stairs, flat rails, ledges en wheelie-bakjes) een transitionzone (met quarters, een volcano, speedbump, hip, teardrop, spine).",
                "Hazebeekstraat z/n",
                "Oostduinkerke",
                "8670",
                "West-Vlaanderen",
                "http://www.uitinvlaanderen.be/agenda/e/skatepark-oodeekay/52e92855-0b4a-48f1-85c6-d56eec7d2134",
                2,
                false,
                true,
                51.1118093,
                2.6909461,
                17
        );
    }
}
