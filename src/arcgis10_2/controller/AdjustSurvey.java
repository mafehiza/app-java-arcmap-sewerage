package arcgis10_2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.esri.arcgis.geodatabase.ICursor;
import com.esri.arcgis.geodatabase.IDataset;
import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFeatureClass;
import com.esri.arcgis.geodatabase.IFeatureWorkspace;
import com.esri.arcgis.geodatabase.IFeatureWorkspaceProxy;
import com.esri.arcgis.geodatabase.IQueryFilter;
import com.esri.arcgis.geodatabase.IQueryFilterDefinition;
import com.esri.arcgis.geodatabase.IRow;
import com.esri.arcgis.geodatabase.ITable;
import com.esri.arcgis.geodatabase.IWorkspaceEditProxy;
import com.esri.arcgis.geodatabase.QueryFilter;
import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.geometry.IPoint;
import com.esri.arcgis.geometry.IPolyline;
import com.esri.arcgis.geometry.IRelationalOperator;
import com.esri.arcgis.geometry.ISpatialReference;
import com.esri.arcgis.geometry.ISpatialReferenceFactory2;
import com.esri.arcgis.geometry.Polyline;
import com.esri.arcgis.geometry.SpatialReferenceEnvironment;
import com.esri.arcgis.interop.AutomationException;

import arcgis10_2.model.Manhole;
import arcgis10_2.model.Pipeline;
import arcgis10_2.model.PipelineTable;
import arcgis10_2.model.SurveyPoint;
import arcgis10_2.util.ChangeNull;
import arcgis10_2.util.Resources;
import arcgis10_2.util.SpatialTools;

// Adjust Network To Survey Point
public class AdjustSurvey {

	// Data Manhole
	private Manhole pManhole;

	// Data Pipeline Side
	private String strSidePipeline;
	private Double dblBottom;
	private Double dblDiameter;
	private Double dblLength;
	private Double dblLevelCover; // Cota Rasante
	private Double dblLevelGround; // Cota Terreno
	private Double dblLavelHigh; // Cota Clave
	private Double dblLevelLow; // Cota Batea
	private Double dblLevelBase; // Cota Fondo

	private IWorkspaceEditProxy pWorkspaceEdit;
	private IFeatureWorkspace pFeatureWorkspace;

	private ISpatialReference pSR_Planar;
	private IPoint pPointSurvey;

	public AdjustSurvey(Manhole pManhole) throws AutomationException, IOException {

		this.pManhole = pManhole;

	}

	public void run() {

		try {

			IDataset pDataset = pManhole.getDatasetManhole();
			// Workspace Edit
			pWorkspaceEdit = new IWorkspaceEditProxy(pDataset.getWorkspace());
			// Workspace FC
			pFeatureWorkspace = new IFeatureWorkspaceProxy(pDataset.getWorkspace());
			// Reference System - Geo Coordinates
			ISpatialReferenceFactory2 spatialReferenceFactory = new SpatialReferenceEnvironment();
			pSR_Planar = spatialReferenceFactory.createSpatialReference(102233); // Planar Coordinates -> 3116

			// Get Survey Point
			// Get Level Cover (Cota Rasante) and Level Ground (Cota Terreno)
			// From Survey Table
			getDataSurveyTable();

			// Get List of Pipelines intersected with Manhole
			ArrayList<Pipeline> listPipelines = getListPipelineIntersected();

			Boolean isLevelBase = true;
			for (Pipeline pPipeline : listPipelines) {

				// Move Pipeline to the Survey Point
				// Get Side and Length from the new Pipeline
				MovePipeline(pPipeline);

				if (!strSidePipeline.equals("")) {

					// Get Bottom and Diameter
					// From Pipeline Table
					getDataPipelineTable(pPipeline);

					// Save Pipeline
					savePipeline(pPipeline, isLevelBase);
				}
			}

			// Save Manhole
			saveManhole();

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error running AdjustSurvey.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error running AdjustSurvey.\n" + e.getMessage());
			e.printStackTrace();
		}

	}

	// Get Survey Point
	// Get Level Cover (Cota Rasante) and Level Ground (Cota Terreno)
	// From Survey Table
	private void getDataSurveyTable() {

		try {

			// Query
			ITable pTableTopografia = pFeatureWorkspace.openTable(Resources.gdb.getString("Survey.Table"));
			IQueryFilter pQ = new QueryFilter();
			pQ.setWhereClause(Resources.gdb.getString("Survey.Field.Id") + "='" + pManhole.getIdManhole() + "'");
			// Search
			ICursor pCursor;
			pCursor = pTableTopografia.ITable_search(pQ, false);
			// Result
			IRow pRow = pCursor.nextRow();
			if (pRow != null) {
				SurveyPoint pSurveyPoint = new SurveyPoint(pRow);
				pPointSurvey = pSurveyPoint.getPoint(pSR_Planar); // Survey Point
				dblLevelCover = ChangeNull.ToZero(pSurveyPoint.getLevelCover()); // Level Cover (Cota Rasante)
				dblLevelGround = ChangeNull.ToZero(pSurveyPoint.getLevelGround()); // Level Ground (CotaTerreno)
			}

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error Method Get Data SurveyTable.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Method Get Data SurveyTable.\n" + e.getMessage());
			e.printStackTrace();
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
				ArrayList<IFeature> a = SpatialTools.getListFeatureIntersected(pManhole, pFeatureClass);
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

	// Move Pipeline to the Survey Point
	// Get Side and Length from the new Pipeline
	private void MovePipeline(Pipeline pPipeline) {

		try {

			// Pipeline Geom
			IGeometry geomPipeline = pPipeline.getShape();

			// Reference System
			ISpatialReference pSRPipeline = geomPipeline.getSpatialReference();
			pPointSurvey.project(pSRPipeline); // Project to Geographic (Pipeline/Manhole)
			// pPointSurvey.setSpatialReferenceByRef(pSRPipeline);

			// Copy Same Pipeline Geom
			IPolyline pPolylinePipeline = (IPolyline) geomPipeline;
			// pPolylinePipeline.project(pSRPipeline); // Project to Geographic
			// pPolylinePipeline.setSpatialReferenceByRef(pSRPipeline);

			// Pipeline Points Start and End
			IPoint pPointFromPipeline = (IPoint) pPolylinePipeline.getFromPoint();
			IPoint pPointToPipeline = (IPoint) pPolylinePipeline.getToPoint();

			// Change Pipeline Geom: Start or End Points
			IPolyline pNewPolyline = new Polyline();
			pNewPolyline.setSpatialReferenceByRef(pPolylinePipeline.getSpatialReference());
			pNewPolyline.setFromPoint(pPointFromPipeline);
			pNewPolyline.setToPoint(pPointToPipeline);

			// Change Start and End Point and Select Side of Pipeline
			IRelationalOperator pRelOp = (IRelationalOperator) pManhole.getShape();
			if (pRelOp.esri_equals(pPointFromPipeline)) {
				strSidePipeline = Resources.gdb.getString("PipelineTable.SideStart"); // Side
				pPolylinePipeline.setFromPoint(pPointSurvey); // Same geom
				pNewPolyline.setFromPoint(pPointSurvey); // Change geom
			} else if (pRelOp.esri_equals(pPointToPipeline)) {
				strSidePipeline = Resources.gdb.getString("PipelineTable.SideEnd"); // Side
				pPolylinePipeline.setToPoint(pPointSurvey); // Same geom
				pNewPolyline.setToPoint(pPointSurvey); // Change geom
			} else { // Warning Reference System
				// errorSRMapa.add(id_conducto);
				// pozosOk.remove(id_pozo);
			}

			// Set Pipeline Geometry
			pNewPolyline.project(pSR_Planar);
			// pPipeline.setShapeByRef(pPolylinePipeline); // Same geom
			pPipeline.setShapeByRef(pNewPolyline); // Change geom

			// Set Pipeline Length
			dblLength = pNewPolyline.getLength();

			// Save Pipeline
			pPipeline.store();

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error Method Move Pipeline.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Method Move Pipeline.\n" + e.getMessage());
			e.printStackTrace();
		}

	}

	// Get Bottom and Diameter from the Pipeline Table
	private void getDataPipelineTable(Pipeline pPipeline) {

		String numContract = "1-02-26600-0759-2015";

		try {

			// Query
			ITable pTable = pFeatureWorkspace.openTable(Resources.gdb.getString("PipelineTable.Name"));
			IQueryFilter pQFTable = new QueryFilter();
			pQFTable.setWhereClause(Resources.gdb.getString("PipelineTable.Field.Id") + " = '"
					+ pPipeline.getIdPipeline() + "' and "
					+ Resources.gdb.getString("PipelineTable.Field.Contract") + " = '" + numContract + "' and "
					+ Resources.gdb.getString("PipelineTable.Field.Side") + "  = '" + strSidePipeline + "'");
			// Order
			IQueryFilterDefinition pQFD = new com.esri.arcgis.geodatabase.IQueryFilterDefinitionProxy(pQFTable);
			pQFD.setPostfixClause("order by " + pTable.getOIDFieldName() + " desc");
			// Search
			ICursor pCursorTable = pTable.ITable_search(pQFTable, false);
			// Result
			IRow pRow = pCursorTable.nextRow();
			if (pRow != null) {
				PipelineTable pPipelineTable = new PipelineTable(pRow);
				dblBottom = ChangeNull.ToZero(pPipelineTable.getBottom()); // Bottom
				dblDiameter = ChangeNull.ToZero(pPipelineTable.getDiameter()); // Diameter
			}

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error Method Get Data Pipeline Table.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Method Get Data Pipeline Table.\n" + e.getMessage());
			e.printStackTrace();
		}

	}

	private void savePipeline(Pipeline pPipeline, Boolean isLevelBase) {

		try {

			// Calculate Level High (Cota Clave)
			dblLavelHigh = dblLevelCover - dblBottom;
			
			// Calculate Level Low (Cota Batea)
			if (!Arrays.asList(Resources.gdb.getString("Pipeline.FeatureClassLateral").split(","))
					.contains(pPipeline.getNameFeature())) {
				String section = pPipeline.getSection();
				if (!section.equals("5")) {
					dblDiameter = ChangeNull.ToZero(pPipeline.getHeight());
				} 
				if (ChangeNull.ToEmpty(section).equals("")) {
					JOptionPane.showMessageDialog(null, "Error: El tubo con OID= " + pPipeline.getOID()
							+ " que colinda con el pozo " + pManhole.getOID() + " no tiene seccion");
				}
			}
			dblLevelLow = dblLavelHigh - dblDiameter;

			// Set Pipeline Side Start Levels
			if (strSidePipeline == Resources.gdb.getString("PipelineTable.SideStart")) {
				pPipeline.setLevelCoverStart(dblLevelCover); // Set Level Cover (Cota Rasante)
				pPipeline.setLevelHighStart(dblLavelHigh); // Set Level High (Cota Clave)
				pPipeline.setLevelLowStart(dblLevelLow); // Set Level Low (Cota Batea)
			}
			// Set Pipeline Side End Levels
			if (strSidePipeline == Resources.gdb.getString("PipelineTable.SideEnd")) {
				pPipeline.setLevelCoverEnd(dblLevelCover); // Set Level Cover (Cota Rasante)
				pPipeline.setLevelHighEnd(dblLavelHigh); // Set Level High (Cota Clave)
				pPipeline.setLevelLowEnd(dblLevelLow); // Set Level Low (Cota Batea)
			}

			// Caculate Manhole Level Base (Cota Fondo)
			if (isLevelBase) {
				dblLevelBase = dblLevelLow;
				isLevelBase = false;
			} else {
				if (dblLevelLow < dblLevelBase)
					dblLevelBase = dblLevelLow;
			}

			// Set Pipeline Length
			pPipeline.setLengthM(dblLength);

			// Set Pipeline Slop - Bottom Mean
			if (!Arrays.asList(Resources.gdb.getString("Pipeline.FeatureClassLateral").split(","))
					.contains(pPipeline.getNameFeature())) {

				Double dHighStart = ChangeNull.ToZero(pPipeline.getLevelHighStart());
				Double dHighEnd = ChangeNull.ToZero(pPipeline.getLevelHighEnd());
				Double dlbSlope = (dHighStart - dHighEnd) / dblLength * 100;

				Double dCoverStart = ChangeNull.ToZero(pPipeline.getLevelCoverStart());
				Double dCoverEnd = ChangeNull.ToZero(pPipeline.getLevelCoverEnd());
				Double dlbBottomMean = ((dCoverStart - dHighStart) + (dCoverEnd - dHighEnd)) / 2;

				pPipeline.setSlope(dlbSlope); // Slop
				pPipeline.setBottomMean(dlbBottomMean); // Bottom Mean
			}

			// Start Edition
			boolean isEditionActive = pWorkspaceEdit.isBeingEdited();
			if (!isEditionActive)
				pWorkspaceEdit.startEditing(true);
			pWorkspaceEdit.startEditOperation();
			// Save Pipeline
			pPipeline.store();
			// End Edition
			pWorkspaceEdit.stopEditOperation();
			if (!isEditionActive)
				pWorkspaceEdit.stopEditing(true);

			JOptionPane.showMessageDialog(null, Resources.messages.getString("AdjustSurvey.saveData.text"));

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error Method Save Pipeline.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Method Save Pipeline.\n" + e.getMessage());
			e.printStackTrace();
		}

	}

	private void saveManhole() {

		try {

			// Set Manhole Geometry
			pManhole.setShapeByRef(pPointSurvey);
			// Set Manhole Level Cover (Cota Rasante)
			pManhole.setLevelCover(dblLevelCover);
			// Set Manhole Level Ground (Cota Terreno)
			pManhole.setLevelGround(dblLevelGround);
			// Set Manhole Level Base (Cota Fondo)
			pManhole.setLevelBase(dblLevelBase);
			// Set Manhole Bottom
			pManhole.setBottom(dblLevelCover - dblLevelBase);

			// Start Edition
			boolean isEditionActive = pWorkspaceEdit.isBeingEdited();
			if (!isEditionActive)
				pWorkspaceEdit.startEditing(true);
			pWorkspaceEdit.startEditOperation();
			// Save Manhole
			pManhole.store();
			// End Edition
			pWorkspaceEdit.stopEditOperation();
			if (!isEditionActive)
				pWorkspaceEdit.stopEditing(true);

			JOptionPane.showMessageDialog(null, Resources.messages.getString("AdjustSurvey.saveData.text"));

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error Method Save Manhole.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Method Save Manhole.\n" + e.getMessage());
			e.printStackTrace();
		}

	}

}
