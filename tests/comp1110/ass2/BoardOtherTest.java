package comp1110.ass2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


public class BoardOtherTest {
    @Test
    public void getRugTest(){
        BoardOther boardOther = new BoardOther("Pc03015iPy03015iPp03015iPr03015iA31NBr00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00");
        RugSimple expectedRug = new RugSimple('r',0);
        RugSimple actualRug = boardOther.getRug(0,0);
        Assertions.assertTrue(expectedRug.equalRugS(actualRug));
    }

    @Test
    public void isRugOnBoardTrue(){
        BoardOther boardOther = new BoardOther("Pc03015iPy03015iPp03015iPr03015iA31NBr00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00");
        RugSimple rug = new RugSimple('r',0);
        Assertions.assertTrue(boardOther.isRugOnBoard(rug));
    }

    @Test
    public void isRugOnBoardFalse(){
        BoardOther boardOther = new BoardOther("Pc03015iPy03015iPp03015iPr03015iA31NBr00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00");
        RugSimple rug = new RugSimple('r',1);
        Assertions.assertFalse(boardOther.isRugOnBoard(rug));
    }

    @Test
    void isRugAllowedComplexFalse1() {
        BoardOther boardOther = new BoardOther("Pc02612iPy03112iPp03612iPr02713iA30NBn00n00y01n00n00n00n00n00y05y05r04n00n00n00p05c03c03r01n00n00n00p05y06y06p00n00n00n00n00r02n00n00n00n00n00n00n00n00c07c07n00n00n00n00n00n00n00r03r03");
        Rug rug = new Rug("r073132");
        Assam assam = new Assam("A30N");
        Assertions.assertFalse(boardOther.isRugAllowed(rug, assam));
    }
    @Test
    void isRugAllowedComplexFalse2() {
        BoardOther boardOther = new BoardOther("Pc02612iPy03112iPp03612iPr02713iA11EBn00n00y01n00n00n00n00n00y05y05r04n00n00n00p05c03c03r01n00n00n00p05y06y06p00n00n00n00n00r02n00n00n00n00n00n00n00n00c07c07n00n00n00n00n00n00n00r03r03");
        Rug rug = new Rug("y082122");
        Assam assam = new Assam("A11E");
        Assertions.assertFalse(boardOther.isRugAllowed(rug, assam));
    }

    @Test
    void isRugAllowedComplexTrue() {
        BoardOther boardOther = new BoardOther("Pc03014iPy03014iPp03215iPr02815iA14SBn00n00y01n00n00n00n00n00n00y01n00n00n00n00n00n00n00p00n00n00n00n00n00n00p00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00n00");
        Rug rug = new Rug("r011323");
        Assam assam = new Assam("A14S");
        Assertions.assertTrue(boardOther.isRugAllowed(rug, assam));
    }

    @Test
    void  paymentAmount1() {
        BoardOther boardOther = new BoardOther("Pc02612iPy03013iPp03213iPr02813iA15NBn00n00n00n00n00n00n00n00r01c01c02c02r00r00n00r01n00n00n00r02p01n00n00n00n00y01r02p01n00n00n00n00n00y00y00n00n00n00c00n00n00n00n00n00n00c00n00n00n00");
        IntPair intPair = new IntPair(1,5);
        Assertions.assertEquals(4,boardOther.paymentAmount(intPair));
    }
    @Test
    void  paymentAmount2() {
        BoardOther boardOther = new BoardOther("Pc02612iPy03013iPp03213iPr02813iA15NBn00n00n00n00n00n00n00n00r01c01c02c02r00n00n00r01n00n00n00r02p01n00n00n00n00y01r02p01n00n00n00n00n00y00y00n00n00n00c00n00n00n00n00n00n00c00n00n00n00");
        IntPair intPair = new IntPair(1,5);
        Assertions.assertEquals(3,boardOther.paymentAmount(intPair));
    }
    @Test
    void  paymentAmount3() {
        BoardOther boardOther = new BoardOther("Pc02612iPy03013iPp03213iPr02813iA15NBn00n00n00n00n00n00n00n00r01c01c02c02r00r00n00r01n00n00n00n02p01n00n00n00n00y01r02p01n00n00n00n00n00y00y00n00n00n00c00n00n00n00n00n00n00c00n00n00n00");
        IntPair intPair = new IntPair(1,5);
        Assertions.assertEquals(2,boardOther.paymentAmount(intPair));
    }
    @Test
    void  paymentAmount4() {
        BoardOther boardOther = new BoardOther("Pc02612iPy03013iPp03213iPr02813iA15NBn00n00n00n00n00n00n00n00r01r05r07r07r00r00n00r01n00n00n00n02p01n00n00n00n00y01r02p01n00n00n00n00n00y00y00n00n00n00c00n00n00n00n00n00n00c00n00n00n00");
        IntPair intPair = new IntPair(1,5);
        Assertions.assertEquals(7,boardOther.paymentAmount(intPair));
    }
    @Test
    void  paymentAmount5() {
        BoardOther boardOther = new BoardOther("Pc02612iPy03013iPp03213iPr02813iA15NBn00n00n00n00n00n00n00n00r01r05r07r07r00r00n00n00n00n00n00n02p01n00n00n00n00y01r02p01n00n00n00n00n00y00y00n00n00n00c00n00n00n00n00n00n00c00n00n00n00");
        IntPair intPair = new IntPair(1,5);
        Assertions.assertEquals(6,boardOther.paymentAmount(intPair));
    }
    @Test
    void  paymentAmount6() {
        BoardOther boardOther = new BoardOther("Pc02612iPy03013iPp03213iPr02813iA15NBn00n00n00n00n00r06n00n00n00n00n00r07r00r00n00n00n00n00n00r02p01n00n00n00n00y01r02p01n00n00n00n00n00y00y00n00n00n00c00n00n00n00n00n00n00c00n00n00n00");
        IntPair intPair = new IntPair(1,5);
        Assertions.assertEquals(6,boardOther.paymentAmount(intPair));
    }
    @Test
    void  paymentAmount7() {
        BoardOther boardOther = new BoardOther("Pc02612iPy03013iPp03213iPr02813iA15NBn00n00n00n00n00r06n00n00n00n00n00r07r00r00n00n00n00n00n00r02p01n00n00n00n00y01n02p01n00n00n00n00n00y00y00n00n00n00c00n00n00n00n00n00n00c00n00n00n00");
        IntPair intPair = new IntPair(1,5);
        Assertions.assertEquals(5,boardOther.paymentAmount(intPair));
    }
    @Test
    void  paymentAmount8() {
        BoardOther boardOther = new BoardOther("Pc02612iPy03013iPp03213iPr02813iA15NBn00n00n00n00n00n00n00n00r01n05r07r07r00r00n00n00n00n00n00n02p01n00n00n00n00y01r02p01n00n00n00n00n00y00y00n00n00n00c00n00n00n00n00n00n00c00n00n00n00");
        IntPair intPair = new IntPair(1,5);
        Assertions.assertEquals(4,boardOther.paymentAmount(intPair));
    }


}
