import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern DELETE_PATTERN = Pattern.compile("삭제\\?id=(\\d+)");
    private static final Pattern UPDATE_PATTERN = Pattern.compile("수정\\?id=(\\d+)");

    private static final String COMMAND_INPUT = "명령) ";
    private static final String END = "종료";
    private static final String APP_START = "== 명언 앱 ==";
    private static final String REGISTRATION = "등록";
    private static final String QUOTE = "명언 : ";
    private static final String WRITER = "작가 : ";
    private static final String LINE = "----------------------";
    private static final String SLASH = " / ";
    private static final String LIST = "목록";
    private static final String NUMBER_WRITER_QUOTE = "번호 / 작가 / 명언";
    private static final String REGISTER_NOTICE = "번 명언이 등록되었습니다.";
    private static final String DELETE_NOTICE = "번 명언이 삭제되었습니다.";
    private static final String NOT_EXIST_NOTICE = "번 명언은 존재하지 않습니다.";
    private static final String QUOTE_ORIGINAL = "명언(기존) : ";
    private static final String WRITER_ORIGINAL = "작가(기존) : ";

    private static int id = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<QuoteSet> quoteSets = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(APP_START);

        while (true) {
            System.out.print(COMMAND_INPUT);
            String command = scanner.nextLine();

            if (command.equals(END)) {
                break;
            }

            handleCommand(command);
        }
    }

    private static void handleCommand(String command) {
        if (command.equals(REGISTRATION)) {
            registerQuote();
        } else if (command.equals(LIST)) {
            listQuotes();
        } else if (DELETE_PATTERN.matcher(command).matches()) {
            deleteQuote(command);
        } else if (UPDATE_PATTERN.matcher(command).matches()) {
            updateQuote(command);
        }
    }

    private static void registerQuote() {
        System.out.print(QUOTE);
        String quote = scanner.nextLine();
        System.out.print(WRITER);
        String writer = scanner.nextLine();

        id++;
        quoteSets.add(new QuoteSet(id, quote, writer));
        System.out.println(id + REGISTER_NOTICE);
    }

    private static void listQuotes() {
        System.out.println(NUMBER_WRITER_QUOTE);
        System.out.println(LINE);

        for (int i = quoteSets.size() - 1; i >= 0; i--) {
            QuoteSet quoteSet = quoteSets.get(i);
            System.out.println(quoteSet.id + SLASH + quoteSet.writer + SLASH + quoteSet.quote);
        }
    }

    private static void deleteQuote(String command) {
        int deleteId = extractId(command, DELETE_PATTERN);
        QuoteSet quoteSet = findQuoteById(deleteId);

        if (quoteSet == null) {
            System.out.println(deleteId + NOT_EXIST_NOTICE);
        } else {
            quoteSets.remove(quoteSet);
            System.out.println(deleteId + DELETE_NOTICE);
        }
    }

    private static void updateQuote(String command) {
        int updateId = extractId(command, UPDATE_PATTERN);
        QuoteSet quoteSet = findQuoteById(updateId);

        if (quoteSet == null) {
            System.out.println(updateId + NOT_EXIST_NOTICE);
        } else {
            System.out.println(QUOTE_ORIGINAL + quoteSet.quote);
            System.out.print(QUOTE);
            String updateQuote = scanner.nextLine();

            System.out.println(WRITER_ORIGINAL + quoteSet.writer);
            System.out.print(WRITER);
            String updateWriter = scanner.nextLine();

            quoteSet.quote = updateQuote;
            quoteSet.writer = updateWriter;
        }
    }

    private static int extractId(String command, Pattern pattern) {
        Matcher matcher = pattern.matcher(command);
        if (matcher.matches()) {
            return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }

    private static QuoteSet findQuoteById(int id) {
        for (QuoteSet quoteSet : quoteSets) {
            if (quoteSet.id == id) {
                return quoteSet;
            }
        }
        return null;
    }
}
