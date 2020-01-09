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

public class ItemDefinitionLookup extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private DefaultMutableTreeNode items;
	
	private JTree tree;
	
	private JTable details;
	
	private List<String> detailsData = new ArrayList<String>(Arrays.asList(new String[] { "Id", "Name", "Description", "CertID", "CertTemplateID", "ModelID", "MaleEquip1", "MaleEquip2", "MaleEquip3", "FemaleEquip1", "FemaleEquip2", "FemaleEquip3", "RotationX", "RotationY", "ModelOffsetX", "ModelOffsetY", "MaleXOffset", "MaleYOffset", "MaleZOffset", "FemaleXOffset", "FemaleYOffset", "FemaleZOffset", "EditedModelColor", "NewModelColor", "DataType" }));
	
	public ItemDefinitionLookup() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Item Definition Lookup");
		setSize(550, 600);
		setVisible(true);
		
		items = new DefaultMutableTreeNode("Items");
		
		tree = new JTree(items);
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
					
					int itemId = Integer.parseInt(s.substring(0, s.indexOf(" ")));
					
					loadDetails(ItemDefinition.forID(itemId));
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
	
	public void loadDetails(ItemDefinition rsi) {
		int column = 0;
		
		details.getModel().setValueAt(rsi.id, column++, 1);
		details.getModel().setValueAt(rsi.name, column++, 1);
		details.getModel().setValueAt(rsi.description, column++, 1);
		details.getModel().setValueAt(rsi.certID, column++, 1);
		details.getModel().setValueAt(rsi.certTemplateID, column++, 1);
		details.getModel().setValueAt(rsi.modelID, column++, 1);
		details.getModel().setValueAt(rsi.maleEquip1, column++, 1);
		details.getModel().setValueAt(rsi.maleEquip2, column++, 1);
		details.getModel().setValueAt(rsi.maleEquip3, column++, 1);
		details.getModel().setValueAt(rsi.femaleEquip1, column++, 1);
		details.getModel().setValueAt(rsi.femaleEquip2, column++, 1);
		details.getModel().setValueAt(rsi.femaleEquip3, column++, 1);
		details.getModel().setValueAt(rsi.rotationX, column++, 1);
		details.getModel().setValueAt(rsi.rotationY, column++, 1);
		details.getModel().setValueAt(rsi.modelOffsetX, column++, 1);
		details.getModel().setValueAt(rsi.modelOffsetY, column++, 1);
		details.getModel().setValueAt(rsi.maleXOffset, column++, 1);
		details.getModel().setValueAt(rsi.maleYOffset, column++, 1);
		details.getModel().setValueAt(rsi.maleZOffset, column++, 1);
		details.getModel().setValueAt(rsi.femaleXOffset, column++, 1);
		details.getModel().setValueAt(rsi.femaleYOffset, column++, 1);
		details.getModel().setValueAt(rsi.femaleZOffset, column++, 1);
		details.getModel().setValueAt(Arrays.toString(rsi.editedModelColor), column++, 1);
		details.getModel().setValueAt(Arrays.toString(rsi.newModelColor), column++, 1);
		details.getModel().setValueAt(rsi.dataType, column++, 1);
	}
	
	public void loadItems() {
		for (int i = 0; i < 70_000; i++) {
			ItemDefinition def = ItemDefinition.forID(i);
			
			if (def == null) {
				continue;
			}
			
			if (def.id == 1) {
				continue;
			}
			
			DefaultMutableTreeNode itemNode = new DefaultMutableTreeNode(def.id + " - " + def.name);
			
			items.add(itemNode);
			
		}
		
		tree.expandPath(tree.getPathForRow(0));
	}

}
