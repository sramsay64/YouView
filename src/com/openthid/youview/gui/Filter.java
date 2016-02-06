package com.openthid.youview.gui;

import java.util.Arrays;

import com.openthid.youview.data.Episode;
import com.openthid.youview.data.Genre;
import com.openthid.youview.data.Rating;
import com.openthid.youview.data.Show;

public class Filter {

	/**
	 * A filter that lets everything through
	 */
	public static final Filter EVERYTHING = new Filter();
	/**
	 * A filter that only allows G Rated content
	 */
	public static final Filter G_RATED = new Filter();

	private Rating maxRating = Rating.MA;
	private String showSearch = "";
	private String episodeSearch = "";
	private String episodeDescSearch = "";
	private boolean genreKidsAllowed = true;
	private boolean genreNewsAllowed = true;
	private boolean genreOtherAllowed = true;

	/**
	 * Never <code>null</code>
	 */
	public Rating getMaxRating() {
		return maxRating;
	}

	public String getShowSearch() {
		return showSearch;
	}

	public String getEpisodeSearch() {
		return episodeSearch;
	}

	public String getEpisodeDescSearch() {
		return episodeDescSearch;
	}

	public boolean isGenreKidsAllowed() {
		return genreKidsAllowed;
	}

	public boolean isGenreNewsAllowed() {
		return genreNewsAllowed;
	}

	public boolean isGenreOtherAllowed() {
		return genreOtherAllowed;
	}

	public Filter setMaxRating(Rating maxRating) {
		this.maxRating = maxRating;
		return this;
	}

	public Filter setShowSearch(String showSearch) {
		this.showSearch = showSearch;
		return this;
	}

	public Filter setEpisodeSearch(String episodeSearch) {
		this.episodeSearch = episodeSearch;
		return this;
	}

	public Filter setEpisodeDescSearch(String episodeDescSearch) {
		this.episodeDescSearch = episodeDescSearch;
		return this;
	}

	public Filter setGenreKidsAllowed(boolean genreKidsAllowed) {
		this.genreKidsAllowed = genreKidsAllowed;
		return this;
	}

	public Filter setGenreNewsAllowed(boolean genreNewsAllowed) {
		this.genreNewsAllowed = genreNewsAllowed;
		return this;
	}

	public Filter setGenreOtherAllowed(boolean genreOtherAllowed) {
		this.genreOtherAllowed = genreOtherAllowed;
		return this;
	}

	/**
	 * @param show The show to be tested
	 * @return Whether or not the show should be included in the filtered list. If false then the show should be rejected
	 */
	public boolean include(Show show) {
		boolean b = true;
		b &= show.getTitle().contains(showSearch);
		b &= Arrays.stream(show.getItems()).anyMatch(episode -> include(episode));
		return b;
	}

	/**
	 * @param episode The episode to be tested
	 * @return Whether or not the episode should be included in the filtered list. If false then the episode should be rejected
	 */
	public boolean include(Episode episode) {
		boolean b = true;
		b &= maxRating.canInclude(episode.getRating());
		b &= episode.getTitle().contains(episodeSearch);
		b &= episode.getDescription().contains(episodeDescSearch);
		b &= !(episode.getGenre() == Genre.KIDS && !genreKidsAllowed);
		b &= !(episode.getGenre() == Genre.NEWS && !genreNewsAllowed);
		b &= !(episode.getGenre() == Genre.OTHER && !genreOtherAllowed);
		return b;
	}

	public String getName() {
		StringBuilder name = new StringBuilder();
		if (maxRating != null) {
			name.append("MaxRating: ");
			name.append(maxRating);
		}
		if (showSearch != null && !showSearch.equals("")) {
			name.append(" ShowSearch: ");
			name.append(showSearch);
		}
		if (episodeSearch != null && !episodeSearch.equals("")) {
			name.append(" EpSearch: ");
			name.append(episodeSearch);
		}
		if (episodeDescSearch != null && !episodeDescSearch.equals("")) {
			name.append(" DescSearch: ");
			name.append(episodeDescSearch);
		}
		return name.toString();
	}

	@Override
	public String toString() {
		return getName();
	}
}