package com.simplicity.tools;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.simplicity.client.cache.definitions.ItemDefinition;

/**
 * A tool used for looking up model colors.
 * 
 * @author Blake
 *
 */
public class RS2ColorPicker extends JFrame {
	
	private static final long serialVersionUID = 1L;

	ColorPanel canvas;
	JSlider red;
	JSlider blue;
	JSlider green;
	public RS2ColorPicker() {
		super("Color Sliders");
		setLookAndFeel();
		setSize(270,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		
		canvas = new ColorPanel();
		red = new JSlider(0, 255, 255);
		green = new JSlider(0, 255,0);
		blue = new JSlider(0,255,0);
		
		//sets slider for red
		red.setMajorTickSpacing(50);
		red.setMinorTickSpacing(10);
		red.setPaintTicks(true);
		red.setPaintLabels(true);
		//red.addChangeListener(this);

		//slider for green
		green.setMajorTickSpacing(50);
		green.setMinorTickSpacing(10);
		green.setPaintTicks(true);
		green.setPaintLabels(true);
		//green.addChangeListener(this);
		
		//slider for blue
		blue.setMajorTickSpacing(50);
		blue.setMinorTickSpacing(10);
		blue.setPaintTicks(true);
		blue.setPaintLabels(true);
		//blue.addChangeListener(this);
		
		//labels
		JLabel redLabel = new JLabel("Red: ");
		JLabel greenLabel = new JLabel("Green: ");
		JLabel blueLabel = new JLabel("Blue: ");
		GridLayout grid = new GridLayout(4,1);
		FlowLayout right = new FlowLayout (FlowLayout.RIGHT);
		setLayout(grid);
		
		JPanel greenPanel = new JPanel();
		greenPanel.setLayout(right);
		greenPanel.add(greenLabel);
		greenPanel.add(green);
		add(greenPanel);
 
		JPanel redPanel = new JPanel();
		redPanel.setLayout(right);
		redPanel.add(redLabel);
		redPanel.add(red);
		add(redPanel);
		
		JPanel bluePanel = new JPanel();
		bluePanel.setLayout(right);
		bluePanel.add(blueLabel);
		bluePanel.add(blue);
		add(bluePanel);
		add(canvas);
		
		setVisible(true);
	}

	private void setLookAndFeel() {
	
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch (Exception e){
			
		}
	}

	public void stateChanged(ChangeEvent event) {
		//changin value of color when sliders change
		JSlider source = (JSlider) event.getSource();
		if(source.getValueIsAdjusting() != true) {
			Color  current = new Color(red.getValue(),
					green.getValue(),
					blue.getValue());
			canvas.changeColor(current);
			canvas.repaint();
			
		}
		
	}
	public static void main(String[] args) {
		RS2ColorPicker cs = new RS2ColorPicker();
	}
	
	class ColorPanel  extends JPanel {
		Color background;
		
		ColorPanel() {
			background = Color.red;
		}
		
		public void paintComponent(Graphics comp) {
			//displays color of rgb value
			Graphics2D comp2D = (Graphics2D) comp;
			comp2D.setColor(background);
			comp2D.fillRect(0, 0, getSize().width, getSize().height);
			comp2D.setColor(Color.black);
			if(red.getValue() < 135 && green.getValue() < 135 && blue.getValue() < 135){
				comp2D.setColor(Color.white);
			}
			//draws the rbg value
			comp2D.drawString("rgb: (" + red.getValue() + "," + green.getValue() + "," + blue.getValue() + ")" ,20, 30);
			
		}
	
	void changeColor(Color newBackground) {
		background = newBackground;
		}
	}
	
}
