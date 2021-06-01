package learn.gomoku.players;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Stone;

import java.util.List;
import java.util.Random;

public class CustomPlayer implements Player {

    private final String name;

    public CustomPlayer(String name) {
        this.name = name;
    }

    private final Random random = new Random();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Stone generateMove(List<Stone> previousMoves) {

        boolean isBlack = true;
        if (previousMoves != null && !previousMoves.isEmpty()) {
            Stone lastMove = previousMoves.get(previousMoves.size() - 1);
            isBlack = !lastMove.isBlack();
        }
        // TODO for a CustomPlayer, we could not random.nextInt(previousMove.getRow() + 1) and same for column
        // TODO I wonder if there's an overloaded version of random.nextInt() that can pass in a lower bound as well as upper...
        return new Stone(
                random.nextInt(Gomoku.WIDTH),
                random.nextInt(Gomoku.WIDTH),
                isBlack);
    }
}
