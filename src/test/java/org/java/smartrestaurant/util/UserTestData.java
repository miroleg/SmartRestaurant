package org.java.smartrestaurant.util;

import org.java.smartrestaurant.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.java.smartrestaurant.util.DateTimeUtil.stringToDate;

public class UserTestData {
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static final int USER_ID_SEQ = 1;
    public static final int USER_1_ID = USER_ID_SEQ;
    public static final int USER_2_ID = USER_ID_SEQ + 1;
    public static final int USER_3_ID = USER_ID_SEQ + 2;
    public static final int USER_4_ID = USER_ID_SEQ + 3;
    public static final int USER_5_ID = USER_ID_SEQ + 4;

    public final static User USER_1 = new User(USER_1_ID,"mick", "mick@gmail.com", "qwerty1", stringToDate("2018-12-12"), true, Collections.emptyList());
    public final static User USER_2 = new User(USER_2_ID,"alex", "alex@gmail.com", "qwerty2", stringToDate("2018-12-13"), true, Collections.emptyList());
    public final static User USER_3 = new User(USER_3_ID,"joel", "joel@gmail.com", "qwerty3", stringToDate("2018-12-14"), true, Collections.emptyList());
    public final static User USER_4 = new User(USER_4_ID,"bill", "bill@gmail.com", "qwerty4", stringToDate("2018-12-15"), true, Collections.emptyList());
    public final static User USER_5 = new User(USER_5_ID,"jack", "jack@gmail.com", "qwerty5", stringToDate("2018-12-16"), true, Collections.emptyList());


    public static List<User> USERS = new ArrayList<User>() {{
        add(USER_1);
        add(USER_2);
        add(USER_3);
        add(USER_4);
    }};


}
