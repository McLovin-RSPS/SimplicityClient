package com.simplicity;

import com.simplicity.client.Client;
import com.simplicity.client.ClientSettings;
import com.simplicity.client.ResourceLoader;
import com.simplicity.client.signlink;

import java.net.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;

public class Jframe extends Client implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static JFrame frame;

	public Jframe(int width, int height, boolean resizable) {
		super();
		try {
			signlink.startpriv(InetAddress.getByName(Configuration.HOST));
			initUI(width, height, resizable);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void initUI(int width, int height, boolean resizable) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);
			
			frame = new JFrame(Configuration.CLIENT_NAME);
			frame.setLayout(new BorderLayout());
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

			
			frame.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent we) { 
		        String options[] = {"Yes", "No"};
		        int userPrompt = JOptionPane.showOptionDialog(null, "Are you sure you wish to exit?", "Simplicity",
		        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options , options[1]);
		        if(userPrompt == JOptionPane.YES_OPTION) {
		        	if(!Configuration.LOCALHOST) {
						//openURL("http://simplicityps.org/forums");
					}
		        	ClientSettings.save();
					System.exit(-1);
		            System.exit(0);
		        } else {
		        	 
		        }
		    }
		});
			setFocusTraversalKeysEnabled(false);
			JPanel gamePanel = new JPanel();
			Insets insets = this.getInsets();
			if(System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0) {
				width += 10;
				height += 10;
			}
			super.setPreferredSize(new Dimension(width - 10, height - 10));
			frame.setMinimumSize(new Dimension(width - 10, height - 10));
			frame.setLayout(new BorderLayout());
			gamePanel.setLayout(new BorderLayout());
			gamePanel.add(this);
			gamePanel.setBackground(Color.BLACK);
			setClientIcon();
			initializeMenuBar();
			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
			frame.pack();
			frame.setResizable(resizable);
			init();
			Jframe.super.graphics = getGameComponent().getGraphics();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true); 
			frame.createBufferStrategy(2);
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void rebuildFrame(int width, int height, boolean resizable, boolean undecorated) {
		

		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		frame = new JFrame(Configuration.CLIENT_NAME);
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent we) { 
	        String options[] = {"Yes", "No"};
	        int userPrompt = JOptionPane.showOptionDialog(null, "Are you sure you wish to exit?", "Simplicity", 
	        		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options , options[1]);
	        if(userPrompt == JOptionPane.YES_OPTION) {
				if(!Configuration.LOCALHOST) {
					openURL("http://simplicityps.org/forums");
				}
				System.exit(-1);
	            System.exit(0);
	        } else {
	        	 
	        }
	    }
	});
		frame.setUndecorated(undecorated);
		setFocusTraversalKeysEnabled(false);
		final JPanel gamePanel = new JPanel();
		Insets insets = this.getInsets();
		super.setPreferredSize(new Dimension(width - 10, height - 10));
		frame.setLayout(new BorderLayout());
		gamePanel.setLayout(new BorderLayout());
		gamePanel.add(this, BorderLayout.CENTER);
		gamePanel.setBackground(Color.BLACK);
		if(!undecorated) {
			frame.getContentPane().add(menuPanel, BorderLayout.NORTH);
		}
		frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(resizable);
		frame.setMinimumSize(new Dimension(width - 10, height - 10));
		//init();
		graphics = getGameComponent().getGraphics();
		frame.setLocation((screenWidth - width) / 2, ((screenHeight - height) / 2) - screenHeight == getMaxHeight() ? 0 : undecorated ? 0 : 70);
		frame.setVisible(true); 
		frame.createBufferStrategy(2);
		

		frame.addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentResized(ComponentEvent e) {

				Dimension dimension = new Dimension(frame.getWidth(), frame.getHeight());
				
				gamePanel.setMinimumSize(dimension);
				gamePanel.setPreferredSize(dimension);
				gamePanel.setSize(dimension);
				
				Jframe.super.setPreferredSize(new Dimension(frame.getWidth() - 10, frame.getHeight() - 10));
				//Jframe.super.revalidate();
				Jframe.super.repaint();

				Jframe.super.graphics = getGameComponent().getGraphics();

			}
			
		});
		
	}

	public void setClientIcon() {
		URL icon64 = Jframe.class.getResource("/com/simplicity/client/images/i64.png");
		URL icon32 = Jframe.class.getResource("/com/simplicity/client/images/i32.png");
		URL icon16 = Jframe.class.getResource("/com/simplicity/client/images/i16.png");
		try {
			Image whip64 = ImageIO.read(icon64.openStream());
			Image whip32 = ImageIO.read(icon32.openStream());
			Image whip16 = ImageIO.read(icon16.openStream());
			ArrayList<Image> icons = new ArrayList<Image>();
			icons.add(whip64);
			icons.add(whip32);
			icons.add(whip16);
			frame.setIconImages(icons);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Our jpanel for the menu bar
	 */
	private static JPanel menuPanel;
	
	/**
	 * Initializes the menu bar
	 */
	public void initializeMenuBar() {

		/*
		 * Initialize our menu panel to hold our menu buttons
		 */
		menuPanel = new JPanel();

		/*
		 * Set the menu panel as non focusable
		 */
		menuPanel.setFocusable(false);

		/*
		 * Disable focus traversal keys
		 */
		menuPanel.setFocusTraversalKeysEnabled(false);

		menuPanel.setBackground(Color.decode("0x145200"));

		menuPanel.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
		menuPanel.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);

		/*
		 * Create our buttons
		 */
		JButton homeButton = createButton("Home", "House_icon.png", "Open the official Simplicity homepage.");
		JButton forumsButton = createButton("Forums", "forums.png", "Open the official Simplicity forums.");

		JButton knowledgeBaseButton = createButton("Wiki", "3366503.gif", "Open the Simplicity Wiki.");
		JButton storeButton = createButton("Store", "cart_icon.gif", "Open the official Simplicity store.");
		JButton voteButton = createButton("Vote", "Small-checkmark.png", "Open the official Simplicity voting page.");
		JButton scoresButton = createButton("Hiscores", "hiscores.png", "Open the official Simplicity Hiscores");

		JButton discordButton = createButton("Join Discord", "discord.png", "Join the Rune Vision discord.");

		
		/*
		 * Add our buttons to the menu panel
		 */
		menuPanel.add(homeButton);
		menuPanel.add(forumsButton);
		menuPanel.add(knowledgeBaseButton);
		menuPanel.add(storeButton);
		menuPanel.add(voteButton);
		menuPanel.add(scoresButton);
		menuPanel.add(discordButton);

		/*
		 * Add our menu panel to our frame
		 */
		frame.getContentPane().add(menuPanel, BorderLayout.NORTH);
	}
	
	/**
	 * Creates a button on the menu panel
	 * 
	 * @param title
	 *            The Title of the button
	 * @param image
	 *            The image to display
	 * @param tooltip
	 *            The tooltip when hovering over the button
	 * @return The created button
	 */
	private JButton createButton(String title, String image, String tooltip) {
		JButton button = new JButton(title);
		if (image != null)
			button.setIcon(new ImageIcon(ResourceLoader.loadImage(image)));
		button.addActionListener(this);
		if (tooltip != null)
			button.setToolTipText(tooltip);
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setContentAreaFilled(false);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setForeground(Color.WHITE);
		return button;
	}

	public URL getCodeBase() {
		try {
			return new URL("http://" + Configuration.HOST + "/");
		} catch (Exception e) {
			return super.getCodeBase();
		}
	}

	public URL getDocumentBase() {
		return getCodeBase();
	}

	public void loadError(String s) {
		System.out.println("loadError: " + s);
	}

	public String getParameter(String key) {
			return "";
	}

	public static void openUpWebSite(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url)); 	
		} catch (Exception e) {
		}
	}

	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		try {
			if (cmd != null) {
				switch(cmd) {
					case "Home":
						openURL("https://www.simplicityps.org/");
						break;
					case "Forums":
						openURL("https://www.simplicityps.org/forums");
						break;
					case "Wiki":
						openURL("https://simplicityps.wikia.com");
						break;
					case "Store":
						openURL("https://www.simplicityps.org/store/");
						break;
					case "Vote":
						openURL("https://www.simplicityps.org/vote/");
						break;
					case "Hiscores":
						openURL("https://www.simplicityps.org/hiscores/");
						break;
					case "Join Discord":
						openURL("https://discord.gg/VJy7QAH");
						break;
				}
			}
		} catch (Exception e) {
		}
	}
	
	/**
	 * Opens a URL in your default web browser
	 * 
	 * @param url
	 *            The url of the website to open
	 */
	static void openURL(String url) {
		Desktop d = Desktop.getDesktop();
		try {
			d.browse(new URI(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolkit.getScreenSize();
	int screenWidth = (int) screenSize.getWidth();
	int screenHeight = (int) screenSize.getHeight();
	//SUICIDE
}