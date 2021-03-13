DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS menu_item;
DROP TABLE IF EXISTS restaurant;

CREATE TABLE users (
                       id         INTEGER IDENTITY PRIMARY KEY,
                       name       VARCHAR(50)             NOT NULL,
                       email      VARCHAR(50)             NOT NULL,
                       password   VARCHAR(100)            NOT NULL,
                       registered TIMESTAMP DEFAULT now() NOT NULL,
                       enabled    BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE UNIQUE INDEX users_unique_name_idx
  ON users (name);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE role (
                      id   INTEGER IDENTITY PRIMARY KEY,
                      name VARCHAR(50) NOT NULL
);
CREATE UNIQUE INDEX role_unique_name_idx
  ON role (name);

CREATE TABLE user_roles (
                            user_id INT NOT NULL,
                            role_id INT NOT NULL,
                            FOREIGN KEY (user_id) REFERENCES users (id)
                                ON DELETE CASCADE,
                            FOREIGN KEY (role_id) REFERENCES role (id)
                                ON DELETE CASCADE,
                            UNIQUE (user_id, role_id)
);
CREATE INDEX user_roles_user_role_idx
  ON user_roles (user_id, role_id);

CREATE TABLE restaurant (
                            id          INTEGER IDENTITY PRIMARY KEY,
                            name        VARCHAR(50)  NOT NULL,
                            description LONGVARCHAR  NOT NULL,
                            contact     VARCHAR(200) NOT NULL,
                            site        VARCHAR(50)  NOT NULL,
                            email       VARCHAR(50)  NOT NULL,
                            phones      VARCHAR(50)  NOT NULL
);
CREATE UNIQUE INDEX restaurant_unique_name_idx
  ON restaurant (name);



CREATE TABLE dish (
                      id            INTEGER IDENTITY PRIMARY KEY,
                      name          VARCHAR(150) NOT NULL,
                      description   LONGVARCHAR,
                      restaurant_id INTEGER      NOT NULL,
                      FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX dish_name_restaurant_unique_idx
  ON dish (name, restaurant_id);

CREATE TABLE menu_item (
                           id            INTEGER IDENTITY PRIMARY KEY,
                           datei         DATE,
                           restaurant_id INTEGER NOT NULL,
                           dish_id       INTEGER NOT NULL,
                           price         DECIMAL(20, 2),
                           FOREIGN KEY (dish_id) REFERENCES dish (id) ON DELETE CASCADE,
                           FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE INDEX menu_item_date_index_menu_item_idx
  ON menu_item (datei);
CREATE INDEX menu_item_restaurant_index_menu_item_idx
  ON menu_item (restaurant_id);
CREATE INDEX menu_item__dish_idx
  ON menu_item (dish_id);
CREATE UNIQUE INDEX menu_item_date_dish_price_unique_idx
  ON menu_item (datei, dish_id, price);

CREATE TABLE orderfromauser (
                      id            INTEGER IDENTITY PRIMARY KEY,
                      dateord         DATE,
                      user_id       INTEGER NOT NULL,
                      restaurant_id INTEGER NOT NULL,
                      FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                      FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE

);
CREATE INDEX order_date_idx
  ON orderfromauser (dateord);
CREATE INDEX order_user_idx
  ON orderfromauser (user_id);
CREATE INDEX order_restaurant_index_vote_idx
  ON orderfromauser (restaurant_id);


CREATE TABLE order_item (
                           id            INTEGER IDENTITY PRIMARY KEY,
                           dateo         DATE,
                           orderfromauser_id      INTEGER NOT NULL,
                           user_id       INTEGER NOT NULL,
                           restaurant_id INTEGER NOT NULL,
                           dish_id       INTEGER NOT NULL,
                           price         DECIMAL(20, 2),
                           FOREIGN KEY (dish_id) REFERENCES dish (id) ON DELETE CASCADE,
                           FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
                           FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                           FOREIGN KEY (orderfromauser_id) REFERENCES orderfromauser (id) ON DELETE CASCADE
);
CREATE INDEX order_item_date_index_order_item_idx
  ON order_item (dateo);
CREATE INDEX order_item_restaurant_index_order_item_idx
  ON order_item (restaurant_id);
CREATE INDEX order_order__dish_idx
  ON order_item (dish_id);
CREATE UNIQUE INDEX order_item_date_dish_price_unique_idx
  ON order_item (dateo, dish_id, price);

CREATE TABLE vote (
                      id            INTEGER IDENTITY PRIMARY KEY,
                      datev         DATE,
                      user_id       INTEGER NOT NULL,
                      restaurant_id INTEGER NOT NULL,
                      FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                      FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);
CREATE INDEX vote_date_idx
  ON vote (datev);
CREATE INDEX vote_user_idx
  ON vote (user_id);
CREATE INDEX vote_restaurant_index_vote_idx
  ON vote (restaurant_id);
/*CREATE UNIQUE INDEX vote_date_user_unique_idx
  ON vote (datev, user_id);*/ /* deleted for testing*/