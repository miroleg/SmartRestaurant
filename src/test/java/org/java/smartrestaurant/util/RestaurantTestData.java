package org.java.smartrestaurant.util;

import org.java.smartrestaurant.dto.ResultObject;
import org.java.smartrestaurant.model.Restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.java.smartrestaurant.util.DishTestData.DISHES;

public class RestaurantTestData {
    public static final int RESTAURANT_ID_SEQ = 1;
    public static final int RESTAURANT_1_ID = RESTAURANT_ID_SEQ;
    public static final int RESTAURANT_2_ID = RESTAURANT_ID_SEQ + 1;
    public static final int RESTAURANT_3_ID = RESTAURANT_ID_SEQ + 2;
    public static final int RESTAURANT_4_ID = RESTAURANT_ID_SEQ + 3;
    public static final int RESTAURANT_5_ID = RESTAURANT_ID_SEQ + 4;
    public static final int RESTAURANT_6_ID = RESTAURANT_ID_SEQ + 5;



    public final static Restaurant RESTAURANT_1 =
            new Restaurant(RESTAURANT_1_ID,"Frenchette",
                    "The everyday French bistro is fresh again at Frenchette, a lively restaurant in Tribeca from the chefs behind mainstays like Balthazar. Riad Nasr and Lee Hanson offer a constantly changing menu with simple yet compelling options like rotisserie lobster, duck frites, and charred carrots. A natural wine list culled by Jorge Riera means that both by-the-glass and bottle lists are worth exploring, too. But a warm room and even warmer service makes Frenchette a modern destination, where downtown dining feels alive. Reservations, or dining in the bar area, are highly recommended.",
                    "241 W Broadway New York, NY 10013",
                    "https://www.frenchettenyc.com/", "reservations@frenchettenyc.com", "(212) 334-3883",
                    //new ArrayList<>(DISHES.subList(0, 6)));
            Collections.emptySet(), Collections.emptySet(), Collections.emptySet());

    public final static Restaurant RESTAURANT_2 =
            new Restaurant(RESTAURANT_2_ID,"Xian Famous Foods",
                    "Xian Famous Foods, which started as a stand in Flushing, now has more than a dozen locations across NYC. But despite its chain status, the family-run restaurant has maintained quality, gaining cult following status for spicy, tacky hand-ripped noodles. The cumin lamb is particularly popular, but the cold-skin noodles come in close second, often selling out on busy days. Though prices range from location to location, a satisfying meal can be had for under $15 at this counter-service restaurant.",
                    "45 Bayard St New York, NY 10013",
                    "https://www.xianfoods.com/", "info@xianfoods.com", "(212) 786-2068",
                    //new ArrayList<>(DISHES.subList(6, 14)));
                    Collections.emptySet(), Collections.emptySet(), Collections.emptySet());

    public final static Restaurant RESTAURANT_3 =
            new Restaurant(RESTAURANT_3_ID,"Le Coucou",
                    "Restaurateur Stephen Starr and chef Daniel Rose take cues from traditional French restaurants, transforming their place into one of the most exciting upscale restaurants in гserFromUserTo York. The dining room offers perfect light in a room adorned in stately yet stylish decor. The menu is obvious in its luxuries: Lobster, foie gras, and oysters all make appearances. Also look for dishes like the caviar course and the halibut beurre blanc. For dessert, do not miss the omelette Norvegienne, essentially a baked Alaska.",
                    "138 Lafayette St New York, NY",
                    "https://lecoucou.com/", "lecoucou.info@starr-restaurants.com", "(212) 271-4252",
                    //new ArrayList<>(DISHES.subList(14, 26)));
                    Collections.emptySet(), Collections.emptySet(), Collections.emptySet());

    public final static Restaurant RESTAURANT_4 =
            new Restaurant(RESTAURANT_4_ID,"Wildair",
                    "At Wildair, Jeremiah Stone and Fabian von Hauske - the chef-restaurateurs behind Contra down the block — serve inventive small plates that don't easily fit into any one culinary classification. A meal here might include Southern-style white shrimp, rich pork rillettes, crispy squid with green onions, bright scallop ceviche, and spicy chopped tuna on toast. To drink, this Lower East Side neo-bistro offers an exciting selection of natural wines, available by the bottle or by the glass. It’s minimalist decor and a tight squeeze, yet the vibe is super convivial.",
                    "142 Orchard St New York, NY",
                    "http://wildair.nyc/", "info@wildair.nyc", "(646) 964-5624",
                    //new ArrayList<>(DISHES.subList(26, 42)));
                    Collections.emptySet(), Collections.emptySet(), Collections.emptySet());

    public final static Restaurant RESTAURANT_5 =
            new Restaurant(RESTAURANT_5_ID,"Uncle Boons",
                    "This Nolita lounge is still turning out some of the citys most captivating Thai fare, courtesy of Per Se alums Ann Redding and Matt Danzer. Look for dishes like green curry snails, wood-fired yellowtail collar, a spicy lamb laab, or a savory crab fried rice. The space is an eclectic way to start a night out; order an overflowing beer slushie to get in the mood.",
                    "7 Spring St New York, NY", "http://www.uncleboons.com/", "info@uncleboons.com" , "(646) 370-6650",
                    //new ArrayList<>(DISHES.subList(42, DISHES.size())));
                    Collections.emptySet(), Collections.emptySet(), Collections.emptySet());

    public final static Restaurant RESTAURANT_6 =
            new Restaurant(RESTAURANT_6_ID,"Prince Street Pizza",
                    "Tiny Soho slice shop Prince Street has been slinging its bouncy, thick pizza since 2012, and it has quickly become one of the city’s favorite pizza options. The go-to order is the Spicy Spring, which has tiny, curled pepperonis and a spicy, garlic-spiked sauce. The shop itself, which is decorated with photos of celebs who have stopped by, is petite and mostly a take-out situation. It’s an ideal pit stop during Soho shopping trips; the line moves fast.",
                    "27 Prince St A New York, NY 10012", "https://princestreetpizzanyc.com", "info@princestreetpizzanyc.com" , "(212) 966-4100",
                    Collections.emptySet(), Collections.emptySet(), Collections.emptySet());


    public static List<Restaurant> RESTAURANTS = new ArrayList<Restaurant>() {{
        add(RESTAURANT_1);
        add(RESTAURANT_2);
        add(RESTAURANT_3);
        add(RESTAURANT_4);
        add(RESTAURANT_5);
    }};

    static {
        RESTAURANT_1.setDishes(new HashSet<>(DISHES.entrySet().stream().filter(el -> el.getKey() <= 5).map(x -> x.getValue()).collect(Collectors.toList())));
        RESTAURANT_2.setDishes(new HashSet<>(DISHES.entrySet().stream().filter(el -> el.getKey() <= 13 & el.getKey() > 5).map(x -> x.getValue()).collect(Collectors.toList())));
        RESTAURANT_3.setDishes(new HashSet<>(DISHES.entrySet().stream().filter(el -> el.getKey() <= 25 & el.getKey() > 13).map(x -> x.getValue()).collect(Collectors.toList())));
        RESTAURANT_4.setDishes(new HashSet<>(DISHES.entrySet().stream().filter(el -> el.getKey() <= 41 & el.getKey() > 25).map(x -> x.getValue()).collect(Collectors.toList())));
        RESTAURANT_5.setDishes(new HashSet<>(DISHES.entrySet().stream().filter(el -> el.getKey() <= 50 & el.getKey() > 41).map(x -> x.getValue()).collect(Collectors.toList())));
    }

    public final static List<ResultObject> VOTES_RESULT_20181229 = new ArrayList() {{
        add(new ResultObject(RESTAURANT_2.getName(), 2L));
        add(new ResultObject(RESTAURANT_1.getName(), 1L));
    }};


}
