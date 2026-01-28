import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");
        int id = 0;
        ArrayList<QuoteSet> quoteSets = new ArrayList<>();

        String deleteRegex = "삭제\\?id=(\\d+)";
        Pattern pattern = Pattern.compile(deleteRegex);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("명령) ");
            String command = scanner.next();
            scanner.nextLine();

            if (command.equals("종료")) {
                break;
            }

            if (command.equals("등록")) {
                System.out.print("명언 : ");
                String quote = scanner.nextLine();
                System.out.print("작가 : ");
                String writer = scanner.nextLine();

                id++;
                quoteSets.add(new QuoteSet(id, quote, writer));
                System.out.println(id + "번 명언이 등록되었습니다.");
            }

            if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                for (int i = quoteSets.size() - 1; i >= 0; i--) {
                    QuoteSet quoteSet = quoteSets.get(i);
                    System.out.println(quoteSet.id + " / " + quoteSet.writer + " / " + quoteSet.quote);
                }
            }

            Matcher matcher = pattern.matcher(command);
            if (matcher.matches()) {
                int deleteId = Integer.parseInt(matcher.group(1));
                boolean found = false;

                for (int i = 0; i < quoteSets.size(); i++) {
                    if (quoteSets.get(i).id == deleteId) {
                        quoteSets.remove(i);
                        System.out.println(deleteId + "번 명언이 삭제되었습니다.");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
                }
            }
        }
    }
}
