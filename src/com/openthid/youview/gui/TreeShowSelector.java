package com.openthid.youview.gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.alee.laf.tree.WebTree;
import com.openthid.youview.PythonIViewInterface;
import com.openthid.youview.data.Show;

@SuppressWarnings("serial")
public class TreeShowSelector extends JPanel {

	private PythonIViewInterface iViewInterface;
	private Filter filter;
	private IconTreeNode rootNode;

	public TreeShowSelector(PythonIViewInterface iViewInterface, Filter filter) {
		this.iViewInterface = iViewInterface;
		this.filter = filter;
		this.rootNode  = new IconTreeNode(filter.getName(), MainWindow.icon("zoom.png"));
		
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		WebTree<DefaultMutableTreeNode> tree = new WebTree<DefaultMutableTreeNode>(generateTree());
		tree.setCellRenderer(new IconTreeCellRenderer());
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				if (e.getPath().getPathCount() == 1) {
					System.out.println("TODO: filter change gui");
				}
			}
		});
		scrollPane.setViewportView(tree);
	}

	private IconTreeNode generateTree() {
		Show[] shows = iViewInterface.getIndexSafe();
		for (int i = 0; i < shows.length; i++) {
			if (filter.include(shows[i])) {
				IconTreeNode showNode = new IconTreeNode(shows[i].getSimpleString(), MainWindow.icon("film.png"));
				for (int j = 0; j < shows[i].getItems().length; j++) {
					IconTreeNode episodeNode = new IconTreeNode(shows[i].getItems()[j].getSimpleString(), MainWindow.icon("film.png"));
					showNode.add(episodeNode);
				}
				rootNode.add(showNode);
			}
		}
		return rootNode;
	}
}