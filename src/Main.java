import java.util.Scanner;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/20 下午 09:19
 */
public class Main {
    /**
     * 遊戲的規則
     */
    private static final int BIGGER_WIN = 1;
    private static final int SMALLER_WIN = 2;
    private static final int HAVE_MOST_POINT_WIN = 3;

    /**
     * 玩家id
     */
    private static final int player1 = 0;
    private static final int player2 = 1;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isContinue = true;
        while(isContinue){
            System.out.println("-----------------------骰子遊戲-----------------------");
            System.out.println("請輸入骰子數量(為少3個，最多6個)：");
            int diceNum = SelectCheck.filterSelection(3, 6, sc);
            System.out.println("請選擇遊戲規則\n1.點數大者勝\n2.點數小者勝\n3.相同點數多者勝");
            int rule = SelectCheck.filterSelection(BIGGER_WIN, HAVE_MOST_POINT_WIN, sc);
            System.out.println("請輸入回合數(必須為奇數且大於0，若輸入非奇數默認回合3)：");
            int roundTime = SelectCheck.filterSelection(1, Integer.MAX_VALUE, sc);

            DiceGame diceGame = new DiceGame(diceNum, roundTime);
            DiceGame.Rule ruleInstance = getRule(diceGame, rule);
            diceGame.setRule(getRule(diceGame, rule));

            //紀錄贏的次數
            int player1WinTime = 0;
            int player2WinTime = 0;

            System.out.println("遊戲開始：");
            if (ruleInstance != null) {
                for (int i = 0; i < roundTime; i++) {
                    System.out.printf("第%d回合：\n", i + 1);
                    System.out.println("玩家1按enter骰骰子：");
                    sc.nextLine();
                    int[] dice1 = diceGame.roleDice();
                    ruleInstance.recordPoint(player1, i, dice1);

                    System.out.println("結果：");
                    for (int point : dice1) {
                        System.out.print(point + " ");
                    }

                    System.out.println("\n玩家2按enter骰骰子：");
                    sc.nextLine();
                    int[] dice2 = diceGame.roleDice();
                    ruleInstance.recordPoint(player2, i, dice2);

                    System.out.println("結果：");
                    for (int point : dice2) {
                        System.out.print(point + " ");
                    }
                    int player1Result = diceGame.getResultRecord()[player1][i];
                    int player2Result = diceGame.getResultRecord()[player2][i];

                    if (player1Result > player2Result) {
                        player1WinTime++;
                    } else if (player1Result < player2Result) {
                        player2WinTime++;
                    }
                    System.out.printf("\n玩家1：贏%d次，玩家2：贏%d次\n", player1WinTime, player2WinTime);
                    System.out.println("\n========================");
                }
                System.out.println("結果發表：");
                ruleInstance.showWinner();
            }

            System.out.println("開始下一局？1.是 2.否");
            int nextGameStart = SelectCheck.filterSelection(1,2,sc);
            if(nextGameStart==2){
                isContinue = false;
            }
        }

    }

    public static DiceGame.Rule getRule(DiceGame diceGame, int select) {
        switch (select) {
            case BIGGER_WIN:
                return diceGame.new BiggerWin();
            case SMALLER_WIN:
                return diceGame.new SmallerWin();
            case HAVE_MOST_POINT_WIN:
                return diceGame.new MostWin();
        }
        return null;
    }
}
