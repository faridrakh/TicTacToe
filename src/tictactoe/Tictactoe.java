package tictactoe;

public class Tictactoe {
	public static Board window = new Board();
	
	public static void main(String[] args) {
		if(window.frame.isVisible() == false){
			window.frame.setVisible(true);
		}	
	}
}
	