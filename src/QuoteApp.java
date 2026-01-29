import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class QuoteApp {
    private static final String COMMAND_REGISTER = "등록";
    public static final String COMMAND_LIST = "목록";
    private static final String COMMAND_EXIT = "종료";
    private static final Pattern DELETE_PATTERN = Pattern.compile("삭제\\?id=(\\d+)");
    private static final Pattern UPDATE_PATTERN = Pattern.compile("수정\\?id=(\\d+)");

    private final QuoteRepository repository;
    private final Scanner scanner;

    public QuoteApp() {
        this.repository = new QuoteRepository();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String command = scanner.nextLine();
            if (command.equals(COMMAND_EXIT)) {
                break;
            }
            handleCommand(command);
        }
        scanner.close();
    }

    private void handleCommand(String command) {
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

    private void registerQuote() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String writer = scanner.nextLine();

        int id = repository.save(content, writer);
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    private void listQuotes() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        ArrayList<Quote> quotes = repository.findAll();
        for (int i = quotes.size() - 1; i >= 0; i--) {
            Quote quote = quotes.get(i);
            System.out.println(quote.getId() + " / " + quote.getWriter() + " / " + quote.getContent());
        }
    }

    private void deleteQuote(String command) {
        int id = extractId(command, DELETE_PATTERN);

        if (repository.deleteById(id)) {
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }

    private void updateQuote(String command) {
        int id = extractId(command, UPDATE_PATTERN);
        Quote quote = repository.findById(id);

        if (quote == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        } else {
            System.out.println("명언(기존) : " + quote.getContent());
            System.out.print("명언 : ");
            String newContent = scanner.nextLine();

            System.out.println("작가(기존) : " + quote.getWriter());
            System.out.print("작가 : ");
            String newWriter = scanner.nextLine();

            quote.update(newContent, newWriter);
        }
    }

    private int extractId(String command, Pattern pattern) {
        Matcher matcher = pattern.matcher(command);
        if (matcher.matches()) {
            return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }
}
