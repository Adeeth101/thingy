package comp1110.ass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameState {
    private ArrayList<Player> players;
    private Assam assam;
    private BoardOther board;
    private char currentColour;
    private int rugCount;

    public GameState(){
        players = new ArrayList<>();
        List<Character> colours = new ArrayList<>();
        colours.add('c');
        colours.add('y');
        colours.add('r');
        colours.add('p');
        for (int i=0 ; i < 4; i++){
            this.players.add(new Player(colours.get(i), 30, 15, true));
        }
        this.assam = new Assam(new IntPair(3,3), 180);
        this.board = new BoardOther();
        this.rugCount = 0;
        this.currentColour = 'c';
    }

    public GameState(Player[] players, Assam assam, BoardOther board, BoardOther boardOther){
        this.players = new ArrayList<>(Arrays.asList(players));
        this.assam = assam;
        this.board = board;
    }

    public GameState(String gameString){
        String playerString = gameString.substring(0, 32);
        String assamString = gameString.substring(32,36);
        String boardString = gameString.substring(36);
        this.players = new ArrayList<Player>();
        for (int i = 0; i < playerString.length(); i += 8) {
            String newPlayer = playerString.substring(i, Math.min(i + 8, playerString.length()));
            players.add(new Player(newPlayer));
        }
        assam = new Assam(assamString);
        board = new BoardOther(gameString);
    }

    public Assam getAssam() {
        return assam;
    }

    public BoardOther getBoard() {
        return board;
    }

    public void setBoardOther(BoardOther boardOther) {
        this.board = board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrent() {
        for (Player player : players) {
            if (player.getColour() == currentColour) {
                return player;
            }
        }
        return null;
    }

    public void setCurrentColour(char currentColour) {
        this.currentColour = currentColour;
    }

    public void setPlayers(int numPlayers) {
            for (int i = numPlayers; i < 4; i++) {
                System.out.println(i);
                this.players.get(i).outGame();
            }
        }

    public void nextPlayer() {
        int position = players.indexOf(this.getCurrent());
        int nextPosition = (position + 1) % players.size();
        while (nextPosition != position) {
            if (players.get(nextPosition).isInGame()) {
                currentColour = players.get(nextPosition).getColour();
                break;
            }
            nextPosition = (nextPosition + 1) % players.size();
        }
    }


    public String toString(){
        String string = "";
        for (Player player : this.players){
            string += player.toPlayerString();
        }
        string += (this.assam).toString();
        string += this.board.toString();
        return string;
    }

    public void rotateAssam(int rotation){
        this.assam.changeDirection(rotation);
    }

    public void moveAssam(int number){
        this.assam.move(number);
        //this.assam = new Assam(Marrakech.moveAssam(assam.toString(), number));
    }
    public int getRugCount(){
        return rugCount;
    }
    public void placeRug(Rug rug){
        if(board.isRugAllowed(rug, assam) ) {
            this.board.placeRug(rug);
            this.rugCount++;
            this.getCurrent().useRug();
        }
    }

    public void paySomeone(){
        int amount = board.paymentAmount(this.getAssam().getPosition());
        if (amount != 0){
            switch ((board.getRug(assam.getPosition().getX(), assam.getPosition().getY()).getColour())){
                case 'c':
                    if (players.get(0).isInGame()){
                        players.get(0).changeMoney(amount);
                    }
                case 'y':
                    if (players.get(1).isInGame()){
                        players.get(1).changeMoney(amount);
                    }
                case 'r':
                    if (players.get(2).isInGame()){
                        players.get(2).changeMoney(amount);
                    }
                case 'p':
                    if (players.get(3).isInGame()){
                        players.get(3).changeMoney(amount);
                    }
            }

        }
        this.getCurrent().changeMoney(-amount);
    }
}
