package arcgis10_2.view;

import javax.swing.JPanel;
import javax.swing.JTextField;

import arcgis10_2.util.ComboBox;
import arcgis10_2.util.Resources;

import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class EditPipelineView extends JPanel {

	private JTextField txtPipelineId;
	private ComboBox cmbPipelineSubtype;
	private ComboBox cmbPipelineSystemType;
	private ComboBox cmbPipelineMaterial;
	private ComboBox cmbPipelineSection;
	private ComboBox cmbPipelineNetworkState;
	private ComboBox cmbPipelineOperationalState;
	private ComboBox cmbPipelineMaterialPublicSpace;
	private ComboBox cmbPipelineCameraInspection;
	private JTextField txtPipelineBottom;
	private JTextField txtPipelineDiameter;
	private JTextField txtPipelineBase;
	private JTextField txtPipelineHigh;
	private ComboBox cmbPipelineNumLines;
	private ComboBox cmbPipelineMaterial2;
	private JTextField txtPipelineObservations;
	private JButton btnSavePipeline;

	/**
	 * Create the panel.
	 */
	public EditPipelineView() {
		setLayout(null);

		JLabel lblPipelineId = new JLabel(Resources.messages.getString("EditManhole.Pipeline.Id"));
		lblPipelineId.setBounds(0, 0, 75, 14);
		add(lblPipelineId);

		JLabel lblPipelineType = new JLabel(Resources.messages.getString("EditManhole.Pipeline.SubType"));
		lblPipelineType.setBounds(0, 42, 46, 14);
		add(lblPipelineType);

		txtPipelineId = new JTextField();
		txtPipelineId.setBounds(0, 15, 70, 20);
		add(txtPipelineId);

		cmbPipelineSubtype = new ComboBox();
		cmbPipelineSubtype.setBounds(0, 56, 150, 20);
		add(cmbPipelineSubtype);

		JLabel lblPipelineSystemType = new JLabel(Resources.messages.getString("EditManhole.Pipeline.TypeSystem"));
		lblPipelineSystemType.setBounds(160, 42, 101, 14);
		add(lblPipelineSystemType);

		cmbPipelineSystemType = new ComboBox();
		cmbPipelineSystemType.setBounds(160, 56, 150, 20);
		add(cmbPipelineSystemType);

		JLabel lblPipelineMaterial = new JLabel(Resources.messages.getString("EditManhole.Pipeline.Material"));
		lblPipelineMaterial.setBounds(383, 0, 46, 14);
		add(lblPipelineMaterial);

		cmbPipelineMaterial = new ComboBox();
		cmbPipelineMaterial.setBounds(375, 15, 120, 20);
		add(cmbPipelineMaterial);

		JLabel lblPipelineSection = new JLabel(Resources.messages.getString("EditManhole.Pipeline.Section"));
		lblPipelineSection.setBounds(514, 0, 46, 14);
		add(lblPipelineSection);

		cmbPipelineSection = new ComboBox();
		cmbPipelineSection.setBounds(503, 15, 120, 20);
		add(cmbPipelineSection);

		JLabel lblPipelineNetworkState = new JLabel(Resources.messages.getString("EditManhole.Pipeline.NetworkState"));
		lblPipelineNetworkState.setBounds(320, 42, 106, 14);
		add(lblPipelineNetworkState);

		cmbPipelineNetworkState = new ComboBox();
		cmbPipelineNetworkState.setBounds(320, 56, 150, 20);
		add(cmbPipelineNetworkState);

		JLabel lblPipelineOperationalState = new JLabel(
				Resources.messages.getString("EditManhole.Pipeline.OperationalState"));
		lblPipelineOperationalState.setBounds(480, 42, 144, 14);
		add(lblPipelineOperationalState);

		cmbPipelineOperationalState = new ComboBox();
		cmbPipelineOperationalState.setBounds(479, 56, 144, 20);
		add(cmbPipelineOperationalState);

		JLabel lblPipelineMaterialPublicSpace = new JLabel(
				Resources.messages.getString("EditManhole.Pipeline.MaterialPublicSpace"));
		lblPipelineMaterialPublicSpace.setBounds(0, 82, 112, 14);
		add(lblPipelineMaterialPublicSpace);

		cmbPipelineMaterialPublicSpace = new ComboBox();
		cmbPipelineMaterialPublicSpace.setBounds(0, 97, 150, 20);
		add(cmbPipelineMaterialPublicSpace);

		JLabel lblPipelineCameraInspection = new JLabel(
				Resources.messages.getString("EditManhole.Pipeline.CameraInspection"));
		lblPipelineCameraInspection.setBounds(160, 82, 86, 14);
		add(lblPipelineCameraInspection);

		cmbPipelineCameraInspection = new ComboBox();
		cmbPipelineCameraInspection.setBounds(160, 97, 171, 20);
		add(cmbPipelineCameraInspection);

		JLabel lblPipelineBottom = new JLabel(Resources.messages.getString("EditManhole.Pipeline.Bottom"));
		lblPipelineBottom.setBounds(281, 0, 101, 14);
		add(lblPipelineBottom);

		txtPipelineBottom = new JTextField();
		txtPipelineBottom.setBounds(281, 15, 85, 20);
		add(txtPipelineBottom);

		JLabel lblPipelineDiameter = new JLabel(Resources.messages.getString("EditManhole.Pipeline.Diameter"));
		lblPipelineDiameter.setBounds(77, 0, 86, 14);
		add(lblPipelineDiameter);

		txtPipelineDiameter = new JTextField();
		txtPipelineDiameter.setBounds(77, 15, 73, 20);
		add(txtPipelineDiameter);

		JLabel lblPipelineBase = new JLabel(Resources.messages.getString("EditManhole.Pipeline.Base"));
		lblPipelineBase.setBounds(162, 0, 61, 14);
		add(lblPipelineBase);

		txtPipelineBase = new JTextField();
		txtPipelineBase.setBounds(160, 15, 55, 20);
		add(txtPipelineBase);

		JLabel lblPipelineHigh = new JLabel(Resources.messages.getString("EditManhole.Pipeline.High"));
		lblPipelineHigh.setBounds(218, 0, 61, 14);
		add(lblPipelineHigh);

		txtPipelineHigh = new JTextField();
		txtPipelineHigh.setBounds(218, 15, 55, 20);
		add(txtPipelineHigh);

		JLabel lblPipelineNumLines = new JLabel(Resources.messages.getString("EditManhole.Pipeline.NumLines"));
		lblPipelineNumLines.setBounds(341, 83, 88, 14);
		add(lblPipelineNumLines);

		cmbPipelineNumLines = new ComboBox();
		cmbPipelineNumLines.setBounds(341, 98, 98, 20);
		add(cmbPipelineNumLines);

		JLabel lblPipelineMaterial2 = new JLabel(Resources.messages.getString("EditManhole.Pipeline.Material2"));
		lblPipelineMaterial2.setBounds(449, 82, 75, 14);
		add(lblPipelineMaterial2);

		cmbPipelineMaterial2 = new ComboBox();
		cmbPipelineMaterial2.setBounds(448, 97, 175, 20);
		add(cmbPipelineMaterial2);

		JLabel lblPipelineObservations = new JLabel(Resources.messages.getString("EditManhole.Pipeline.Observations"));
		lblPipelineObservations.setBounds(0, 132, 46, 14);
		add(lblPipelineObservations);

		txtPipelineObservations = new JTextField();
		txtPipelineObservations.setBounds(45, 129, 450, 20);
		add(txtPipelineObservations);

		btnSavePipeline = new JButton(Resources.messages.getString("EditManhole.btnSavePipeline"));
		//btnSavePipeline.setEnabled(false);
		btnSavePipeline.setBounds(503, 128, 120, 22);
		add(btnSavePipeline);

	}

	public JTextField getTxtPipelineId() {
		return txtPipelineId;
	}

	public void setTxtPipelineId(JTextField txtPipelineId) {
		this.txtPipelineId = txtPipelineId;
	}

	public ComboBox getCmbPipelineSubtype() {
		return cmbPipelineSubtype;
	}

	public void setCmbPipelineSubtype(ComboBox cmbPipelineSubtype) {
		this.cmbPipelineSubtype = cmbPipelineSubtype;
	}

	public ComboBox getCmbPipelineSystemType() {
		return cmbPipelineSystemType;
	}

	public void setCmbPipelineSystemType(ComboBox cmbPipelineSystemType) {
		this.cmbPipelineSystemType = cmbPipelineSystemType;
	}

	public ComboBox getCmbPipelineMaterial() {
		return cmbPipelineMaterial;
	}

	public void setCmbPipelineMaterial(ComboBox cmbPipelineMaterial) {
		this.cmbPipelineMaterial = cmbPipelineMaterial;
	}

	public ComboBox getCmbPipelineSection() {
		return cmbPipelineSection;
	}

	public void setCmbPipelineSection(ComboBox cmbPipelineSection) {
		this.cmbPipelineSection = cmbPipelineSection;
	}

	public ComboBox getCmbPipelineNetworkState() {
		return cmbPipelineNetworkState;
	}

	public void setCmbPipelineNetworkState(ComboBox cmbPipelineNetworkState) {
		this.cmbPipelineNetworkState = cmbPipelineNetworkState;
	}

	public ComboBox getCmbPipelineOperationalState() {
		return cmbPipelineOperationalState;
	}

	public void setCmbPipelineOperationalState(ComboBox cmbPipelineOperationalState) {
		this.cmbPipelineOperationalState = cmbPipelineOperationalState;
	}

	public ComboBox getCmbPipelineMaterialPublicSpace() {
		return cmbPipelineMaterialPublicSpace;
	}

	public void setCmbPipelineMaterialPublicSpace(ComboBox cmbPipelineMaterialPublicSpace) {
		this.cmbPipelineMaterialPublicSpace = cmbPipelineMaterialPublicSpace;
	}

	public ComboBox getCmbPipelineCameraInspection() {
		return cmbPipelineCameraInspection;
	}

	public void setCmbPipelineCameraInspection(ComboBox cmbPipelineCameraInspection) {
		this.cmbPipelineCameraInspection = cmbPipelineCameraInspection;
	}

	public JTextField getTxtPipelineBottom() {
		return txtPipelineBottom;
	}

	public void setTxtPipelineBottom(JTextField txtPipelineBottom) {
		this.txtPipelineBottom = txtPipelineBottom;
	}

	public JTextField getTxtPipelineDiameter() {
		return txtPipelineDiameter;
	}

	public void setTxtPipelineDiameter(JTextField txtPipelineDiameter) {
		this.txtPipelineDiameter = txtPipelineDiameter;
	}

	public JTextField getTxtPipelineBase() {
		return txtPipelineBase;
	}

	public void setTxtPipelineBase(JTextField txtPipelineBase) {
		this.txtPipelineBase = txtPipelineBase;
	}

	public JTextField getTxtPipelineHigh() {
		return txtPipelineHigh;
	}

	public void setTxtPipelineHigh(JTextField txtPipelineHigh) {
		this.txtPipelineHigh = txtPipelineHigh;
	}

	public ComboBox getCmbPipelineNumLines() {
		return cmbPipelineNumLines;
	}

	public void setCmbPipelineNumLines(ComboBox cmbPipelineNumLines) {
		this.cmbPipelineNumLines = cmbPipelineNumLines;
	}

	public ComboBox getCmbPipelineMaterial2() {
		return cmbPipelineMaterial2;
	}

	public void setCmbPipelineMaterial2(ComboBox cmbPipelineMaterial2) {
		this.cmbPipelineMaterial2 = cmbPipelineMaterial2;
	}

	public JTextField getTxtPipelineObservations() {
		return txtPipelineObservations;
	}

	public void setTxtPipelineObservations(JTextField txtPipelineObservations) {
		this.txtPipelineObservations = txtPipelineObservations;
	}

	public JButton getBtnSavePipeline() {
		return btnSavePipeline;
	}

	public void setBtnSavePipeline(JButton btnSavePipeline) {
		this.btnSavePipeline = btnSavePipeline;
	}

}
