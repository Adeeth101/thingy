package comp1110.ass2;

import java.util.ArrayList;

/**
 * Authored by Cameron Werner u7265364
 */

public class BoardOther {
    private RugSimple [] [] rugs;

    public BoardOther(RugSimple [] [] rugs){
        this.rugs = rugs;
    }

    public BoardOther(){
        this.rugs = new RugSimple[7][7];
    }

    public BoardOther(String boardString){
        String cut = boardString.substring(37,boardString.length());
        RugSimple [] [] boardA = new RugSimple [7] [7];
        char [] boardC = cut.toCharArray();
        RugSimple [] rugS = new RugSimple [49];
        for(int i = 0; i < boardC.length; i += 3){
            char color = boardC[i];
            char [] idC = {boardC[i+1], boardC[i+2]};
            String idS = new String(idC);
            int id = Integer.parseInt(idS);
            RugSimple rugSimple = new RugSimple(color,id);
            rugS [i/3] = rugSimple;
        }
        for(int x = 0; x < 49; x++){
            boardA [(x / 7)] [(x % 7)] = rugS [x];
        }
        this.rugs = boardA;
    }

    public RugSimple[][] getRugs() {
        return rugs;
    }

    public RugSimple getRug(int x, int y) {
        RugSimple [] [] board = rugs;
        RugSimple rug = board [x] [y];
        return rug;
    }

    public boolean isRugAllowed(Rug rug, Assam assam){
        IntPair sec1 = rug.getSection1();
        IntPair sec2 = rug.getSection2();
        IntPair aPos = assam.getPosition();
        RugSimple rugSimple = new RugSimple(rug);
        if(sec1.equals(sec2)){
            return false;
        }
        if(sec1.getX() < 0 || sec1.getX() > 6 || sec1.getY() < 0 || sec1.getY() > 6){
            return false;
        }
        if(sec1.equals(aPos) || sec2.equals(aPos)){
            return false;
        }
        if(isRugOnBoard(rugSimple)){
            return false;
        }
        if((sec1.getX() == aPos.getX() || sec1.getX() == aPos.getX() +1 || sec1.getX() == aPos.getX() -1) &&
                (sec1.getY() == aPos.getY() || sec1.getY() == aPos.getY() +1 || sec1.getY() == aPos.getY() -1)){

            if ((getRug(sec1.getX(),sec1.getY()).getColour() == 'n')
                    || (getRug(sec2.getX(),sec2.getY()).getColour() == 'n')){
                return true;
            }

            if(((getRug(sec2.getX(), sec2.getY())).getColour() == (getRug(sec1.getX(), sec1.getY())).getColour())
                    && ((getRug(sec2.getX(), sec2.getY())).getId() == (getRug(sec1.getX(), sec1.getY())).getId())) {
                return false;
            } else return true;

        } else {
            return false;
        }
    }

    public int paymentAmount (IntPair coords){
        int x = coords.getX();
        int y = coords.getY();
        RugSimple rug = getRug(x, y);
        char colour = rug.getColour();
        if (colour == 'n'){
            return 0;
        }else {
            ArrayList<IntPair> intPairs = new ArrayList<>();
            ArrayList<IntPair> used = new ArrayList<>();
            intPairs.add(coords);
            return adjacentMatchingRugs(intPairs, used, 0).size();
        }
    }

    public ArrayList<IntPair> adjacentMatchingRugs (ArrayList<IntPair> adjacent, ArrayList<IntPair> used, int count){
        ArrayList<IntPair> newAdjacent = adjacent;
        ArrayList<IntPair> allMatches = new ArrayList<>();
        ArrayList<IntPair> filteredMatches = new ArrayList<>();

        for (IntPair intPair : adjacent){
            ArrayList<IntPair> matches = matching(intPair);
            allMatches.addAll(matches);

        }
        for(IntPair match : allMatches){
            if(!used.contains(match) && !filteredMatches.contains(match)){
                filteredMatches.add(match);
            }
        }
        newAdjacent.addAll(filteredMatches);

        if(count > 32){
            return newAdjacent;
        }else {
            return adjacentMatchingRugs(newAdjacent, adjacent, count+1);
        }
    }

    public ArrayList<IntPair> matching (IntPair coords){
        ArrayList<IntPair> neighbours = new ArrayList<>();
        int x = coords.getX();
        int y = coords.getY();
        RugSimple rug = getRug(x, y);
        char colour = rug.getColour();
        ArrayList<IntPair> possibleNeighbours = new ArrayList<>();
        if(!(x+1 > 6)){
            possibleNeighbours.add(new IntPair(x+1,y));
        }
        if(!(x-1 < 0)){
            possibleNeighbours.add(new IntPair(x-1,y));
        }
        if(!(y+1 > 6)){
            possibleNeighbours.add(new IntPair(x,y+1));
        }
        if(!(y-1 < 0)){
            possibleNeighbours.add(new IntPair(x,y-1));
        }

        for (IntPair neighbour: possibleNeighbours) {
            if(getRug(neighbour.getX(),neighbour.getY()).getColour() == colour){
                neighbours.add(neighbour);
            }
        }
        return neighbours;
    }


    public boolean isRugOnBoard(RugSimple rug){
        for(int x = 0; x < 7; x++){
            for(int y = 0; y < 7; y++){
                if(getRug(x,y).equalRugS(rug)){
                    return true;
                }
            }
        }
        return false;
    }

    public RugSimple [] toArray(){
        RugSimple [] rugArray = new RugSimple[49];
        int c = 0;
        for(RugSimple [] rugA : rugs){
            for(RugSimple rug : rugA){
                rugArray [c] = rug;
                c++;
            }
        }
        return rugArray;
    }

    public void placeRug(Rug rug){
        RugSimple rugSimple = new RugSimple(rug);
        IntPair sec1 = rug.getSection1();
        IntPair sec2 = rug.getSection2();
        rugs [sec1.getX()] [sec1.getY()] = rugSimple;
        rugs [sec2.getX()] [sec2.getY()] = rugSimple;
    }

    public void randomPlaceRug(char colour, int id, Assam assam) {
        int min = -1;
        int max = 1;
        int random1 = (int)Math.floor(Math.random() * (max - min + 1) + min);
        int random2 = (int)Math.floor(Math.random() * (max - min + 1) + min);
        int random3 = (int)Math.floor(Math.random() * (max - min + 1) + min);
        int random4 = (int)Math.floor(Math.random() * (max - min + 1) + min);

        IntPair coords = assam.getPosition();
        int ax = coords.getX();
        int ay = coords.getY();
        int x = ax + random1;
        int y = ay + random2;
        int x2 = x + random3;
        int y2 = y + random4;
        Rug rug = new Rug(colour,id, new IntPair(x,y),new IntPair(x2,y2));
        if(isRugAllowed(rug, assam)){
            placeRug(rug);
        }else{
            randomPlaceRug(colour,id,assam);
        }
    }


    public String toString(){
        String string = "B";
        for(int x = 0; x < 7; x++){
            for(int y = 0; y < 7; y++){
                RugSimple rugSimple = rugs [x] [y];
                string = string + rugSimple.toString();
            }
        }
        return string;
    }
}
