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
import com.simplicity.client.cache.definitions.SpotAnimDefinition;

/**
 * A tool used for looking up graphic definitions.
 * 
 * @author Blake
 *
 */
public class GraphicDefinitionLookup extends JFrame {
	
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
	private JRadioButton rdbtnAnimation;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private List<String> detailsData = new ArrayList<String>(Arrays.asList(new String[] { "Id", "Animation ID", "Model ID", "Rotation", "Lightness", "Shadow", "SizeXY", "SizeZ", "Original colors", "Dest colors", "DataType" }));
	
	private static final int TYPE_ID = 0;
	private static final int TYPE_MODEL = 1;
	private static final int TYPE_ANIM = 2;

	/**
	 * Create the frame.
	 */
	public GraphicDefinitionLookup() {
		setTitle("GFX Lookup");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(475, 375);
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
		
		rdbtnModel = new JRadioButton("Model");
		buttonGroup.add(rdbtnModel);
		rdbtnModel.setBounds(214, 7, 73, 23);
		contentPane.add(rdbtnModel);
		
		rdbtnAnimation = new JRadioButton("Animation");
		buttonGroup.add(rdbtnAnimation);
		rdbtnAnimation.setBounds(289, 7, 73, 23);
		contentPane.add(rdbtnAnimation);
		
		objects = new DefaultMutableTreeNode("Graphics");
		
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
				
				int id = Integer.parseInt(text);
				
				loadSearch(id);
			}
		});
		btnSubmit.setBounds(384, 7, 65, 23);
		contentPane.add(btnSubmit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 131, 288);
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
						objectId += SpotAnimDefinition.OSRS_GFX_OFFSET;
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
		details.setBounds(151, 37, 298, 287);
		
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
		
		SpotAnimDefinition gfx = SpotAnimDefinition.cache[id];
		
		details.getModel().setValueAt(id, column++, 1);
		details.getModel().setValueAt(gfx.getAnimationId(), column++, 1);
		details.getModel().setValueAt(gfx.getModelId(), column++, 1);
		details.getModel().setValueAt(gfx.rotation, column++, 1);
		details.getModel().setValueAt(gfx.lightness, column++, 1);
		details.getModel().setValueAt(gfx.shadow, column++, 1);
		details.getModel().setValueAt(gfx.sizeXY, column++, 1);
		details.getModel().setValueAt(gfx.sizeZ, column++, 1);
		details.getModel().setValueAt(Arrays.toString(gfx.originalColours), column++, 1);
		details.getModel().setValueAt(Arrays.toString(gfx.destColours), column++, 1);
		details.getModel().setValueAt(gfx.dataType, column++, 1);
	}
	
	public int getSelectedType() {
		if (rdbtnModel.isSelected()) {
			return TYPE_MODEL;
		} else if (rdbtnAnimation.isSelected()) {
			return TYPE_ANIM;
		}
		
		return TYPE_ID;
	}
	
	public void init() {
		createNodes(objects);
		
		for (int i = 0; i < SpotAnimDefinition.cache.length; i++) {
			SpotAnimDefinition def = SpotAnimDefinition.cache[i];
			
			if (def == null) {
				continue;
			}
			
			int id = def.dataType == DataType.OLDSCHOOL ? i - SpotAnimDefinition.OSRS_GFX_OFFSET : i;
			
			DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(id);
			
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
	
	public void loadSearch(int id) {
		clearTree();
		
		resetDetails();
		
		int type = getSelectedType();
		
		for (int i = 0; i < SpotAnimDefinition.OSRS_GFX_OFFSET; i++) {
			SpotAnimDefinition def = SpotAnimDefinition.cache[i];
			
			if (def == null) {
				continue;
			}
			
			if (type == TYPE_ID && i != id || type == TYPE_MODEL && def.getModelId() != id
					|| type == TYPE_ANIM && def.getAnimationId() != id) {
				continue;
			}
			
			regular.add(new DefaultMutableTreeNode(i));
		}
		
		for (int i = SpotAnimDefinition.OSRS_GFX_OFFSET; i < SpotAnimDefinition.cache.length; i++) {
			SpotAnimDefinition def = SpotAnimDefinition.cache[i];
			
			if (def == null) {
				continue;
			}
			
			if (type == TYPE_ID && i != id + SpotAnimDefinition.OSRS_GFX_OFFSET || type == TYPE_MODEL && def.getModelId() != id
					|| type == TYPE_ANIM && def.getAnimationId() != id) {
				continue;
			}
			
			osrs.add(new DefaultMutableTreeNode(i - SpotAnimDefinition.OSRS_GFX_OFFSET));
		}
		
		tree.expandPath(tree.getPathForRow(0));
	}
}
