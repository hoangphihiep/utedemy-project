package vn.iotstar.strategy;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class VietnameseNormalizer {
    public static String normalize(String input) {
        if (input == null) return "";
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("").toLowerCase();
    }
}


