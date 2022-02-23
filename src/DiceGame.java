import java.util.List;
import java.util.Random;

/**
 * @author Lillian
 * @Description
 * @date 2022/2/20 下午 07:09
 */
public class DiceGame {
    private int diceNum;
    private Rule rule;
    private int roundTime;
    private final int playerNum = 2;
    /**
     * [0]第幾個玩家
     * [1]第幾回合
     */
    private int[][] resultRecord;

    public abstract class Rule {
        /**
         * @param playerId 第幾個玩家的紀錄
         * @param roundTh  第幾回合
         * @param points   骰出的結果
         * @return
         */
        public abstract void recordPoint(int playerId, int roundTh, int[] points);

        /**
         * 根據規則印出贏家
         */
        abstract void showWinner();
    }

    public class BiggerWin extends Rule {
        /**
         * 紀錄每回合單個玩家的總和
         */
        @Override
        public void recordPoint(int playerId, int roundTh, int[] points) {
            for (int i = 0; i < points.length; i++) {
                resultRecord[playerId][roundTh] += points[i];
            }
        }

        /**
         * 看每個回合誰的點數大
         */
        @Override
        void showWinner() {
            int[] winTime = new int[playerNum];
            for (int i = 0; i < roundTime; i++) {
                int player0 = resultRecord[0][i];
                int player1 = resultRecord[1][i];
                if (player0 > player1) {
                    winTime[0]++;
                } else if (player0 < player1) {
                    winTime[1]++;
                }
            }

            if(winTime[0]>winTime[1]){
                System.out.println("\\\\玩家1獲勝//");
            }else if(winTime[0]<winTime[1]){
                System.out.println("\\\\玩家2獲勝//");
            }else{
                System.out.println("平手");
            }
        }
    }

    public class SmallerWin extends Rule {
        /**
         * 紀錄總和
         */
        @Override
        public void recordPoint(int playerId, int roundTh, int[] points) {
            for (int i = 0; i < points.length; i++) {
                resultRecord[playerId][roundTh] += points[i];
            }
        }

        /**
         * 看每個回合誰的點數小
         */
        @Override
        void showWinner() {
            int[] winTime = new int[playerNum];
            for (int i = 0; i < roundTime; i++) {
                int player0 = resultRecord[0][i];
                int player1 = resultRecord[1][i];
                if (player0 < player1) {
                    winTime[0]++;
                } else if (player0 > player1) {
                    winTime[1]++;
                }
            }

            if(winTime[0]>winTime[1]){
                System.out.println("\\\\玩家1獲勝//");
            }else if(winTime[0]<winTime[1]){
                System.out.println("\\\\玩家2獲勝//");
            }else{
                System.out.println("平手");
            }
        }
    }

    public class MostWin extends Rule {
        /**
         * 紀錄出現最多的次數
         */
        @Override
        public void recordPoint(int playerId, int roundTh, int[] points) {
            int[] maxCountRecord = new int[6];
            for (int i = 0; i < points.length; i++) {
                 int point = points[i]; //拿到骰子點數
                 maxCountRecord[point - 1]++; //次數++(點數1->index 0)
            }

            int maxCount = 0; //出現最多次數
            for (int i = 0; i < maxCountRecord.length; i++) {
                if (maxCountRecord[i] > maxCount) {
                    maxCount = maxCountRecord[i];
                }
            }
            resultRecord[playerId][roundTh] = maxCount;
        }

        /**
         * 看每個回合誰一樣的點數多
         */
        @Override
        void showWinner() {
            int[] winTime = new int[playerNum];
            for (int i = 0; i < roundTime; i++) {
                int player0 = resultRecord[0][i];
                int player1 = resultRecord[1][i];
                if (player0 > player1) {
                    winTime[0]++;
                } else if (player0 < player1) {
                    winTime[1]++;
                }
            }

            if(winTime[0]>winTime[1]){
                System.out.println("\\\\玩家1獲勝//");
            }else if(winTime[0]<winTime[1]){
                System.out.println("\\\\玩家2獲勝//");
            }else{
                System.out.println("平手");
            }
        }
    }

    /**
     * @return 依照骰子數骰骰子
     */
    public int[] roleDice() {
        int[] result = new int[diceNum];
        for (int i = 0; i < diceNum; i++) {
            result[i] = new Random().nextInt(5) + 1;
        }
        return result;
    }


    public DiceGame(int diceNum, int roundTime) {
        if (diceNum > 6 || diceNum < 3) {
            this.diceNum = 3;
        } else {
            this.diceNum = diceNum;
        }

        if (roundTime < 0 || roundTime % 2 == 0) {
            this.roundTime = 3;
        } else {
            this.roundTime = roundTime;
        }
        resultRecord = new int[playerNum][roundTime];
    }

    public int getDiceNum() {
        return diceNum;
    }

    public void setDiceNum(int diceNum) {
        this.diceNum = diceNum;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(int roundTime) {
        this.roundTime = roundTime;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public int[][] getResultRecord() {
        return resultRecord;
    }

    public void setResultRecord(int[][] resultRecord) {
        this.resultRecord = resultRecord;
    }
}
