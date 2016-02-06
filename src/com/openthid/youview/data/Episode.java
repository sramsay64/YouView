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

	@Calculated
	private Rating ratingX;
	@Calculated
	private Genre genre;
	@Calculated
	private Show show;

	@Given
	public String getUrl() {
		return url;
	}

	@Given
	public String getWarning() {
		return warning;
	}

	@Given
	public String getTitle() {
		return title;
	}

	@Calculated
	public Rating getRating() {
		if (ratingX == null) {
			ratingX = Rating.fromString(rating);
		}
		return ratingX;
	}

	public String getDescription() {
		return description;
	}

	@Given
	public float getSize() {
		return size;
	}

	@Given
	public String getExpires() {
		return expires;
	}

	@Given
	public String getCategory() {
		return category;
	}

	@Given
	public int getDuration() {
		return duration;
	}

	@Given
	public String getLivestream() {
		return livestream;
	}

	@Given
	public String getHome() {
		return home;
	}

	@Given
	public String getDate() {
		return date;
	}

	@Given
	public String getThumb() {
		return thumb;
	}

	@Given
	public int getId() {
		return id;
	}

	@Calculated
	public Genre getGenre() {
		if (genre == null) {
			genre = Genre.fromURL(getUrl());
		}
		return genre;
	}

	@Calculated
	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	@Override
	public String toString() {
		return getTitle();
	}

	public String toCompleteString() {
		return "Episode [url=" + url + ", warning=" + warning + ", title="
				+ title + ", rating=" + rating + ", description=" + description
				+ ", size=" + size + ", expires=" + expires + ", category="
				+ category + ", duration=" + duration + ", livestream="
				+ livestream + ", home=" + home + ", date=" + date + ", thumb="
				+ thumb + ", id=" + id + "]";
	}
}