package arcgis10_2.button;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.esri.arcgis.addins.desktop.Button;
import com.esri.arcgis.arcmapui.IMxDocument;
import com.esri.arcgis.carto.Map;
import com.esri.arcgis.framework.IApplication;
import com.esri.arcgis.geodatabase.IEnumFeatureProxy;
import com.esri.arcgis.geodatabase.IEnumFeatureSetupProxy;
import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.interop.AutomationException;

import arcgis10_2.controller.TurnSink;
import arcgis10_2.model.Sink;
import arcgis10_2.util.Resources;

public class TurnSinksButton extends Button {

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
						turnSink();
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

	public void turnSink() throws AutomationException, IOException {

		// Initialize the Map
		mxDoc = (IMxDocument) app.getDocument();
		Map map = (Map) mxDoc.getMaps().getItem(0);
		// IMap pMap = (IMap)mxDoc.getFocusMap();

		// Select Features on the Map
		IEnumFeatureProxy pEnumFeature = new IEnumFeatureProxy(map.getFeatureSelection());
		pEnumFeature.reset();
		IEnumFeatureSetupProxy pEnumFeatSetup = new IEnumFeatureSetupProxy(pEnumFeature);
		pEnumFeatSetup.setAllFields(true);

		IFeature pFeature = pEnumFeature.next();
		if (pFeature == null) {
			JOptionPane.showMessageDialog(null, Resources.messages.getString("TurnSkin.selectData.text"));
		}

		while (pFeature != null) {

			// Turn Sink
			Sink pSink = new Sink(pFeature);
			if (pSink.isSink) {
				new TurnSink(pSink).run();
			}

			pFeature = pEnumFeature.next();
		}

	}
}
