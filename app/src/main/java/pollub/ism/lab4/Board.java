package pollub.ism.lab4;

public class Board{
    private final String[][] boardTiles;
    private String currentPlayer;
    private String endGameMessage;

    public Board() {
        this.boardTiles = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.boardTiles[i][j] = "";
            }
        }
        currentPlayer = "O";
        endGameMessage = null;
    }

    public Board(String[][] boardTiles, String currentPlayer, String endGameMessage) {
        this.boardTiles = boardTiles;
        this.currentPlayer = currentPlayer;
        this.endGameMessage = endGameMessage;
    }

    void clearBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.boardTiles[i][j] = "";
            }
        }
        currentPlayer = "O";
        endGameMessage = null;
    }

    boolean isTileFree(int buttonNumber) {
        int x = buttonNumber / 3;
        int y = buttonNumber % 3;
        return boardTiles[x][y].equals("");
    }

    String getBoardTile(int buttonNumber) {
        int x = buttonNumber / 3;
        int y = buttonNumber % 3;
        return this.boardTiles[x][y];
    }

    void setBoardTile(int buttonNumber) {
        int x = buttonNumber / 3;
        int y = buttonNumber % 3;
        this.boardTiles[x][y] = currentPlayer;
    }

    void processBoardState() {
        if (checkWin()) {
            endGameMessage = getWinMessage(currentPlayer);
        } else if (checkDraw()){
            endGameMessage = "Remis!";
        } else {
            currentPlayer = getNextPlayer();
        }
    }

    String getNextPlayer(){
        return currentPlayer.equals("X") ? "O" : "X";
    }

    String getWinMessage(String player) {
        return player.equals("O") ? "Kółka wygrały! WOHOOO" : "Krzyżyki wygrały! WOHOOO";
    }

    boolean checkDraw(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.boardTiles[i][j].equals("")) return false;
            }
        }
        return true;
    }

    boolean checkWin() {
        return checkHorizontalWinCondition(currentPlayer) || checkVerticalWinCondition(currentPlayer) || checkDiagonalWinCondition(currentPlayer);
    }

    boolean checkHorizontalWinCondition(String player) {
        for (int i = 0; i < 3; i++) {
            if (boardTiles[i][0].equals(player) && boardTiles[i][1].equals(player) && boardTiles[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }

    boolean checkVerticalWinCondition(String player) {
        for (int i = 0; i < 3; i++) {
            if (boardTiles[0][i].equals(player) && boardTiles[1][i].equals(player) && boardTiles[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }

    boolean checkDiagonalWinCondition(String player) {
        if (boardTiles[0][0].equals(player) && boardTiles[1][1].equals(player) && boardTiles[2][2].equals(player)) {
            return true;
        } else if (boardTiles[0][2].equals(player) && boardTiles[1][1].equals(player) && boardTiles[2][0].equals(player)) {
            return true;
        }
        return false;

    }

    public String getEndGameMessage() {
        return endGameMessage;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String[][] getBoardTiles() {
        return boardTiles;
    }
}
