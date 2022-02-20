import java.util.Scanner;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/20 下午 09:19
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean on = true;
        String goOn;

        System.out.println("-----------------------骰子遊戲-----------------------");
        System.out.println("請輸入骰子數量(為少3個，最多6個)：");
        int diceNum = SelectCheck.filterSelection(3, 6);
        System.out.println("請選擇遊戲規則\n1.點數大者勝\n2.點數小者勝\n3.相同點數多者勝");
        int rule = SelectCheck.filterSelection(1, 3);
        System.out.println("請輸入回合數(必須為奇數且大於0，若輸入非奇數默認回合3)：");
        int roundTime = SelectCheck.filterSelection(1, Integer.MAX_VALUE);

        DiceGame diceGame = new DiceGame(diceNum, roundTime);
        DiceGame.Rule ruleInstance = getRule(diceGame, rule);
        diceGame.setRule(getRule(diceGame, rule));

        System.out.println("遊戲開始：");
        for (int i = 0; i < roundTime; i++) {
            System.out.printf("第%d回合：\n",i+1);
            System.out.println("玩家1按enter骰骰子：");
            goOn = sc.nextLine();
            int[] dice1 = diceGame.roleDice();
            ruleInstance.recordPoint(0,i,dice1);
            System.out.println("結果：");
            for(int point :dice1){
                System.out.print(point + " ");
            }
            System.out.println();

            System.out.println("玩家2按enter骰骰子：");
            goOn = sc.nextLine();
            int[] dice2 = diceGame.roleDice();
            ruleInstance.recordPoint(1,i,dice2);
            System.out.println("結果：");
            for(int point :dice2){
                System.out.print(point + " ");
            }
            System.out.println("\n========================");
        }

        System.out.println("結果發表：");
        ruleInstance.showWinner();
    }

    public static DiceGame.Rule getRule(DiceGame diceGame, int select) {
        switch (select) {
            case 1:
                return diceGame.new BiggerWin();
            case 2:
                return diceGame.new SmallerWin();
            case 3:
                return diceGame.new MostWin();
        }
        return null;
    }
}
