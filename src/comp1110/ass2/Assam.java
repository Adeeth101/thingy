package comp1110.ass2;

/*
 * Authored by Cameron Werner u7265364
 */


public class Assam {
    private IntPair position;
    private int direction;

    public Assam(IntPair position, int direction ) {
        this.position = position;
        this.direction = direction;
    }

    public Assam(String assam){
        char[] charList = assam.toCharArray();
        int x = Character.getNumericValue(charList[1]);
        int y = Character.getNumericValue(charList[2]);
        char d = charList[3];
        this.position = new IntPair(x, y);
        if (d == 'N'){
            this.direction = 0;
        }else if (d == 'E'){
            this.direction = 90;
        } else if (d =='S') {
            this.direction = 180;
        } else if (d == 'W') {
            this.direction = 270;
        }
    }

    public IntPair getPosition(){
        return position;
    }

    public int getDirection(){
        return direction;
    }

    /*
     * Code takes a new heading for the assam as an int input
     * and changes the direction that the assam is heading
     */

    public void changeDirection(int heading){
        if(heading % 90 == 0 && !(heading == 180)){
            int d = direction + heading;
            while (d >= 360){
                d -= 360;
            }
            this.direction = d;
        }
    }

    /*
     * Code takes the distance that the assam is
     * going to move and then updates the assam's position
     */

    public void move(int distance) {
        IntPair pos = position;
        int x = pos.getX();
        int y = pos.getY();

        IntPair end = new IntPair(x,y);

        /*
         * this code changes the assam's position but doesn't
         * account for the edges of the board
         */

        if(direction == 0){
            end.setY(y - distance);
        } else if (direction == 90) {
            end.setX(x + distance);
        } else if (direction == 180) {
            end.setY(y + distance);
        } else {
            end.setX(x - distance);
        }

        /*
         * this code takes the assam's new position and then changes
         * it so that the assam stays on the board with each if loop
         * for a different edge that it goes off
         */

        //going off the top edge
        if(end.getY() < 0){
            if(end.getX() == 0 || end.getX() == 2 || end.getX() == 4){
                end.setX(end.getX()+1);
                end.setY(-end.getY()-1);
                this.direction = 180;
            } else if (end.getX() == 1 || end.getX() == 3 || end.getX() == 5){
                end.setX(end.getX()-1);
                end.setY(-end.getY()-1);
                this.direction = 180;
            } else if (end.getX() == 6) {
                end.setX(7+end.getY());
                end.setY(0);
                this.direction = 270;
            }
        }
        //going off the bottom edge
        if(end.getY() > 6){
            if(end.getX() == 6 || end.getX() == 2 || end.getX() == 4){
                end.setX(end.getX()-1);
                end.setY(6 - (end.getY()-7));
                this.direction = 0;
            } else if (end.getX() == 1 || end.getX() == 3 || end.getX() == 5){
                end.setX(end.getX()+1);
                end.setY(6 - (end.getY()-7));
                this.direction = 0;
            } else if (end.getX() == 0) {
                end.setX(end.getY()-7);
                end.setY(6);
                this.direction = 90;
            }
        }
        //going off the left edge
        if(end.getX() < 0){
            if(end.getY() == 0 || end.getY() == 2 || end.getY() == 4){
                end.setY(end.getY()+1);
                end.setX(-end.getX()-1);
                this.direction = 90;
            } else if (end.getY() == 1 || end.getY() == 3 || end.getY() == 5){
                end.setY(end.getY()-1);
                end.setX(-end.getX()-1);
                this.direction = 90;
            } else if (end.getY() == 6) {
                end.setY(7+end.getX());
                end.setX(0);
                this.direction = 0;
            }
        }
        //going off the right edge
        if(end.getX() > 6){
            if(end.getY() == 6 || end.getY() == 2 || end.getY() == 4){
                end.setY(end.getY()-1);
                end.setX(6 - (end.getX()-7));
                this.direction = 270;
            } else if (end.getY() == 1 || end.getY() == 3 || end.getY() == 5){
                end.setY(end.getY()+1);
                end.setX(6 - (end.getX()-7));
                this.direction = 270;
            } else if (end.getY() == 0) {
                end.setY(end.getX()-7);
                end.setX(6);
                this.direction = 180;
            }
        }
        this.position = end;
    }

    public void randomDirection() {
        int min = 0;
        int max = 2;
        int random = (int)Math.floor(Math.random() * (max - min + 1) + min);
        switch (random){
            case 1 -> changeDirection(90);
            case 2 -> changeDirection(270);
        }
    }

    public String toString(){

        int x = position.getX();
        int y = position.getY();
        String pos = x + String.valueOf(y);

        if (direction == 0){
            return ("A" + pos + "N");

        }else if (direction == 90){
            return ("A" + pos + "E");

        }else if(direction == 180){
            return ("A" + pos + "S");

        }else{
            return ("A" + pos + "W");

        }
    }


}
