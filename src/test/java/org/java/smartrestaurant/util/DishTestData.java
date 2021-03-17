package org.java.smartrestaurant.util;

import org.java.smartrestaurant.model.Dish;
import org.java.smartrestaurant.model.Restaurant;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.java.smartrestaurant.util.RestaurantTestData.*;

public class DishTestData {
    public static final int DISH_ID_SEQ = 1;
    private static String[] names =
            {"Buffalo Chicken Salad","Fried Rice with Vegetables","Salmon on bed","Roast beef sandwich","Tuna salad sandwich","Lamb Pao-Mo Soup","Spicy Cumin Lamb Hand-Ripped Noodles","Spicy & Tingly Beef Hand-Ripped Noodles","Chang'An Spicy Tofu","Pork \"Zha Jiang\" Hand-Ripped Noodles","Stewed Pork Burger","Stir-Fried Liang Pi \"Cold-Skin Noodles\"",
            "Stewed Oxtail Hand-Ripped Noodles in Soup","Huîtres, Granité aux Algues","Velouté de Pommes de Terre","Quenelle de brochet “Route de Reims”","Salade de homard, vinaigrette à la moutarde","Boudin “Les Aldudes”","Retour de Pêche","Bar noir de nos côtes « souvenir de Marseille »","Canette Sainte-Baume","Filet de boeuf ‘Bourse et la Vie’","Pêches à la crème","Chocolat Noir","Paris-Brest","Oysters (½ dozen)","Selection of cheese","Breakfast radishes, seaweed butter",
            "Scallop crudo, meyer lemon, chicory","Beef tartare, cheddar, horseradish, brazil nuts","Potato darphin, maine uni, jalapeno","Black maitake, stracciatella, chicken skin, tomatillo","Crispy grain salad, hearts of palm, tarragon","Confit bacon toast, pickled pineapple, nduja butter","Pork milanese, gribiche, mustard greens","American Wagyu steak (24oz)","Fried squid, lemon, squid ink mayo","Maine lobster with buckwheat crepes","Chocolate hazelnut tart","Panna cotta, satsuma mandarin granita","Top neck clams, XO, almond milk","MENG KUM","HOI TAK","MUU TOD KAPI","KHAO SOI KAA KAI","KAO PAT PUU",
            "KAI YANG MUAY THAI", "SAI KROK AMPAI","PLA MUUK", "PLAA"};
    private static String[] description =
            {"The Count: 1,130 calories, 74 grams fat, 3,290 milligrams sodium.\"Salad\" is stretching it! Fried meat, oily sauce, and cheese push the calories in this meal through the roof at one popular restaurant. It has about as many as a whole pint of chocolate chip cookie dough ice cream. The salad also packs nearly 25% more fat.",
                    "The Count:  910 calories, 16 grams fat, 1,360 milligrams sodiumGetting Chinese takeout? Dont assume the veggie options are the healthiest. Vegetarian fried rice can pack an unhealthy wallop. Instead, go for steamed dishes with lots of veggies and brown rice if it’s on the menu. Keep the rice to a half-cup -- that\"s about half the size of a baseball. Always ask for sauce on the side.",
            "Roasted beet and 5 0z. salmon on bed of kale and spinach greens with balsamic vinegar drizzle",
            "1 small whole-grain hoagie bun, 2 ounces lean roast beef, 1 slice part-skim mozzarella, cheese, 2 slices tomato, 1/4 cup mushrooms (cooked in 1 teaspoon corn/canola oil), 1 teaspoon mustard",
            "2 slices rye bread, 2 ounces tuna, 1 tablespoon mayonnaise, 1 tablespoon chopped, celery, 1/2 cup shredded lettuce",
            "Our housemade unleavened bread boiled in lamb broth with sliced lamb meat and scallions, topped with cilantro.",
            "Our biangbiang wide, hand-ripped noodles mixed with sauteed spicy cumin lamb.",
            "Our biangbiang wide, hand-ripped noodles mixed with chunks of lean beef, with a spicy and tingly (because of Sichuan peppercorns) sauce.",
            "Housemade soft tofu drizzled with soy sauce, vinegar, chili oil, and fresh cilantro.",
            "Our wide hand-ripped biangbiang noodles mixed with a savory, and slightly-sweet ground pork meat sauce, tossed with cucumbers, scallions, celery, and chives.",
            "Pork belly meat, stewed and diced with its own juices, then packed into a warm and crispy flatbread-like bun.",
            "Chewy wheat flour noodles, quickly stir-fried with bean sprouts, cucumbers, cilantro, and cubes of spongy gluten, in all our proprietary sauces, which includes soy sauce, black vinegar, chili oil (unless requested not spicy).",
            "Our biangbiang wide, hand-ripped noodles, topped with sliced stewed oxtails which were stewed in soy sauce and spices, in a beef broth.",
            "oysters, seaweed ice", "crème fraîche, caviar", "champagne beurre blanc, caviar", "grilled lobster refreshed with lime and mustard, exotic pears from here",
            "blood sausage, apple, piment d’Espelette", "tilefish cooked on its scales, endive and mussels",
            "local black bass and stuffed squid, traditional fish soup", "grilled duckling and figs",
            "prime filet, sauce au poivre, pommes frites", "peaches, crème légère, pistachio",
            "salted caramel ice cream", "hazelnut praline, pâte choux", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",
            "Bettel Leaf Wrap with Ginger, Lime, Toasted Coconut, Dried Shrimp, Chilies & Peanuts - Traditional Thai snack",
            "Green Curry Snails with Crispy Garlic & Herbs", "Shrimp Paste Pork Riblets & Radishes with Fish sauce Caramel",
            "Northern Style Golden Curry with Home Made Egg Noodles, Chicken Leg, Pickled Mustard Greens and Fresh Turmeric",
            "Traditional Crab Fried Rice with Egg, Cilantro and Lime", "Rotisserie Half Chicken with Dipping Sauces & Mango Salad",
            "Grilled Issan Pork & Rice Sour Sausage. Mommy Pais recipe", "Griled Baby Octopus",
            "Yellow Tail Collar"
            };
 /*   private static Integer[] duration =
            {10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
             10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
             10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
             10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
             10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
    };


  */
    public static Map<Integer, Dish> DISHES = new LinkedHashMap<Integer,Dish>();

    static {
        Restaurant restaurant = null;
        for(var i = DISH_ID_SEQ; i < names.length + 1; i++) {
            if (i <= 5) {
                restaurant = RESTAURANT_1;
            } else
            if ((i > 5) & (i <= 13)) {
                restaurant = RESTAURANT_2;
            } else
            if ((i > 13) & (i <= 25)) {
                restaurant = RESTAURANT_3;
            } else
            if ((i > 25) & (i <= 41)) {
                restaurant = RESTAURANT_4;
            } else
            if ((i > 41) & (i <= 50)) {
                restaurant = RESTAURANT_5;
            }

            DISHES.put(i, new Dish(i, names[i - 1], description[i - 1], restaurant, 10));
        }

    }

    public static int NEW_DISH_ID = DISHES.size() + 1;
    public static Dish NEW_DISH = new Dish(NEW_DISH_ID, "New Dish", "New Dish Description", RESTAURANT_1,10);


}
