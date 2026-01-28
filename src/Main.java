import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");
        int id=0;
        ArrayList<QuoteSet> quoteSets = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("명령) ");
            String command = scanner.next();
            scanner.nextLine();

            if (command.equals("종료")) {
                break;
            }

            if(command.equals("등록")){
                System.out.print("명언 : ");
                String quote = scanner.nextLine();
                System.out.print("작가 : ");
                String writer = scanner.nextLine();

                id++;
                quoteSets.add(new QuoteSet(id, quote, writer));
                System.out.println(id + "번 명언이 등록되었습니다.");
            }

            if(command.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                for (QuoteSet quoteSet : quoteSets) {
                    System.out.println(quoteSet.id + " / " + quoteSet.writer + " / " + quoteSet.quote);
                }
            }
        }
    }
}
