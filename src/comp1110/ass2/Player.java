package comp1110.ass2;

public class Player {
    private char colour;
    private int money;
    private int remainRugs;
    private static boolean inGame;

    public Player(char colour, int money, int remainRugs, boolean inGame){
        this.colour = colour;
        this.money = money;
        this.remainRugs = remainRugs;
        this.inGame = inGame;
    }
    public Player(String player){
        this.colour = player.charAt(1);
        String money = player.substring(2,5);
        String rugs = player.substring(5,7);
        char inGame = player.charAt(7);
        this.money = Integer.parseInt(money);
        this.remainRugs= Integer.parseInt(rugs);
        if (inGame == 'i'){
            this.inGame = true;
        }
        else {
            this.inGame = false;
        }
    }

    public char getColour() {
        return colour;
    }

    public int getMoney() {
        return money;
    }

    public int getRemainRugs() {
        return remainRugs;
    }

    public static boolean isInGame() {
        return inGame;
    }

    public void changeMoney(int change){
        money = money + change;
    }

    public void useRug(){
        remainRugs = (remainRugs - 1);
    }

    public void outGame(){
        inGame = false;
    }

    public String toString(){
        return colour + " money: " + Integer.toString(money) + " rugs: " + Integer.toString(remainRugs);
    }

    public String toPlayerString(){
        char[] string = new char[8];
        string[0] = 'P';
        string[1] = this.colour;
        String numberStr = String.format("%03d", this.money);
        char[] xxx = numberStr.toCharArray();
        string[2] = xxx[0];
        string[3] = xxx[1];
        string[4] = xxx[2];
        String numberStr2 = String.format("%02d", this.remainRugs);
        char[] yyy = numberStr2.toCharArray();
        string[5] = yyy[0];
        string[6] = yyy[1];
        if (this.inGame){
            string[7] = 'i';
        }
        else{
            string[7] = 'o';
        }
        return new String(string);
    }
}
