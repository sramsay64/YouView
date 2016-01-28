package com.openthid.youview.gui;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.TreeSelectionEvent;

import com.alee.laf.WebLookAndFeel;
import com.openthid.youview.PythonIViewInterface;
import com.openthid.youview.data.Episode;

public class MainWindow {

	private PythonIViewInterface iViewInterface;

	private JFrame frame;
	private JLabel lblDescription;
//	private MultilineLabel lblDescription;

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
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new TreeShowSelector(iViewInterface, Filter.EVERYTHING, this::selectionChanged);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		
		lblDescription = new JLabel("Description");
//		lblDescription = new MultilineLabel("Desc");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblDescription.insets = new Insets(0, 0, 0, 5);
		gbc_lblDescription.gridx = 0;
		gbc_lblDescription.gridy = 1;
		frame.getContentPane().add(lblDescription, gbc_lblDescription);
		
		JButton btnDownload = new JButton("Download");
		GridBagConstraints gbc_btnDownload = new GridBagConstraints();
		gbc_btnDownload.gridx = 1;
		gbc_btnDownload.gridy = 1;
		frame.getContentPane().add(btnDownload, gbc_btnDownload);
	}

	public void selectionChanged(TreeSelectionEvent e) {
		Object nodeObj = e.getPath().getLastPathComponent();
		if (nodeObj instanceof IconTreeNode) {
			IconTreeNode node = (IconTreeNode) nodeObj;
			Object episodeObj = node.getUserObject();
			if (episodeObj instanceof Episode) {
				Episode episode = (Episode) episodeObj;
				lblDescription.setText("<html>" + episode.getDescription() + "/<html>");
			}
		}
	}

	public static ImageIcon icon(String name)  {
		return new ImageIcon(MainWindow.class.getResource("/com/famfamfam/icons/" + name));
	}
}