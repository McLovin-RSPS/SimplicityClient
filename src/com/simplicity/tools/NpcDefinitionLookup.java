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

import com.simplicity.client.RSInterface;
import com.simplicity.client.cache.definitions.ItemDefinition;
import com.simplicity.client.cache.definitions.MobDefinition;
import com.simplicity.client.cache.definitions.ObjectDefinition;

public class NpcDefinitionLookup extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private DefaultMutableTreeNode npcs;
	
	private JTree tree;
	
	private JTable details;
	
	private List<String> detailsData = new ArrayList<String>(Arrays.asList(new String[] { "Id", "Name", "Description", "Combat Level", "Actions", "Model IDs", "Head Model IDs", "SizeX", "SizeY", "Size", "On minimap", "Walk anim", "Stand anim", "DataType" }));
	
	public NpcDefinitionLookup() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("NPC Definition Lookup");
		setSize(550, 600);
		setVisible(true);
		
		npcs = new DefaultMutableTreeNode("Npcs");
		
		tree = new JTree(npcs);
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
					
					int objectId = Integer.parseInt(s.substring(0, s.indexOf(" ")));
					
					loadDetails(MobDefinition.forID(objectId));
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
		
		loadItems();
	}
	
	public void loadDetails(MobDefinition def) {
		int column = 0;
		
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
	
	public void loadItems() {
		for (int i = 0; i < MobDefinition.OSRS_NPCS_OFFSET; i++) {
			MobDefinition def = MobDefinition.forID(i);
			
			if (def == null) {
				continue;
			}
			
			DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(def.type + " - " + def.name);
			
			npcs.add(itemNode);
		
		}
		for (int i = MobDefinition.OSRS_NPCS_OFFSET; i < MobDefinition.OSRS_NPCS_OFFSET + MobDefinition.totalOSRSNPCs; i++) {
			MobDefinition def = MobDefinition.forID(i);
			
			if (def == null) {
				continue;
			}
			
			DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(def.type + " - " + def.name);
			
			npcs.add(itemNode);
			
		}
		
		tree.expandPath(tree.getPathForRow(0));
	}

}
