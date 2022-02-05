package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import board.Board;
import model.Case;

public class ViewController extends JPanel {

    private Board humanBoard;
    private Board computerBoard;
    private static final int BOXSIZE = 40;
    private Color tabColor = new Color(237, 247, 255);
    private Color backgroundColor = new Color(161, 215, 255);
    private Color borderColor = new Color(161, 215, 255);
    private Color red = new Color(217, 46, 77);
    private Color yellow = new Color(236, 159, 5);
    private Color gray = new Color(54, 54, 54);

    /**
     * Construcs method to draw two board in one window
     * 
     * @param humanBoard    humanBoard was created
     * @param computerBoard computerBoard was created
     */

    public ViewController(Board humanBoard, Board computerBoard) {
        this.humanBoard = humanBoard;
        this.computerBoard = computerBoard;
        this.setBackground(backgroundColor);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
        this.addMouseListener(mouseEvent);
    }

    /**
     * Method to draw a window
     * 
     * @param g Graphics to draw
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Text
        g.setColor(gray);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Human board", 175, 490);
        g.drawString("Computer board", 625, 490);
        g.drawString("Score: " + computerBoard.getScore(), 50, 45);
        g.drawString("Miss: " + computerBoard.getMiss(), 355, 45);

        g.drawString("Score: " + humanBoard.getScore(), 510, 45);
        g.drawString("Miss: " + humanBoard.getMiss(), 815, 45);

        drawBoard(g, humanBoard, 50, 55);
        drawBoard(g, computerBoard, 510, 55);

        repaint();
    }

    /**
     * Draw main board
     * 
     * @param g     Graphics to draw
     * @param board Main board
     * @param gapX  Gap vertical between board's edge and window's edge
     * @param gapY  Gap horizontal between board's edge and window's edge
     */
    private void drawBoard(Graphics g, Board board, int gapX, int gapY) {
        g.setColor(tabColor);
        g.fillRect(gapX, gapY, BOXSIZE * board.getSize(), BOXSIZE * board.getSize());

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getBoard(i, j) instanceof Case)
                    drawBox(g, borderColor, i, j, gapX, gapY);

                if (board.getBoard(i, j).getShip() != null && board.isHumanBoard())
                    drawShip(g, gray, i, j, gapX, gapY);

                if (board.getBoard(i, j).getShip() != null && !board.isHumanBoard()) {
                    drawShip(g, tabColor, i, j, gapX, gapY);
                    drawBox(g, borderColor, i, j, gapX, gapY);
                }

                if (board.getBoard(i, j).getShip() != null && board.getBoard(i, j).getShip().getWeight() == 0)
                    drawShip(g, red, i, j, gapX, gapY);

                if (board.getBoard(i, j).isShot())
                    drawBullet(g, yellow, i, j, gapX, gapY);

                if (board.getBoard(i, j).getShip() != null && board.getBoard(i, j).isShot())
                    drawBullet(g, red, i, j, gapX, gapY);
            }
        }

    }

    /**
     * Draw small box in main board
     * 
     * @param g    Graphics to draw
     * @param c    Color
     * @param x    Point x
     * @param y    Point y
     * @param gapX Gap vertical between board's edge and window's edge
     * @param gapY Gap horizontal between board's edge and window's edge
     */
    private void drawBox(Graphics g, Color c, int x, int y, int gapX, int gapY) {
        g.setColor(c);
        g.drawRect(gapX + x * BOXSIZE, gapY + y * BOXSIZE, BOXSIZE, BOXSIZE);
    }

    /**
     * Draw ship in main board
     * 
     * @param g    Graphics to draw
     * @param c    Color
     * @param x    Point x
     * @param y    Point y
     * @param gapX Gap vertical between board's edge and window's edge
     * @param gapY Gap horizontal between board's edge and window's edge
     */
    private void drawShip(Graphics g, Color c, int x, int y, int gapX, int gapY) {
        g.setColor(c);
        g.fillRect(gapX + x * BOXSIZE, gapY + y * BOXSIZE, BOXSIZE, BOXSIZE);
    }

    /**
     * draw bullet is shot
     * 
     * @param g    graphics to draw
     * @param c    color
     * @param x    Point x
     * @param y    Point y
     * @param gapX Gap vertical between board's edge and window's edge
     * @param gapY Gap horizontal between board's edge and window's edge
     */
    private void drawBullet(Graphics g, Color c, int x, int y, int gapX, int gapY) {
        g.setColor(c);
        g.fillOval(gapX + x * BOXSIZE + 5, gapY + y * BOXSIZE + 5, BOXSIZE - 10, BOXSIZE - 10);
    }

    /**
     * Mouse Listener will call shoot method to play and show result in terminal
     */
    private MouseListener mouseEvent = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int xPos = e.getX();
            int yPos = e.getY();

            Point point = new Point((xPos - 510) / BOXSIZE, (yPos - 55) / BOXSIZE);

            if (xPos > 510 && xPos < 510 + computerBoard.getSize() * BOXSIZE && yPos > 55
                    && yPos < 55 + computerBoard.getSize() * BOXSIZE && !computerBoard.getBoard(point).isShot()) {
                if (humanBoard.getWinner() == null && computerBoard.getWinner() == null) {
                    computerBoard.humanShoot(point);
                    humanBoard.computerShoot();
                }
            }

            if (xPos > 50 && xPos < 50 + computerBoard.getSize() * BOXSIZE && yPos > 55
                    && yPos < 55 + computerBoard.getSize() * BOXSIZE)
                System.out.println("Can't shoot in your area!");

            if (humanBoard.getWinner() != null)
                System.out.println(humanBoard.getWinner());

            if (computerBoard.getWinner() != null)
                System.out.println(computerBoard.getWinner());

            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }
    };

}
