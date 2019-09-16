package arcgis10_2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.esri.arcgis.geodatabase.IDataset;
import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFeatureClass;
import com.esri.arcgis.geodatabase.IFeatureWorkspace;
import com.esri.arcgis.geodatabase.IFeatureWorkspaceProxy;
import com.esri.arcgis.geodatabase.IWorkspace;
import com.esri.arcgis.geodatabase.IWorkspaceEditProxy;
import com.esri.arcgis.interop.AutomationException;

import arcgis10_2.model.Manhole;
import arcgis10_2.model.Pipeline;
import arcgis10_2.util.Domains;
import arcgis10_2.util.Resources;
import arcgis10_2.util.SpatialTools;
import arcgis10_2.view.EditManholeView;
import arcgis10_2.view.EditPipelineView;

public class EditManhole {

	private Manhole model;
	private EditManholeView view;

	IWorkspaceEditProxy pWorkspaceEdit;
	IFeatureWorkspace pFeatureWorkspace;

	Domains pDomains;

	public EditManhole(Manhole model, EditManholeView view) {
		this.model = model;
		this.view = view;
	}

	public void run() {

		try {

			IDataset pDataset = model.getDatasetManhole();
			// Workspace Edit
			pWorkspaceEdit = new IWorkspaceEditProxy(pDataset.getWorkspace());
			// Workspace FC
			pFeatureWorkspace = new IFeatureWorkspaceProxy(pDataset.getWorkspace());

			// Workspace FC
			IWorkspace pWorkspace = pDataset.getWorkspace();
			// Domains
			pDomains = new Domains(pWorkspace);

			setPanelDomains();
			setDataManhole();
			setListeners();
			setTablePipelines();

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error running EditManhole.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error running EditManhole.\n" + e.getMessage());
			e.printStackTrace();
		}

	}

	private void setPanelDomains() {

		view.getPanelManhole().getCmbTypeManhole().setModel(pDomains.getDomManholeType());

		view.getPanelManhole().getCmbStateManhole().fill(pDomains.getDomStateManhole().pCodedValueDomain);
		view.getPanelManhole().getCmbSystemType().fill(pDomains.getDomSystemType().pCodedValueDomain);
		view.getPanelManhole().getCmbPhysicalState().fill(pDomains.getDomPhysicalState().pCodedValueDomain);
		view.getPanelManhole().getCmbOperationalState().fill(pDomains.getDomOperationalState().pCodedValueDomain);
		view.getPanelManhole().getCmbNetworkState().fill(pDomains.getDomNetworkState().pCodedValueDomain);
		view.getPanelManhole().getCmbWatershed().fill(pDomains.getDomWatershed().pCodedValueDomain);
		view.getPanelManhole().getCmbLoadingState().fill(pDomains.getDomLoadingState().pCodedValueDomain);
		view.getPanelManhole().getCmbFrameState().fill(pDomains.getDomFrameState().pCodedValueDomain);
		view.getPanelManhole().getCmbTrunkState().fill(pDomains.getDomTrunkState().pCodedValueDomain);
		view.getPanelManhole().getCmbTrapChamber().fill(pDomains.getDomTrapChamber().pCodedValueDomain);
		view.getPanelManhole().getCmbCoverState().fill(pDomains.getDomCoverState().pCodedValueDomain);
		view.getPanelManhole().getCmbCoverMaterial().fill(pDomains.getDomCoverMaterial().pCodedValueDomain);
		view.getPanelManhole().getCmbCylinderState().fill(pDomains.getDomCylinderState().pCodedValueDomain);
		view.getPanelManhole().getCmbCylinderMaterial().fill(pDomains.getDomCylinderMaterial().pCodedValueDomain);
		view.getPanelManhole().getCmbTreadState().fill(pDomains.getDomTreadState().pCodedValueDomain);
		view.getPanelManhole().getCmbTreadMaterial().fill(pDomains.getDomTreadMaterial().pCodedValueDomain);
		view.getPanelManhole().getCmbConeExist().fill(pDomains.getDomConeExist().pCodedValueDomain);
		view.getPanelManhole().getCmbConeType().fill(pDomains.getDomConeType().pCodedValueDomain);
		view.getPanelManhole().getCmbConeState().fill(pDomains.getDomConeState().pCodedValueDomain);
		view.getPanelManhole().getCmbConeMaterial().fill(pDomains.getDomConeMaterial().pCodedValueDomain);

	}

	private void setDataManhole() {

		// Set Id Manhole
		view.getPanelManhole().getTxtIdManhole().setText(model.getIdManhole());

		// Set Values Domains Manhole
		view.getPanelManhole().getCmbStateManhole()
				.setSelectedItem(pDomains.getDomStateManhole().getDBName(model.getStateManhole()));
		view.getPanelManhole().getCmbSystemType()
				.setSelectedItem(pDomains.getDomSystemType().getDBName(model.getSystemType()));
		view.getPanelManhole().getCmbPhysicalState()
				.setSelectedItem(pDomains.getDomPhysicalState().getDBName(model.getPhysicalState()));
		view.getPanelManhole().getCmbNetworkState()
				.setSelectedItem(pDomains.getDomNetworkState().getDBName(model.getNetworkState()));
		view.getPanelManhole().getCmbWatershed()
				.setSelectedItem(pDomains.getDomWatershed().getDBName(model.getWatershed()));
		view.getPanelManhole().getCmbTrapChamber()
				.setSelectedItem(pDomains.getDomTrapChamber().getDBName(model.getTrapChamber()));

	}

	private void setTablePipelines() throws AutomationException, IOException {

		// Get List of Pipelines intersected with Manhole
		ArrayList<Pipeline> listPipelines = getListPipelineIntersected();

		view.getTabbedPipelines().removeAll();

		for (Pipeline pPipeline : listPipelines) {

			// Create View for Pipeline Edition
			EditPipelineView viewEditPipeline = new EditPipelineView();

			// Create Controller for Pipeline Edition
			EditPipeline EditPipelineController = new EditPipeline(pPipeline, viewEditPipeline);
			EditPipelineController.run();

			view.getTabbedPipelines().addTab(pPipeline.getIdPipeline(), viewEditPipeline);

		}

	}

	// Get List of Pipelines intersected with Manhole
	private ArrayList<Pipeline> getListPipelineIntersected() {

		// List Pipeline Intersected
		ArrayList<Pipeline> listPipelines = new ArrayList<Pipeline>();

		try {

			String[] pListNamePipelines = new String[] { "alp_RedTroncal", "alp_RedLocal", "alp_LineaLateral",
					"als_RedTroncal", "als_RedLocal", "als_LineaLateral" };

			for (String strNameLine : pListNamePipelines) {

				// Feature Class Pipeline
				IFeatureClass pFeatureClass = pFeatureWorkspace.openFeatureClass(strNameLine);
				// Spatial Query
				ArrayList<IFeature> a = SpatialTools.getListFeatureIntersected(model, pFeatureClass);
				for (IFeature pFeature : a) {
					Pipeline pPipeline = new Pipeline(pFeature);
					listPipelines.add(pPipeline);
				}

			}

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error Method Get List Pipeline Intersected.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Method Get List Pipeline Intersected.\n" + e.getMessage());
			e.printStackTrace();
		}

		return listPipelines;
	}

	private void setListeners() {
		view.getPanelManhole().getBtnSaveManhole().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveManhole();
			}
		});
	}

	private void saveManhole() {

		try {

			model.setIdManhole(view.getPanelManhole().getTxtIdManhole().getText());
			model.setStateManhole((String) pDomains.getDomStateManhole()
					.getDBValue(view.getPanelManhole().getCmbStateManhole().getSelectedItem().toString()));
			model.setSystemType((String) pDomains.getDomSystemType()
					.getDBValue(view.getPanelManhole().getCmbSystemType().getSelectedItem().toString()));
			model.setPhysicalState((String) pDomains.getDomPhysicalState()
					.getDBValue(view.getPanelManhole().getCmbPhysicalState().getSelectedItem().toString()));
			model.setNetworkState((String) pDomains.getDomNetworkState()
					.getDBValue(view.getPanelManhole().getCmbNetworkState().getSelectedItem().toString()));
			model.setWatershed((String) pDomains.getDomWatershed()
					.getDBValue(view.getPanelManhole().getCmbWatershed().getSelectedItem().toString()));
			model.setTrapChamber((String) pDomains.getDomTrapChamber()
					.getDBValue(view.getPanelManhole().getCmbTrapChamber().getSelectedItem().toString()));

			// Start Edition
			boolean BooIsEditingBefore = pWorkspaceEdit.isBeingEdited();
			if (!BooIsEditingBefore)
				pWorkspaceEdit.startEditing(true);
			pWorkspaceEdit.startEditOperation();
			// Save
			model.store();
			// End Edition
			pWorkspaceEdit.stopEditOperation();
			if (!BooIsEditingBefore)
				pWorkspaceEdit.stopEditing(true);

			JOptionPane.showMessageDialog(null, Resources.messages.getString("EditManhole.saveData.text"));

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error Method saveManhole.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Method saveManhole.\n" + e.getMessage());
			e.printStackTrace();
		}

	}

	public Manhole getModel() {
		return model;
	}

	public void setModel(Manhole model) {
		this.model = model;
	}

	public EditManholeView getView() {
		return view;
	}

	public void setView(EditManholeView view) {
		this.view = view;
	}

}
