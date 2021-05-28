package learn.gomoku.UI;

import learn.gomoku.game.Stone;
import learn.gomoku.players.Player;

import java.util.List;

public class GameMenu {

    Player player1 = new Player() {
        @Override
        public String getName() {
            return null;
        }

        @Override
        public Stone generateMove(List<Stone> previousMoves) {
            return null;
        }
    }
}
