package hurrnrn; 

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;
public class Roulette{
    public ArrayList<RouletteNum> wheel;
    private String[] wheelList;
    private RouletteWheel theWheel;
    private final double ANGLE_TO_NUMBER = 38 / (2 * Math.PI);
    public Roulette(Player player){
        wheel = new ArrayList<RouletteNum>();
        RouletteWheel theWheel = new RouletteWheel();
        try{
            ReadFile wheelGen = new ReadFile("rouletteWheel.txt");
            wheelList = wheelGen.OpenFile();
        } catch(IOException e){
            wheelList = new String[0];
            System.out.println(e.getMessage());
        }
        for(String numberName : wheelList){
            int value = Integer.parseInt(numberName.substring(0,4).trim());
            String color = numberName.substring(4); // g is green, b is black, r is red
            wheel.add(new RouletteNum(color, value));
        }
        boolean keepPlaying = true;
        while(keepPlaying){
            Scanner reader = new Scanner(System.in);
            System.out.println("How much would you like to bet?");
            double bet = reader.nextDouble();
            while(bet > player.checkWallet()){
                System.out.println("You don't have that much money.");
                bet = reader.nextDouble();
            }
            reader.nextLine(); //tosses out a nextLine because otherwise doesn't work
            //Doesn't subtract money immediately because that's how real casinos do it.
            System.out.println("How would you like to bet? (Enter \"Bets\" to see types of bets and a brief description)");
            boolean valid = false;
            String betRecord = "";
            String playerBet = "";
            String word = "";
            while(!valid){
                playerBet = reader.nextLine().toLowerCase();
                if(playerBet.indexOf(" ") > 0){
                    word = playerBet.substring(0, playerBet.indexOf(" "));
                } else {
                    word = playerBet;
                }

                switch(word){
                    case "reds":
                    valid = true;
                    break;

                    case "blacks":
                    valid = true;
                    break;

                    case "evens":
                    valid = true;
                    break;

                    case "odds":
                    valid = true;
                    break;

                    case "high":
                    valid = true;
                    break;

                    case "low":
                    valid = true;
                    break;

                    case "single":
                    if(playerBet.indexOf(" ") > 0){
                        betRecord = playerBet.substring(playerBet.indexOf(" ") + 1);
                    }
                    if(Integer.parseInt(betRecord) > 36 || Integer.parseInt(betRecord) < 0){
                        System.out.println("I'm sorry, we don't offer that kind of bet. Type \"Bets\" to see what kind of bets we do offer.");
                        betRecord = "";
                    } else {
                        valid = true;
                    }
                    break;

                    case "dozen":
                    if(playerBet.indexOf(" ") > 0){
                        betRecord = playerBet.substring(playerBet.indexOf(" ") + 1);
                    }
                    if(!(betRecord.equals("1") || betRecord.equals("3") || betRecord.equals("2"))){
                        System.out.println("I'm sorry, we don't offer that kind of bet. Type \"Bets\" to see what kind of bets we do offer.");
                        betRecord = "";
                    } else {
                        valid = true;
                    }
                    break;

                    case "bets":
                    System.out.println("Reds: Bets that the ball will land on a red square. Payout: 1 to 1");
                    System.out.println("Blacks: Bets that the ball will land on a black square. Payout: 1 to 1");
                    System.out.println("Evens: Bets that the ball will land on an even square. Payout: 1 to 1");
                    System.out.println("Odds: Bets that the ball will land on an odd square. Payout: 1 to 1");
                    System.out.println("Lows: Bets that the ball will land on a square from 1 - 18. Payout: 1 to 1");
                    System.out.println("Highs: Bets that the ball will land on a square from 19 - 36. Payout: 1 to 1");
                    System.out.println("Single [your number]: Bets that the ball will land on a specific number. Payout: 35 to 1");
                    System.out.println("Dozen [1 2 or 3]: Bets that the ball will land on the first (1 - 12), second (13 - 24), or third (25 - 36) dozen. Payout: 2 to 1");
                    break;

                    default:
                    System.out.println("I'm sorry, we don't offer that kind of bet. Type \"Bets\" to see what kind of bets we do offer.");
                    break;
                }
            }

            RouletteNum numGot = rollWheel(theWheel);

            switch(word){
                case "reds":
                if(numGot.getColor().equals("r")){
                    System.out.println("You won $" + bet + "!");
                    player.addMoney(bet);
                } else {
                    System.out.println("Better luck next time.");
                    player.addMoney(-bet);
                }
                break;

                case "blacks":
                if(numGot.getColor().equals("b")){
                    System.out.println("You won $" + bet + "!");
                    player.addMoney(bet);
                } else {
                    System.out.println("Better luck next time.");
                    player.addMoney(-bet);
                }
                break;

                case "evens":
                if(numGot.getNumber() % 2 == 0){
                    System.out.println("You won $" + bet + "!");
                    player.addMoney(bet);
                } else {
                    System.out.println("Better luck next time.");
                    player.addMoney(-bet);
                }
                break; 
                case "odds":
                if(numGot.getNumber() % 2 == 1){
                    System.out.println("You won $" + bet + "!");
                    player.addMoney(bet);
                } else {
                    System.out.println("Better luck next time.");
                    player.addMoney(-bet);
                }
                break;
                case "high":

                if(numGot.getNumber() > 18 && numGot.getNumber() < 37){
                    System.out.println("You won $" + bet + "!");
                    player.addMoney(bet);
                } else {
                    System.out.println("Better luck next time.");
                    player.addMoney(-bet);
                }
                break;

                case "low":
                if(numGot.getNumber() < 19){
                    System.out.println("You won $" + bet + "!");
                    player.addMoney(bet);
                } else {
                    System.out.println("Better luck next time.");
                    player.addMoney(-bet);
                }
                break;

                case "single":
                if(betRecord.equals("0") && numGot.getNumber() == 100){
                    System.out.println("You won $" + bet * 35 + "!");
                    player.addMoney(bet * 35);
                } else if(betRecord.equals("00") && numGot.getNumber() == 1000){
                    System.out.println("You won $" + bet * 35 + "!");
                    player.addMoney(bet * 35);
                } else if(numGot.getNumber() == Integer.parseInt(betRecord)){
                    System.out.println("You won $" + bet * 35 + "!");
                    player.addMoney(bet * 35);
                } else {
                    System.out.println("Better luck next time.");
                    player.addMoney(-bet);
                }
                break;

                case "dozen":
                int dozen = Integer.parseInt(betRecord);
                if(dozen == 1){
                    if(numGot.getNumber() < 19){
                        System.out.println("You won $" + bet * 2 + "!");
                        player.addMoney(bet * 2);
                    } else {
                        System.out.println("Better luck next time.");
                        player.addMoney(-bet);
                    }
                } else if (dozen == 2){
                    if(numGot.getNumber() > 18 && numGot.getNumber() < 25){
                        System.out.println("You won $" + bet * 2 + "!");
                        player.addMoney(bet * 2);
                    } else {
                        System.out.println("Better luck next time.");
                        player.addMoney(-bet);
                    }
                } else {
                    if(numGot.getNumber() > 24){
                        System.out.println("You won $" + bet * 2 + "!");
                        player.addMoney(bet * 2);
                    } else {
                        System.out.println("Better luck next time.");
                        player.addMoney(-bet);
                    }
                }
            }
            boolean correct = false;
            System.out.println("Keep Playing or nah");
            while(!correct){
                String playerInput = reader.nextLine().toLowerCase();
                switch(playerInput){
                    case "y":
                    correct = true;
                    break;

                    case "n":
                    System.out.println("Bye");
                    return;

                    case "yea":
                    correct = true;
                    break;

                    case "nah":
                    System.out.println("Bye");
                    return;

                    case "keep playing":
                    correct = true;
                    break;

                    default:
                    System.out.println("Sorry, I don't understand what you're saying. Say it again but different.");
                }
            }
        }
    }

    public RouletteNum rollWheel(RouletteWheel toRoll){
        toRoll.rollAgain();
        try
        {
            Thread.sleep(15000);
        } 
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        return wheel.get((int)(toRoll.returnAngle() * 38 / (2 * Math.PI)));
    }
}