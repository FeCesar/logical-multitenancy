package com.api.parkingcontrol.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return Optional.of(request)
                .map(req -> this.setTenantAtrribute(req, req.getServerName().toLowerCase()))
                .orElse(false);
    }

    private boolean setTenantAtrribute(HttpServletRequest request, String tenantName){
        request.setAttribute("tenantName", tenantName);
        return true;
    }

}
