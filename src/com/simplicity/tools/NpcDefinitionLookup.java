package com.simplicity.tools;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.MobDefinition;

/**
 * A tool used for looking up npc definitions.
 * 
 * @author Blake
 *
 */
public class NpcDefinitionLookup extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private DefaultMutableTreeNode objects;
	
	private DefaultMutableTreeNode regular;
	private DefaultMutableTreeNode osrs;
	
	private JTree tree;

	private JPanel contentPane;
	private JTextField textField;
	private JTable details;
	private JRadioButton rdbtnId;
	private JRadioButton rdbtnModel;
	private JRadioButton rdbtnName;
	private JRadioButton rdbtnAnimation;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private List<String> detailsData = new ArrayList<String>(Arrays.asList(new String[] { "Id", "Name", "Description", "Combat Level", "Actions", "Model IDs", "Head Model IDs", "SizeX", "SizeY", "Size", "On minimap", "Walk anim", "Stand anim", "DataType" }));
	
	private static final int TYPE_ID = 0;
	private static final int TYPE_NAME = 1;
	private static final int TYPE_MODEL = 2;
	private static final int TYPE_ANIM = 3;

	/**
	 * Create the frame.
	 */
	public NpcDefinitionLookup() {
		setTitle("NPC Definition Lookup");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 375);
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
		
		rdbtnModel = new JRadioButton("Model");
		buttonGroup.add(rdbtnModel);
		rdbtnModel.setBounds(289, 7, 73, 23);
		contentPane.add(rdbtnModel);
		
		rdbtnName = new JRadioButton("Name");
		buttonGroup.add(rdbtnName);
		rdbtnName.setBounds(214, 7, 73, 23);
		contentPane.add(rdbtnName);
		
		rdbtnAnimation = new JRadioButton("Animation");
		buttonGroup.add(rdbtnAnimation);
		rdbtnAnimation.setBounds(364, 7, 73, 23);
		contentPane.add(rdbtnAnimation);
		
		objects = new DefaultMutableTreeNode("NPC's");
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String text = textField.getText();
				
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
		btnSubmit.setBounds(459, 7, 65, 23);
		contentPane.add(btnSubmit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 202, 288);
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
					
					int objectId = Integer.parseInt(s.substring(0, s.indexOf(" ")));
					
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
		details.setBounds(226, 37, 298, 287);
		
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
		
		MobDefinition def = MobDefinition.forID(id);
		
		details.getModel().setValueAt(def.type, column++, 1);
		details.getModel().setValueAt(def.name, column++, 1);
		details.getModel().setValueAt(def.description, column++, 1);
		details.getModel().setValueAt(def.combatLevel, column++, 1);
		details.getModel().setValueAt(Arrays.toString(def.actions), column++, 1);
		details.getModel().setValueAt(Arrays.toString(def.models), column++, 1);
		details.getModel().setValueAt(Arrays.toString(def.npcHeadModels), column++, 1);
		details.getModel().setValueAt(def.sizeXZ, column++, 1);
		details.getModel().setValueAt(def.sizeY, column++, 1);
		details.getModel().setValueAt(def.squaresNeeded, column++, 1);
		details.getModel().setValueAt(def.drawMinimapDot, column++, 1);
		details.getModel().setValueAt(def.walkAnim, column++, 1);
		details.getModel().setValueAt(def.standAnim, column++, 1);
		details.getModel().setValueAt(def.dataType, column++, 1);
	}
	
	public int getSelectedType() {
		if (rdbtnName.isSelected()) {
			return TYPE_NAME;
		} else if (rdbtnModel.isSelected()) {
			return TYPE_MODEL;
		} else if (rdbtnAnimation.isSelected()) {
			return TYPE_ANIM;
		}
		
		return TYPE_ID;
	}
	
	public void init() {
		createNodes(objects);
		
		for (int i = 0; i < MobDefinition.OSRS_NPCS_OFFSET + MobDefinition.totalOSRSNPCs; i++) {
			MobDefinition def = MobDefinition.forID(i);
			
			if (def == null) {
				continue;
			}
			
			DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(def.type + " - " + def.name);
			
			if (def.dataType == DataType.REGULAR) {
				regular.add(itemNode);
			} else if (def.dataType == DataType.OLDSCHOOL){
				osrs.add(itemNode);
			}
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
	
	public void loadSearch(String text) {
		clearTree();
		
		resetDetails();
		
		int type = getSelectedType();
		
		int id = -1;
		
		if (type != TYPE_NAME) {
			id = Integer.parseInt(text);
		} else {
			text = text.toLowerCase();
		}
		
		for (int i = 0; i < MobDefinition.OSRS_NPCS_OFFSET + MobDefinition.totalOSRSNPCs; i++) {
			MobDefinition def = MobDefinition.forID(i);
			
			if (def == null) {
				continue;
			}
			
			if (type == TYPE_ID && i != id || type == TYPE_ANIM && def.walkAnim != id) {
				continue;
			}
			
			if (type == TYPE_NAME) {
				if (def.name != null && (def.name.toLowerCase().startsWith(text) || def.name.toLowerCase().contains(text) || def.name.equalsIgnoreCase(text))) {
					
				} else {
					continue;
				}
			}
			
			if (type == TYPE_MODEL) {
				boolean has = false;
				
				if (def.models != null) {
					for (int mdl : def.models) {
						if (mdl == id) {
							System.out.println(def.type + ", has model, id: " + id);
							has = true;
						}
					}
				}
				
				if (!has) {
					continue;
				}
			}
			
			if (def.dataType == DataType.OLDSCHOOL) {
				osrs.add(new DefaultMutableTreeNode(def.type + " - " + def.name));
			} else {
				regular.add(new DefaultMutableTreeNode(def.type + " - " + def.name));
			}
		}
		
		tree.expandPath(tree.getPathForRow(0));
	}
}
