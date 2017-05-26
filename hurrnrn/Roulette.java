import java.util.*;
public class Roulette{
    private ArrayList<RouletteNumber> wheel = new ArrayList<RouletteNumber>();
    public Roulette(){
        for(int i = 1; i <= 36; i++){
            if(i%2==0){
                if(i <= 10){
                    wheel.add(new RouletteNumber(i,"black"));
                } else if(i <= 18){
                    wheel.add(new RouletteNumber(i,"red"));
                } else if(i <= 28){
                    wheel.add(new RouletteNumber(i,"red"));
                } else{
                    wheel.add(new RouletteNumber(i,"black"));
                }
            } else {
                if(i <= 10){
                    wheel.add(new RouletteNumber(i,"red"));
                } else if(i <= 18){
                    wheel.add(new RouletteNumber(i,"black"));
                } else if(i <= 28){
                    wheel.add(new RouletteNumber(i,"red"));
                } else{
                    wheel.add(new RouletteNumber(i,"black"));
                }
            }
        }
        wheel.add(new RouletteNumber(100, "green"));
        wheel.add(new RouletteNumber(1000, "green"));
    }

    public void play(Player player){
        Scanner reader = new Scanner(System.in);
        Scanner otherReader = new Scanner(System.in);
        boolean valid = false;
        boolean wonBet = false;
        System.out.println("How much will you bet");
        double betAmount = reader.nextDouble();
        double winnings = 0;
        do {
            System.out.println("How will you bet?");
            String bet = otherReader.nextLine();
            String betWord = "";
            if(bet.indexOf(" ") >= 0){
                betWord = bet.substring(0, bet.indexOf(" "));
            } else {
                betWord = bet;
            }
            switch(betWord){
                case "reds":
                RouletteNumber rolled = roll();
                if(rolled.getColor().equals("red")){
                    wonBet = true;
                    winnings = betAmount;
                }
                valid = true;
                break;

                case "blacks":
                rolled = roll();
                if(rolled.getColor().equals("black")){
                    wonBet = true;
                    winnings = betAmount;
                }
                valid = true;
                break;

                case "high":
                rolled = roll();
                if(rolled.getNumber() <= 18){
                    wonBet = true;
                    winnings = betAmount;
                }
                valid = true;
                break;

                case "low":
                rolled = roll();
                if(rolled.getNumber() >= 18 && rolled.getNumber() < 100){
                    wonBet = true;
                    winnings = betAmount;
                }
                valid = true;
                break;

                case "evens":
                rolled = roll();
                if(rolled.getNumber()%2 == 0){
                    wonBet = true;
                    winnings = betAmount;
                }
                valid = true;
                break;

                case "odds":
                rolled = roll();
                if(rolled.getNumber() % 2 == 1){
                    wonBet = true;
                    winnings = betAmount;
                }
                valid = true;
                break;

                case "types":
                System.out.println("Outside Bets: high, low, evens, odds, blacks, reds, dozen [position]");
                System.out.println("Inside Bets: straight [position]");
                break;

                /*case "info":
                betInfo(bet);
                break;*/
                default:
                System.out.println("Not a valid bet. Type \"types\" to see valid commands, and \"info\" followed by the desired bet type to see info on your bet.");
            }
            System.out.println(bet + " " + betWord);
        } while(!valid);

        if(wonBet){
            System.out.println("You won " + winnings);
            player.addMoney(winnings);
        } else {
            System.out.println("You lose haha lol sucker get rekt scrub come bakc in 2000 years xddddd!!one!11!");
            player.addMoney(-betAmount);
        }
    }

    /*public void betInfo(String bet){
        String betWord ="";
        if(bet.indexOf(" ") >= 0){
            betWord = bet.substring(bet.indexOf(" ") + 1);
        } else {
            betWord = bet;
        }
        String betMiddle = "";
        if(betWord.indexOf(" ") >= 0){
            betMiddle = betWord.substring(betWord.indexOf(" ") + 1);
        } else {
            betMiddle = betWord;
        }

        switch(betMiddle){
            case "reds":
            System.out.println("Bets that the ball will land on a Red number. 1:1 earnings.");
            break;

            case "blacks":
            System.out.println("Bets that the ball will land on a Black number. 1:1 earnings.");
            break;

            case "evens":
            System.out.println("Bets that the ball will land on a even number. 1:1 earnings.");
            break;

            case "odds":
            System.out.println("Bets that the ball will land on a odd number. 1:1 earnings.");
            break;

            case "high":
            System.out.println("Bets that the ball will land on a number greater than 18. 1:1 earnings.");
            break;

            case "low":
            System.out.println("Bets that the ball will land on a number less than or equal to 18 (Not including the zeroes). 1:1 earnings.");
            break;
            case "dozen":
            String number = bet.substring(6, bet.length() + 1);
            if(number.equals("dozen 1") || number.equals("dozen one")){
                System.out.println("Bets that the ball will land between 1 and 12 inclusive. 2:1 earnings.");
            } else if(number.equals("dozen 2") || number.equals("dozen two")){
                System.out.println("Bets that the ball will land between 13 and 24 inclusive. 2:1 earnings.");
            } else if(number.equals("dozen 3") || number.equals("dozen three")){
                System.out.println("Bets that the ball will land between 25 and 36 inclusive. 2:1 earnings.");
            } else {
                System.out.println("Example formatting for a dozen bet: dozen one. Must be between 1 and 3.");
            }
            break;
            
            default:
            System.out.println("wuzzat");
        }
        System.out.println(bet + " " + betWord);
    }*/

    public RouletteNumber roll(){
        return wheel.get((int) (Math.random() * 38));
    }
}