import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Keno{
    private int[][] payTable = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {3, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                {0, 15, 2, 2, 0, 0, 0, 0, 0, 0},
                                {0, 0, 48, 5, 3, 3, 1, 0, 0, 0},
                                {0, 0, 0, 100, 13, 4, 2, 2, 1, 0},
                                {0, 0, 0, 0, 838, 75, 22, 13, 6, 5},
                                {0, 0, 0, 0, 0, 1660, 422, 100, 44, 24},
                                {0, 0, 0, 0, 0, 0, 7000, 1670, 362, 146},
                                {0, 0, 0, 0, 0, 0, 0, 10000, 4700, 1000},
                                {0, 0, 0, 0, 0, 0, 0, 0, 10000, 4500},
                                {0, 0, 0, 0, 0, 0, 0, 0, 0, 10000}};
    public void playKeno(Player player){
        System.out.println("Welcome to Keno");
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("How many numbers do you want to pick?");
        int numbersPicked = reader.nextInt();
        while(numbersPicked > 10){
            System.out.println("You can only pick up to 10");
            numbersPicked = reader.nextInt(); //butt
        }
        System.out.println("How much money would you like to bet?");
        boolean cannotAfford = true;
        double moneyBet = 0;
        while(cannotAfford){
            moneyBet = reader.nextDouble();
            if(moneyBet <= player.checkWallet()){
                player.addMoney(-moneyBet);
                cannotAfford = false;
            } else {
                System.out.println("You don't have that much money");
            }
        }
        System.out.println("Pick some numbers");
        ArrayList<Integer> kenoPicks = new ArrayList<Integer>();
        int å = 0;
        while(å < numbersPicked){
            int picked = reader.nextInt();
            if(picked >= 1 && picked <= 80){
                kenoPicks.add(picked);
                å++;
            } else {
                System.out.println("Pick a number from 1 - 80");
            }
        }
        System.out.println("Pulling some numbers...");
        ArrayList<Integer> kenoList = new ArrayList<Integer>();
        for(int i = 1; i <= 80; i++){
            kenoList.add(i);
        }
        Collections.shuffle(kenoList);
        int catches = 0;
        for(int i = 0; i < 20; i++){
            int picked = kenoList.remove(0);
            System.out.println("Picked: " + picked);
            for(int j : kenoPicks){
                if(picked == j){
                    catches++;
                    break;
                }
            }
        }
        System.out.println("Catches: " + catches);
        double moneyEarned = payTable[catches][numbersPicked - 1] * moneyBet;
        System.out.println("You won " + moneyEarned + " Dollars!");
        player.addMoney(moneyEarned);
    }
}