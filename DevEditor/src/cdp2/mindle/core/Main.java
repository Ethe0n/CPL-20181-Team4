package cdp2.mindle.core;

import java.awt.GridLayout;

import javax.swing.JFrame;

import cdp2.mindle.gui.MainFrame;

public class Main {
	public static void main(String[] args)
	{
		MainFrame frame = new MainFrame("Mindle Script Editor");
		
		frame.setSize(1000, 1000);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
