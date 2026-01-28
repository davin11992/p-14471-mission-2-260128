import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("== 명언 앱 ==");
        int i=0;

        while(true){
            System.out.print("명령) ");

            Scanner scanner = new Scanner(System.in);
            String command = scanner.next();
            scanner.nextLine();

            if (command.equals("종료")) {
                break;
            }

            if(command.equals("등록")){
                System.out.print("명언 : ");
                String quote = scanner.next();
                System.out.print("작가 : ");
                String writer = scanner.next();

                i++;
                System.out.println(i+"번 명언이 등록되었습니다.");
            }
        }
    }
}