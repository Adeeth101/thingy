package comp1110.ass2;

/**
 * Authored by Cameron Werner u7265364
 */

public class RugSimple {
    private final char colour;
    private final int id;

    public RugSimple(char colour, int id) {
        this.colour = colour;
        this.id = id;
    }
    public RugSimple(String rugstring) {
        char[] rugC = rugstring.toCharArray();
        this.colour = rugC[0];
        String id = rugstring.substring(1,2);
        this.id = Integer.parseInt(id);
    }

    public  RugSimple (Rug rug){
        this.colour = rug.getColour();
        this.id = rug.getId();
    }


    public int getId() {
        return id;
    }

    public char getColour() {
        return colour;
    }

    public boolean equalRugS(RugSimple rug) {
        if(rug.getId() == id && rug.getColour() == colour){
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
        if(id < 10){
            return colour + "0" + id;
        } else {
            return colour + String.valueOf(id);
        }
    }
}
