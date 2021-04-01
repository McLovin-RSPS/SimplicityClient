package com.simplicity.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.simplicity.client.Client;
import com.simplicity.client.Model;
import com.simplicity.client.cache.definitions.MobDefinition;
import com.simplicity.client.cache.definitions.ObjectDefinition;
import com.simplicity.client.entity.Position;
import com.simplicity.util.StringUtils;

import javafx.application.Platform;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 * A tool used for looking up object definitions.
 * 
 * @author Blake
 *
 */
public class ObjectDefinitionLookup extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private ObjectDefinition selected;
	
	private DefaultMutableTreeNode objects;
	
	private DefaultMutableTreeNode regular;
	private DefaultMutableTreeNode osrs;
	
	private JTree tree;

	private JPanel contentPane;
	private JTextField textField;
	private JTable details;
	private JRadioButton rdbtnId;
	private JRadioButton rdbtnName;
	private JRadioButton rdbtnAnimation;
	private JCheckBox spawnOnClick;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private List<String> detailsData = new ArrayList<String>(Arrays.asList(new String[] { "Id", "Name", "Description", "HasActions", "Actions", "ObjectModelIDs", "ObjectModelTypes", "Animation ID", "AdjustToTerrain", "SizeX", "SizeY", "ABoolean736", "ABoolean751", "ABoolean757", "ABoolean764", "ABoolean779", "AnInt775", "ConfigID", "ConfigObjectIDs", "IsUnwalkable", "VarbitIndex", "Brightness", "Contrast", "DataType" }));
	
	private static final int TYPE_ID = 0;
	private static final int TYPE_NAME = 1;
	private static final int TYPE_ANIM = 2;

	/**
	 * Create the frame.
	 */
	public ObjectDefinitionLookup() {
		setTitle("Object Definition Lookup");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(475, 533);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFilter = new JLabel("Search:");
		lblFilter.setBounds(10, 11, 45, 14);
		contentPane.add(lblFilter);
		
		textField = new JTextField();
		textField.setBounds(55, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		rdbtnId = new JRadioButton("ID");
		rdbtnId.setSelected(true);
		buttonGroup.add(rdbtnId);
		rdbtnId.setBounds(147, 7, 65, 23);
		contentPane.add(rdbtnId);
		
		rdbtnName = new JRadioButton("Name");
		buttonGroup.add(rdbtnName);
		rdbtnName.setBounds(214, 7, 73, 23);
		contentPane.add(rdbtnName);
		
		rdbtnAnimation = new JRadioButton("Animation");
		buttonGroup.add(rdbtnAnimation);
		rdbtnAnimation.setBounds(289, 7, 73, 23);
		contentPane.add(rdbtnAnimation);
		
		objects = new DefaultMutableTreeNode("Objects");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = textField.getText().toLowerCase();
				
				if (text.isEmpty()) {
					objects.removeAllChildren();
					clearTree();
					resetDetails();
					init();
					return;
				}
				
				loadSearch(text);
			}
		});
		btnSubmit.setBounds(384, 7, 65, 23);
		contentPane.add(btnSubmit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 131, 450);
		contentPane.add(scrollPane);
		
		tree = new JTree(objects);
		scrollPane.setViewportView(tree);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				
				if (node == null || node.getParent() == null || node.getParent().equals(objects)) {
					return;
				}
				
				if (node.getParent() != null) {
					
					String s = node.getUserObject().toString();
					
					int objectId = Integer.parseInt(s);
					
					if (node.getParent().equals(osrs)) {
						objectId += 100_000;
					}
					
					loadDetails(objectId);
				}
				
			}
		});
		
		Object rowData[][] = new Object[detailsData.size()][2];
		
		for (int i = 0; i < detailsData.size(); i++) {
			String name = detailsData.get(i);
			rowData[i][0] = name;
			rowData[i][1] = "";
		}

		Object columnNames[] = { "", "" };
		
		details = new JTable(rowData, columnNames);
		details.setTableHeader(null);
		details.setBounds(151, 37, 298, 430);
		
		contentPane.add(details);
		
		spawnOnClick = new JCheckBox("Spawn On Click");
		spawnOnClick.setBounds(146, 467, 150, 25);
		contentPane.add(spawnOnClick);
	
	/** START COLORS **/
		
		
		JButton button = new JButton("Copy Colors");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected == null) {
					System.out.println("SELECTED NULL");
					return;
				}
				
				if (selected.objectModelIDs == null) {
					System.out.println("no models");
					return;
				}
				
				Set<Integer> colors = new HashSet<>();
				
				for (int idx = 0; idx < selected.objectModelIDs.length; idx++) {
					int modelId = selected.objectModelIDs[idx];
					
					Model model = Model.fetchModel(modelId, selected.dataType);
					
					if (model != null && model.face_color != null) {
						for (int color : model.face_color) {
							colors.add(color);
						}
					}
				}
				
				String colorString = StringUtils.intSetToString(colors, true);
				String orig = "object.originalColours = new int[] " + colorString + ";";
				String dest = "object.destColours = new int[] " + colorString + ";";
				System.out.println(orig);
				System.out.println(dest);
				
				Platform.runLater(() -> {
					ClipboardContent content = new ClipboardContent();
					content.putString(orig + "\r\n" + dest);
					Clipboard.getSystemClipboard().setContent(content);
				});
			}
		});
		button.setBounds(246, 467, 100, 25);
		contentPane.add(button);
		
		JButton editColors = new JButton("Edit colors");
		editColors.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected == null) {
					return;
				}
				
				if (selected.objectModelIDs == null) {
					System.out.println("no models");
					return;
				}
				
				String[] options = new String[selected.objectModelIDs.length];
				
				for (int i = 0; i < selected.objectModelIDs.length; i++) {
					options[i] = Integer.toString(selected.objectModelIDs[i]);
				}
				
				String input = JOptionPane.showInputDialog(null, null, "Select model",
				        JOptionPane.QUESTION_MESSAGE, null, options, options[0]).toString();
				ModelViewer.of(Integer.parseInt(input), selected.dataType);
			}
		});
		editColors.setBounds(346, 467, 100, 25);
		contentPane.add(editColors);
		init();
	}
	
	
	/** END COLORS **/
	public void createNodes(DefaultMutableTreeNode root) {
		regular = new DefaultMutableTreeNode("Regular");
		root.add(regular);
		
		osrs = new DefaultMutableTreeNode("OSRS");
		root.add(osrs);
	}
	
	public void loadDetails(int id) {
		resetDetails();
		
		int column = 0;
		
		ObjectDefinition def = ObjectDefinition.forID(id);
		
		details.getModel().setValueAt(def.type, column++, 1);
		details.getModel().setValueAt(def.name, column++, 1);
		details.getModel().setValueAt(def.description == null ? "-" : new String(def.description), column++, 1);
		details.getModel().setValueAt(def.hasActions, column++, 1);
		details.getModel().setValueAt(Arrays.toString(def.actions), column++, 1);
		details.getModel().setValueAt(Arrays.toString(def.objectModelIDs), column++, 1);
		details.getModel().setValueAt(Arrays.toString(def.objectModelTypes), column++, 1);
		details.getModel().setValueAt(def.animationID, column++, 1);
		details.getModel().setValueAt(def.adjustToTerrain, column++, 1);
		details.getModel().setValueAt(def.sizeX, column++, 1);
		details.getModel().setValueAt(def.sizeY, column++, 1);
		details.getModel().setValueAt(def.aBoolean736, column++, 1);
		details.getModel().setValueAt(def.aBoolean751, column++, 1);
		details.getModel().setValueAt(def.aBoolean757, column++, 1);
		details.getModel().setValueAt(def.aBoolean764, column++, 1);
		details.getModel().setValueAt(def.aBoolean779, column++, 1);
		details.getModel().setValueAt(def.anInt775, column++, 1);
		details.getModel().setValueAt(def.configID, column++, 1);
		details.getModel().setValueAt(Arrays.toString(def.configObjectIDs), column++, 1);
		details.getModel().setValueAt(def.isUnwalkable, column++, 1);
		details.getModel().setValueAt(def.varbitIndex, column++, 1);
		details.getModel().setValueAt(def.brightness, column++, 1);
		details.getModel().setValueAt(def.contrast, column++, 1);
		details.getModel().setValueAt(def.dataType, column++, 1);

		if (spawnOnClick.isSelected()) {
			int type = 10;
			
			if (def.objectModelTypes != null) {
				type = def.objectModelTypes[0];
			}
			
			Position pos = Client.instance.getPlayerPos();
			
			Client.instance.addObject(id, pos.getX(), pos.getY(), 0, type);
		}
		
		selected = def;
		
	}
	
	public int getSelectedType() {
		if (rdbtnName.isSelected()) {
			return TYPE_NAME;
		} else if (rdbtnAnimation.isSelected()) {
			return TYPE_ANIM;
		}
		
		return TYPE_ID;
	}
	
	public void init() {
		createNodes(objects);
		
		for (int i = 0; i < ObjectDefinition.totalObjects667; i++) {
			ObjectDefinition def = ObjectDefinition.forID(i);

			if (def == null) {
				continue;
			}
			
			DefaultMutableTreeNode objectNode = new DefaultMutableTreeNode(i);
			regular.add(objectNode);
		}
		
		for (int i = 100_000; i < 100_000 + ObjectDefinition.totalObjectsOSRS; i++) {
			ObjectDefinition def = ObjectDefinition.forID(i);

			if (def == null) {
				continue;
			}
			
			DefaultMutableTreeNode objectNode = new DefaultMutableTreeNode(i - 100_000);
			osrs.add(objectNode);
		}
		
		tree.expandPath(tree.getPathForRow(0));
	}
	
	public void clearTree() {
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		
		regular.removeAllChildren();
		
		osrs.removeAllChildren();
		
		model.reload();
	}
	
	public void resetDetails() {
		details.clearSelection();
		
		if (details.getCellEditor() != null) {
			details.getCellEditor().stopCellEditing();
		}
		
		for (int i = 0; i < detailsData.size(); i++) {
			details.getModel().setValueAt("", i, 1);
		}
	}
	
	public void loadSearch(String query) {
		if (query.isEmpty()) {
			init();
			return;
		}
		
		clearTree();
		
		resetDetails();
		
		int type = getSelectedType();
		
		int searchedId = -1;
		
		if (type == TYPE_ID || type == TYPE_ANIM) {
			searchedId = Integer.parseInt(query);
		}
		
		for (int i = 0; i < ObjectDefinition.totalObjects667; i++) {
			if (type == TYPE_ID && searchedId > ObjectDefinition.totalObjects667) {
				break;
			}
			ObjectDefinition def = ObjectDefinition.forID(i);
			
			if (def == null) {
				continue;
			}
			
			if (!query.isEmpty()) {
				if (type == TYPE_NAME) {
					if (def.name == null) {
						continue;
					}
					
					String name = def.name.toLowerCase();
					
					if (!name.startsWith(query) && !name.equals(query) && !name.contains(query)) {
						continue;
					}
					
				} else if (type == TYPE_ID) {
					if (i != searchedId) {
						continue;
					}
				} else if (type == TYPE_ANIM) {
					if (def.animationID != searchedId) {
						continue;
					}
				}
			}
			
			regular.add(new DefaultMutableTreeNode(i));
		}
		
		int maxOSRS = 100_000 + ObjectDefinition.totalObjectsOSRS;
		
		for (int i = 100_000; i < maxOSRS; i++) {
			if (type == TYPE_ID && searchedId > maxOSRS) {
				break;
			}
			
			ObjectDefinition def = ObjectDefinition.forID(i);
			
			if (def == null) {
				continue;
			}
			
			if (!query.isEmpty()) {
				if (type == TYPE_NAME) {
					if (def.name == null) {
						continue;
					}

					String name = def.name.toLowerCase();

					if (!name.startsWith(query) && !name.equals(query) && !name.contains(query)) {
						continue;
					}
				} else if (type == TYPE_ID) {
					if (i - 100_000 != searchedId) {
						continue;
					}
				} else if (type == TYPE_ANIM) {
					if (def.animationID != searchedId) {
						continue;
					}
				}
			}
			
			osrs.add(new DefaultMutableTreeNode(i - 100_000));
		}
		
		tree.expandPath(tree.getPathForRow(0));
	}
	
}
