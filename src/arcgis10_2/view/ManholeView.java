package arcgis10_2.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import arcgis10_2.util.ComboBox;
import arcgis10_2.util.Resources;

@SuppressWarnings("serial")
public class ManholeView extends JPanel {

	private JTextField txtIdManhole;
	private ComboBox cmbTypeManhole;
	private ComboBox cmbStateManhole;
	private ComboBox cmbSystemType;
	private ComboBox cmbPhysicalState;
	private ComboBox cmbOperationalState;
	private ComboBox cmbNetworkState;
	private ComboBox cmbWatershed;
	private ComboBox cmbLoadingState;
	private ComboBox cmbFrameState;
	private ComboBox cmbTrunkState;
	private ComboBox cmbTrapChamber;
	private ComboBox cmbCoverState;
	private ComboBox cmbCoverMaterial;
	private ComboBox cmbCylinderState;
	private ComboBox cmbCylinderMaterial;
	private ComboBox cmbTreadState;
	private ComboBox cmbTreadMaterial;
	private ComboBox cmbConeExist;
	private ComboBox cmbConeType;
	private ComboBox cmbConeState;
	private ComboBox cmbConeMaterial;
	private JButton btnSaveManhole;

	/**
	 * Create the panel.
	 */
	public ManholeView() {
		setLayout(null);

		JLabel lblIdManhole = new JLabel(Resources.messages.getString("EditManhole.ManholeId"));
		lblIdManhole.setBounds(0, 3, 69, 15);
		add(lblIdManhole);

		txtIdManhole = new JTextField();
		txtIdManhole.setBounds(70, 0, 76, 21);
		add(txtIdManhole);

		JLabel lblTypeManhole = new JLabel(Resources.messages.getString("EditManhole.TypeManhole"));
		lblTypeManhole.setBounds(156, 3, 82, 15);
		add(lblTypeManhole);

		cmbTypeManhole = new ComboBox();
		cmbTypeManhole.setBounds(247, 0, 166, 23);
		add(cmbTypeManhole);

		JLabel lblDateInspection = new JLabel(Resources.messages.getString("EditManhole.DateInspection"));
		lblDateInspection.setBounds(423, 3, 114, 15);
		add(lblDateInspection);
		
		JLabel lblDate = new JLabel(Resources.messages.getString("EditManhole.date"));
		lblDate.setBounds(543, 3, 76, 14);
		add(lblDate);

		JLabel lblStateManhole = new JLabel(Resources.messages.getString("EditManhole.StateManhole"));
		lblStateManhole.setBounds(0, 39, 75, 15);
		add(lblStateManhole);

		cmbStateManhole = new ComboBox();
		cmbStateManhole.setBounds(85, 35, 200, 23);
		add(cmbStateManhole);

		JLabel lblSystemType = new JLabel(Resources.messages.getString("EditManhole.SystemType"));
		lblSystemType.setBounds(0, 66, 75, 15);
		add(lblSystemType);

		cmbSystemType = new ComboBox();
		cmbSystemType.setBounds(85, 62, 200, 23);
		add(cmbSystemType);

		JLabel lblPhysicalState = new JLabel(Resources.messages.getString("EditManhole.PhysicalState"));
		lblPhysicalState.setBounds(0, 93, 75, 15);
		add(lblPhysicalState);

		cmbPhysicalState = new ComboBox();
		cmbPhysicalState.setBounds(85, 89, 200, 23);
		add(cmbPhysicalState);

		JLabel lblOperationalState = new JLabel(Resources.messages.getString("EditManhole.OperationalState"));
		lblOperationalState.setBounds(303, 43, 121, 15);
		add(lblOperationalState);

		cmbOperationalState = new ComboBox();
		cmbOperationalState.setBounds(423, 35, 200, 23);
		add(cmbOperationalState);

		JLabel lblNetworkState = new JLabel(Resources.messages.getString("EditManhole.NetworkState"));
		lblNetworkState.setBounds(303, 70, 105, 15);
		add(lblNetworkState);

		cmbNetworkState = new ComboBox();
		cmbNetworkState.setBounds(423, 62, 200, 23);
		add(cmbNetworkState);

		JLabel lblWatershed = new JLabel(Resources.messages.getString("EditManhole.Watershed"));
		lblWatershed.setBounds(303, 97, 105, 15);
		add(lblWatershed);

		cmbWatershed = new ComboBox();
		cmbWatershed.setBounds(423, 89, 200, 23);
		add(cmbWatershed);

		JLabel lblLoadingState = new JLabel(Resources.messages.getString("EditManhole.LoadingState"));
		lblLoadingState.setBounds(303, 124, 89, 15);
		add(lblLoadingState);

		cmbLoadingState = new ComboBox();
		cmbLoadingState.setBounds(423, 116, 200, 23);
		add(cmbLoadingState);

		JLabel lblFrameState = new JLabel(Resources.messages.getString("EditManhole.FrameState"));
		lblFrameState.setBounds(303, 151, 89, 15);
		add(lblFrameState);

		cmbFrameState = new ComboBox();
		cmbFrameState.setBounds(423, 143, 200, 23);
		add(cmbFrameState);

		JLabel lblTrunkState = new JLabel(Resources.messages.getString("EditManhole.TrunkState"));
		lblTrunkState.setBounds(303, 178, 89, 15);
		add(lblTrunkState);

		cmbTrunkState = new ComboBox();
		cmbTrunkState.setBounds(423, 170, 200, 23);
		add(cmbTrunkState);

		JLabel lblTrapChamber = new JLabel(Resources.messages.getString("EditManhole.TrapChamber"));
		lblTrapChamber.setBounds(303, 205, 89, 15);
		add(lblTrapChamber);

		cmbTrapChamber = new ComboBox();
		cmbTrapChamber.setBounds(423, 197, 200, 23);
		add(cmbTrapChamber);

		JSeparator sepCover = new JSeparator();
		sepCover.setBounds(0, 119, 285, 2);
		add(sepCover);

		JLabel lblCover = new JLabel(Resources.messages.getString("EditManhole.Cover"));
		lblCover.setHorizontalAlignment(SwingConstants.CENTER);
		lblCover.setBounds(0, 124, 285, 14);
		add(lblCover);

		JLabel lblCoverState = new JLabel(Resources.messages.getString("EditManhole.CoverState"));
		lblCoverState.setBounds(0, 150, 75, 15);
		add(lblCoverState);

		cmbCoverState = new ComboBox();
		cmbCoverState.setBounds(85, 143, 200, 23);
		add(cmbCoverState);

		JLabel lblCoverMaterial = new JLabel(Resources.messages.getString("EditManhole.CoverMaterial"));
		lblCoverMaterial.setBounds(0, 177, 75, 15);
		add(lblCoverMaterial);

		cmbCoverMaterial = new ComboBox();
		cmbCoverMaterial.setBounds(85, 170, 200, 23);
		add(cmbCoverMaterial);

		JSeparator sepCylinder = new JSeparator();
		sepCylinder.setBounds(0, 200, 285, 2);
		add(sepCylinder);

		JLabel lblCylinder = new JLabel(Resources.messages.getString("EditManhole.Cylinder"));
		lblCylinder.setHorizontalAlignment(SwingConstants.CENTER);
		lblCylinder.setBounds(0, 205, 285, 14);
		add(lblCylinder);

		JLabel lblCylinderState = new JLabel(Resources.messages.getString("EditManhole.CylinderState"));
		lblCylinderState.setBounds(0, 230, 55, 15);
		add(lblCylinderState);

		cmbCylinderState = new ComboBox();
		cmbCylinderState.setBounds(85, 223, 200, 23);
		add(cmbCylinderState);

		JLabel lblCylinderMaterial = new JLabel(Resources.messages.getString("EditManhole.CylinderMaterial"));
		lblCylinderMaterial.setBounds(0, 257, 55, 15);
		add(lblCylinderMaterial);

		cmbCylinderMaterial = new ComboBox();
		cmbCylinderMaterial.setBounds(85, 250, 200, 23);
		add(cmbCylinderMaterial);

		JSeparator sepTread = new JSeparator();
		sepTread.setBounds(0, 280, 285, 2);
		add(sepTread);

		JLabel lblTread = new JLabel(Resources.messages.getString("EditManhole.Tread"));
		lblTread.setHorizontalAlignment(SwingConstants.CENTER);
		lblTread.setBounds(0, 285, 285, 14);
		add(lblTread);

		JLabel lblTreadState = new JLabel(Resources.messages.getString("EditManhole.TreadState"));
		lblTreadState.setBounds(0, 312, 75, 15);
		add(lblTreadState);

		cmbTreadState = new ComboBox();
		cmbTreadState.setBounds(85, 304, 200, 23);
		add(cmbTreadState);

		JLabel lblTreadMaterial = new JLabel(Resources.messages.getString("EditManhole.TreadMaterial"));
		lblTreadMaterial.setBounds(0, 339, 75, 15);
		add(lblTreadMaterial);

		cmbTreadMaterial = new ComboBox();
		cmbTreadMaterial.setBounds(85, 331, 200, 23);
		add(cmbTreadMaterial);

		JSeparator sepCone = new JSeparator();
		sepCone.setBounds(303, 227, 321, 2);
		add(sepCone);

		JLabel lblCone = new JLabel(Resources.messages.getString("EditManhole.Cone"));
		lblCone.setHorizontalAlignment(SwingConstants.CENTER);
		lblCone.setBounds(296, 231, 328, 14);
		add(lblCone);

		JLabel lblConeExist = new JLabel(Resources.messages.getString("EditManhole.ConeExist"));
		lblConeExist.setBounds(306, 250, 55, 15);
		add(lblConeExist);

		cmbConeExist = new ComboBox();
		cmbConeExist.setBounds(423, 250, 200, 23);
		add(cmbConeExist);

		JLabel lblConeType = new JLabel(Resources.messages.getString("EditManhole.ConeType"));
		lblConeType.setBounds(306, 277, 55, 15);
		add(lblConeType);

		cmbConeType = new ComboBox();
		cmbConeType.setBounds(423, 277, 200, 23);
		add(cmbConeType);

		JLabel lblConeState = new JLabel(Resources.messages.getString("EditManhole.ConeState"));
		lblConeState.setBounds(306, 307, 55, 15);
		add(lblConeState);

		cmbConeState = new ComboBox();
		cmbConeState.setBounds(423, 304, 200, 23);
		add(cmbConeState);

		JLabel lblConeMaterial = new JLabel(Resources.messages.getString("EditManhole.ConeMaterial"));
		lblConeMaterial.setBounds(306, 334, 55, 15);
		add(lblConeMaterial);

		cmbConeMaterial = new ComboBox();
		cmbConeMaterial.setBounds(423, 331, 200, 23);
		add(cmbConeMaterial);

		btnSaveManhole = new JButton(Resources.messages.getString("EditManhole.btnSaveManhole"));
		btnSaveManhole.setBounds(252, 365, 121, 23);
		add(btnSaveManhole);

	}

	public JTextField getTxtIdManhole() {
		return txtIdManhole;
	}

	public void setTxtIdManhole(JTextField txtIdManhole) {
		this.txtIdManhole = txtIdManhole;
	}

	public ComboBox getCmbTypeManhole() {
		return cmbTypeManhole;
	}

	public void setCmbTypeManhole(ComboBox cmbTypeManhole) {
		this.cmbTypeManhole = cmbTypeManhole;
	}

	public ComboBox getCmbStateManhole() {
		return cmbStateManhole;
	}

	public void setCmbStateManhole(ComboBox cmbStateManhole) {
		this.cmbStateManhole = cmbStateManhole;
	}

	public ComboBox getCmbSystemType() {
		return cmbSystemType;
	}

	public void setCmbSystemType(ComboBox cmbSystemType) {
		this.cmbSystemType = cmbSystemType;
	}

	public ComboBox getCmbPhysicalState() {
		return cmbPhysicalState;
	}

	public void setCmbPhysicalState(ComboBox cmbPhysicalState) {
		this.cmbPhysicalState = cmbPhysicalState;
	}

	public ComboBox getCmbOperationalState() {
		return cmbOperationalState;
	}

	public void setCmbOperationalState(ComboBox cmbOperationalState) {
		this.cmbOperationalState = cmbOperationalState;
	}

	public ComboBox getCmbNetworkState() {
		return cmbNetworkState;
	}

	public void setCmbNetworkState(ComboBox cmbNetworkState) {
		this.cmbNetworkState = cmbNetworkState;
	}

	public ComboBox getCmbWatershed() {
		return cmbWatershed;
	}

	public void setCmbWatershed(ComboBox cmbWatershed) {
		this.cmbWatershed = cmbWatershed;
	}

	public ComboBox getCmbLoadingState() {
		return cmbLoadingState;
	}

	public void setCmbLoadingState(ComboBox cmbLoadingState) {
		this.cmbLoadingState = cmbLoadingState;
	}

	public ComboBox getCmbFrameState() {
		return cmbFrameState;
	}

	public void setCmbFrameState(ComboBox cmbFrameState) {
		this.cmbFrameState = cmbFrameState;
	}

	public ComboBox getCmbTrunkState() {
		return cmbTrunkState;
	}

	public void setCmbTrunkState(ComboBox cmbTrunkState) {
		this.cmbTrunkState = cmbTrunkState;
	}

	public ComboBox getCmbTrapChamber() {
		return cmbTrapChamber;
	}

	public void setCmbTrapChamber(ComboBox cmbTrapChamber) {
		this.cmbTrapChamber = cmbTrapChamber;
	}

	public ComboBox getCmbCoverState() {
		return cmbCoverState;
	}

	public void setCmbCoverState(ComboBox cmbCoverState) {
		this.cmbCoverState = cmbCoverState;
	}

	public ComboBox getCmbCoverMaterial() {
		return cmbCoverMaterial;
	}

	public void setCmbCoverMaterial(ComboBox cmbCoverMaterial) {
		this.cmbCoverMaterial = cmbCoverMaterial;
	}

	public ComboBox getCmbCylinderState() {
		return cmbCylinderState;
	}

	public void setCmbCylinderState(ComboBox cmbCylinderState) {
		this.cmbCylinderState = cmbCylinderState;
	}

	public ComboBox getCmbCylinderMaterial() {
		return cmbCylinderMaterial;
	}

	public void setCmbCylinderMaterial(ComboBox cmbCylinderMaterial) {
		this.cmbCylinderMaterial = cmbCylinderMaterial;
	}

	public ComboBox getCmbTreadState() {
		return cmbTreadState;
	}

	public void setCmbTreadState(ComboBox cmbTreadState) {
		this.cmbTreadState = cmbTreadState;
	}

	public ComboBox getCmbTreadMaterial() {
		return cmbTreadMaterial;
	}

	public void setCmbTreadMaterial(ComboBox cmbTreadMaterial) {
		this.cmbTreadMaterial = cmbTreadMaterial;
	}

	public ComboBox getCmbConeExist() {
		return cmbConeExist;
	}

	public void setCmbConeExist(ComboBox cmbConeExist) {
		this.cmbConeExist = cmbConeExist;
	}

	public ComboBox getCmbConeType() {
		return cmbConeType;
	}

	public void setCmbConeType(ComboBox cmbConeType) {
		this.cmbConeType = cmbConeType;
	}

	public ComboBox getCmbConeState() {
		return cmbConeState;
	}

	public void setCmbConeState(ComboBox cmbConeState) {
		this.cmbConeState = cmbConeState;
	}

	public ComboBox getCmbConeMaterial() {
		return cmbConeMaterial;
	}

	public void setCmbConeMaterial(ComboBox cmbConeMaterial) {
		this.cmbConeMaterial = cmbConeMaterial;
	}

	public JButton getBtnSaveManhole() {
		return btnSaveManhole;
	}

	public void setBtnSaveManhole(JButton btnSaveManhole) {
		this.btnSaveManhole = btnSaveManhole;
	}
}
