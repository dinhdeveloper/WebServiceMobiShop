package com.main;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class MobiShopUtil {
    public static String getBaseUrl() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest req = ((ServletRequestAttributes) requestAttributes).getRequest();
            return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
            // build URL from request
        } else {
            // fallback logic if request won't work...
            return "Nothing";
        }
    }
}
