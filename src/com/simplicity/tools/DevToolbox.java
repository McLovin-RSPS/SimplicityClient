package com.simplicity.tools;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.simplicity.client.Client;
import com.simplicity.client.Model;
import com.simplicity.client.cache.definitions.ItemDefinition;
import com.simplicity.client.cache.definitions.MobDefinition;
import com.simplicity.tools.colortools.WindowSelectColor;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * A tool used for looking up object definitions.
 * 
 * @author Blake
 *
 */
public class DevToolbox extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	final JFXPanel fxPanel = new JFXPanel();
	
	/**
	 * Create the frame.
	 */
	public DevToolbox() {
		//setLookAndFeel();
		setTitle("Developer's Toolbox");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(368, 300);
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
            		ModelViewer viewer = new ModelViewer(false);
            		viewer.setVisible(true);
            	});
			}
		});
		btnModels.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnModels);
		
		JButton rs2ColorPicker = new JButton("RS2 Color Picker");
		rs2ColorPicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SwingUtilities.invokeLater(() -> {
					WindowSelectColor rs2Picker = new WindowSelectColor();
            		rs2Picker.setVisible(true);
            	});
			}
		});
		rs2ColorPicker.setBounds(x, y += 30, 150, 23);
		contentPane.add(rs2ColorPicker);
		
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
		
		JButton btnDump = new JButton("Dump File");
		btnDump.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] options = {"1 - model", "2 - animation", "3 - music", "4 - map", "7 - osrs model", "8 - osrs anim", "9 - osrs map", "10 - custom model"};
				Object searchType = JOptionPane.showInputDialog(null, null, "Choose archive",
				        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				System.out.println(searchType);
				
				String type = searchType.toString();
				
				int archive = Integer.parseInt(type.substring(0, type.indexOf(" ")));
				
				int file = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter file:"));
				
				Platform.runLater(() -> {
					FileChooser chooser2 = new FileChooser();
					chooser2.setTitle("Select destination folder");
					chooser2.setInitialDirectory(new File(System.getProperty("user.home") + "/Desktop"));
					chooser2.setInitialFileName(file + ".gz");
					
					File selected = chooser2.showSaveDialog(null);
					
					if (selected != null) {
						Client.instance.getCacheIndice(archive).dump(file, selected.getAbsolutePath());
					}
				});
			}
		});
		
		btnDump.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnDump);
		
		JButton btnDumpItemSprites = new JButton("Dump Item Sprites");
		btnDumpItemSprites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Client.instance.dumpItemImages(false);
			}
		});
		btnDumpItemSprites.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnDumpItemSprites);
		
		JButton btnRepack = new JButton("Repack File");
		btnRepack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] options = {"1 - model", "2 - animation", "3 - music", "4 - map", "7 - osrs model", "8 - osrs anim", "9 - osrs map", "10 - custom model"};
				Object searchType = JOptionPane.showInputDialog(null, null, "Choose archive to repack",
				        JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				
				String type = searchType.toString();
				
				int archive = Integer.parseInt(type.substring(0, type.indexOf(" ")));
				
				Platform.runLater(() -> {
					FileChooser chooser2 = new FileChooser();
					chooser2.setTitle("Select file");
					chooser2.setInitialDirectory(new File(System.getProperty("user.home") + "/Desktop"));
					chooser2.setSelectedExtensionFilter(new ExtensionFilter("Models", "gz", "dat"));
					
					File selected = chooser2.showOpenDialog(null);
					
					if (selected != null) {
						int fileIndex = NumberUtils.toInt(Client.getFileNameWithoutExtension(selected.getName()), -1);

						SwingUtilities.invokeLater(() -> {
							int fileId = -1;

							if (fileIndex != -1) {
								fileId = NumberUtils.toInt(JOptionPane.showInputDialog(null, "Enter file id:", fileIndex), -1);
							} else {
								fileId = NumberUtils.toInt(JOptionPane.showInputDialog(null, "Enter file id:"), -1);
							}

							if (fileId != -1) {
								Client.instance.getCacheIndice(archive).pack(fileId, selected);

								if (archive == Client.MODEL_IDX || archive == Client.OSRS_MODEL_IDX || archive == Client.CUSTOM_MODEL_IDX) { // Models
									reloadModelCache();
								}

								JOptionPane.showInputDialog("Successfully packed the file at index: " + fileId);
							}
						});
					}
				});
			}
		});
		btnRepack.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnRepack);
		
		JButton btnReloadModel = new JButton("Reload Model");
		btnReloadModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    reloadModelCache();
			}
		});
		btnReloadModel.setBounds(x, y += 30, 150, 23);
		contentPane.add(btnReloadModel);
	}
	
	private void reloadModelCache() {
		Model.reinit();
	    ItemDefinition.modelCache.clear();
	    ItemDefinition.modelCacheCustom.clear();
	    ItemDefinition.modelCacheOSRS.clear();
	    ItemDefinition.spriteCache.clear();
	    MobDefinition.reloadCache();
	    Client.myPlayer.clearCache();
	}
	
	private void setLookAndFeel(){
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch ( Exception ignored) {}
    }
	
}
