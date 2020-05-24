import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String content = "";
        try {
            Scanner scanner = new Scanner(new File("./inputData.txt"));
            while (scanner.hasNext()) {
                content = content.concat(scanner.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read the input data.");
        } catch (Exception e) {
            System.out.println("There are a following problem: " + e.getMessage());
        }
        String[] groupNames = {
                "knowJava",
                "yearsOfExperienceInJava",
                "knowPython",
                "yearsOfExperienceInPython"
        };
        String stringPattern = "-(?<name>[^:]+):";
        for (String groupName : groupNames) {
            stringPattern = stringPattern.concat("[^=]+=(?<" + groupName + ">[^;]+);");
        }

        Pattern pattern = Pattern.compile(stringPattern);
        Matcher matcher = pattern.matcher(content);
        System.out.println("We will offer a job for the following candidates:");
        while (matcher.find()) {
            String applicantName = matcher.group("name");
            boolean knowJava = Boolean.parseBoolean(matcher.group("knowJava"));
            int yearsOfExperienceInJava = Integer.parseInt(matcher.group("yearsOfExperienceInJava"), 10);
            boolean knowPython = Boolean.parseBoolean(matcher.group("knowPython"));
            int yearsOfExperienceInPython = Integer.parseInt(matcher.group("yearsOfExperienceInPython"), 10);
            boolean offerAJob =
                    (knowJava && yearsOfExperienceInJava >= 1) ||
                            (knowPython && yearsOfExperienceInPython >= 3);
            if (offerAJob) {
                System.out.println(applicantName);
            }
        }
    }
}
