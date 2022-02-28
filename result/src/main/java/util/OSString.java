package util;

import java.nio.charset.StandardCharsets;

public class OSString {

    public static String convert(String s) {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            return new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        }
        else {
            return s;
        }
    }

}
