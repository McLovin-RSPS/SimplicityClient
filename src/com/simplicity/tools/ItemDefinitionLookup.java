package com.simplicity.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.ItemDefinition;

/**
 * A tool used for looking up object definitions.
 * 
 * @author Blake
 *
 */
public class ItemDefinitionLookup extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private DefaultMutableTreeNode objects;
	
	private DefaultMutableTreeNode regular;
	private DefaultMutableTreeNode osrs;
	
	private JTree tree;

	private JPanel contentPane;
	private JTextField textField;
	private JTable details;
	private JRadioButton rdbtnId;
	private JRadioButton rdbtnName;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private List<String> detailsData = new ArrayList<String>(Arrays.asList(new String[] { "Id", "Name", "Description", "CertID", "CertTemplateID", "ModelID", "MaleEquip1", "MaleEquip2", "MaleEquip3", "FemaleEquip1", "FemaleEquip2", "FemaleEquip3", "RotationX", "RotationY", "ModelOffsetX", "ModelOffsetY", "MaleXOffset", "MaleYOffset", "MaleZOffset", "FemaleXOffset", "FemaleYOffset", "FemaleZOffset", "EditedModelColor", "NewModelColor", "Actions", "GroundActions", "DataType" }));
	
	private static final int TYPE_ID = 0;
	private static final int TYPE_NAME = 1;

	/**
	 * Create the frame.
	 */
	public ItemDefinitionLookup() {
		setTitle("Item Definition Lookup");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(475, 518);
		setLocationRelativeTo(null);
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
		
		objects = new DefaultMutableTreeNode("Items");
		
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
		scrollPane.setBounds(10, 36, 131, 431);
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
						objectId += ItemDefinition.OSRS_ITEMS_OFFSET;
					}
					
					loadDetails(objectId);
				}
				
			}
		});
		
		Object rowData[][] = new Object[detailsData.size()][3];
		
		for (int i = 0; i < detailsData.size(); i++) {
			String name = detailsData.get(i);
			rowData[i][0] = name;
			rowData[i][1] = "";
		}

		Object columnNames[] = { "", "", "" };
		
		details = new JTable(rowData, columnNames);
		TableColumnModel columnModel = details.getColumnModel();
		columnModel.getColumn(2).setPreferredWidth(1);
		details.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = details.getSelectedRow();
				int col = details.getSelectedColumn();
				
				if (col == 2) {
					Object value = details.getValueAt(row, col - 1);
					
					if (value instanceof Integer) {
						int modelID = (int) value;
						
						if (modelID != -1) {
							value = details.getValueAt(detailsData.size() - 1, 1);
							
							DataType type = DataType.valueOf(value.toString());
							
							ModelViewer.of(modelID, type);
						}
					}
				}
			}
		});
		details.setTableHeader(null);
		details.setBounds(151, 37, 298, 431);
		
		contentPane.add(details);
		
		init();
	}
	
	public void createNodes(DefaultMutableTreeNode root) {
		regular = new DefaultMutableTreeNode("Regular");
		root.add(regular);
		
		osrs = new DefaultMutableTreeNode("OSRS");
		root.add(osrs);
	}
	
	public void loadDetails(int id) {
		resetDetails();
		
		int column = 0;
		
		ItemDefinition def = ItemDefinition.forID(id);
		
		details.getModel().setValueAt(def.id, column++, 1);
		details.getModel().setValueAt(def.name, column++, 1);
		details.getModel().setValueAt(def.description, column++, 1);
		details.getModel().setValueAt(def.certID, column++, 1);
		details.getModel().setValueAt(def.certTemplateID, column++, 1);
		
		viewColor(def.modelID, column);
		details.getModel().setValueAt(def.modelID, column++, 1);
		
		viewColor(def.maleEquip1, column);
		details.getModel().setValueAt(def.maleEquip1, column++, 1);
		
		viewColor(def.maleEquip2, column);
		details.getModel().setValueAt(def.maleEquip2, column++, 1);
		
		viewColor(def.maleEquip3, column);
		details.getModel().setValueAt(def.maleEquip3, column++, 1);
		
		viewColor(def.femaleEquip1, column);
		details.getModel().setValueAt(def.femaleEquip1, column++, 1);
		
		viewColor(def.femaleEquip2, column);
		details.getModel().setValueAt(def.femaleEquip2, column++, 1);
		
		viewColor(def.femaleEquip3, column);
		details.getModel().setValueAt(def.femaleEquip3, column++, 1);
		
		details.getModel().setValueAt(def.rotationX, column++, 1);
		details.getModel().setValueAt(def.rotationY, column++, 1);
		details.getModel().setValueAt(def.modelOffsetX, column++, 1);
		details.getModel().setValueAt(def.modelOffsetY, column++, 1);
		details.getModel().setValueAt(def.maleXOffset, column++, 1);
		details.getModel().setValueAt(def.maleYOffset, column++, 1);
		details.getModel().setValueAt(def.maleZOffset, column++, 1);
		details.getModel().setValueAt(def.femaleXOffset, column++, 1);
		details.getModel().setValueAt(def.femaleYOffset, column++, 1);
		details.getModel().setValueAt(def.femaleZOffset, column++, 1);
		details.getModel().setValueAt(Arrays.toString(def.editedModelColor), column++, 1);
		details.getModel().setValueAt(Arrays.toString(def.newModelColor), column++, 1);
		details.getModel().setValueAt(Arrays.toString(def.actions), column++, 1);
		details.getModel().setValueAt(Arrays.toString(def.groundActions), column++, 1);
		details.getModel().setValueAt(def.dataType, column++, 1);
	}
	
	private void viewColor(int modelID, int column) {
		if (modelID != -1) {
			details.getModel().setValueAt("[Colors]", column, 2);
		} else {
			details.getModel().setValueAt("", column, 2);
		}
	}

	public int getSelectedType() {
		if (rdbtnName.isSelected()) {
			return TYPE_NAME;
		}
		
		return TYPE_ID;
	}
	
	public void init() {
		createNodes(objects);
		
		for (int i = 0; i < ItemDefinition.totalItems; i++) {
			ItemDefinition def = ItemDefinition.forID(i);

			if (def == null) {
				continue;
			}
			
			DefaultMutableTreeNode objectNode = new DefaultMutableTreeNode(i);
			regular.add(objectNode);
		}
		
		for (int i = ItemDefinition.OSRS_ITEMS_OFFSET; i < ItemDefinition.OSRS_ITEMS_OFFSET + ItemDefinition.totalItemsOSRS; i++) {
			ItemDefinition def = ItemDefinition.forID(i);

			if (def == null) {
				continue;
			}
			
			DefaultMutableTreeNode objectNode = new DefaultMutableTreeNode(i - ItemDefinition.OSRS_ITEMS_OFFSET);
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
		
		if (type == TYPE_ID) {
			try {
				searchedId = Integer.parseInt(query);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(
				        null, "Invalid ID specified.", "Error", JOptionPane.ERROR_MESSAGE);
			}
			return;
		}
		
		for (int i = 0; i < ItemDefinition.totalItems; i++) {
			if (type == TYPE_ID && searchedId > ItemDefinition.totalItems) {
				break;
			}
			
			ItemDefinition def = null;
			
			try {
				def = ItemDefinition.forID(i);
			} catch (Exception e) {
				
			}
			
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
				}
			}
			
			regular.add(new DefaultMutableTreeNode(i));
		}
		
		for (int i = ItemDefinition.OSRS_ITEMS_OFFSET; i < ItemDefinition.OSRS_ITEMS_OFFSET + ItemDefinition.totalItemsOSRS; i++) {
			if (type == TYPE_ID && searchedId > ItemDefinition.OSRS_ITEMS_OFFSET + ItemDefinition.totalItemsOSRS) {
				break;
			}
			
			ItemDefinition def = null;
			
			try {
				def = ItemDefinition.forID(i);
			} catch (Exception e) {
				
			}
			
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
					if (i - ItemDefinition.OSRS_ITEMS_OFFSET != searchedId) {
						continue;
					}
				}
			}
			
			osrs.add(new DefaultMutableTreeNode(i - ItemDefinition.OSRS_ITEMS_OFFSET));
		}
		
		tree.expandPath(tree.getPathForRow(0));
	}
	
}
