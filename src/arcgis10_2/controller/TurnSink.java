package arcgis10_2.controller;

import java.io.IOException;

import javax.swing.JOptionPane;

import com.esri.arcgis.geodatabase.IDataset;
import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFeatureClass;
import com.esri.arcgis.geodatabase.IFeatureCursor;
import com.esri.arcgis.geodatabase.IFeatureWorkspace;
import com.esri.arcgis.geodatabase.IFeatureWorkspaceProxy;
import com.esri.arcgis.geodatabase.ISpatialFilter;
import com.esri.arcgis.geodatabase.IWorkspaceEditProxy;
import com.esri.arcgis.geodatabase.SpatialFilter;
import com.esri.arcgis.geodatabase.esriSpatialRelEnum;
import com.esri.arcgis.geometry.IPoint;
import com.esri.arcgis.geometry.IPolygon;
import com.esri.arcgis.geometry.IPolyline;
import com.esri.arcgis.geometry.IProximityOperator;
import com.esri.arcgis.geometry.ISpatialReference;
import com.esri.arcgis.geometry.ISpatialReferenceFactory2;
import com.esri.arcgis.geometry.ITopologicalOperator;
import com.esri.arcgis.geometry.SpatialReferenceEnvironment;
import com.esri.arcgis.geometry.esriSegmentExtension;
import com.esri.arcgis.interop.AutomationException;

import arcgis10_2.model.Sink;
import arcgis10_2.util.Resources;

public class TurnSink {

	private Sink pSink;

	IDataset pDataset;
	IWorkspaceEditProxy pWorkspaceEdit;
	IFeatureWorkspace pFeatureWorkspace;
	ISpatialReference pSR_Planar;

	public TurnSink(Sink pSink) {

		this.pSink = pSink;

	}

	public void run() {

		try {

			pDataset = pSink.getDatasetSink();
			// Workspace Edit
			pWorkspaceEdit = new IWorkspaceEditProxy(pDataset.getWorkspace());
			// Workspace FC
			pFeatureWorkspace = new IFeatureWorkspaceProxy(pDataset.getWorkspace());
			// Reference System - Planar Coordinates
			ISpatialReferenceFactory2 spatialReferenceFactory = new SpatialReferenceEnvironment();
			pSR_Planar = spatialReferenceFactory.createSpatialReference(3116);

			edit();

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error constructor TurnSink.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error constructor TurnSink.\n" + e.getMessage());
			e.printStackTrace();
		}

	}

	private void edit() {

		try {

			// Get Rotation
			Double rotation = getRotation();
			pSink.setTurnSymbol(rotation);

			// Start Edition
			boolean BooIsEditingBefore = pWorkspaceEdit.isBeingEdited();
			if (!BooIsEditingBefore)
				pWorkspaceEdit.startEditing(true);
			pWorkspaceEdit.startEditOperation();
			// Save
			pSink.store();
			// End Edition
			pWorkspaceEdit.stopEditOperation();
			if (!BooIsEditingBefore)
				pWorkspaceEdit.stopEditing(true);

			JOptionPane.showMessageDialog(null, Resources.messages.getString("TurnSkin.saveData.text"));

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error running TurnSink.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error running TurnSink.\n" + e.getMessage());
			e.printStackTrace();
		}

	}

	private Double getRotation() {

		Double dblAngle = 0.0;

		try {

			// System Reference of Sink
			ISpatialReference pSR_Sink = pSink.getShape().getSpatialReference();

			// Sink
			IPoint point = (IPoint) pSink.getShape();
			point.setSpatialReferenceByRef(pSR_Sink);
			point.project(pSR_Planar); // Project to Planar Coordinates

			// Buffer Sink
			Double dblDistance = (Double) 1500000.0;
			ITopologicalOperator pTopOperator = (ITopologicalOperator) point;
			IPolygon polygon = (IPolygon) pTopOperator.buffer(50);
			// polygon.setSpatialReferenceByRef(pSR_Sink);

			String[] listLinesNetwork = new String[] {};
			if (pDataset.getName().equals("alp_Sumidero"))
				listLinesNetwork = new String[] { "alp_RedTroncal", "alp_RedLocal" };
			if (pDataset.getName().equals("als_Sumidero"))
				listLinesNetwork = new String[] { "als_RedTroncal", "als_RedLocal" };

			IPoint pNearestPoint = null;
			IPolyline pPolyline = null;

			// Find Lines near to Point
			for (String nameLine : listLinesNetwork) {

				IFeatureClass pFC_Line = pFeatureWorkspace.openFeatureClass(nameLine);

				// Lines intersected with the Sink Buffer
				ISpatialFilter pSF_Buffer = new SpatialFilter();
				pSF_Buffer.setGeometryByRef(polygon);
				pSF_Buffer.setGeometryField(pFC_Line.getShapeFieldName());
				pSF_Buffer.setSpatialRel(esriSpatialRelEnum.esriSpatialRelIntersects);

				IFeatureCursor pFCursor = pFC_Line.search(pSF_Buffer, false);
				IFeature pTubo;
				while ((pTubo = pFCursor.nextFeature()) != null) {

					IPolyline tubo = (IPolyline) pTubo.getShape();
					tubo.project(pSR_Planar); // Project to Planar Coordinates
					IProximityOperator pPO = (IProximityOperator) tubo;

					if (pPO.returnDistance(point) < dblDistance) {
						pNearestPoint = pPO.returnNearestPoint(point, esriSegmentExtension.esriNoExtension);
						pPolyline = tubo;
						dblDistance = pPO.returnDistance(point);
					}
				}
			}
			if (pPolyline != null) {
				dblAngle = getRoundAngle(getSlope(point, pNearestPoint));
			} else {
				JOptionPane.showMessageDialog(null,
						"The OID " + pSink.getOID() + " from " + pDataset.getName() + " didn't turn.");
			}

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error Method getRotation.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Method getRotation.\n" + e.getMessage());
			e.printStackTrace();
		}

		return dblAngle;
	}

	private Double getRoundAngle(Double numero) {
		return Math.rint(numero * 100) / 100;
	}

	private Double getSlope(IPoint point1, IPoint point2) {

		Double slope = 0.0;

		try {

			point1.setSpatialReferenceByRef(pSR_Planar);
			point2.project(pSR_Planar);

			Double x = point2.getX() - point1.getX();
			Double y = point2.getY() - point1.getY();

			slope = Math.toDegrees(Math.atan2(y, x));
			if ((x < 0 && y < 0) || (x > 0 && y > 0) || (x > 0 && y < 0)) {
				slope = 90 - slope;
			}
			if (x < 0 && y > 0) {
				slope = 450 - slope;
			}

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error Method getAngle.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Method getAngle.\\n" + e.getMessage());
			e.printStackTrace();
		}

		return slope;
	}
}
