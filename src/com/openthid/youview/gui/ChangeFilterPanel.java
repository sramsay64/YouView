package com.openthid.youview.gui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JComboBox;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JLabel;

import com.openthid.youview.data.Genre;
import com.openthid.youview.data.Rating;

import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class ChangeFilterPanel extends JPanel {

	private Filter old;
	private JComboBox<Rating> comboBox;
	private JTextField txtShowSearch;
	private JTextField txtEpisodeSearch;
	private JTextField txtDescriptionSearch;
	private JCheckBox chckbxKids;
	private JCheckBox chckbxNews;
	private JCheckBox chckbxOther;

	/**
	 * Create the panel.
	 */
	public ChangeFilterPanel(Filter old) {
		this.old = old;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		comboBox.setSelectedItem(old.getMaxRating());
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 4;
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
		txtShowSearch.setText(old.getShowSearch());
		txtShowSearch.setName("Show Search");
		txtShowSearch.setToolTipText("Show Search");
		GridBagConstraints gbc_txtShowSearch = new GridBagConstraints();
		gbc_txtShowSearch.gridwidth = 4;
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
		txtEpisodeSearch.setText(old.getEpisodeSearch());
		txtEpisodeSearch.setName("Episode Search");
		txtEpisodeSearch.setToolTipText("Episode Search");
		GridBagConstraints gbc_txtEpisodeSearch = new GridBagConstraints();
		gbc_txtEpisodeSearch.gridwidth = 4;
		gbc_txtEpisodeSearch.insets = new Insets(0, 0, 5, 0);
		gbc_txtEpisodeSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEpisodeSearch.gridx = 1;
		gbc_txtEpisodeSearch.gridy = 2;
		add(txtEpisodeSearch, gbc_txtEpisodeSearch);
		txtEpisodeSearch.setColumns(10);
		
		JLabel lblDescriptionSearch = new JLabel("Description Search");
		GridBagConstraints gbc_lblDescriptionSearch = new GridBagConstraints();
		gbc_lblDescriptionSearch.anchor = GridBagConstraints.EAST;
		gbc_lblDescriptionSearch.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescriptionSearch.gridx = 0;
		gbc_lblDescriptionSearch.gridy = 3;
		add(lblDescriptionSearch, gbc_lblDescriptionSearch);
		
		txtDescriptionSearch = new JTextField();
		txtDescriptionSearch.setText(old.getEpisodeDescSearch());
		txtDescriptionSearch.setName("Description Search");
		txtDescriptionSearch.setToolTipText("Episode Description Search");
		GridBagConstraints gbc_txtDescriptionSearch = new GridBagConstraints();
		gbc_txtDescriptionSearch.gridwidth = 4;
		gbc_txtDescriptionSearch.insets = new Insets(0, 0, 5, 0);
		gbc_txtDescriptionSearch.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescriptionSearch.gridx = 1;
		gbc_txtDescriptionSearch.gridy = 3;
		add(txtDescriptionSearch, gbc_txtDescriptionSearch);
		txtDescriptionSearch.setColumns(10);
		
		JLabel lblGenres = new JLabel("Allowed Genres");
		GridBagConstraints gbc_lblGenres = new GridBagConstraints();
		gbc_lblGenres.anchor = GridBagConstraints.EAST;
		gbc_lblGenres.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenres.gridx = 0;
		gbc_lblGenres.gridy = 4;
		add(lblGenres, gbc_lblGenres);
		
		chckbxKids = new JCheckBox("Kids", Icons.icon(Genre.KIDS), old.isGenreKidsAllowed());
		GridBagConstraints gbc_chckbxKids = new GridBagConstraints();
		gbc_chckbxKids.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxKids.gridx = 1;
		gbc_chckbxKids.gridy = 4;
		add(chckbxKids, gbc_chckbxKids);
		
		chckbxNews = new JCheckBox("News", Icons.icon(Genre.NEWS), old.isGenreNewsAllowed());
		GridBagConstraints gbc_chckbxNews = new GridBagConstraints();
		gbc_chckbxNews.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNews.gridx = 2;
		gbc_chckbxNews.gridy = 4;
		add(chckbxNews, gbc_chckbxNews);
		
		chckbxOther = new JCheckBox("Other", Icons.icon(Genre.OTHER), old.isGenreOtherAllowed());
		GridBagConstraints gbc_chckbxOther = new GridBagConstraints();
		gbc_chckbxOther.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxOther.gridx = 3;
		gbc_chckbxOther.gridy = 4;
		add(chckbxOther, gbc_chckbxOther);
	}

	public Filter getOld() {
		return old;
	}

	public Filter getNew() {
		return new Filter()
		.setMaxRating((Rating) comboBox.getSelectedItem())
		.setShowSearch(txtShowSearch.getText())
		.setEpisodeSearch(txtEpisodeSearch.getText())
		.setEpisodeDescSearch(txtDescriptionSearch.getText())
		.setGenreKidsAllowed(chckbxKids.isSelected())
		.setGenreNewsAllowed(chckbxNews.isSelected())
		.setGenreOtherAllowed(chckbxOther.isSelected());
	}

	public Genre[] getAllowedGenres() {
		ArrayList<Genre> genres = new ArrayList<Genre>(3);
		if (chckbxKids.isSelected()) genres.add(Genre.KIDS);
		if (chckbxNews.isSelected()) genres.add(Genre.NEWS);
		if (chckbxOther.isSelected()) genres.add(Genre.OTHER);
		return genres.toArray(new Genre[genres.size()]);
	}
}