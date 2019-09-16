package arcgis10_2.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.esri.arcgis.geodatabase.IDataset;
import com.esri.arcgis.geodatabase.IFeatureWorkspace;
import com.esri.arcgis.geodatabase.IFeatureWorkspaceProxy;
import com.esri.arcgis.geodatabase.IWorkspace;
import com.esri.arcgis.geodatabase.IWorkspaceEditProxy;
import com.esri.arcgis.interop.AutomationException;

import arcgis10_2.model.Pipeline;
import arcgis10_2.util.Domains;
import arcgis10_2.util.Resources;
import arcgis10_2.view.EditPipelineView;

public class EditPipeline {

	private Pipeline model;
	private EditPipelineView view;

	IWorkspaceEditProxy pWorkspaceEdit;
	IFeatureWorkspace pFeatureWorkspace;

	Domains pDomains;

	public EditPipeline(Pipeline model, EditPipelineView view) {
		this.model = model;
		this.view = view;
	}

	public void run() {

		try {

			IDataset pDataset = model.getDatasetPipeline();
			// Workspace Edit
			pWorkspaceEdit = new IWorkspaceEditProxy(pDataset.getWorkspace());
			// Workspace FC
			pFeatureWorkspace = new IFeatureWorkspaceProxy(pDataset.getWorkspace());

			// Workspace FC
			IWorkspace pWorkspace = pDataset.getWorkspace();
			// Domains
			pDomains = new Domains(pWorkspace);

			setPanelDomains();
			setDataPipeline();
			setListeners();

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error running EditManhole.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error running EditManhole.\n" + e.getMessage());
			e.printStackTrace();
		}
	}

	private void setPanelDomains() {

		view.getCmbPipelineNumLines().setModel(pDomains.getDomNumPipelines());

		view.getCmbPipelineSystemType().fill(pDomains.getDomSystemType().pCodedValueDomain);
		view.getCmbPipelineMaterial().fill(pDomains.getDomPipelineMaterial().pCodedValueDomain);
		view.getCmbPipelineSection().fill(pDomains.getDomPipelineSection().pCodedValueDomain);
		view.getCmbPipelineNetworkState().fill(pDomains.getDomNetworkState().pCodedValueDomain);
		view.getCmbPipelineOperationalState().fill(pDomains.getDomOperationalState().pCodedValueDomain);
		view.getCmbPipelineMaterialPublicSpace().fill(pDomains.getDomPipelineMaterialPublicSpace().pCodedValueDomain);
		view.getCmbPipelineCameraInspection().fill(pDomains.getDomCameraInspection().pCodedValueDomain);
		view.getCmbPipelineMaterial2().fill(pDomains.getDomPipelineMaterial().pCodedValueDomain);

	}

	private void setDataPipeline() {

		// Set Id Manhole
		view.getTxtPipelineId().setText(model.getIdPipeline());

		// Set Values Domains Pipeline
		// view.getCmbPipelineSubtype().setSelectedItem();
		view.getCmbPipelineSystemType().setSelectedItem(pDomains.getDomSystemType().getDBName(model.getSystemType()));
		view.getCmbPipelineMaterial().setSelectedItem(pDomains.getDomPipelineMaterial().getDBName(model.getMaterial()));
		view.getCmbPipelineSection().setSelectedItem(pDomains.getDomPipelineSection().getDBName(model.getSection()));
		view.getCmbPipelineNetworkState()
				.setSelectedItem(pDomains.getDomNetworkState().getDBName(model.getNetworkState()));
		view.getCmbPipelineMaterialPublicSpace().setSelectedItem(
				pDomains.getDomPipelineMaterialPublicSpace().getDBName(model.getMaterialPublicSpace()));
		view.getCmbPipelineCameraInspection()
				.setSelectedItem(pDomains.getDomCameraInspection().getDBName(model.getCameraInspection()));
		view.getCmbPipelineNumLines().setSelectedItem(model.getNumPipelines());
		view.getCmbPipelineMaterial2()
				.setSelectedItem(pDomains.getDomPipelineMaterial().getDBName(model.getMaterial2()));
		view.getTxtPipelineObservations().setText(model.getObservations());

		// Get values from table.
		// view.getTxtPipelineBottom().setText();
		// view.getTxtPipelineDiameter().setText();
		// view.getTxtPipelineBase().setText();
		// view.getTxtPipelineHigh().setText();
		// view.getCmbPipelineOperationalState().setSelectedItem(pDomains.getDomOperationalState().getDBName(model.getOperationalState()));

	}

	private void setListeners() {
		view.getBtnSavePipeline().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				savePipeline();
			}
		});
	}

	private void savePipeline() {

		try {

			model.setIdPipeline(view.getTxtPipelineId().getText());
			model.setSystemType((String) pDomains.getDomSystemType()
					.getDBValue(view.getCmbPipelineSystemType().getSelectedItem().toString()));
			model.setMaterial((String) pDomains.getDomPipelineMaterial()
					.getDBValue(view.getCmbPipelineMaterial().getSelectedItem().toString()));
			model.setSection((String) pDomains.getDomPipelineSection()
					.getDBValue(view.getCmbPipelineSection().getSelectedItem().toString()));
			model.setNetworkState((String) pDomains.getDomNetworkState()
					.getDBValue(view.getCmbPipelineNetworkState().getSelectedItem().toString()));
			model.setMaterialPublicSpace((String) pDomains.getDomPipelineMaterialPublicSpace()
					.getDBValue(view.getCmbPipelineMaterialPublicSpace().getSelectedItem().toString()));
			model.setCameraInspection((String) pDomains.getDomCameraInspection()
					.getDBValue(view.getCmbPipelineCameraInspection().getSelectedItem().toString()));
			model.setObservations(view.getTxtPipelineObservations().getText());

			if (!Arrays.asList(Resources.gdb.getString("Pipeline.FeatureClassLateral").split(",")).contains(model.getNameFeature())) {
				model.setNumPipelines(
						(Integer) Integer.valueOf(view.getCmbPipelineNumLines().getSelectedItem().toString()));
				model.setMaterial2((String) pDomains.getDomPipelineMaterial()
						.getDBValue(view.getCmbPipelineMaterial2().getSelectedItem().toString()));
			}

			// Get values from table.
			// view.getTxtPipelineBottom().setText();
			// view.getTxtPipelineDiameter().setText();
			// view.getTxtPipelineBase().setText();
			// view.getTxtPipelineHigh().setText();
			// view.getCmbPipelineOperationalState().setSelectedItem(pDomains.getDomOperationalState().getDBName(model.getOperationalState()));

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

			JOptionPane.showMessageDialog(null, Resources.messages.getString("EditManhole.saveDataPipeline.text"));

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error Method savePipeline.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Method savePipeline.\n" + e.getMessage());
			e.printStackTrace();
		}

	}

}
