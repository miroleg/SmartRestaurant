package org.java.smartrestaurant.util;

import org.java.smartrestaurant.model.Role;

import java.util.ArrayList;
import java.util.List;

public class RoleTestData {
    public static final int ROLE_ID_SEQ = 1;
    public static final int ROLE_1_ID = ROLE_ID_SEQ;
    public static final int ROLE_2_ID = ROLE_ID_SEQ + 1;
    public static final int ROLE_3_ID = ROLE_ID_SEQ + 2;
    public static final int ROLE_4_ID = ROLE_ID_SEQ + 3;

    public final static Role ROLE_1 = new Role(ROLE_1_ID, "USER");
    public final static Role ROLE_2 = new Role(ROLE_2_ID, "ROLE_ADMIN");
    public final static Role ROLE_3 = new Role(ROLE_3_ID, "ROLE_SUPERADMIN");
    public final static Role ROLE_4 = new Role(ROLE_4_ID, "ROLE_SUPERUSER");

    public static List<Role> ROLES = new ArrayList<>() {{
        add(ROLE_1);
        add(ROLE_2);
        add(ROLE_3);

    }};


}
