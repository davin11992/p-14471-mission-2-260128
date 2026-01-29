import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String COMMAND_REGISTER = "등록";
    public static final String COMMAND_LIST = "목록";
    private static final String COMMAND_EXIT = "종료";
    private static final Pattern DELETE_PATTERN = Pattern.compile("삭제\\?id=(\\d+)");
    private static final Pattern UPDATE_PATTERN = Pattern.compile("수정\\?id=(\\d+)");

    private static int id = 0;
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<QuoteSet> quoteSets = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine();

            if (command.equals(COMMAND_EXIT)) {
                break;
            }

            handleCommand(command);
        }
    }

    private static void handleCommand(String command) {
        if (command.equals(COMMAND_REGISTER)) {
            registerQuote();
        } else if (command.equals(COMMAND_LIST)) {
            listQuotes();
        } else if (DELETE_PATTERN.matcher(command).matches()) {
            deleteQuote(command);
        } else if (UPDATE_PATTERN.matcher(command).matches()) {
            updateQuote(command);
        }
    }

    private static void registerQuote() {
        System.out.print("명언 : ");
        String quote = scanner.nextLine();
        System.out.print("작가 : ");
        String writer = scanner.nextLine();

        id++;
        quoteSets.add(new QuoteSet(id, quote, writer));
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    private static void listQuotes() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (int i = quoteSets.size() - 1; i >= 0; i--) {
            QuoteSet quoteSet = quoteSets.get(i);
            System.out.println(quoteSet.id + " / " + quoteSet.writer + " / " + quoteSet.quote);
        }
    }

    private static void deleteQuote(String command) {
        int deleteId = extractId(command, DELETE_PATTERN);
        QuoteSet quoteSet = findQuoteById(deleteId);

        if (quoteSet == null) {
            System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
        } else {
            quoteSets.remove(quoteSet);
            System.out.println(deleteId + "번 명언이 삭제되었습니다.");
        }
    }

    private static void updateQuote(String command) {
        int updateId = extractId(command, UPDATE_PATTERN);
        QuoteSet quoteSet = findQuoteById(updateId);

        if (quoteSet == null) {
            System.out.println(updateId + "번 명언은 존재하지 않습니다.");
        } else {
            System.out.println("명언(기존) : " + quoteSet.quote);
            System.out.print("명언 : ");
            String updateQuote = scanner.nextLine();

            System.out.println("작가(기존) : " + quoteSet.writer);
            System.out.print("작가 : ");
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
