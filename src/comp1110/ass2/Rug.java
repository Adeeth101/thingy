package comp1110.ass2;


public class Rug {
    private IntPair section1;
    private IntPair section2;
    private char colour;
    private int id;
    public Rug(char colour, int id, IntPair s1, IntPair s2){
        this.colour = colour;
        this.id = id;
        this.section1 = s1;
        this.section2 = s2;
    }

    public Rug(String rugstring){
        char [] rugC = rugstring.toCharArray();
        this.colour = rugC[0];
        char [] id = {rugC[1], rugC[2]};
        String idS = new String(id);
        this.id =Integer.parseInt(idS);
        int sectionx1 = Character.getNumericValue(rugC[3]);
        int sectiony1 = Character.getNumericValue(rugC[4]);
        this.section1 = new IntPair(sectionx1,sectiony1);
        int sectionx2 = Character.getNumericValue(rugC[5]);
        int sectiony2 = Character.getNumericValue(rugC[6]);
        this.section2 = new IntPair(sectionx2,sectiony2);
    }

    public char getColour() {return colour;}

    public IntPair getSection1() {
        return section1;
    }

    public IntPair getSection2() {
        return section2;
    }

    public String toString(){
        return null;
    }

    public int getId() {
        return id;
    }

    public int getAmount(String rugString){

        return 0;
    }
}
