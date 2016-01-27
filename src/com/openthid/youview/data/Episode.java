package com.openthid.youview.data;

/**
 * An Object for Gson deserialisation which represents an Episode {@link Show}
 * that is available for download
 */
public class Episode {
	private String url;
	private String warning;
	private String title;
	private String rating;
	private String description;
	private float size;
	private String expires; // TODO Add date parsing
	private String category;
	private int duration;
	private String livestream;
	private String home;
	private String date; // TODO Add date parsing
	private String thumb; // TODO Add Thumbnail downloading
	private int id;

	public String getUrl() {
		return url;
	}

	public String getWarning() {
		return warning;
	}

	public String getTitle() {
		return title;
	}

	public String getRating() {
		return rating;
	}

	public String getDescription() {
		return description;
	}

	public float getSize() {
		return size;
	}

	public String getExpires() {
		return expires;
	}

	public String getCategory() {
		return category;
	}

	public int getDuration() {
		return duration;
	}

	public String getLivestream() {
		return livestream;
	}

	public String getHome() {
		return home;
	}

	public String getDate() {
		return date;
	}

	public String getThumb() {
		return thumb;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Episode [url=" + url + ", warning=" + warning + ", title="
				+ title + ", rating=" + rating + ", description=" + description
				+ ", size=" + size + ", expires=" + expires + ", category="
				+ category + ", duration=" + duration + ", livestream="
				+ livestream + ", home=" + home + ", date=" + date + ", thumb="
				+ thumb + ", id=" + id + "]";
	}

	public String getSimpleString() {
		return getTitle();
	}
}