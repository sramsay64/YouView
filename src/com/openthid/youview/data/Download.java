package com.openthid.youview.data;

/**
 * An Object for Gson deserialisation which represents an {@link Episode} being downloaded.
 */
public class Download {
	private String title;
	private boolean stopped;
	private boolean failed;
	private boolean going;
	private boolean doneIt;
	private float percent;
	private String folder;
	private String filename;

	public String getTitle() {
		return title;
	}

	public boolean isStopped() {
		return stopped;
	}

	public boolean isFailed() {
		return failed;
	}

	public boolean isGoing() {
		return going;
	}

	public boolean isDoneIt() {
		return doneIt;
	}

	public float getPercent() {
		return percent;
	}

	public String getFolder() {
		return folder;
	}

	public String getFilename() {
		return filename;
	}
	
	@Override
	public String toString() {
		return "Download [title=" + title + ", stopped=" + stopped
				+ ", failed=" + failed + ", going=" + going + ", doneIt="
				+ doneIt + ", percent=" + percent + ", folder=" + folder
				+ ", filename=" + filename + "]";
	}
}