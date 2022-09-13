package models;

import java.util.Random;

public class Dice {
    private int count;
    private int min;
    private int max;

    public Dice(int count, int min, int max) {
        this.count = count;
        this.min = min;
        this.max = max;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int rollDice(){

        int i=0, number = 0;

        while(i < count){
            Random random = new Random();
            int num = random.nextInt(min, max+1);
            number+=num;
            i++;
        }
        return number;
    }
}
