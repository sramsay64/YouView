package com.openthid.youview.data;

public enum Genre {
OTHER,
KIDS,
NEWS;

	/**
	 * This function assumes that the URL is in the format:
	 * <code>_video/showname_xx_xx_650000.mp4</code> for <code>OTHER</code>
	 * or <code>_video/kids_showname_xx_xx_650000.mp4</code> for <code>KIDS</code>
	 * or <code>_video/news_showname_xx_xx_650000.mp4</code> for <code>NEWS</code>
	 * 
	 */
	public static Genre fromURL(String url) {
		if (url.startsWith("_video/kids")) {
			return KIDS;
		} else if (url.startsWith("_video/news")) {
			return NEWS;
		} else {
			return OTHER;
		}
	}
}