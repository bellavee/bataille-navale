package board;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.*;

public class Board {
    private int size;
    private Case[][] board;
    private boolean isHumanBoard;
    private int score;
    private int miss;

    /**
     * Constructs a board with a size and a bool to check this board will be human's
     * * board or computer's board
     * 
     * @param size         size of 2 dimensions board
     * @param isHumanBoard true - humanBoard; false - computerBoard
     */

    public Board(int size, boolean isHumanBoard) {
        this.size = size;
        this.board = new Case[size][size];
        this.isHumanBoard = isHumanBoard;
        this.score = 0;
        this.miss = 0;
        createBoard();
    }

    /**
     * Create a case's board which contains a posiions and none of ship
     */

    public void createBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new Case(new Point(i, j), null);
            }
        }
    }

    /**
     * Check if this board is human's board
     * 
     * @return boolean
     */
    public boolean isHumanBoard() {
        return this.isHumanBoard;
    }

    /**
     * Get board's case with a position
     * 
     * @param p A position
     * @return Board's case
     */
    public Case getBoard(Point p) {
        return this.board[p.x][p.y];
    }

    /**
     * Cet board's case with a point x and a point y
     * 
     * @param x Point x
     * @param y Point y
     * @return Board's case
     */
    public Case getBoard(int x, int y) {
        return this.board[x][y];
    }

    /**
     * Get size of board
     * 
     * @return Board's size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Get case's ship of board
     * 
     * @param p
     * @return Case's ship
     */
    public Ship getShip(Point p) {
        return this.board[p.x][p.y].getShip();
    }

    /**
     * Get current score (when shoot a ship)
     * 
     * @return Number of score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Get current missing bullet
     * 
     * @return Number of missing bullet
     */
    public int getMiss() {
        return this.miss;
    }

    /**
     * Calculate point adjacent vertical or horizontal of a point
     * 
     * @param point     Start point
     * @param direction Vertical: true; Horizontal: false
     * @return New point
     */

    public Point calculShipPos(Point point, boolean direction) {
        Point end = null;
        if (direction)
            end = new Point(point.x + 1, point.y);
        else
            end = new Point(point.x, point.y + 1);
        return end;
    }

    /**
     * Check if position is valid in board
     * 
     * @param point Position wants to check
     * @return boolean
     */
    public boolean isValidPoint(Point point) {
        return point.x >= 0 && point.y >= 0 && point.x < size && point.y < size;
    }

    /**
     * Place a ship with a position and a direction
     * 
     * @param ship      Ship to place
     * @param point     Start point
     * @param direction Place ship vertical or horizontal
     */
    public void placeShip(Ship ship, Point point, boolean direction) {
        Point end = point;
        List<Point> positions = new ArrayList<>();

        for (int i = 0; i < ship.getWeight(); i++) {
            if (isValidPoint(end)) {
                positions.add(end);
                end = calculShipPos(end, direction);
            }
        }

        for (Point p : positions)
            this.board[p.x][p.y].setShip(ship);

    }

    /**
     * Shoot with a position in board, calculate score and missing bullet
     * 
     * @param point Position will be shot
     */
    public void shoot(Point point) {
        if (!this.board[point.x][point.y].isShot())
            this.board[point.x][point.y].shoot();

        if (this.board[point.x][point.y].getShip() != null && this.board[point.x][point.y].isShot()) {
            this.board[point.x][point.y].shoot();
            this.board[point.x][point.y].getShip().isShot();
            this.score++;
        }

        else
            this.miss++;

    }

    /**
     * Get random point
     * 
     * @return New random point
     */
    public Point getRandom() {
        Random random = new Random();
        return new Point(random.nextInt(10), random.nextInt(10));
    }

    /**
     * Computer will shoot random in board
     */
    public void computerShoot() {
        Point point = getRandom();
        while (this.board[point.x][point.y].isShot())
            point = getRandom();
        shoot(point);
    }

    /**
     * Human shoot with a position in board
     * 
     * @param point Position will be shot
     */
    public void humanShoot(Point point) {
        if (!this.board[point.x][point.y].isShot())
            shoot(point);

    }

    /**
     * Show string if there is a winner
     * 
     * @return Name of winner
     */
    public String getWinner() {
        if (this.score == 17) {
            if (this.isHumanBoard)
                return "Computer wins";
            else
                return "Human wins";
        }

        return null;
    }

}
