INSERT INTO users (name, email, password, registered, enabled)
VALUES ('mick', 'mick@gmail.com', '$2a$10$QCLWZmfLbLD1jXRP/IjX3e9L7XcNmDZdRYbV6icrds.JN2Wy4Fmeq', '2018-12-12', true);

/*qwerty1*/

INSERT INTO users (name, email, password, registered, enabled)
VALUES ('alex', 'alex@gmail.com', '$2a$10$qcZdYIAdxliLejgWwcOZt.t5HOpUJ0X6ctHYBSqYwSIKbYLjGzVzC', '2018-12-13', true);
/*qwerty2 and etc*/

INSERT INTO users (name, email, password, registered, enabled)
VALUES ('joel', 'joel@gmail.com', '$2a$10$y7NUV.il0WoZ4Bm4sSnDGe1MdlznkrELAZ6Z.eDO47/KGJZkIqGvy', '2018-12-14', true);

INSERT INTO users (name, email, password, registered, enabled)
VALUES ('bill', 'bill@gmail.com', '$2a$10$III4LWdEygJq0yUcQzDOYOihtxPHzTlzx1/syBMwsDhoA0B34HUuK', '2018-12-15', true);


INSERT INTO role (name)
VALUES ('ROLE_USER');
INSERT INTO role (name)
VALUES ('ROLE_ADMIN');
INSERT INTO role (name)
VALUES ('ROLE_SUPERADMIN');


INSERT INTO user_roles VALUES (1, 1);
INSERT INTO user_roles VALUES (2, 2);
INSERT INTO user_roles VALUES (2, 1);
INSERT INTO user_roles VALUES (3, 1);




INSERT INTO restaurant (name, description, contact, site, email, phones)
VALUES ('Frenchette',
        'The everyday French bistro is fresh again at Frenchette, a lively restaurant in Tribeca from the chefs behind mainstays like Balthazar. Riad Nasr and Lee Hanson offer a constantly changing menu with simple yet compelling options like rotisserie lobster, duck frites, and charred carrots. A natural wine list culled by Jorge Riera means that both by-the-glass and bottle lists are worth exploring, too. But a warm room and even warmer service makes Frenchette a modern destination, where downtown dining feels alive. Reservations, or dining in the bar area, are highly recommended.',
        '241 W Broadway New York, NY 10013', 'https://www.frenchettenyc.com/', 'reservations@frenchettenyc.com', '(212) 334-3883');

INSERT INTO restaurant (name, description, contact, site, email, phones)
VALUES ('Xian Famous Foods',
        'Xian Famous Foods, which started as a stand in Flushing, now has more than a dozen locations across NYC. But despite its chain status, the family-run restaurant has maintained quality, gaining cult following status for spicy, tacky hand-ripped noodles. The cumin lamb is particularly popular, but the cold-skin noodles come in close second, often selling out on busy days. Though prices range from location to location, a satisfying meal can be had for under $15 at this counter-service restaurant.',
        '45 Bayard St New York, NY 10013', 'https://www.xianfoods.com/', 'info@xianfoods.com', '(212) 786-2068');

INSERT INTO restaurant (name, description, contact, site, email, phones)
VALUES ('Le Coucou',
        'Restaurateur Stephen Starr and chef Daniel Rose take cues from traditional French restaurants, transforming their place into one of the most exciting upscale restaurants in гserFromUserTo York. The dining room offers perfect light in a room adorned in stately yet stylish decor. The menu is obvious in its luxuries: Lobster, foie gras, and oysters all make appearances. Also look for dishes like the caviar course and the halibut beurre blanc. For dessert, do not miss the omelette Norvegienne, essentially a baked Alaska.',
        '138 Lafayette St New York, NY', 'https://lecoucou.com/', 'lecoucou.info@starr-restaurants.com', '(212) 271-4252');

INSERT INTO restaurant (name, description, contact, site, email, phones)
VALUES ('Wildair',
        'At Wildair, Jeremiah Stone and Fabian von Hauske - the chef-restaurateurs behind Contra down the block — serve inventive small plates that don''t easily fit into any one culinary classification. A meal here might include Southern-style white shrimp, rich pork rillettes, crispy squid with green onions, bright scallop ceviche, and spicy chopped tuna on toast. To drink, this Lower East Side neo-bistro offers an exciting selection of natural wines, available by the bottle or by the glass. It’s minimalist decor and a tight squeeze, yet the vibe is super convivial.',
        '142 Orchard St New York, NY', 'http://wildair.nyc/', 'info@wildair.nyc', '(646) 964-5624');

INSERT INTO restaurant (name, description, contact, site, email, phones)
VALUES ('Uncle Boons',
        'This Nolita lounge is still turning out some of the citys most captivating Thai fare, courtesy of Per Se alums Ann Redding and Matt Danzer. Look for dishes like green curry snails, wood-fired yellowtail collar, a spicy lamb laab, or a savory crab fried rice. The space is an eclectic way to start a night out; order an overflowing beer slushie to get in the mood.',
        '7 Spring St New York, NY', 'http://www.uncleboons.com/', 'info@uncleboons.com' , '(646) 370-6650');


INSERT INTO dish (name, description, restaurant_id) VALUES ('Buffalo Chicken Salad',
                                                            'The Count: 1,130 calories, 74 grams fat, 3,290 milligrams sodium.' ||
                                                            '"Salad" is stretching it! Fried meat, oily sauce, and cheese push the calories in this meal through the roof at one popular restaurant. It has about as many as a whole pint of chocolate chip cookie dough ice cream. The salad also packs nearly 25% more fat.', 1);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Fried Rice with Vegetables', 'The Count:  910 calories, 16 grams fat, 1,360 milligrams sodium' ||
                                                                                          'Getting Chinese takeout? Dont assume the veggie options are the healthiest. Vegetarian fried rice can pack an unhealthy wallop. Instead, go for steamed dishes with lots of veggies and brown rice if it’s on the menu. Keep the rice to a half-cup -- that"s about half the size of a baseball. Always ask for sauce on the side.', 1);

INSERT INTO dish (name, description, restaurant_id) VALUES ('Salmon on bed', 'Roasted beet and 5 0z. salmon on bed of kale and spinach greens with balsamic vinegar drizzle', 1);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Roast beef sandwich', '1 small whole-grain hoagie bun, 2 ounces lean roast beef, 1 slice part-skim mozzarella, cheese, 2 slices tomato, 1/4 cup mushrooms (cooked in 1 teaspoon corn/canola oil), 1 teaspoon mustard', 1);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Tuna salad sandwich', '2 slices rye bread, 2 ounces tuna, 1 tablespoon mayonnaise, 1 tablespoon chopped, celery, 1/2 cup shredded lettuce', 1);


INSERT INTO dish (name, description, restaurant_id) VALUES ('Lamb Pao-Mo Soup', 'Our housemade unleavened bread boiled in lamb broth with sliced lamb meat and scallions, topped with cilantro.', 2);

INSERT INTO dish (name, description, restaurant_id) VALUES ('Spicy Cumin Lamb Hand-Ripped Noodles', 'Our biangbiang wide, hand-ripped noodles mixed with sauteed spicy cumin lamb.', 2);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Spicy & Tingly Beef Hand-Ripped Noodles', 'Our biangbiang wide, hand-ripped noodles mixed with chunks of lean beef, with a spicy and tingly (because of Sichuan peppercorns) sauce.', 2);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Chang''An Spicy Tofu', 'Housemade soft tofu drizzled with soy sauce, vinegar, chili oil, and fresh cilantro.', 2);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Pork "Zha Jiang" Hand-Ripped Noodles', 'Our wide hand-ripped biangbiang noodles mixed with a savory, and slightly-sweet ground pork meat sauce, tossed with cucumbers, scallions, celery, and chives.', 2);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Stewed Pork Burger', 'Pork belly meat, stewed and diced with its own juices, then packed into a warm and crispy flatbread-like bun.', 2);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Stir-Fried Liang Pi "Cold-Skin Noodles"', 'Chewy wheat flour noodles, quickly stir-fried with bean sprouts, cucumbers, cilantro, and cubes of spongy gluten, in all our proprietary sauces, which includes soy sauce, black vinegar, chili oil (unless requested not spicy).', 2);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Stewed Oxtail Hand-Ripped Noodles in Soup', 'Our biangbiang wide, hand-ripped noodles, topped with sliced stewed oxtails which were stewed in soy sauce and spices, in a beef broth.', 2);

INSERT INTO dish (name, description, restaurant_id) VALUES ('Huîtres, Granité aux Algues', 'oysters, seaweed ice', 3);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Velouté de Pommes de Terre', 'crème fraîche, caviar', 3);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Quenelle de brochet “Route de Reims”', 'champagne beurre blanc, caviar', 3);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Salade de homard, vinaigrette à la moutarde', 'grilled lobster refreshed with lime and mustard, exotic pears from here', 3);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Boudin “Les Aldudes”', 'blood sausage, apple, piment d’Espelette', 3);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Retour de Pêche', 'tilefish cooked on its scales, endive and mussels', 3);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Bar noir de nos côtes « souvenir de Marseille »', 'local black bass and stuffed squid, traditional fish soup', 3);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Canette Sainte-Baume', 'grilled duckling and figs', 3);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Filet de boeuf ‘Bourse et la Vie’', 'prime filet, sauce au poivre, pommes frites', 3);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Pêches à la crème', 'peaches, crème légère, pistachio', 3);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Chocolat Noir', 'salted caramel ice cream', 3);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Paris-Brest', 'hazelnut praline, pâte choux', 3);

INSERT INTO dish (name, description, restaurant_id) VALUES ('Oysters (½ dozen)', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Selection of cheese', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Breakfast radishes, seaweed butter', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Scallop crudo, meyer lemon, chicory', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Beef tartare, cheddar, horseradish, brazil nuts', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Potato darphin, maine uni, jalapeno', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Black maitake, stracciatella, chicken skin, tomatillo', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Crispy grain salad, hearts of palm, tarragon', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Confit bacon toast, pickled pineapple, nduja butter', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Pork milanese, gribiche, mustard greens', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('American Wagyu steak (24oz)', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Fried squid, lemon, squid ink mayo', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Maine lobster with buckwheat crepes', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Chocolate hazelnut tart', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Panna cotta, satsuma mandarin granita', '', 4);
INSERT INTO dish (name, description, restaurant_id) VALUES ('Top neck clams, XO, almond milk', '', 4);

INSERT INTO dish (name, description, restaurant_id) VALUES ('MENG KUM', 'Bettel Leaf Wrap with Ginger, Lime, Toasted Coconut, Dried Shrimp, Chilies & Peanuts - Traditional Thai snack', 5);
INSERT INTO dish (name, description, restaurant_id) VALUES ('HOI TAK', 'Green Curry Snails with Crispy Garlic & Herbs', 5);
INSERT INTO dish (name, description, restaurant_id) VALUES ('MUU TOD KAPI', 'Shrimp Paste Pork Riblets & Radishes with Fish sauce Caramel', 5);
INSERT INTO dish (name, description, restaurant_id) VALUES ('KHAO SOI KAA KAI', 'Northern Style Golden Curry with Home Made Egg Noodles, Chicken Leg, Pickled Mustard Greens and Fresh Turmeric', 5);
INSERT INTO dish (name, description, restaurant_id) VALUES ('KAO PAT PUU', 'Traditional Crab Fried Rice with Egg, Cilantro and Lime', 5);
INSERT INTO dish (name, description, restaurant_id) VALUES ('KAI YANG MUAY THAI', 'Rotisserie Half Chicken with Dipping Sauces & Mango Salad', 5);
INSERT INTO dish (name, description, restaurant_id) VALUES ('SAI KROK AMPAI', 'Grilled Issan Pork & Rice Sour Sausage. Mommy Pais recipe', 5);
INSERT INTO dish (name, description, restaurant_id) VALUES ('PLA MUUK', 'Griled Baby Octopus', 5);
INSERT INTO dish (name, description, restaurant_id) VALUES ('PLAA', 'Yellow Tail Collar', 5);

INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2020-12-28', 1, 1, 10.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-28', 1, 2, 11.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-28', 1, 3, 12.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-29', 1, 4, 10.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-29', 1, 5, 11.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-29', 1, 1, 12.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-30', 1, 2, 10.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-30', 1, 3, 11.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-30', 1, 4, 12.00);

INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-28', 2, 6, 11.11);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-28', 2, 7, 12.99);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-28', 2, 8, 13.44);

INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-29', 2, 9, 12.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-29', 2, 10, 12.33);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-29', 2, 11, 13.88);

INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-30', 2, 12, 14.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-30', 2, 13, 13.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-30', 2, 6, 18.00);

INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-28', 3, 14, 22.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-28', 3, 15, 23.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-28', 3, 16, 24.00);

INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-29', 3, 17, 25.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-29', 3, 18, 26.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-29', 3, 19, 27.00);

INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-30', 3, 20, 28.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-30', 3, 21, 29.00);
INSERT INTO menu_item(datei, restaurant_id, dish_id, price) values('2018-12-30', 3, 22, 39.00);

INSERT INTO orderfromauser (dateord, user_id, restaurant_id) VALUES ('2018-12-30', 2, 3);
INSERT INTO orderfromauser (dateord, user_id, restaurant_id) VALUES ('2018-12-30', 2, 1);
INSERT INTO orderfromauser (dateord, user_id, restaurant_id) VALUES ('2018-12-29', 2, 2);

INSERT INTO order_item(dateo, orderfromauser_id, user_id, restaurant_id, dish_id, price) values('2018-12-30', 1, 2, 3, 20, 28.00);
INSERT INTO order_item(dateo, orderfromauser_id, user_id, restaurant_id, dish_id, price) values('2018-12-30', 1, 2, 3, 21, 29.00);
INSERT INTO order_item(dateo, orderfromauser_id, user_id, restaurant_id, dish_id, price) values('2018-12-30', 1, 2, 3, 22, 39.00);
INSERT INTO order_item(dateo, orderfromauser_id, user_id, restaurant_id, dish_id, price) values('2018-12-30', 2, 2, 1,  2, 13.20);
INSERT INTO order_item(dateo, orderfromauser_id, user_id, restaurant_id, dish_id, price) values('2018-12-30', 2, 2, 1,  3, 15.00);
INSERT INTO order_item(dateo, orderfromauser_id, user_id, restaurant_id, dish_id, price) values('2018-12-30', 2, 2, 1,  4, 19.00);
INSERT INTO order_item(dateo, orderfromauser_id, user_id, restaurant_id, dish_id, price) values('2018-12-30', 3, 2, 2, 12, 28.00);
INSERT INTO order_item(dateo, orderfromauser_id, user_id, restaurant_id, dish_id, price) values('2018-12-30', 3, 2, 2, 13, 29.00);
INSERT INTO order_item(dateo, orderfromauser_id, user_id, restaurant_id, dish_id, price) values('2018-12-30', 3, 2, 2,  6, 39.00);




INSERT INTO vote (datev, user_id, restaurant_id) VALUES ('2018-12-28', 1, 1);
INSERT INTO vote (datev, user_id, restaurant_id) VALUES ('2018-12-28', 2, 1);
INSERT INTO vote (datev, user_id, restaurant_id) VALUES ('2018-12-28', 3, 2);
INSERT INTO vote (datev, user_id, restaurant_id) VALUES ('2018-12-28', 3, 1);


INSERT INTO vote (datev, user_id, restaurant_id) VALUES ('2018-12-29', 1, 2);
INSERT INTO vote (datev, user_id, restaurant_id) VALUES ('2018-12-29', 2, 1);
INSERT INTO vote (datev, user_id, restaurant_id) VALUES ('2018-12-29', 3, 2);

