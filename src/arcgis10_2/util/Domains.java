package arcgis10_2.util;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.esri.arcgis.geodatabase.ICodedValueDomain;
import com.esri.arcgis.geodatabase.IDomain;
import com.esri.arcgis.geodatabase.IWorkspace;
import com.esri.arcgis.geodatabase.IWorkspaceDomains;
import com.esri.arcgis.interop.AutomationException;

import javax.swing.DefaultComboBoxModel;

public class Domains {

	IWorkspace pWS_Sewerage;
	IWorkspaceDomains pWSD_Sewerage;

	private DefaultComboBoxModel<String> domManholeType;
	private Domain domStateManhole;
	private Domain domSystemType;
	private Domain domPhysicalState;
	private Domain domNetworkState;
	private Domain domWatershed;
	private Domain domTrapChamber;
	private Domain domOperationalState;
	private Domain domLoadingState;
	private Domain domFrameState;
	private Domain domTrunkState;
	private Domain domCoverState;
	private Domain domCoverMaterial;
	private Domain domCylinderState;
	private Domain domCylinderMaterial;
	private Domain domTreadState;
	private Domain domTreadMaterial;
	private Domain domConeExist;
	private Domain domConeType;
	private Domain domConeState;
	private Domain domConeMaterial;

	private DefaultComboBoxModel<Integer> domNumPipelines;
	private Domain domPipelineMaterial;
	private Domain domPipelineDiameter;
	private Domain domPipelineMaterialPublicSpace;
	private Domain domPipelineSection;
	private Domain domCameraInspection;

	public Domains(IWorkspace pWS_Sewerage) throws AutomationException, IOException {
		this.pWS_Sewerage = pWS_Sewerage;
		this.pWSD_Sewerage = new com.esri.arcgis.geodatabase.IWorkspaceDomainsProxy(pWS_Sewerage);

		domManholeType = new DefaultComboBoxModel<String>();
		String[] listManholeType = Resources.gdb.getString("CmdManholeType").split(",");
		for (String strType : listManholeType) {
			domManholeType.addElement(strType);
		}

		domStateManhole = new Domain(Resources.gdb.getString("DomStateManhole"));
		domSystemType = new Domain(Resources.gdb.getString("DomSystemType"));
		domPhysicalState = new Domain(Resources.gdb.getString("DomPhysicalState"));
		domNetworkState = new Domain(Resources.gdb.getString("DomNetworkState"));
		domWatershed = new Domain(Resources.gdb.getString("DomWatershed"));
		domTrapChamber = new Domain(Resources.gdb.getString("DomTrapChamber"));
		domOperationalState = new Domain(Resources.gdb.getString("DomOperationalState"));
		domLoadingState = new Domain(Resources.gdb.getString("DomLoadingState"));
		domFrameState = new Domain(Resources.gdb.getString("DomFrameState"));
		domTrunkState = new Domain(Resources.gdb.getString("DomTrunkState"));
		domCoverState = new Domain(Resources.gdb.getString("DomCoverState"));
		domCoverMaterial = new Domain(Resources.gdb.getString("DomCoverMaterial"));
		domCylinderState = new Domain(Resources.gdb.getString("DomCylinderState"));
		domCylinderMaterial = new Domain(Resources.gdb.getString("DomCylinderMaterial"));
		domTreadState = new Domain(Resources.gdb.getString("DomTreadState"));
		domTreadMaterial = new Domain(Resources.gdb.getString("DomTreadMaterial"));
		domConeExist = new Domain(Resources.gdb.getString("DomConeExist"));
		domConeType = new Domain(Resources.gdb.getString("DomConeType"));
		domConeState = new Domain(Resources.gdb.getString("DomConeState"));
		domConeMaterial = new Domain(Resources.gdb.getString("DomConeMaterial"));

		domNumPipelines = new DefaultComboBoxModel<Integer>();
		String[] listNumPipelines = Resources.gdb.getString("CmbNumPipelines").split(",");
		for (String strType : listNumPipelines) {
			domNumPipelines.addElement(Integer.parseInt(strType));
		}
		
		domPipelineMaterial = new Domain(Resources.gdb.getString("DomPipelineMaterial"));
		domPipelineDiameter = new Domain(Resources.gdb.getString("DomPipelineDiameter"));
		domPipelineMaterialPublicSpace = new Domain(Resources.gdb.getString("DomPipelineMaterialPublicSpace"));
		domPipelineSection = new Domain(Resources.gdb.getString("DomPipelineSection"));
		domCameraInspection = new Domain(Resources.gdb.getString("DomCameraInspection"));
		
	}

	public class Domain {

		public ICodedValueDomain pCodedValueDomain;

		public Domain(String nameDomain) throws AutomationException, IOException {
			IDomain pDomain = pWSD_Sewerage.getDomainByName(nameDomain);
			this.pCodedValueDomain = (ICodedValueDomain) pDomain;
		}

		public Object getDBValue(String nameDomain) {
			Object valueDomain = "";
			try {
				for (int i = 0; i < pCodedValueDomain.getCodeCount(); i++) {
					if (pCodedValueDomain.getName(i).equals(nameDomain)) {
						valueDomain = pCodedValueDomain.getValue(i);
					}
				}
			} catch (AutomationException e) {
				JOptionPane.showMessageDialog(null, "Error getDBValue: " + e.toString());
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error getDBValue: " + e.toString());
				e.printStackTrace();
			}
			return valueDomain;
		}

		public String getDBName(String valueDomain) {
			String nameDomain = "";
			try {
				for (int i = 0; i < pCodedValueDomain.getCodeCount(); i++) {
					if (pCodedValueDomain.getValue(i).equals(valueDomain)) {
						nameDomain = pCodedValueDomain.getName(i);
					}
				}
			} catch (AutomationException e) {
				JOptionPane.showMessageDialog(null, "Error getDBName: " + e.toString());
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error getDBName: " + e.toString());
				e.printStackTrace();
			}
			return nameDomain;
		}
	}

	public DefaultComboBoxModel<String> getDomManholeType() {
		return domManholeType;
	}

	public void setDomManholeType(DefaultComboBoxModel<String> domManholeType) {
		this.domManholeType = domManholeType;
	}

	public Domain getDomSystemType() {
		return domSystemType;
	}

	public void setDomSystemType(Domain domSystemType) {
		this.domSystemType = domSystemType;
	}

	public Domain getDomStateManhole() {
		return domStateManhole;
	}

	public void setDomStateManhole(Domain domStateManhole) {
		this.domStateManhole = domStateManhole;
	}

	public Domain getDomPhysicalState() {
		return domPhysicalState;
	}

	public void setDomPhysicalState(Domain domPhysicalState) {
		this.domPhysicalState = domPhysicalState;
	}

	public Domain getDomNetworkState() {
		return domNetworkState;
	}

	public void setDomNetworkState(Domain domNetworkState) {
		this.domNetworkState = domNetworkState;
	}

	public Domain getDomWatershed() {
		return domWatershed;
	}

	public void setDomWatershed(Domain domWatershed) {
		this.domWatershed = domWatershed;
	}

	public Domain getDomTrapChamber() {
		return domTrapChamber;
	}

	public void setDomTrapChamber(Domain domTrapChamber) {
		this.domTrapChamber = domTrapChamber;
	}

	public Domain getDomOperationalState() {
		return domOperationalState;
	}

	public void setDomOperationalState(Domain domOperationalState) {
		this.domOperationalState = domOperationalState;
	}

	public Domain getDomLoadingState() {
		return domLoadingState;
	}

	public void setDomLoadingState(Domain domLoadingState) {
		this.domLoadingState = domLoadingState;
	}

	public Domain getDomFrameState() {
		return domFrameState;
	}

	public void setDomFrameState(Domain domFrameState) {
		this.domFrameState = domFrameState;
	}

	public Domain getDomTrunkState() {
		return domTrunkState;
	}

	public void setDomTrunkState(Domain domTrunkState) {
		this.domTrunkState = domTrunkState;
	}

	public Domain getDomCoverState() {
		return domCoverState;
	}

	public void setDomCoverState(Domain domCoverState) {
		this.domCoverState = domCoverState;
	}

	public Domain getDomCoverMaterial() {
		return domCoverMaterial;
	}

	public void setDomCoverMaterial(Domain domCoverMaterial) {
		this.domCoverMaterial = domCoverMaterial;
	}

	public Domain getDomCylinderState() {
		return domCylinderState;
	}

	public void setDomCylinderState(Domain domCylinderState) {
		this.domCylinderState = domCylinderState;
	}

	public Domain getDomCylinderMaterial() {
		return domCylinderMaterial;
	}

	public void setDomCylinderMaterial(Domain domCylinderMaterial) {
		this.domCylinderMaterial = domCylinderMaterial;
	}

	public Domain getDomTreadState() {
		return domTreadState;
	}

	public void setDomTreadState(Domain domTreadState) {
		this.domTreadState = domTreadState;
	}

	public Domain getDomTreadMaterial() {
		return domTreadMaterial;
	}

	public void setDomTreadMaterial(Domain domTreadMaterial) {
		this.domTreadMaterial = domTreadMaterial;
	}

	public Domain getDomConeExist() {
		return domConeExist;
	}

	public void setDomConeExist(Domain domConeExist) {
		this.domConeExist = domConeExist;
	}

	public Domain getDomConeType() {
		return domConeType;
	}

	public void setDomConeType(Domain domConeType) {
		this.domConeType = domConeType;
	}

	public Domain getDomConeState() {
		return domConeState;
	}

	public void setDomConeState(Domain domConeState) {
		this.domConeState = domConeState;
	}

	public Domain getDomConeMaterial() {
		return domConeMaterial;
	}

	public void setDomConeMaterial(Domain domConeMaterial) {
		this.domConeMaterial = domConeMaterial;
	}

	public Domain getDomPipelineMaterial() {
		return domPipelineMaterial;
	}

	public void setDomPipelineMaterial(Domain domPipelineMaterial) {
		this.domPipelineMaterial = domPipelineMaterial;
	}

	public Domain getDomPipelineDiameter() {
		return domPipelineDiameter;
	}

	public void setDomPipelineDiameter(Domain domPipelineDiameter) {
		this.domPipelineDiameter = domPipelineDiameter;
	}

	public Domain getDomPipelineMaterialPublicSpace() {
		return domPipelineMaterialPublicSpace;
	}

	public void setDomPipelineMaterialPublicSpace(Domain domPipelineMaterialPublicSpace) {
		this.domPipelineMaterialPublicSpace = domPipelineMaterialPublicSpace;
	}

	public Domain getDomPipelineSection() {
		return domPipelineSection;
	}

	public void setDomPipelineSection(Domain domPipelineSection) {
		this.domPipelineSection = domPipelineSection;
	}

	public Domain getDomCameraInspection() {
		return domCameraInspection;
	}

	public void setDomCameraInspection(Domain domCameraInspection) {
		this.domCameraInspection = domCameraInspection;
	}

	public DefaultComboBoxModel<Integer> getDomNumPipelines() {
		return domNumPipelines;
	}

	public void setDomNumPipelines(DefaultComboBoxModel<Integer> domNumPipelines) {
		this.domNumPipelines = domNumPipelines;
	}

}
