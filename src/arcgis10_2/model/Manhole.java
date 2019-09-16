package arcgis10_2.model;

import java.io.IOException;
import java.util.MissingResourceException;

import javax.swing.JOptionPane;

import com.esri.arcgis.geodatabase.IDataset;
import com.esri.arcgis.geodatabase.IDatasetProxy;
import com.esri.arcgis.geodatabase.IFeature;
import com.esri.arcgis.geodatabase.IFields;
import com.esri.arcgis.geodatabase.IObjectClass;
import com.esri.arcgis.geodatabase.ITable;
import com.esri.arcgis.geometry.IEnvelope;
import com.esri.arcgis.geometry.IGeometry;
import com.esri.arcgis.interop.AutomationException;

import arcgis10_2.util.Resources;

@SuppressWarnings("serial")
public class Manhole implements IFeature {

	// Fields
	private String idManhole;
	private String typeManhole;
	private String stateManhole;
	private String systemType;
	private String physicalState;
	private String networkState;
	private String watershed;
	private String trapChamber;
	private Double levelCover;
	private Double levelGround;
	private Double levelBase;
	private Double bottom;

	private IFeature pF_Manhole;
	private IDataset pD_Manhole;

	public Boolean isManhole;

	public Manhole(IFeature pF_Manhole) {

		this.isManhole = true;

		this.pF_Manhole = pF_Manhole;

		try {
			// Manhole Dataset
			this.pD_Manhole = new IDatasetProxy(this.esri_getClass());

			// Manhole Fields
			this.idManhole = (String) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.ManholeId")));
			this.stateManhole = (String) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.StateManhole")));
			this.systemType = (String) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.SystemType")));
			this.physicalState = (String) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.PhysicalState")));
			this.networkState = (String) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.NetworkState")));
			this.watershed = (String) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.Watershed")));
			this.trapChamber = (String) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.TrapChamber")));
			this.levelCover = (Double) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.LevelCover")));
			this.levelGround = (Double) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.LevelGround")));
			this.levelBase = (Double) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.LevelBase")));
			this.bottom = (Double) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.Bottom")));

		} catch (MissingResourceException e) {
			JOptionPane.showMessageDialog(null, "MissingResourceException\n" + e.getClassName() + "\n"
					+ e.getLocalizedMessage() + "\n" + e.getKey() + "\n" + e.getMessage() + "\n");
		} catch (AutomationException e) {
			this.isManhole = false;
			// JOptionPane.showMessageDialog(null, "Any field was not founded in Manhole
			// FeatureClass." + "\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			this.isManhole = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public IDataset getDatasetManhole() {
		return pD_Manhole;
	}

	public void setDatasetManhole(IDataset pD_Manhole) {
		this.pD_Manhole = pD_Manhole;
	}

	public String getIdManhole() {
		return idManhole;
	}

	public void setIdManhole(String idManhole) {
		this.idManhole = idManhole;
	}

	public String getTypeManhole() {
		return typeManhole;
	}

	public void setTypeManhole(String typeManhole) {
		this.typeManhole = typeManhole;
	}

	public String getStateManhole() {
		return stateManhole;
	}

	public void setStateManhole(String stateManhole) {
		this.stateManhole = stateManhole;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getPhysicalState() {
		return physicalState;
	}

	public void setPhysicalState(String physicalState) {
		this.physicalState = physicalState;
	}

	public String getNetworkState() {
		return networkState;
	}

	public void setNetworkState(String networkState) {
		this.networkState = networkState;
	}

	public String getWatershed() {
		return watershed;
	}

	public void setWatershed(String watershed) {
		this.watershed = watershed;
	}

	public String getTrapChamber() {
		return trapChamber;
	}

	public void setTrapChamber(String trapChamber) {
		this.trapChamber = trapChamber;
	}

	public Double getLevelCover() {
		return levelCover;
	}

	public void setLevelCover(Double levelCover) {
		this.levelCover = levelCover;
	}

	public Double getLevelGround() {
		return levelGround;
	}

	public void setLevelGround(Double levelGround) {
		this.levelGround = levelGround;
	}

	public Double getLevelBase() {
		return levelBase;
	}

	public void setLevelBase(Double levelBase) {
		this.levelBase = levelBase;
	}

	public Double getBottom() {
		return bottom;
	}

	public void setBottom(Double bottom) {
		this.bottom = bottom;
	}

	@Override
	public IObjectClass esri_getClass() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return this.pF_Manhole.esri_getClass();
	}

	@Override
	public void delete() throws IOException, AutomationException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getOID() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return this.pF_Manhole.getOID();
	}

	@Override
	public ITable getTable() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isHasOID() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void store() throws IOException, AutomationException {
		// TODO Auto-generated method stub

		this.setValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.ManholeId")), idManhole);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.StateManhole")), stateManhole);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.SystemType")), systemType);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.PhysicalState")),
				physicalState);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.NetworkState")), networkState);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.Watershed")), watershed);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.TrapChamber")), trapChamber);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.LevelCover")), levelCover);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.LevelGround")), levelGround);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.LevelBase")), levelBase);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Manhole.Field.Bottom")), bottom);

		this.pF_Manhole.store();
	}

	@Override
	public IFields getFields() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return this.pF_Manhole.getFields();
	}

	@Override
	public Object getValue(int index) throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return this.pF_Manhole.getValue(index);
	}

	@Override
	public void setValue(int index, Object value) throws IOException, AutomationException {
		// TODO Auto-generated method stub
		this.pF_Manhole.setValue(index, value);
	}

	@Override
	public IEnvelope getExtent() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFeatureType() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IGeometry getShape() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return this.pF_Manhole.getShape();
	}

	@Override
	public IGeometry getShapeCopy() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShapeByRef(IGeometry shape) throws IOException, AutomationException {
		// TODO Auto-generated method stub
		this.pF_Manhole.setShapeByRef(shape);
	}

}
