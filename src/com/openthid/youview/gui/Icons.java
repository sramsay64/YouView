package com.openthid.youview.gui;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import com.openthid.youview.data.Episode;
import com.openthid.youview.data.Genre;
import com.openthid.youview.data.Rating;

public class Icons {

	public static ImageIcon icon(Genre genre) {
		switch (genre) {
		case KIDS:
			return icon("user");
		case NEWS:
			return icon("world");
		case OTHER:
			return icon("film");
		}
		throw new RuntimeException("Unknown Genre: " + genre);
	}

	public static ImageIcon icon(String name)  {
		return new ImageIcon(MainWindow.class.getResource("/com/famfamfam/icons/" + name + ".png"));
	}

	public static ImageIcon icon(Rating rating)  {
		return new ImageIcon(MainWindow.class.getResource("/org/wikimedia/upload/" + rating + ".png"));
	}

	public static ImageIcon icon(Episode episode) {//TODO Cache icons
		return concatX(icon(episode.getGenre()), icon(episode.getRating()));
	}

	public static ImageIcon concatX(ImageIcon a, ImageIcon b) {
		BufferedImage bufferedImage = new BufferedImage(32, 16, BufferedImage.TYPE_INT_ARGB);
		bufferedImage.getGraphics().drawImage(a.getImage(), 0, 0, a.getIconWidth(), a.getIconHeight(), null);
		bufferedImage.getGraphics().drawImage(b.getImage(), a.getIconWidth(), 0, b.getIconWidth(), b.getIconHeight(), null);
		return new ImageIcon(bufferedImage);
	}
}