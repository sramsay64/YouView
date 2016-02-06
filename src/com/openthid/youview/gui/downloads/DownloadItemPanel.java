package com.openthid.youview.gui.downloads;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JProgressBar;
import javax.swing.JButton;

import com.openthid.youview.data.Download;

@SuppressWarnings("serial")
public class DownloadItemPanel extends JPanel {

	private static final int MAXPROGRESS = 1000;

	private JProgressBar progressBar;
	private Download download;

	/**
	 * Create the panel.
	 */
	public DownloadItemPanel(Download download) {
		this.download = download;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblShow = new JLabel(download.getTitle());
		GridBagConstraints gbc_lblShow = new GridBagConstraints();
		gbc_lblShow.insets = new Insets(0, 0, 0, 5);
		gbc_lblShow.gridx = 0;
		gbc_lblShow.gridy = 0;
		add(lblShow, gbc_lblShow);
		
		progressBar = new JProgressBar(0, MAXPROGRESS);
		progressBar.setStringPainted(true);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.insets = new Insets(0, 0, 0, 5);
		gbc_progressBar.gridx = 1;
		gbc_progressBar.gridy = 0;
		add(progressBar, gbc_progressBar);
		
		JButton btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 0;
		add(btnCancel, gbc_btnCancel);
		
		updateProgress();
	}

	@Deprecated
	public void updateProgress() {
		int n = Math.round(MAXPROGRESS*download.getPercent());
		progressBar.setValue(n);
	}
}