package com.simplicity.tools;

import java.awt.Dimension;
import java.awt.FlowLayout;
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

import com.simplicity.client.Client;
import com.simplicity.client.cache.DataType;
import com.simplicity.client.cache.definitions.Animation;
import com.simplicity.client.cache.definitions.SpotAnimDefinition;

public class AnimDefinitionLookup extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private DefaultMutableTreeNode objects;
	private DefaultMutableTreeNode regular;
	private DefaultMutableTreeNode osrs;
	
	private JTree tree;
	
	private JTable details;
	
	private JPanel contentPane;
	private JTextField textField;
	private JRadioButton rdbtnId;
	private JRadioButton rdbtnItem;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private List<String> detailsData = new ArrayList<String>(Arrays.asList(new String[] { "Id", "Frame IDs", "Frame IDs 2", "Delays", "Framestep", "Framecount", "ForcedPriority", "DelayType", "Loopdelay", "Right Hand Item", "Left Hand Item", "DataType" }));
	
	private static final int TYPE_ID = 0;
	private static final int TYPE_ITEM = 1;
	
	public AnimDefinitionLookup() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Animation Definition Lookup");
		setSize(470, 370);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
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
		
		rdbtnItem = new JRadioButton("Item");
		buttonGroup.add(rdbtnItem);
		rdbtnItem.setBounds(214, 7, 73, 23);
		contentPane.add(rdbtnItem);
		
		objects = new DefaultMutableTreeNode("Animations");
		
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
						objectId += Animation.OSRS_ANIM_OFFSET;
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
	
	private void init() {
		createNodes(objects);
		
		for (int i = 0; i < Animation.anims.length; i++) {
			Animation anim = Animation.anims[i];
			
			if (anim == null) {
				continue;
			}
			
			int id = anim.dataType == DataType.OLDSCHOOL ? i - Animation.OSRS_ANIM_OFFSET : i;
			
			DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(id);
			
			if (anim.dataType == DataType.REGULAR) {
				regular.add(itemNode);
			} else if (anim.dataType == DataType.OLDSCHOOL) {
				osrs.add(itemNode);
			}
		}
		
		tree.expandPath(tree.getPathForRow(0));
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
		
		Animation animation = Animation.anims[id];
		
		details.getModel().setValueAt(id, column++, 1);
		details.getModel().setValueAt(Arrays.toString(animation.frameIDs), column++, 1);
		details.getModel().setValueAt(Arrays.toString(animation.frameIDs2), column++, 1);
		details.getModel().setValueAt(Arrays.toString(animation.delays), column++, 1);
		details.getModel().setValueAt(animation.frameStep, column++, 1);
		details.getModel().setValueAt(animation.frameCount, column++, 1);
		details.getModel().setValueAt(animation.forcedPriority, column++, 1);
		details.getModel().setValueAt(animation.delayType, column++, 1);
		details.getModel().setValueAt(animation.loopDelay, column++, 1);
		details.getModel().setValueAt(animation.rightHandItem > 512 ? animation.rightHandItem - 512 : animation.rightHandItem, column++, 1);
		details.getModel().setValueAt(animation.leftHandItem > 512 ? animation.leftHandItem - 512 : animation.leftHandItem, column++, 1);
		details.getModel().setValueAt(animation.dataType, column++, 1);
		
		Client.instance.playAnim(id);
	}
	
	public int getSelectedType() {
		if (rdbtnItem.isSelected()) {
			return TYPE_ITEM;
		}
		
		return TYPE_ID;
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
		
		System.out.println("id: " + id);
		
		for (int i = 0; i < Animation.anims.length; i++) {
			Animation anim = Animation.anims[i];
			
			if (anim == null) {
				continue;
			}
			
			if (type == TYPE_ID && i != id || type == TYPE_ITEM && (anim.rightHandItem - 512 != id && anim.leftHandItem - 512 != id)) {
				continue;
			}
			
			if (anim.dataType == DataType.OLDSCHOOL) {
				osrs.add(new DefaultMutableTreeNode(i - Animation.OSRS_ANIM_OFFSET));
			} else {
				regular.add(new DefaultMutableTreeNode(i));
			}
		}
		
		tree.expandPath(tree.getPathForRow(0));
	}

}
