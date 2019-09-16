package arcgis10_2.button;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.esri.arcgis.addins.desktop.Button;
import com.esri.arcgis.arcmapui.IMxDocument;
import com.esri.arcgis.carto.Map;
import com.esri.arcgis.framework.IApplication;
import com.esri.arcgis.geodatabase.IEnumFeature;
import com.esri.arcgis.geodatabase.IEnumFeatureSetupProxy;
import com.esri.arcgis.geodatabase.IFeature;

import arcgis10_2.controller.EditManhole;
import arcgis10_2.model.Manhole;
import arcgis10_2.util.Resources;
import arcgis10_2.view.EditManholeView;

public class EditManholeButton extends Button {

	IApplication app;
	IMxDocument mxDoc;

	// Returns whether this button is checked
	public boolean isChecked() {
		return false;
	}

	// Returns whether this button is enabled
	public boolean isEnabled() {
		return true;
	}

	// This is called when the button is clicked
	public void onClick() {
		try {
			SwingUtilities.invokeLater(new Runnable() {

				@Override
				public void run() {
					try {
						editManhole();
					} catch (Exception ex) {
					}
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// This initializes the button and gets a reference to the hosting ArcGIS
	// application
	public void init(IApplication app) {
		this.app = app;
	}

	// This will open a Panel to edit the manhole selected on the map.
	public void editManhole() throws Exception {

		// Initialize the Map
		mxDoc = (IMxDocument) app.getDocument();
		Map map = (Map) mxDoc.getMaps().getItem(0);

		// Select Features on the Map
		IEnumFeature pEnumFeature = (new com.esri.arcgis.geodatabase.IEnumFeatureProxy(map.getFeatureSelection()));
		pEnumFeature.reset();
		IEnumFeatureSetupProxy pEnumFeatSetup = new IEnumFeatureSetupProxy(pEnumFeature);
		pEnumFeatSetup.setAllFields(true);

		IFeature pFeature = pEnumFeature.next();
		if (pFeature == null) {
			JOptionPane.showMessageDialog(null, Resources.messages.getString("EditManhole.selectData.text"));
		}

		while (pFeature != null) {

			// Edit Manhole
			Manhole pManhole = new Manhole(pFeature);
			if (pManhole.isManhole) {

				// Create View for Manhole Edition
				EditManholeView viewEditManhole = EditManholeView.getInstance();

				// Create Controller for Manhole Edition
				EditManhole EditManholeController = new EditManhole(pManhole, viewEditManhole);
				EditManholeController.run();

				viewEditManhole.addWindowListener(viewEditManhole);
				viewEditManhole.setVisible(true);

			}

			pFeature = pEnumFeature.next();

		}

	}
}
