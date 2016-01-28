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
	private TreeSelectionListener listener;

	private IconTreeNode rootNode;
	private JScrollPane scrollPane;

	public TreeShowSelector(PythonIViewInterface iViewInterface, Filter filter, TreeSelectionListener listener) {
		this.iViewInterface = iViewInterface;
		this.filter = filter;
		this.listener = listener;
		this.rootNode  = new IconTreeNode(filter.getName(), MainWindow.icon("zoom.png"));
		
		setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		add(scrollPane);
		
		rebuildTree();
	}

	private void setFilter(Filter filter) {
		this.filter = filter;
		rebuildTree();
	}

	private void rebuildTree() {
		WebTree<DefaultMutableTreeNode> tree = new WebTree<DefaultMutableTreeNode>(generateTree());
		tree.setCellRenderer(new IconTreeCellRenderer());
		TreeShowSelector selector = this;
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				if (e.getPath().getPathCount() == 1) {
					new ChangeFilterDialog(filter, selector::setFilter);
				}
			}
		});
		tree.addTreeSelectionListener(listener);
		scrollPane.setViewportView(tree);
	}

	private IconTreeNode generateTree() {
		Show[] shows = iViewInterface.getIndexSafe();
		for (int i = 0; i < shows.length; i++) {
			if (filter.include(shows[i])) {
				IconTreeNode showNode = new IconTreeNode(shows[i], MainWindow.icon("film.png"));
				for (int j = 0; j < shows[i].getItems().length; j++) {
					IconTreeNode episodeNode = new IconTreeNode(shows[i].getItems()[j], MainWindow.icon("film.png"));
					showNode.add(episodeNode);
				}
				rootNode.add(showNode);
			}
		}
		return rootNode;
	}
}