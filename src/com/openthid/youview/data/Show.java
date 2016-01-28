package com.openthid.youview.data;

import java.util.Arrays;

/**
 * An Object for Gson deserialisation which represents a show which has {@link Episode}s available for download.
 * A show is not to be confused with an {@link Episode}, {@link Show} is a kind of episode. A normal {@link Show}
 * has many {@link Episode}s but some such as documentaries have one.
 * There are also usually one {@link Show} per season of the actual show.
 */
public class Show {
	private String title;
	private String series;
	private Episode[] items;
	private int id;

	public String getTitle() {
		return title;
	}

	public String getSeries() {
		return series;
	}

	public Episode[] getItems() {
		return items;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Show [title=" + title + ", series=" + series + ", items="
				+ Arrays.toString(items) + ", id=" + id + "]";
	}

	public String getSimpleString() {
		StringBuilder builder = new StringBuilder(title);
		if (getSeries() != null) {
			builder.append(" - Series ");
			builder.append(getSeries());
		}
		return builder.toString();
	}
}