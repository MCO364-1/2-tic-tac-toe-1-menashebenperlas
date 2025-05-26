import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {

        Model ticTacToe = new Model();

        //Main Frame Window
        JFrame window = new JFrame();
        window.setSize(500, 500);
        window.setTitle("TicTacToe");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Button Panel
        JPanel ticPanel = new JPanel();
        ticPanel.setLayout(new GridLayout(3, 3));

        //Status Bar & New Game Panel
        JPanel statusBar = new JPanel();

        //Status Label
        JLabel statusLabel = new JLabel();
        statusLabel.setPreferredSize(new Dimension(100, 50));
        statusLabel.setVerticalAlignment(SwingConstants.CENTER);
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusLabel.setPreferredSize(new Dimension(200, 30));
        statusBar.add(statusLabel, BorderLayout.WEST);
        statusLabel.setText("Player " + ticTacToe.getCurrentPlayer() + "'s Turn!");

        //2d array to store buttons
        JButton[][] buttons = new JButton[3][3];

        //Create the buttons
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int row = i;
                int col = j;
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 45));
                buttons[i][j].setBackground(new Color(16, 174, 231));
                buttons[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                buttons[i][j].setOpaque(true);

                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            ticTacToe.makeMove(row, col);
                            buttons[row][col].setText(ticTacToe.getCurrentPlayer() + "");

                            //Check for Winner
                            if (ticTacToe.checkWinner() != Model.Player.__) {
                                statusLabel.setText(ticTacToe.getCurrentPlayer() + " Wins!");
                                disableButtons(buttons);
                                return;
                            }

                            //Check for Draw
                            if (ticTacToe.isBoardFull()) {
                                statusLabel.setText("It's a Draw!");
                                disableButtons(buttons);
                                return;
                            }

                            ticTacToe.switchPlayer();
                            statusLabel.setText("Player " + ticTacToe.getCurrentPlayer() + "'s Turn!");

                        } catch (IllegalStateException ex) {
                            statusLabel.setText(ex.getMessage());
                            Timer timer = new Timer(1000, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    statusLabel.setText("Player " + ticTacToe.getCurrentPlayer() + "'s Turn!");
                                }
                            });

                            timer.setRepeats(false); // Ensure it runs only once
                            timer.start();
                        }
                    }
                });
                ticPanel.add(buttons[i][j]);
            }
        }

        //New Game Button
        JButton newGameBtn = new JButton("New Game");
        newGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ticTacToe.clearBoard();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        buttons[i][j].setEnabled(true);
                        buttons[i][j].setText("");
                        ticTacToe.setPlayerToX();
                        statusLabel.setText("Player " + ticTacToe.getCurrentPlayer() + "'s Turn!");
                    }
                }

            }
        });
        statusBar.add(newGameBtn, BorderLayout.EAST);
        window.add(statusBar, BorderLayout.SOUTH);
        window.add(ticPanel);
        window.setVisible(true);
    }

    private static void disableButtons(JButton[][] buttons) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }
}