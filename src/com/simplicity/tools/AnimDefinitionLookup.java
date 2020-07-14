package com.simplicity.tools;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
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
	
	private List<String> detailsData = new ArrayList<String>(Arrays.asList(new String[] { "Id", "Frame IDs", "Frame IDs 2", "Delays", "Framestep", "Framecount", "ForcedPriority", "DelayType", "Loopdelay", "DataType" }));
	
	public AnimDefinitionLookup() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Animation Definition Lookup");
		setSize(550, 600);
		setVisible(true);
		
		objects = new DefaultMutableTreeNode("Animations");
		
		tree = new JTree(objects);
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
		
		JScrollPane treeView = new JScrollPane(tree);
		treeView.setPreferredSize(new Dimension(200, 500));
		
		JPanel left = new JPanel();
		left.add(treeView);
		
		Object rowData[][] = new Object[detailsData.size()][2];
		
		for (int i = 0; i < detailsData.size(); i++) {
			String name = detailsData.get(i);
			rowData[i][0] = name;
			rowData[i][1] = "";
		}

		Object columnNames[] = { "", "" };
		
		details = new JTable(rowData, columnNames);
		details.setTableHeader(null);
		
		JScrollPane tableView = new JScrollPane(details);
		tableView.setPreferredSize(new Dimension(300, 500));

		JPanel right = new JPanel();
		right.add(tableView);
		
		getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));
		getContentPane().add(left);
		getContentPane().add(right);
		
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
				System.out.println("adding regular node");
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
		details.getModel().setValueAt(animation.dataType, column++, 1);
		
		Client.instance.playAnim(id);
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

}
