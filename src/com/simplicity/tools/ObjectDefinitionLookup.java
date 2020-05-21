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

import com.simplicity.client.cache.definitions.ObjectDefinition;

public class ObjectDefinitionLookup extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private DefaultMutableTreeNode objects;
	
	private JTree tree;
	
	private JTable details;
	
	private List<String> detailsData = new ArrayList<String>(Arrays.asList(new String[] { "Id", "Name", "Description", "HasActions", "Actions", "ObjectModelIDs", "ObjectModelTypes", "Animation ID", "AdjustToTerrain", "SizeX", "SizeY", "ABoolean736", "ABoolean751", "ABoolean757", "ABoolean764", "ABoolean779", "AnInt775", "ConfigID", "ConfigObjectIDs", "IsUnwalkable", "VarbitIndex", "DataType" }));
	
	public ObjectDefinitionLookup() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Object Definition Lookup");
		setSize(550, 600);
		setVisible(true);
		
		objects = new DefaultMutableTreeNode("Objects");
		
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
					
					int objectId = Integer.parseInt(s.substring(0, s.indexOf(" ")));
					
					loadDetails(ObjectDefinition.forID(objectId));
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
	
	public void loadDetails(ObjectDefinition def) {
		int column = 0;
		
		details.getModel().setValueAt(def.type, column++, 1);
		details.getModel().setValueAt(def.name, column++, 1);
		details.getModel().setValueAt(def.description, column++, 1);
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
		details.getModel().setValueAt(def.dataType, column++, 1);
	}
	
	public void loadItems() {
		/*for (int i = 0; i < 60_000; i++) {
			ObjectDefinition def = ObjectDefinition.forID(i);
			
			if (def == null) {
				continue;
			}
			
			DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(def.type + " - " + def.name);
			
			objects.add(itemNode);
		
		}*/
		for (int i = 100_000; i < 100_000 + 34649; i++) {
			ObjectDefinition def = ObjectDefinition.getDefOldschool(i);
			
			if (def == null) {
				continue;
			}
			
			DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(def.type + " - " + def.name);
			
			objects.add(itemNode);
			
		}
		
		tree.expandPath(tree.getPathForRow(0));
	}

}
