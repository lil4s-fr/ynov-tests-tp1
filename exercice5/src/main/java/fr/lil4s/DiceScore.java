package fr.lil4s;

public class DiceScore {
    private final Dice dice;

    public DiceScore(Dice dice) {
        this.dice = dice;
    }

    public int getScore(){
        int firstRoll = dice.getRoll();
        int secondRoll = dice.getRoll();

        if (firstRoll == secondRoll){
            if (firstRoll == 6){
                return 30;
            }

            return firstRoll + secondRoll + 10;
        }
        else {
            return Math.max(firstRoll, secondRoll);
        }
    }
}
