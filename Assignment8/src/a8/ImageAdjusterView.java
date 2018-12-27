package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ImageAdjusterView extends JPanel implements ChangeListener{
	
	private Picture source;
	private PictureView picture_view;
	private JPanel subpanel;
	private JSlider blur_slider, bright_slider, sat_slider;
	private int blur_value, sat_value, bright_value;
	
	
	public ImageAdjusterView(Picture picture) {
		this.source = picture;
		setLayout(new BorderLayout());
		
		picture_view = new PictureView(picture.createObservable());
		add(picture_view, BorderLayout.CENTER);
		
		subpanel = new JPanel();
		subpanel.setLayout(new GridLayout (3,2));
		
		//create the blur slider
		blur_slider = new JSlider(0,5,0);
		blur_slider.setMajorTickSpacing(1);
		blur_slider.setPaintTicks(true);
		blur_slider.setPaintLabels(true);
		
		//create the brightness slider
		bright_slider = new JSlider(-100,100,0);
		bright_slider.setMajorTickSpacing(25);
		bright_slider.setPaintTicks(true);
		bright_slider.setPaintLabels(true);
		
		//create the saturation slider
		sat_slider = new JSlider(-100,100,0);
		sat_slider.setMajorTickSpacing(25);
		sat_slider.setPaintTicks(true);
		sat_slider.setPaintLabels(true);
		
		//make the labels for the sliders
		JLabel blur = new JLabel("Blur: ");
		JLabel sat = new JLabel("Saturation: ");
		JLabel brightness = new JLabel("Brightness: ");
		
		//add the labels to the subpanel
		subpanel.add(blur);
		subpanel.add(blur_slider);
		subpanel.add(sat);
		subpanel.add(sat_slider);
		subpanel.add(brightness);
		subpanel.add(bright_slider);
		
		//add to the bottom of the widget
		add(subpanel, BorderLayout.SOUTH);
		
		blur_slider.addChangeListener(this);
		sat_slider.addChangeListener(this);
		bright_slider.addChangeListener(this);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		blur_value = blur_slider.getValue();
		sat_value = sat_slider.getValue();
		bright_value = bright_slider.getValue();
		Picture blurred = blur(source);
		Picture saturated = saturate(blurred);
		Picture brightened = brighten(saturated);
		picture_view.setPicture(brightened.createObservable());
	}
	
	public Picture blur(Picture p) {
		Picture clone = new PictureImpl(source.getWidth(), source.getHeight());
		for (int i = 0; i < p.getWidth(); i++) {
			for (int j = 0; j < p.getHeight(); j++) {
				Pixel newpix = new ColorPixel(p.getPixel(i, j).getRed(), p.getPixel(i,j).getGreen(), p.getPixel(i, j).getBlue());
				clone.setPixel(i, j, newpix);
			}
		}
		for (int x = 0; x < p.getWidth(); x++) {
			for (int y = 0; y < p.getHeight(); y++) {
				double red = 0;
				double green = 0;
				double blue = 0;
				double counter = 0;
				for (int g = -1 * blur_value; g <= blur_value; g++) {
					for (int h = -1 * blur_value; h <= blur_value; h++) {
						if (g == 0 && h == 0) {
							continue;
						} else if (x + g < 0 || x + g >= p.getWidth()) {
							continue;
						} else if (y + h < 0 || y + h >= p.getHeight()) {
							continue;
						} 
						red += p.getPixel(x + g, y + h).getRed();
						green += p.getPixel(x + g, y + h).getGreen();
						blue += p.getPixel(x + g, y + h).getBlue();
						counter += 1;
					}
				}
				if (counter != 0) {
				clone.setPixel(x,y, new ColorPixel((red/counter), (green/counter), (blue/counter)));
				}
			}
		}
		
		return clone;
	}
	
	public Picture saturate(Picture p) {
		Picture clone = new PictureImpl(source.getWidth(), source.getHeight());
		for (int i = 0; i < p.getWidth(); i++) {
			for (int j = 0; j < p.getHeight(); j++) {
				Pixel newpix = new ColorPixel(p.getPixel(i, j).getRed(), p.getPixel(i,j).getGreen(), p.getPixel(i, j).getBlue());
				clone.setPixel(i, j, newpix);
			}
		}
		for (int x = 0; x < p.getWidth(); x++) {
			for (int y = 0; y < p.getHeight(); y++) {
				if (sat_value < 0) {
					//new = old * (1.0 + (f / 100.0) ) - (b * f / 100.0)
					Pixel sat_pix = new ColorPixel((p.getPixel(x, y).getRed() * (1 + (sat_value/100.0)) - (p.getPixel(x,y).getIntensity() * sat_value/100.0)),
							(p.getPixel(x, y).getGreen() * (1 + (sat_value/100.0)) - (p.getPixel(x,y).getIntensity() * sat_value/100.0)),
							(p.getPixel(x, y).getBlue() * (1.0 + (sat_value/100.0)) - (p.getPixel(x,y).getIntensity() * sat_value/100.0)));
					clone.setPixel(x,y, sat_pix);
				} else if (sat_value > 0) {
					double largest;
					//if red is greatest value
					if (p.getPixel(x, y).getRed() > p.getPixel(x, y).getBlue() && p.getPixel(x, y).getRed() > p.getPixel(x, y).getGreen()) {
						largest = p.getPixel(x, y).getRed();
						//new = old * ((a + ((1.0 - a) * (f / 100.0))) / a)
						Pixel sat_pix = new ColorPixel(p.getPixel(x,y).getRed() * ((largest + ((1 - largest) * (sat_value / 100.0))) / largest),
								p.getPixel(x,y).getGreen() * ((largest + ((1 - largest) * (sat_value / 100.0))) / largest), 
								p.getPixel(x,y).getBlue() * ((largest + ((1 - largest) * (sat_value / 100.0))) / largest));
						clone.setPixel(x, y, sat_pix);
					//if green is largest value	
					} else if (p.getPixel(x, y).getGreen() > p.getPixel(x, y).getBlue() && p.getPixel(x, y).getGreen() > p.getPixel(x, y).getRed()) {
						largest = p.getPixel(x, y).getGreen();
						Pixel sat_pix = new ColorPixel(p.getPixel(x,y).getRed() * ((largest + ((1 - largest) * (sat_value / 100.0))) / largest),
								p.getPixel(x,y).getGreen() * ((largest + ((1 - largest) * (sat_value / 100.0))) / largest), 
								p.getPixel(x,y).getBlue() * ((largest + ((1 - largest) * (sat_value / 100.0))) / largest));
						clone.setPixel(x, y, sat_pix);
					//if blue is largest value
					} else if (p.getPixel(x, y).getBlue() > p.getPixel(x, y).getRed() && p.getPixel(x, y).getBlue() > p.getPixel(x, y).getGreen()) {
						largest = p.getPixel(x, y).getBlue();
						Pixel sat_pix = new ColorPixel(p.getPixel(x,y).getRed() * ((largest + ((1 - largest) * (sat_value / 100.0))) / largest),
								p.getPixel(x,y).getGreen() * ((largest + ((1 - largest) * (sat_value / 100.0))) / largest), 
								p.getPixel(x,y).getBlue() * ((largest + ((1 - largest) * (sat_value / 100.0))) / largest));
						clone.setPixel(x, y, sat_pix);
					}
					
				}
			}
		}
		return clone;
	}
	
	public Picture brighten(Picture p) {
		Picture clone = new PictureImpl(p.getWidth(), p.getHeight());
		for (int i = 0; i < p.getWidth(); i++) {
			for (int j = 0; j < p.getHeight(); j++) {
				Pixel newpix = new ColorPixel(p.getPixel(i, j).getRed(), p.getPixel(i,j).getGreen(), p.getPixel(i, j).getBlue());
				clone.setPixel(i, j, newpix);
			}
		}
		for (int x = 0; x < p.getWidth(); x++) {
			for (int y = 0; y < p.getHeight(); y++) {
				if (bright_value < 0) {
					clone.setPixel(x, y, p.getPixel(x, y).darken((double) bright_value / -100));
				}
				if (bright_value > 0) {
					clone.setPixel(x, y, p.getPixel(x, y).lighten((double) bright_value / 100));
				}
			}
		}
		return clone;
	}
}
