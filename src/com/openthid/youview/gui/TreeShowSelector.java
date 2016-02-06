package com.openthid.youview.gui;

import java.awt.BorderLayout;
import java.util.Arrays;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.alee.laf.tree.WebTree;
import com.openthid.youview.PythonIViewInterface;

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
		rootNode  = new IconTreeNode(filter.getName(), Icons.icon("zoom"));
		
		Arrays.stream(iViewInterface.getIndexSafe())
			.filter(filter::include)
			.forEach(show ->
			{
				IconTreeNode showNode = new IconTreeNode(show, Icons.icon(show.getItems()[0]));
				Arrays.stream(show.getItems())
					.filter(filter::include)
					.forEach(episode ->
					{
						IconTreeNode episodeNode = new IconTreeNode(episode, Icons.icon(episode));
						showNode.add(episodeNode);
					}
				);
				rootNode.add(showNode);
			}
		);
		return rootNode;
	}
}