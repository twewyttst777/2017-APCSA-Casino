import java.util.*;
public class Bingo{
    public void playBingo(Player player, int competitors){
        Scanner reader = new Scanner(System.in);
        System.out.println("Welcome to Bingo!");
        System.out.println("How many cards would you like to buy?");
        int cardNumber = reader.nextInt();
        int[][][] cards = new int[cardNumber][5][5];
        for(int i = 0; i < cardNumber; i++){
            for(int j = 0; j < 5; j++){
                ArrayList<Integer> nums = new ArrayList<Integer>();
                for(int k = 1; k <= 15; k++){
                    nums.add(k);
                }
                Collections.shuffle(nums);
                for(int r = 0; r < 5; r++){
                    cards[i][r][j] = nums.remove(0) + (15 * j);
                }
            }
        }
        showCards(cards);
    }

    public static void showCards(int[][][] cards){
        for(int[][] a : cards){
            for(int[] b : a){
                for(int c : b){
                    System.out.print(c + " ");
                }
                System.out.println("");
            }
            System.out.println("");
        }
    }
}
