package com.etranzact.core.utils;

import com.etranzact.core.constants.AppConstant;
import com.etranzact.core.exceptions.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.etranzact.core.constants.AppConstant.DateFormatters.DATE_TIME_FORMATTER;
import static com.etranzact.core.constants.AppConstant.DateFormatters.timeFormat;


/**
 * * Created by johnadeshola on 9/21/19.
 */
@Slf4j
public class AppUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Type type) {
        try {
            return mapper.writeValueAsString(type);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error occurred serializing object to json string, error => " + e.getMessage());
        }
    }

    public static <T> String toJson(T t) {
        try {
            return mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error occurred serializing object to json string, error => " + e.getMessage());
        }
    }


    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new CustomException("Error occurred deserializing object to json string", e.getMessage());
        }
    }

    public static Timestamp parseDate(String dateStr) {
        if (dateStr == null) {
            return new Timestamp(System.currentTimeMillis());
        }
        try {
            return new Timestamp(AppConstant.DateFormatters.dateFormat.parse(dateStr).getTime());
        } catch (ParseException e) {
            throw new CustomException("error occurred converting string to date, please check you date format", e.getMessage());
        }
    }

    public static Timestamp parseDate(String dateStr, String format) throws ParseException {
        if (dateStr == null) {
            return new Timestamp(System.currentTimeMillis());
        }
        return new Timestamp(new SimpleDateFormat(format).parse(dateStr).getTime());
    }

    public static String fromTimeStamp(Timestamp timestamp) {
        return timeFormat.format(timestamp);
    }

    public static Timestamp parseTime(String time) {
        if (time == null) {
            return null;
        }
        try {
            return new Timestamp(timeFormat.parse(time).getTime());
        } catch (ParseException ignore) {
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime(String date) {
        return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
    }

    public String getAppUrl(HttpServletRequest request) {
        return request.getScheme() + "//" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    public static String getIPAddress() {
        List<String> headers = Arrays.asList("x-real-ip", "x-forwarded-for");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        for (String header : headers) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    public static void main(String[] args) {
        System.out.println(getIPAddress());
    }
}
