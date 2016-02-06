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

	@Given
	public String getTitle() {
		return title;
	}

	@Given
	public String getSeries() {
		return series;
	}

	@Given
	public Episode[] getItems() {
		return items;
	}

	@Given
	public int getId() {
		return id;
	}

	public String toComplexString() {
		return "Show [title=" + title + ", series=" + series + ", items="
				+ Arrays.toString(items) + ", id=" + id + "]";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(title);
		if (getSeries() != null) {
			builder.append(" - Series ");
			builder.append(getSeries());
		}
		return builder.toString();
	}
}