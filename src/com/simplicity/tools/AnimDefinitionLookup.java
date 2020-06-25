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

import com.simplicity.client.cache.definitions.Animation;

public class AnimDefinitionLookup extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private DefaultMutableTreeNode objects;
	
	private JTree tree;
	
	private JTable details;
	
	private List<String> detailsData = new ArrayList<String>(Arrays.asList(new String[] { "Id", "Frame IDs", "Frame IDs 2", "Delays", "Framestep", "Framecount", "ForcedPriority", "DelayType", "DataType" }));
	
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
				
				if (node == null) {
					return;
				}
				
				if (node.getParent() != null) {
					String s = node.getUserObject().toString();
					
					int objectId = Integer.parseInt(s);
					
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
		
		loadAnimations();
	}
	
	public void loadDetails(int id) {
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
		details.getModel().setValueAt(animation.dataType, column++, 1);
	}
	
	public void loadAnimations() {
		for (int i = 0; i < Animation.anims.length; i++) {
			Animation anim = Animation.anims[i];
			
			if (anim == null) {
				continue;
			}
			
			DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(i);
			
			objects.add(itemNode);
		}
		
		tree.expandPath(tree.getPathForRow(0));
	}

}
