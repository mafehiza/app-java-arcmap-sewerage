package arcgis10_2.view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

import com.esri.arcgis.interop.AutomationException;

import arcgis10_2.util.Resources;

import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class EditManholeView extends JFrame implements WindowListener {

	private static EditManholeView instance = null;

	private JPanel ManholePanel;

	private ManholeView panelManhole;
	private JTabbedPane tabbedPipelines;

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 * @throws AutomationException
	 */
	public EditManholeView() throws AutomationException, IOException {
		setResizable(false);

		setTitle(Resources.messages.getString("EditManhole.PanelManholeTitle"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 667, 667);
		ManholePanel = new JPanel();
		ManholePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ManholePanel.setLayout(null);
		setContentPane(ManholePanel);

		panelManhole = new ManholeView();
		panelManhole.setBounds(10, 11, 623, 392);
		ManholePanel.add(panelManhole);

		JPanel viewPipeline = new EditPipelineView();
		
		tabbedPipelines = new JTabbedPane(JTabbedPane.TOP);
		tabbedPipelines.setBounds(10, 410, 630, 209);
		tabbedPipelines.addTab("New", viewPipeline);
		ManholePanel.add(tabbedPipelines);

	}

	public static EditManholeView getInstance() throws AutomationException, IOException {
		if (instance == null) {
			instance = new EditManholeView();
			instance.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		}
		return instance;
	}

	public ManholeView getPanelManhole() {
		return panelManhole;
	}

	public void setPanelManhole(ManholeView panelManhole) {
		this.panelManhole = panelManhole;
	}

	public JTabbedPane getTabbedPipelines() {
		return tabbedPipelines;
	}

	public void setTabbedPipelines(JTabbedPane tabbedPipelines) {
		this.tabbedPipelines = tabbedPipelines;
	}

	/*
	 * public PipelineView getPanelPipeline() { return panelPipeline; }
	 * 
	 * public void setPanelPipeline(PipelineView panelPipeline) { this.panelPipeline
	 * = panelPipeline; }
	 */
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
}
