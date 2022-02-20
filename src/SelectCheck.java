import java.util.Scanner;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/20 下午 04:02
 */
public class SelectCheck {

    /**
     * 檢查是否為有效選項，是的話回傳輸入選項
     *
     * @param choiceStart 選項起始
     * @param choiceEnd   選項結束
     * @return
     */
    public static int filterSelection(int choiceStart, int choiceEnd) {
        Scanner sc = new Scanner(System.in);
        do {
            try {
                int input = sc.nextInt();

                while (input < choiceStart || input > choiceEnd) {
                    System.out.println("請輸入有效選項：");
                    input = sc.nextInt();
                }
                String clear = sc.nextLine();
                return input;
            }catch (Exception e){
                System.out.println("請輸入數字！");
                String clear = sc.nextLine();
            }
        }while(true);
    }
}
