package advancedjava.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {
    public static void main(String[] args) {
        String text = "Please feel free to contact me at john.smith@gmail.com " +
                "if you need any further information from my end. I eagerly look forward to your reply.";

        Pattern email = Pattern.compile("(\\w+)@(gmail|yandex)\\.(com|ru)");
        Matcher matcher = email.matcher(text);

        while (matcher.find())
            System.out.println(matcher.group());
    }
}
