import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    //Init Model
    Model model = new Model();

    @org.junit.jupiter.api.Test
    void makeMove() {
        assertEquals(model.getPosition(0, 0), Model.Player.__);
        model.makeMove(0, 0);
        assertEquals(model.getPosition(0, 0), Model.Player.X);
        model.switchPlayer();
        model.makeMove(0, 1);
        assertEquals(model.getPosition(0, 1), Model.Player.O);

    }
    @org.junit.jupiter.api.Test
    void illegalMove() {
        assertEquals(model.getPosition(0, 0), Model.Player.__);
        model.makeMove(0, 0);
        assertEquals(model.getPosition(0, 0), Model.Player.X);
        model.switchPlayer(); //TODO work on this
    }

    @org.junit.jupiter.api.Test
    void checkWinner() {
        assertEquals(model.checkWinner(), Model.Player.__);
        model.makeMove(0, 0);
        model.makeMove(1, 1);
        model.makeMove(2, 2);
        assertEquals(model.checkWinner(), Model.Player.X);

    }

    @org.junit.jupiter.api.Test
    void isBoardFull() {
        assertTrue(!model.isBoardFull());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                model.makeMove(i, j);
            }
        }
        assertTrue(model.isBoardFull());
    }

    @org.junit.jupiter.api.Test
    void switchPlayer() {
        assertTrue(model.getCurrentPlayer() == Model.Player.X);
        model.switchPlayer();
        assertTrue(model.getCurrentPlayer() == Model.Player.O);

    }
}