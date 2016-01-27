package com.openthid.youview;

import java.io.IOException;
import java.util.Arrays;

import com.openthid.youview.data.Show;

public class Tester {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		PythonIViewInterface iViewInterface = new PythonIViewInterface();
		System.out.println(Arrays.toString(iViewInterface.getDownloads()));
		Show[] shows = iViewInterface.getIndex();
		System.out.println("Done!");
	}
}