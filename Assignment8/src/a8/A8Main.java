package a8;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class A8Main {
	public static void main(String[] args) throws IOException {
		Picture p = A8Helper.readFromURL("http://www.cs.unc.edu/~kmp/kmp-in-namibia.jpg");
		//create the widgets to be displayed
		PixelInsepctorView simple_widget = new PixelInsepctorView(p);
		ImageAdjusterView adjuster_widget = new ImageAdjusterView(p);
		FramePuzzleView puzzle_widget = new FramePuzzleView(p);
		
		//PixelAdjusterWidget
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 8 Pixel Inspector");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(simple_widget, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		main_frame.pack();
		main_frame.setVisible(true);
		
		//ImageAdjusterWidget
		JFrame second_frame = new JFrame();
		second_frame.setTitle("Assignment 8 Image Ajduster");
		second_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel top_panel1 = new JPanel();
		top_panel1.setLayout(new BorderLayout());
		top_panel1.add(adjuster_widget, BorderLayout.CENTER);
		second_frame.setContentPane(top_panel1);
		
		second_frame.pack();
		second_frame.setVisible(true);
		
		//FramePuzzleWidget
		JFrame third_frame = new JFrame();
		third_frame.setTitle("Assignment 8 Frame Puzzle");
		third_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel top_panel2 = new JPanel();
		top_panel2.setLayout(new BorderLayout());
		top_panel2.add(puzzle_widget, BorderLayout.CENTER);
		third_frame.setContentPane(top_panel2);
		
		third_frame.pack();
		third_frame.setVisible(true);
	}
}
