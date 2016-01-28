package com.openthid.youview.gui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JComboBox;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JTextField;
import javax.swing.JLabel;

import com.openthid.youview.data.Rating;

@SuppressWarnings("serial")
public class ChangeFilterPanel extends JPanel {

	private Filter old;
	private JComboBox<Rating> comboBox;
	private JTextField txtShowSearch;
	private JTextField txtEpisodeSearch;
	private JTextField txtEpisodeDescSearch;

	/**
	 * Create the panel.
	 */
	public ChangeFilterPanel(Filter old) {
		this.old = old;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblRating = new JLabel("Rating");
		GridBagConstraints gbc_lblRating = new GridBagConstraints();
		gbc_lblRating.insets = new Insets(0, 0, 5, 5);
		gbc_lblRating.anchor = GridBagConstraints.EAST;
		gbc_lblRating.gridx = 0;
		gbc_lblRating.gridy = 0;
		add(lblRating, gbc_lblRating);
		
		comboBox = new JComboBox<Rating>();
		for (Rating rating: Rating.values()) {
			comboBox.addItem(rating);
		}
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		add(comboBox, gbc_comboBox);
		
		JLabel lblShowSearch = new JLabel("Show Search");
		GridBagConstraints gbc_lblShowSearch = new GridBagConstraints();
		gbc_lblShowSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblShowSearch.anchor = GridBagConstraints.EAST;
		gbc_lblShowSearch.gridx = 0;
		gbc_lblShowSearch.gridy = 1;
		add(lblShowSearch, gbc_lblShowSearch);
		
		txtShowSearch = new JTextField();
		txtShowSearch.setText("Show Search");
		GridBagConstraints gbc_txtShowSearch = new GridBagConstraints();
		gbc_txtShowSearch.insets = new Insets(0, 0, 5, 0);
		gbc_txtShowSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtShowSearch.gridx = 1;
		gbc_txtShowSearch.gridy = 1;
		add(txtShowSearch, gbc_txtShowSearch);
		txtShowSearch.setColumns(10);
		
		JLabel lblEpisodeSearch = new JLabel("Episode Search");
		GridBagConstraints gbc_lblEpisodeSearch = new GridBagConstraints();
		gbc_lblEpisodeSearch.anchor = GridBagConstraints.EAST;
		gbc_lblEpisodeSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblEpisodeSearch.gridx = 0;
		gbc_lblEpisodeSearch.gridy = 2;
		add(lblEpisodeSearch, gbc_lblEpisodeSearch);
		
		txtEpisodeSearch = new JTextField();
		txtEpisodeSearch.setText("Episode Search");
		GridBagConstraints gbc_txtEpisodeSearch = new GridBagConstraints();
		gbc_txtEpisodeSearch.insets = new Insets(0, 0, 5, 0);
		gbc_txtEpisodeSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEpisodeSearch.gridx = 1;
		gbc_txtEpisodeSearch.gridy = 2;
		add(txtEpisodeSearch, gbc_txtEpisodeSearch);
		txtEpisodeSearch.setColumns(10);
		
		JLabel lblEpisodeDescriptionSearch = new JLabel("Episode Description Search");
		GridBagConstraints gbc_lblEpisodeDescriptionSearch = new GridBagConstraints();
		gbc_lblEpisodeDescriptionSearch.anchor = GridBagConstraints.EAST;
		gbc_lblEpisodeDescriptionSearch.insets = new Insets(0, 0, 0, 5);
		gbc_lblEpisodeDescriptionSearch.gridx = 0;
		gbc_lblEpisodeDescriptionSearch.gridy = 3;
		add(lblEpisodeDescriptionSearch, gbc_lblEpisodeDescriptionSearch);
		
		txtEpisodeDescSearch = new JTextField();
		txtEpisodeDescSearch.setText("Episode Description Search");
		GridBagConstraints gbc_txtEpisodeDescSearch = new GridBagConstraints();
		gbc_txtEpisodeDescSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEpisodeDescSearch.gridx = 1;
		gbc_txtEpisodeDescSearch.gridy = 3;
		add(txtEpisodeDescSearch, gbc_txtEpisodeDescSearch);
		txtEpisodeDescSearch.setColumns(10);
	}

	public Filter getOld() {
		return old;
	}

	public Filter getNew() {
		return new Filter()
		.setMaxRating((Rating) comboBox.getSelectedItem())
		.setShowSearch(txtShowSearch.getText())
		.setEpisodeSearch(txtEpisodeSearch.getText())
		.setEpisodeDescSearch(txtEpisodeDescSearch.getText());
	}
}