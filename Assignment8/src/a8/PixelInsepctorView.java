package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInsepctorView extends JPanel implements MouseListener {
	
	private PictureView picture_view;
	private double red, green, blue, brightness;
	private int x, y;
	private Picture source;
	private JPanel subpanel;
	private JLabel x_clicked, y_clicked, red_clicked, green_clicked, blue_clicked,
				   brightness_clicked;
	
	public PixelInsepctorView (Picture picture) {
		this.source = picture;
		setLayout(new BorderLayout());
		
		//create a widget with the picture at the center
		picture_view = new PictureView(picture.createObservable());
		picture_view.addMouseListener(this);
		add(picture_view, BorderLayout.CENTER);
		
		//create a subpanel for the information
		subpanel = new JPanel();
		subpanel.setLayout(new GridLayout(6,1));
		
		x_clicked = new JLabel("X: ");
		y_clicked = new JLabel("Y:");
		red_clicked = new JLabel("Red: ");
		green_clicked = new JLabel("Green: ");
		blue_clicked = new JLabel("Blue: ");
		brightness_clicked = new JLabel("Brightness: ");
		
		//create the labels of information to be displayed
		subpanel.add(x_clicked);
		subpanel.add(y_clicked);
		subpanel.add(red_clicked);
		subpanel.add(green_clicked);
		subpanel.add(blue_clicked);
		subpanel.add(brightness_clicked);
		
		//add the subpanel to the left side of the widget
		add(subpanel, BorderLayout.WEST);
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//store all of the information from the stored pixel
		x = e.getX();
		y = e.getY();
		red = source.getPixel(x, y).getRed();
		green = source.getPixel(x,y).getGreen();
		blue = source.getPixel(x, y).getBlue();
		brightness = source.getPixel(x,y).getIntensity();
		
		//round the doubles to 2 decimal places
		red = Math.round(red*100);
		red /= 100;
		green = Math.round(green*100);
		green /= 100;
		blue = Math.round(blue*100);
		blue /= 100;
		brightness = Math.round(brightness*100);
		brightness /= 100;
		
		//print out the information from the pixel that was clicked
		x_clicked.setText("X: " + x);
		y_clicked.setText("Y: " + y);
		red_clicked.setText("Red: " + red);
		green_clicked.setText("Green: " + green);
		blue_clicked.setText("Blue: " + blue);
		brightness_clicked.setText("Brightness " + brightness + " ");
		
	
		
		
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

}
