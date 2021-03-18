package com.simplicity.tools;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.simplicity.client.Client;

/**
 * A tool used for looking up object definitions.
 * 
 * @author Blake
 *
 */
public class DevToolbox extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	public DevToolbox() {
		setTitle("Developer's Toolbox");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(368, 270);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		int x = 10;
		int y = 0;
		
		JLabel lblDef = new JLabel("Definition Lookups");
		lblDef.setFont(new Font("Arial", Font.PLAIN, 14));
		lblDef.setBounds(x + 15, 10, 150, 14);
		contentPane.add(lblDef);
		
		JLabel lblToggles = new JLabel("Miscellaneous");
		lblToggles.setFont(new Font("Arial", Font.PLAIN, 14));
		lblToggles.setBounds(x + 222, 10, 150, 14);
		contentPane.add(lblToggles);
		
		JButton btnAnim = new JButton("Animations");
		btnAnim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(() -> {
            		AnimDefinitionLookup lookup = new AnimDefinitionLookup();
					lookup.setVisible(true);
            	});
			}
		});
		btnAnim.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnAnim);
		
		JButton btnGraphic = new JButton("Graphics");
		btnGraphic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(() -> {
            		GraphicDefinitionLookup lookup = new GraphicDefinitionLookup();
					lookup.setVisible(true);
            	});
			}
		});
		btnGraphic.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnGraphic);
		
		JButton btnItf = new JButton("Interfaces");
		btnItf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(() -> {
            		InterfaceDebugger debug = new InterfaceDebugger();
					debug.setVisible(true);
            	});
			}
		});
		btnItf.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnItf);
		
		JButton btnItems = new JButton("Items");
		btnItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(() -> {
            		ItemDefinitionLookup lookup = new ItemDefinitionLookup();
					lookup.setVisible(true);
            	});
			}
		});
		btnItems.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnItems);
		
		JButton btnModels = new JButton("Model Colors");
		btnModels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(() -> {
            		ModelViewer viewer = new ModelViewer();
            		viewer.setVisible(true);
            	});
			}
		});
		btnModels.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnModels);
		
		JButton btnNpcs = new JButton("NPC's");
		btnNpcs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(() -> {
            		NpcDefinitionLookup lookup = new NpcDefinitionLookup();
					lookup.setVisible(true);
            	});
			}
		});
		btnNpcs.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnNpcs);
		
		JButton btnObjects = new JButton("Objects");
		btnObjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(() -> {
    				ObjectDefinitionLookup lookup = new ObjectDefinitionLookup();
					lookup.setVisible(true);
    			});
			}
		});
		btnObjects.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnObjects);
		
		x = 200;
		y = 0;
		
		JButton btnReload = new JButton("Reload Interfaces");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Client.instance.reloadInterfaces();
			}
		});
		btnReload.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnReload);
		
		JButton btnFps = new JButton("Toggle FPS");
		btnFps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Client.fpsOn = !Client.fpsOn;
			}
		});
		btnFps.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnFps);
		
		JButton btnDumpMap = new JButton("Dump Map Image");
		btnDumpMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Client.instance.dumpMapImage();
			}
		});
		btnDumpMap.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnDumpMap);
	}
	
}
