package com.openthid.youview.gui;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

@SuppressWarnings("serial")
public class IconTreeNode extends DefaultMutableTreeNode {

	protected Icon icon;

	public IconTreeNode(Icon icon) {
		super();
		this.icon = icon;
	}

	public IconTreeNode(Object userObject, Icon icon) {
		super(userObject);
		this.icon = icon;
	}

	public IconTreeNode(Object userObject, boolean allowsChildren, Icon icon) {
		super(userObject, allowsChildren);
		this.icon = icon;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}
}