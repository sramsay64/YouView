package com.openthid.youview.gui;

import java.util.function.Function;

import javax.swing.JPanel;

import com.openthid.youview.PythonIViewInterface;
import com.openthid.youview.data.Show;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class TreeShowSelector extends JPanel {

	PythonIViewInterface iViewInterface;
	Function<Show, Boolean> filter;

	public TreeShowSelector(PythonIViewInterface iViewInterface, Function<Show, Boolean> filter) {
		this.iViewInterface = iViewInterface;
		this.filter = filter;
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);
		
		JTree tree = new JTree(generateTree());
		scrollPane.setViewportView(tree);
	}

	private TreeNode generateTree() {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode();
		Show[] shows = iViewInterface.getIndexSafe();
		for (int i = 0; i < shows.length; i++) {
			DefaultMutableTreeNode showNode = new DefaultMutableTreeNode(shows[i].getSimpleString());
			for (int j = 0; j < shows[i].getItems().length; j++) {
				DefaultMutableTreeNode episodeNode = new DefaultMutableTreeNode(shows[i].getItems()[j].getSimpleString());
				showNode.add(episodeNode);
			}
			rootNode.add(showNode);
		}
		return rootNode;
	}
}