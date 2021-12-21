package com.davinci.custockspringboot.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {
    public static Authentication getContext() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
