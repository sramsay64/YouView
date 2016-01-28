package com.openthid.youview.gui;

import com.openthid.youview.data.Episode;
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
	public static final Filter G_RATED = new Filter().setMaxRating(Rating.G);

	private Rating maxRating;
	private String showSearch;
	private String episodeSearch;
	private String episodeDescSearch;

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

	/**
	 * @param show The show to be tested
	 * @return Whether or not the show should be included in the filtered list. If false then the show should be rejected
	 */
	public boolean include(Show show) {
		boolean b = true;
		if (showSearch != null) {
			b &= show.getTitle().contains(showSearch);
		}
		return b;
	}

	/**
	 * @param episode The episode to be tested
	 * @return Whether or not the episode should be included in the filtered list. If false then the episode should be rejected
	 */
	public boolean include(Episode episode) {
		boolean b = true;
		if (maxRating != null) {
			b &= maxRating.canInclude(episode.getRating());
		}
		if (episodeSearch != null) {
			b &= episode.getTitle().contains(episodeSearch);
		}
		if (episodeDescSearch != null) {
			b &= episode.getDescription().contains(episodeDescSearch);
		}
		return b;
	}

	public String getName() {
		StringBuilder name = new StringBuilder();
		if (maxRating != null) {
			name.append("MaxRating: ");
			name.append(maxRating);
		}
		if (showSearch != null) {
			name.append("showSearch: ");
			name.append(showSearch);
		}
		if (episodeSearch != null) {
			name.append("episodeSearch: ");
			name.append(episodeSearch);
		}
		if (episodeDescSearch != null) {
			name.append("episodeDescSearch: ");
			name.append(episodeDescSearch);
		}
		return name.toString();
	}

	@Override
	public String toString() {
		return getName();
	}

	@Deprecated
	@Override
	public Filter clone() throws CloneNotSupportedException {
		return new Filter()
		.setMaxRating(getMaxRating())
		.setShowSearch(getShowSearch())
		.setEpisodeSearch(getEpisodeSearch())
		.setEpisodeDescSearch(getEpisodeDescSearch());
	}
}