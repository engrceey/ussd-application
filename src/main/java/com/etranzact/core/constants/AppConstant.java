package com.etranzact.core.constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * Created by johnadeshola on 9/21/19.
 */
public interface AppConstant {

    public interface SecurityConstants {
        public String DEFUALT_KEY = "6YXvfUFfkZLqosDiYoEOwMYiG6M4Eb2sVdXozXW8tZZ90ZVA4B2BYzhf799hLmkdz4Q7Y2kbJSFLUfk9vYAyYXvMtDK4oDO4yhRh9xbAMAMrmnOE7HepUQ==";
        public byte[] SALT = {(byte) 0x21, (byte) 0x21, (byte) 0xF0, (byte) 0x55, (byte) 0xC3, (byte) 0x9F, (byte) 0x5A, (byte) 0x75};
        public int ITERATION_COUNT = 31;
        public static String UTF_8 = "UTF-8";
    }

    public interface Utf8CharSet {
        public static String UTF_8 = "UTF-8";
        public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        public static final String lower = upper.toLowerCase();
        public static final String digits = "0123456789";
        public static final String alphanum = upper + lower + digits;
        public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    }

    public interface DateFormatters {
        public static final DateTimeFormatter FORMATTER = ofPattern("dd::MM::yyyy");
        public SimpleDateFormat FORMATTER_ = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        public static final DateTimeFormatter DATE_TIME_FORMATTER = ofPattern("yyyy-MM-dd HH:mm");
        public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
        public static final String DEFAULT_DATE_TIME_FORMAT_ = "yyyy-MM-dd HH:mm:ss";
        public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        public static final DateFormat dateFormat_ = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        public static final DateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    public interface Validations {
        public static final String TOKEN_INVALID = "invalid Token";
        public static final String TOKEN_EXPIRED = "expired";
        public static final String TOKEN_VALID = "valid";
        public static final String VALID = "valid";
        public static final String USER_NOT_FOUND = "User cannot be found";
    }

    public interface Email {
        public static final String FROM = "timadeshola@gmail.com";
        public static final String BCC = "timadeshola@gmail.com";
        String HTML = "html";
        String PLAIN = "textMessage";
        String EMAIL_TEMPLATE_ENCODING = "UTF-8";
    }

    public interface BatchProcess {
        int MIN_BATCH = 10;
        int AVERAGE_BATCH = 20;
        int MAX_BATCH = 50;
    }

    public interface AccountGenerator{
        String BIN = "1004";
    }
}
