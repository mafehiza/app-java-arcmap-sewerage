package arcgis10_2.util;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFeatureClass;
import com.esri.arcgis.geodatabase.IFeatureCursor;
import com.esri.arcgis.geodatabase.ISpatialFilter;
import com.esri.arcgis.geodatabase.SpatialFilter;
import com.esri.arcgis.geodatabase.esriSpatialRelEnum;
import com.esri.arcgis.interop.AutomationException;

public class SpatialTools {

	// Get List of Pipelines intersected with Manhole
	public static ArrayList<IFeature> getListFeatureIntersected(IFeature pFeature, IFeatureClass pFeatureClass) {

		// List Pipeline Intersected
		ArrayList<IFeature> listFeature = new ArrayList<IFeature>();

		try {

			// Spatial Query
			ISpatialFilter pSpatialFilter = new SpatialFilter();
			pSpatialFilter.setGeometryByRef(pFeature.getShape());
			pSpatialFilter.setGeometryField(pFeatureClass.getShapeFieldName());
			pSpatialFilter.setSpatialRel(esriSpatialRelEnum.esriSpatialRelIntersects);
			// Search
			IFeatureCursor pFeatureCursor = pFeatureClass.search(pSpatialFilter, false);
			// Result
			IFeature pFeatureResult;
			while ((pFeatureResult = pFeatureCursor.nextFeature()) != null) {
				listFeature.add(pFeatureResult);
			}

		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error Method Get List Feature Intersected.\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error Method Get List Feature Intersected.\n" + e.getMessage());
			e.printStackTrace();
		}

		return listFeature;
	}
}
