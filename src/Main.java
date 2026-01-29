import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static final String DELETE_REGEX = "삭제\\?id=(\\d+)";
    public static final String UPDATE_REGEX = "수정\\?id=(\\d+)";

    public static final String COMMAND_INPUT = "명령) ";
    public static final String END = "종료";
    public static final String APP_START = "== 명언 앱 ==";
    public static final String REGISTRATION = "등록";
    public static final String QUOTE = "명언 : ";
    public static final String WRITER = "작가 : ";
    public static final String LINE = "----------------------";
    public static final String SLASH = " / ";
    public static final String LIST = "목록";
    public static final String NUMBER_WRITER_QUOTE = "번호 / 작가 / 명언";
    public static final String REGISTER_NOTICE = "번 명언이 등록되었습니다.";
    public static final String DELETE_NOTICE = "번 명언이 삭제되었습니다.";
    public static final String NOT_EXIST_NOTICE = "번 명언은 존재하지 않습니다.";
    public static final String QUOTE_ORIGINAL = "명언(기존) : ";
    public static final String WRITER_ORIGINAL = "작가(기존) : ";

    public static void main(String[] args) {
        System.out.println(APP_START);
        int id = 0;
        ArrayList<QuoteSet> quoteSets = new ArrayList<>();

        String deleteRegex = DELETE_REGEX;
        Pattern deletePattern = Pattern.compile(deleteRegex);
        String updateRegex = UPDATE_REGEX;
        Pattern updatePattern = Pattern.compile(updateRegex);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(COMMAND_INPUT);
            String command = scanner.nextLine();

            if (command.equals(END)) {
                break;
            } else if (command.equals(REGISTRATION)) {
                System.out.print(QUOTE);
                String quote = scanner.nextLine();
                System.out.print(WRITER);
                String writer = scanner.nextLine();

                id++;
                quoteSets.add(new QuoteSet(id, quote, writer));
                System.out.println(id + REGISTER_NOTICE);
            } else if (command.equals(LIST)) {
                System.out.println(NUMBER_WRITER_QUOTE);
                System.out.println(LINE);

                for (int i = quoteSets.size() - 1; i >= 0; i--) {
                    QuoteSet quoteSet = quoteSets.get(i);
                    System.out.println(quoteSet.id + SLASH + quoteSet.writer + SLASH + quoteSet.quote);
                }
            }

            Matcher delteMatcher = deletePattern.matcher(command);
            if (delteMatcher.matches()) {
                int deleteId = Integer.parseInt(delteMatcher.group(1));
                boolean found = false;

                for (int i = 0; i < quoteSets.size(); i++) {
                    if (quoteSets.get(i).id == deleteId) {
                        quoteSets.remove(i);
                        System.out.println(deleteId + DELETE_NOTICE);
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println(deleteId + NOT_EXIST_NOTICE);
                }
            }

            Matcher updateMatcher = updatePattern.matcher(command);
            if (updateMatcher.matches()) {
                int updateId = Integer.parseInt(updateMatcher.group(1));
                boolean found = false;

                for (int i = 0; i < quoteSets.size(); i++) {
                    if (quoteSets.get(i).id == updateId) {
                        QuoteSet quoteSet = quoteSets.get(i);

                        System.out.println(QUOTE_ORIGINAL + quoteSet.quote);
                        System.out.print(QUOTE);
                        String updateQuote = scanner.nextLine();


                        System.out.println(WRITER_ORIGINAL + quoteSet.writer);
                        System.out.print(WRITER);
                        String updateWriter = scanner.nextLine();

                        quoteSet.quote = updateQuote;
                        quoteSet.writer = updateWriter;

                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println(updateId + NOT_EXIST_NOTICE);
                }
            }
        }
    }
}
