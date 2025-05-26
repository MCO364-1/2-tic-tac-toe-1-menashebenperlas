public class Model {


    public enum Player {X, O, __}

    private Player[][] board = new Player[3][3];

    private Player currentPlayer = Player.X;

    //Model
    public Model() {
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Player.__;
            }
        }
    }
    public void makeMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            throw new IllegalArgumentException("Invalid board position!");
        }
        if (board[row][col] != Player.__) {
            throw new IllegalStateException("Spot already taken!");
        }
        board[row][col] = currentPlayer;
    }
    public Player getPosition(int x, int y) {
        return board[x][y];
    }
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public Player checkWinner() {

        for (int i = 0; i < 3; i++) {

            //row check
            if (board[i][0] != Player.__ && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }

            //column check
            if (board[0][i] != Player.__ &&board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }

        if (board[0][0] != Player.__ && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][0];
        }

        if (board[2][0] != Player.__ && board[2][0] == board[1][1] && board[1][1] == board[0][2]) {
            return board[2][0];
        }

        return Player.__;
    }
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Player.__) {
                    return false;
                }
            }
        }
        return true;
    } // Checks if the board is full (for a draw)
    public void switchPlayer() {
        if(currentPlayer == Player.X) {
            currentPlayer = Player.O;
        } else {
            currentPlayer = Player.X;
        }
    } // Switches between X and O
    public void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Player.__;
            }
        }
    }

    public void setPlayerToX() {
        currentPlayer = Player.X;
    }
}