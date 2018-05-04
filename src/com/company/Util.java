package com.company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Util {

    // Taken from https://stackoverflow.com/a/20677345
    static String createDateNow() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
