package tictactoe;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Font;

public class Board implements ActionListener{

	public JFrame frame;
	public static JButton[] button = new JButton[9];  
	Tictactoe ttt = new Tictactoe();
	private boolean isPlay = true;
	private String[] Symbol = new String[]{"","X","O"};
	private int[] board = new int[9];
	private static final int EMPTY = 0;
	private static final int PLAYER = 1;
	private static final int COMPUTER = 2;
	
	public Board() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("TicTacToe");
		frame.setResizable(false);
		frame.setBounds(100, 100, 300, 322);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(3, 3, 0, 0));
		
		for(int i = 0; i < 9; i++){
			button[i] = new JButton(Symbol[0]);
			button[i].setFont(new Font("Tahoma", Font.PLAIN, 60));
			button[i].addActionListener(this);
			frame.getContentPane().add(button[i]);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e){
		if(isPlay){
			for(int i = 0; i < 9; i++){
				if(e.getSource() ==  button[i])
					if(board[i] == EMPTY) {
						button[i].setText(Symbol[PLAYER]);
						button[i].setEnabled(false);
						board[i] = PLAYER;
						checkWhoWin();
						computerPlay();
					}
			}
		}
	}
	
	public void computerPlay(){
		if(isPlay){
			int x = possibleMove(COMPUTER);
			button[x].setText(Symbol[COMPUTER]);
			button[x].setEnabled(false);
			board[x] = COMPUTER;
			checkWhoWin();
		}
	}
	
	public void checkWhoWin(){
		if(checkWin(PLAYER)){
			isPlay = false;
			JOptionPane.showMessageDialog(null, "PLAYER WON!");
		}
		if(checkWin(COMPUTER)){
			isPlay = false;
			JOptionPane.showMessageDialog(null, "COMPUTER WON!");
		}
		if(possibleMove(PLAYER) == -1 && possibleMove(COMPUTER) == -1){
			isPlay = false;
			JOptionPane.showMessageDialog(null, "GAME DRAW!");
		}
	}
		
	public int possibleMove(int turn){
		if(button[4].getText() == "")
			return 4;
		
		int Move = possibleWinningMove(turn);
        if(Move != -1) 
        	return Move;
        
        for(int i=0;i<9;i++)
        	if(board[i] == EMPTY)
                {
                    board[i] = turn;
                    boolean reverse = possibleWinningMove(turn == PLAYER ? COMPUTER : PLAYER) == -1;
                    board[i] = EMPTY;
                    if(reverse) 
                    	return i;
                }
        
        for(int i=0;i<9;i++)
        	if(board[i] == EMPTY)
        		return i;

		return -1;
	}
	
	public int possibleWinningMove(int turn) {
		for(int i = 0; i < 9; i++)
			if(board[i] == EMPTY) {
				board[i] = turn;
		    	boolean win = checkWin(turn);
		    	board[i] = EMPTY;
		    	if(win) 
		    		return i;
		    }
		return -1;
	}

	public boolean checkWin(int turn){
		if(board[0] == turn && board[1] == turn && board[2] == turn) return true;
		if(board[0] == turn && board[3] == turn && board[6] == turn) return true;
		if(board[0] == turn && board[4] == turn && board[8] == turn) return true;
		if(board[1] == turn && board[4] == turn && board[7] == turn) return true;
		if(board[2] == turn && board[5] == turn && board[8] == turn) return true;
		if(board[2] == turn && board[4] == turn && board[6] == turn) return true;
		if(board[3] == turn && board[4] == turn && board[5] == turn) return true;
		if(board[6] == turn && board[7] == turn && board[8] == turn) return true;
		
		return false;
	}
}
