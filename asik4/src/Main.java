import java.util.ArrayList;
import java.util.List;

// Subject interface
interface GameSubject {
    void registerObserver(GameObserver observer);
    void removeObserver(GameObserver observer);
    void notifyObservers(String event);
}
class Game implements GameSubject {
    private List<GameObserver> observers;
    private String latestEvent;

    public Game() {
        this.observers = new ArrayList<>();
    }

    public void performEvent(String event) {
        this.latestEvent = event;
        notifyObservers(event);
    }

    @Override
    public void registerObserver(GameObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(GameObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        for (GameObserver observer : observers) {
            observer.update(event);
        }
    }
}


interface GameObserver {
    void update(String event);
}


class Player implements GameObserver {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public void update(String event) {
        System.out.println(name + ": Round finished - " + event);
    }
}


public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Player player1 = new Player("Sanzhar");
        Player player2 = new Player("Elaman");

        game.registerObserver(player1);
        game.registerObserver(player2);
        game.performEvent("Player 1 wins!");
        game.removeObserver(player1);
        game.performEvent("Game Over");

    }
}