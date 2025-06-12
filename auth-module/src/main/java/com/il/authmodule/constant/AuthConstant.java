package com.il.authmodule.constant;

import java.util.UUID;

public class AuthConstant {
    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String END_USER_ROLE = "end_user";
    public static final String ADMIN_ROLE = "admin";
    public static final UUID END_USER_ROLE_UUID = UUID.fromString("e2b1c3d4-5678-90ab-cdef-1234567890ab");
    public enum PROVIDER_TYPE {
        local,
        google
    }
}
