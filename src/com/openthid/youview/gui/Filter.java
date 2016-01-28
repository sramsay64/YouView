package com.openthid.youview.gui;

import com.openthid.youview.data.Episode;
import com.openthid.youview.data.Rating;
import com.openthid.youview.data.Show;

public class Filter implements Cloneable {

	/**
	 * A filter that lets everything through
	 */
	public static final Filter EVERYTHING = null;
	/**
	 * A filter that only allows G Rated content
	 */
	public static final Filter G_RATED = null;

	private Rating maxRating;

	/**
	 * @param show The show to be tested
	 * @return Whether or not the show should be included in the filtered list. If false then the show should be rejected
	 */
	public boolean include(Show show) {//TODO
		return true;
	}

	/**
	 * @param episode The episode to be tested
	 * @return Whether or not the episode should be included in the filtered list. If false then the episode should be rejected
	 */
	public boolean include(Episode episode) {//TODO
		return maxRating.canInclude(episode.getRating());
	}

	public String getName() {//TODO
		return null;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public Filter clone() {//TODO
		return null;
	}
}