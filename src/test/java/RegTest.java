import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {
    public static void main(String[] args) {
        //funcTest();
        apiTest1();
    }

    public static void funcTest() {
        Pattern pattern = Pattern.compile("(?<name>[A-Za-z]+)\\(\\s*(?<params>.*)\\s*\\)");
        String exp = "add( 1.1 , -3 ,56, 0.154 , 4.485 )";
        Matcher matcher = pattern.matcher(exp);
        if (matcher.matches()) {
            System.out.println(exp);
            System.out.println(matcher.group("name"));
            System.out.println(matcher.group("params"));
        }
        String[] nums = matcher.group("params").split("\\s*,\\s*");
        System.out.println(nums.length);
        for (String num : nums) {
            System.out.println(num);
        }


        //String name = exp.substring(0, exp.indexOf('('));
        //System.out.println(name);
    }

    public static void numTest() {
        Pattern pattern = Pattern.compile("-?((0(\\.\\d+)?)|([1-9]\\d*(\\.\\d+)?))");
        String exp1 = "-3.1415926";
        String exp2 = "10000";
        String exp3 = "1.";
        String exp4 = "00012456";
        String exp5 = "0fdff";
        Matcher m = pattern.matcher(exp3);
        if (m.matches())
            System.out.println(exp1);
    }

    public static void apiTest1() {
        Pattern pattern = Pattern.compile("\\d{3,5}");
        String charSequence = "123-34345-234-00";
        Matcher matcher = pattern.matcher(charSequence);
        System.out.println(matcher.matches());    // false
        System.out.println(matcher.find());        // true
        //System.out.println(matcher.group());    // 34345
        while(matcher.find()) {
            System.out.println(matcher.group());
        }

    }


}
