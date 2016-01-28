package com.openthid.youview.data;

public enum Rating {
	UNCLASIFIED,
	G,
	PG,
	M,
	MA;

	/**
	 * Like {@link Enum}'s <code>valueOf</code> but errors evaluate to <code>Uncla</code>
	 * @param rating to be decoded
	 * @return Rating
	 */
	public static Rating fromString(String rating) {
		try {
			return valueOf(rating);
		} catch (IllegalArgumentException e) {
			return UNCLASIFIED;
		}
	}

	public boolean canInclude(Rating rating) {
		return compareTo(rating) >= 0;
	}
}