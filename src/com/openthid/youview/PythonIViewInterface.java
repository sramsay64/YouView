package com.openthid.youview;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Consumer;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.Gson;
import com.openthid.youview.data.Download;
import com.openthid.youview.data.Episode;
import com.openthid.youview.data.Show;

public class PythonIViewInterface {

	private Gson gson;
	private OkHttpClient httpClient;
	private String baseURL;

	private String downloadJsonCache;

	public PythonIViewInterface() {
		this("http://localhost:8080/");
	}

	public PythonIViewInterface(String baseURL) {
		gson = new Gson();
		httpClient = new OkHttpClient();
		this.baseURL = baseURL;
	}

	public Show[] getIndex() throws IOException {
		Show[] shows = getT("index", Show[].class);
		Arrays.stream(shows).forEach(show -> Arrays.stream(show.getItems()).forEach(episode -> episode.setShow(show)));
		return shows;
	}

	public Show[] getIndexSafe() {
		try {
			return getIndex();
		} catch (IOException e) {
			throw new RuntimeException(e); //TODO Add error handling
		}
	}

	@Deprecated
	public Download[] getDownloads() throws IOException {
		return getT("listDownloads", Download[].class);
	}

	@Deprecated
	public Download[] getDownloadsSafe() {
		try {
			return getDownloads();
		} catch (IOException e) {
			throw new RuntimeException(e); //TODO Add error handling
		}
	}

	public void addDownload(Show show, Episode episode, String folder) throws IOException {
		Request request = new Request.Builder()
			.url(baseURL
					+ "download?showId=" + show.getId()
					+ "&epId=" + episode.getId()
					+ "&folder=" + folder
				)
			.build();
		Response response = httpClient.newCall(request).execute();
		response.body().close();
	}

	private <T> T getT(String url, Class<T> classOfT) throws IOException {	
		Request request = new Request.Builder()
				.url(baseURL + url)
				.build();
		Response response = httpClient.newCall(request).execute();
		String jsonData = response.body().string();
		response.body().close();
		
		return gson.fromJson(jsonData, classOfT);
	}

	public boolean updateDownloadsWith(Consumer<Download[]> consumer) throws IOException {
		Request request = new Request.Builder()
			.url(baseURL + "listDownloads")
			.build();
		Response response = httpClient.newCall(request).execute();
		String jsonData = response.body().string();
		response.body().close();
		
		boolean update = !jsonData.equals(downloadJsonCache);
		if (update) {
			downloadJsonCache = jsonData;
			consumer.accept(gson.fromJson(jsonData, Download[].class));
		}
		return update;
	}

	public boolean updateDownloadsWithSafe(Consumer<Download[]> consumer) {
		try {
			return updateDownloadsWith(consumer);
		} catch (IOException e) {
			throw new RuntimeException(e); //TODO Add error handling
		}
	}
}