import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tigtagtoe {
    int bordWidth = 600;
    int bordHight = 650;

    JFrame frame = new JFrame("Tig_tag_toe");
    JLabel textlabel = new JLabel();
    JPanel textpanel = new JPanel();
    JPanel bordpanel = new JPanel();
    JPanel resetPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String playerX = "x";
    String playerO = "o";
    String currentplayer = playerX;
    boolean gameover = false;

    JButton resetButton = new JButton();

    public Tigtagtoe() {
        frame.setVisible(true);
        frame.setSize(bordWidth, bordHight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textlabel.setBackground(Color.DARK_GRAY);
        textlabel.setForeground(Color.white);
        textlabel.setFont(new Font("Arial", Font.BOLD, 50));
        textlabel.setText("Tig_tag_toe");
        textlabel.setHorizontalAlignment(JLabel.CENTER);
        textlabel.setOpaque(true);

        textpanel.setLayout(new BorderLayout());
        textpanel.add(textlabel);
        frame.add(textpanel, BorderLayout.NORTH);

        bordpanel.setLayout(new GridLayout(3, 3));
        bordpanel.setBackground(Color.DARK_GRAY);
        frame.add(bordpanel);

        resetPanel.setLayout(new FlowLayout());
        resetPanel.setBackground(Color.DARK_GRAY);
        resetButton.setPreferredSize(new Dimension(150, 30));
        resetButton.setBackground(Color.red);
        resetButton.setForeground(Color.white);
        resetButton.setFont(new Font("Arial", Font.BOLD, 30));
        resetButton.setText("reset");
        resetPanel.add(resetButton);
        frame.add(resetPanel, BorderLayout.SOUTH);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                bordpanel.add(tile);

                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 150));
                tile.setFocusable(false);
                // tile.setText(currentplayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!gameover) {
                            JButton tile = (JButton) e.getSource();
                            if (tile.getText().equals("")) {
                                tile.setText(currentplayer);
                                checkwin();

                                if (!gameover) {
                                    currentplayer = currentplayer.equals(playerX) ? playerO : playerX;
                                    textlabel.setText(currentplayer + "'s turn");
                                }
                            }
                        }
                    }
                });
            }

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameover = false;
                currentplayer = playerX;
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        reset(board[r][c]);
                    }
                }
            }
        });
        }

    }

    void  checkwin() {
        for (int r = 0; r <3; r++) {
            if (board[r][0].getText().isEmpty()) continue;
            
            if (board[r][0].getText().equals(board[r][1].getText()) &&
            board[r][1].getText().equals(board[r][2].getText())) {
                for (int c = 0; c < 3; c++) {
                    colorwiner(board[r][c]);
                }
                return;
            }
        }

        for (int r = 0; r <3; r++) {
            if (board[0][r].getText().isEmpty()) continue;
            
            if (board[0][r].getText().equals(board[1][r].getText()) &&
            board[1][r].getText().equals(board[2][r].getText())) {
                for (int c = 0; c < 3; c++) {
                    colorwiner(board[c][r]);
                }
                return;
            }
        }

        if (board[0][0].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][2].getText()) && !board[0][0].getText().equals("")) {
                for (int r = 0; r <3; r++) {
                    colorwiner(board[r][r]);
                }
                return;
        }

        if (board[0][2].getText().equals(board[1][1].getText()) &&
            board[1][1].getText().equals(board[2][0].getText()) && !board[0][2].getText().equals("")) {
                for (int r = 2; r >= 0; r--) {
                    colorwiner(board[2-r][r]);
                }
                return;
        }

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c].getText().equals("")) {
                    return;
                }
            }
        }
        gameover = true;
        textlabel.setText("Toe");
        toe();
    }

    void colorwiner(JButton button) {
        gameover = true;
        button.setBackground(Color.GRAY);
        button.setForeground(Color.green);
        textlabel.setText("The winer is " + currentplayer);
    }

    void reset(JButton button) {
        button.setBackground(Color.DARK_GRAY);
        button.setText("");
        button.setForeground(Color.white);
        textlabel.setText("Tig_tag_toe");
    }

    void toe() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                colortoe(board[r][c]);
            }
        }
    }

    void colortoe(JButton button) {
        button.setBackground(Color.yellow);
        button.setForeground(Color.red);
    }
}
