package com.openthid.youview.gui;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ChangeFilterPanel extends JPanel {

	private Filter old;
	private Filter next;

	/**
	 * Create the panel.
	 */
	public ChangeFilterPanel(Filter old) {
		this.old = old;
		next = old.clone();
	}
}