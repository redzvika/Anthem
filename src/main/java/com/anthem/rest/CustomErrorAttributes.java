package com.anthem.rest;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        // Let Spring handle the error first, we will modify later :)
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        Throwable throwable = getError(webRequest);
        Integer status = (Integer) errorAttributes.get("status");
        if (throwable instanceof javax.validation.ConstraintViolationException) {
            if (status != null && status != 400) {
                errorAttributes.put("status", 400);
                errorAttributes.put("error", "Bad Request");
                errorAttributes.put("message", throwable.getMessage());
                status=400;
                webRequest.setAttribute("javax.servlet.error.status_code", status, 0);
            }

        }


        // format & update timestamp
        Object timestamp = errorAttributes.get("timestamp");
        if (timestamp == null) {
            errorAttributes.put("timestamp", dateFormat.format(new Date()));
        } else {
            errorAttributes.put("timestamp", dateFormat.format((Date) timestamp));
        }

        if (status != null && status == 500) {
            errorAttributes.put("message", "Consult log files");
        }
        // insert a new key
        errorAttributes.put("version", "1.0");

        return errorAttributes;

    }

}