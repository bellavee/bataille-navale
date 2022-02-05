
import java.awt.*;
import javax.swing.*;

import view.ViewController;
import board.Board;
import model.Ship;

public class Main extends JFrame {
    public static final int WIDTH = 980;
    public static final int HEIGHT = 555;

    public Main(Board playerBoard, Board enemyBoard) {
        super("Battleship");
        ViewController draw = new ViewController(playerBoard, enemyBoard);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.add(draw);
        this.repaint();
    }

    public static void main(String[] args) {
        final int SIZE = 10;

        Board playerBoard = new Board(SIZE, true);
        Board enemyBoard = new Board(SIZE, false);
        new Main(playerBoard, enemyBoard);

        // direction: true vertical, false horizontal

        playerBoard.placeShip(new Ship(5), new Point(2, 1), true);
        playerBoard.placeShip(new Ship(4), new Point(6, 5), false);
        playerBoard.placeShip(new Ship(3), new Point(2, 3), false);
        playerBoard.placeShip(new Ship(3), new Point(5, 3), true);
        playerBoard.placeShip(new Ship(2), new Point(1, 8), true);

        enemyBoard.placeShip(new Ship(5), new Point(8, 4), false);
        enemyBoard.placeShip(new Ship(4), new Point(2, 6), false);
        enemyBoard.placeShip(new Ship(3), new Point(1, 2), false);
        enemyBoard.placeShip(new Ship(3), new Point(5, 3), false);
        enemyBoard.placeShip(new Ship(2), new Point(6, 1), true);

    }
}