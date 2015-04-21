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
                Skatepark.PROVINCE.WESTVLAANDEREN,
                "http://www.kortrijk.be/vrije-tijd/jeugd/ruimte/skate-blade-bikespots/marke-luxaplast",
                Skatepark.CAPACITY.SMALL,
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
                Skatepark.PROVINCE.WESTVLAANDEREN,
                "http://www.uitinvlaanderen.be/agenda/e/skatepark-oodeekay/52e92855-0b4a-48f1-85c6-d56eec7d2134",
                Skatepark.CAPACITY.MEDIUM,
                false,
                true,
                51.1118093,
                2.6909461,
                17
        );

        Skatepark s3 = new Skatepark(
                "Skatepark Entrepot",
                "Dit bovenlokaal skatepark net buiten de stadsring te Sint-Jozef is het grootste en meest gebruikte skateterrein van de stad. Naast lokale skaters en bladers trekt het skatepark bovendien ook veel skaters van naburige gemeenten aan. In 2012 wordt het skatepark bovendien nog verder uitgebreid en verfraaid!",
                "Binnenweg 4",
                "Brugge",
                "8000",
                Skatepark.PROVINCE.WESTVLAANDEREN,
                "http://www.uitinvlaanderen.be/agenda/e/skatepark-entrepot/b90651e5-0f66-47a0-a3fa-9996907f504c",
                Skatepark.CAPACITY.MEDIUM,
                false,
                true,
                51.22598,
                3.225813,
                17
        );

        Skatepark s4 = new Skatepark(
                "Skatepark Truespin",
                "Iedereen (skaters, bladers, BMX'ers, scooters) is welkom in dit indoor skatepark.",
                "Trakelweg 78",
                "Roeselare",
                "8800",
                Skatepark.PROVINCE.WESTVLAANDEREN,
                "http://www.uitinvlaanderen.be/agenda/e/skatepark-truespin/d33d9d48-8a13-47bd-bd70-dea2e841bcde",
                Skatepark.CAPACITY.MEDIUM,
                true,
                false,
                50.9425474,
                3.1418783,
                17
        );

        skateparks.add(s1);
        skateparks.add(s2);
        skateparks.add(s3);
        skateparks.add(s4);
    }
}
