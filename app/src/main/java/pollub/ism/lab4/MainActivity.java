package pollub.ism.lab4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Board board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        board = new Board();
    }

    public void clickButton(View view) {
        Button clickedButton = (Button) view;
        int tileNumber = Integer.parseInt(clickedButton.getTag().toString());
        if (board.isTileFree(tileNumber)) {
            board.setBoardTile(tileNumber);
            clickedButton.setText(board.getCurrentPlayer());
            board.processBoardState();
        }
        if (board.getEndGameMessage() != null) {
            showMessage(board.getEndGameMessage());
            resetButtons();
            board.clearBoard();
        }
    }

    public void resetButtons() {
        for (int i = 1; i <= 9; i++) {
            Button button = (Button) findViewById(getResources().getIdentifier("button" + i, "id",
                    this.getPackageName()));
            button.setText("");
        }
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("board", this.board.getBoardTiles());
        outState.putString("currentPlayer", this.board.getCurrentPlayer());
        outState.putString("endGameMessage", this.board.getEndGameMessage());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        board = new Board((String[][]) savedInstanceState.getSerializable("board"),
                savedInstanceState.getString("currentPlayer"),
                savedInstanceState.getString("endGameMessage"));
        restoreButtons();
    }

    public void restoreButtons() {
        for (int i = 1; i <= 9; i++) {
            Button button = (Button) findViewById(getResources().getIdentifier("button" + i, "id",
                    this.getPackageName()));
            int tileNumber = Integer.parseInt(button.getTag().toString());
            button.setText(this.board.getBoardTile(tileNumber));
        }
    }
}