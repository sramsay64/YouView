package com.openthid.youview.gui.downloads;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JPanel;

import com.openthid.youview.PythonIViewInterface;

@SuppressWarnings("serial")
public class DownloadsPanel extends JPanel {

	private PythonIViewInterface iViewInterface;
	private DownloadItemPanel[] panels;

	/**
	 * Create the panel.
	 * @throws IOException From {@link PythonIViewInterface}
	 */
	public DownloadsPanel(PythonIViewInterface iViewInterface) {
		this.iViewInterface = iViewInterface;
//		updateList();
		Thread thread = new Thread(() -> {
			while (true) {
				System.out.println("updateList: " + updateList());
				try {
					Thread.sleep(1000);
				} catch (Exception e) {//TODO Error Handling
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}

	public boolean updateList() {
		return iViewInterface.updateDownloadsWithSafe(downloads -> {
			removeAll();
			System.out.println("UI Change");
			GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{0};
			gridBagLayout.rowHeights = buildInts(downloads.length);
			gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = buildDoubles(downloads.length);
			setLayout(gridBagLayout);
			
			panels = new DownloadItemPanel[downloads.length]; 
			for (int i = 0; i < downloads.length; i++) {
				panels[i] = new DownloadItemPanel(downloads[i]);
				GridBagConstraints gbc_item = new GridBagConstraints();
				gbc_item.fill = GridBagConstraints.HORIZONTAL;
				gbc_item.insets = new Insets(0, 0, 5, 0);
				gbc_item.gridx = 0;
				gbc_item.gridy = i;
				add(panels[i], gbc_item);
			}
		});
	}

	private int[] buildInts(int n) {
		int[] ints = new int[n];
		for (int i = 0; i < ints.length; i++) {
			ints[i] = 0;
		}
		return ints;
	}

	private double[] buildDoubles(int n) {
		double[] doubles = new double[n+1];
		for (int i = 0; i < doubles.length-1; i++) {
			doubles[i] = 0;
		}
		doubles[doubles.length-1] = Double.MIN_VALUE;
		return doubles;
	}
}