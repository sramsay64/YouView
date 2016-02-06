package com.openthid.youview.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.TreeSelectionEvent;

import com.alee.laf.WebLookAndFeel;
import com.openthid.youview.PythonIViewInterface;
import com.openthid.youview.data.Episode;

public class MainWindow {

	private PythonIViewInterface iViewInterface;

	private JFrame frame;
	private JLabel lblDescription;
	private TreeShowSelector treeShowSelector;

	private Episode selectedEpisode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		iViewInterface = new PythonIViewInterface();
		
		WebLookAndFeel.install();
		frame = new JFrame();
		frame.setTitle("YouView");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBorder(null);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panelShows = new JPanel();
		GridBagLayout gbl_panelShows = new GridBagLayout();
		gbl_panelShows.columnWidths = new int[]{0, 0, 0};
		gbl_panelShows.rowHeights = new int[]{0, 0, 0};
		gbl_panelShows.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelShows.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panelShows.setLayout(gbl_panelShows);
		tabbedPane.addTab("Shows", Icons.icon("film"), panelShows, "Select Shows and Episodes");
		
		treeShowSelector = new TreeShowSelector(iViewInterface, Filter.EVERYTHING, this::selectionChanged);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panelShows.add(treeShowSelector, gbc_panel);
		
		lblDescription = new JLabel("Select an episode to view it's description");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 1;
		panelShows.add(lblDescription, gbc_lblDescription);
		
		JButton btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedEpisode != null) {
					try {
						iViewInterface.addDownload(selectedEpisode.getShow(), selectedEpisode, "/home/scott/iViewX");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		GridBagConstraints gbc_btnDownload = new GridBagConstraints();
		gbc_btnDownload.gridx = 1;
		gbc_btnDownload.gridy = 1;
		panelShows.add(btnDownload, gbc_btnDownload);
		
		JPanel panelDownloads = new JPanel();
		tabbedPane.addTab("Downloads", null, panelDownloads, "View the progress of the downloads");
	}

	public void selectionChanged(TreeSelectionEvent e) {
		Object nodeObj = e.getPath().getLastPathComponent();
		lblDescription.setText("Select an episode to view it's description");
		if (nodeObj instanceof IconTreeNode) {
			IconTreeNode node = (IconTreeNode) nodeObj;
			Object episodeObj = node.getUserObject();
			if (episodeObj instanceof Episode) {
				Episode episode = (Episode) episodeObj;
				selectedEpisode = episode;
				lblDescription.setText("<html>" + episode.getDescription() + "<html>");
			}
		}
	}
}