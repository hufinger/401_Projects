package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class FramePuzzleView extends JPanel implements KeyListener, MouseListener {
	
	private PictureView picture_view;
	private Picture source, white_tile;
	private PictureView[][] picture_grid = new PictureView[5][5];
	private JPanel panel;
	private int white_location_x = 4,
			white_location_y = 4,
			tile_width,
			tile_height;
	
	
	public FramePuzzleView(Picture p) {
		this.source = p;
		
		picture_view = new PictureView(source.createObservable());
		panel = new JPanel();
		panel.addKeyListener(this);
		panel.addMouseListener(this);
		panel.setLayout(new GridLayout(5,5));
		
		//make the source into the grid
		makeGrid(source, picture_grid);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//store which key was pressed
		int key_pressed = e.getKeyCode();
		
		//find which key it was
		switch(key_pressed) {
		
		//based on key, move the white square
		case KeyEvent.VK_UP:
			try {
				Picture temp_up = picture_grid[white_location_x][white_location_y - 1].getPicture();
				Picture temp_white = new PictureImpl(tile_width, tile_height);
				temp_white = white_tile;
				picture_grid[white_location_x][white_location_y - 1].setPicture(temp_white.createObservable());
				picture_grid[white_location_x][white_location_y].setPicture(temp_up.createObservable());
				
				white_location_y--;
			} catch (ArrayIndexOutOfBoundsException t){
				
			}
			break;
		case KeyEvent.VK_DOWN:
			try {
				Picture temp_down = picture_grid[white_location_x][white_location_y + 1].getPicture();
				Picture temp_white = new PictureImpl(tile_width, tile_height);
				temp_white = white_tile;
				picture_grid[white_location_x][white_location_y + 1].setPicture(temp_white.createObservable());
				picture_grid[white_location_x][white_location_y].setPicture(temp_down.createObservable());
				
				white_location_y++;
			} catch (ArrayIndexOutOfBoundsException t){
				
			}
			break;
		case KeyEvent.VK_RIGHT:
			try {
				Picture temp_right = picture_grid[white_location_x + 1][white_location_y].getPicture();
				Picture temp_white = new PictureImpl(tile_width, tile_height);
				temp_white = white_tile;
				picture_grid[white_location_x + 1][white_location_y].setPicture(temp_white.createObservable());
				picture_grid[white_location_x][white_location_y].setPicture(temp_right.createObservable());
				
				white_location_x++;
			} catch (ArrayIndexOutOfBoundsException t){
				
			}
			break;
		case KeyEvent.VK_LEFT:
			try {
				Picture temp_left = picture_grid[white_location_x - 1][white_location_y].getPicture();
				Picture temp_white = new PictureImpl(tile_width, tile_height);
				temp_white = white_tile;
				picture_grid[white_location_x - 1][white_location_y].setPicture(temp_white.createObservable());
				picture_grid[white_location_x][white_location_y].setPicture(temp_left.createObservable());
				
				white_location_x--;
			} catch (ArrayIndexOutOfBoundsException t){
				
			}
			break;
		}
		setFocusable(false);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//set the mouse location to be -1 so it is out of view
		int mouse_loc_x = -1;
		int mouse_loc_y = -1;
		//set the location based on what is clicked
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (e.getSource() == picture_grid[i][j]) {
					mouse_loc_x = i;
					mouse_loc_y = j;
				}
			}
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (mouse_loc_x == white_location_x) {
					if (mouse_loc_y > white_location_y) {
						int spots_moved = mouse_loc_y - white_location_y;
						for (int x = 0; x < spots_moved; x++) {
							Picture temp_down = picture_grid[white_location_x][white_location_y + 1].getPicture();
							Picture temp_white = new PictureImpl(tile_width, tile_height);
							temp_white = white_tile;
							picture_grid[white_location_x][white_location_y + 1].setPicture(temp_white.createObservable());
							picture_grid[white_location_x][white_location_y].setPicture(temp_down.createObservable());
							
							white_location_y++;
						}
					} else if (mouse_loc_y < white_location_y) {
						int spots_moved = white_location_y - mouse_loc_y ;
						for (int x = 0; x < spots_moved; x++) {
							Picture temp_up = picture_grid[white_location_x][white_location_y - 1].getPicture();
							Picture temp_white = new PictureImpl(tile_width, tile_height);
							temp_white = white_tile;
							picture_grid[white_location_x][white_location_y - 1].setPicture(temp_white.createObservable());
							picture_grid[white_location_x][white_location_y].setPicture(temp_up.createObservable());
							
							white_location_y--;
						}
					} 
				} else if (mouse_loc_y == white_location_y) {
					if (mouse_loc_x < white_location_x) {
						int spots_moved = white_location_x - mouse_loc_x ;
						for (int x = 0; x < spots_moved; x++) {
							Picture temp_right = picture_grid[white_location_x - 1][white_location_y].getPicture();
							Picture temp_white = new PictureImpl(tile_width, tile_height);
							temp_white = white_tile;
							picture_grid[white_location_x - 1][white_location_y].setPicture(temp_white.createObservable());
							picture_grid[white_location_x][white_location_y].setPicture(temp_right.createObservable());
							
							white_location_x--;
						}
					} else if (mouse_loc_x > white_location_x) {
						int spots_moved = mouse_loc_x - white_location_x;
						for (int x = 0; x < spots_moved; x++) {
							Picture temp_left = picture_grid[white_location_x + 1][white_location_y].getPicture();
							Picture temp_white = new PictureImpl(tile_width, tile_height);
							temp_white = white_tile;
							picture_grid[white_location_x + 1][white_location_y].setPicture(temp_white.createObservable());
							picture_grid[white_location_x][white_location_y].setPicture(temp_left.createObservable());
							
							white_location_x++;
						}
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void makeGrid(Picture p, PictureView[][] array) {
		tile_width = source.getWidth()/5;
		tile_height = source.getHeight()/5;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i == 4 && j == 4) {
					white_tile = new PictureImpl(tile_width, tile_height);
					for (int x = 0; x < tile_width; x++) {
						for(int y = 0; y < tile_height; y++) {
							//set the bottom right frame to be white
							white_tile.setPixel(x, y, new ColorPixel(1,1,1));
						}
					}
					
					//make white_tile observable and add a listener. Also put in bottom right
					PictureView white_tile_view = new PictureView(white_tile.createObservable());
					picture_grid[i][j] = white_tile_view;
					picture_grid[i][j].addMouseListener(this);
				} else {
					//make the subpictures of the original
					SubPictureImpl sub_pics = new SubPictureImpl(source, i*tile_width, j*tile_height, tile_width, tile_height);
					
					//make them Observable and add the listeners
					PictureView grid_view = new PictureView(sub_pics.createObservable());
					grid_view.addMouseListener(this);
					grid_view.addKeyListener(this);
					grid_view.setFocusable(true);
					
					//add to the array
					picture_grid[i][j] = grid_view;
				}
			}
		}
		
		for (int g = 0; g < 5; g++) {
			for (int h = 0; h < 5; h++) {
				//add the pictures in row major order
				panel.add(picture_grid[h][g]);
			}
		}
		//add the panel to the widget
		add(panel, BorderLayout.CENTER);
	}
}
