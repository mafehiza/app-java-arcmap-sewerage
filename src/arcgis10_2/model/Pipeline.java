package arcgis10_2.model;

import java.io.IOException;
import java.util.Arrays;
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
public class Pipeline implements IFeature {

	// Fields
	private String idPipeline;
	private Double levelCoverStart;
	private Double levelCoverEnd;
	private Double levelHighStart;
	private Double levelHighEnd;
	private Double levelLowStart;
	private Double levelLowEnd;
	private Double lengthM;
	private Double base;
	private Double height;
	private Double bottomMean;
	private Double slope;

	private String diameter;
	
	private String systemType;
	private String material;
	private String section;
	private String networkState;
	private String materialPublicSpace;
	private String cameraInspection;
	private Integer numPipelines;
	private String material2;
	private String observations;

	// Data Feature
	private String nameFeature;
	private String nameFieldId;

	private IFeature pF_Pipeline;
	private IDataset pD_Pipeline;

	public Pipeline(IFeature pF_Pipeline) {

		this.pF_Pipeline = pF_Pipeline;

		try {

			// Pipeline Dataset
			pD_Pipeline = new IDatasetProxy(this.esri_getClass());

			// Pipeline Fields
			this.nameFeature = pD_Pipeline.getName();
			this.nameFieldId = "";
			if (Arrays.asList(Resources.gdb.getString("Pipeline.FeatureClassLateral").split(","))
					.contains(this.nameFeature))
				this.nameFieldId = Resources.gdb.getString("Pipeline.Field.IdLateral");
			if (Arrays.asList(Resources.gdb.getString("Pipeline.FeatureClassLocal").split(",")).contains(this.nameFeature))
				this.nameFieldId = Resources.gdb.getString("Pipeline.Field.IdLocal");
			if (Arrays.asList(Resources.gdb.getString("Pipeline.FeatureClassTroncal").split(","))
					.contains(this.nameFeature))
				this.nameFieldId = Resources.gdb.getString("Pipeline.Field.IdTroncal");

			this.idPipeline = (String) this.getValue(this.getFields().findField(this.nameFieldId));

			this.levelCoverStart = (Double) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LevelCoverStart")));
			this.levelCoverEnd = (Double) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LevelCoverEnd")));
			this.levelHighStart = (Double) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LevelHighStart")));
			this.levelHighEnd = (Double) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LevelHighEnd")));
			this.levelLowStart = ((Double) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LevelLowStart"))));
			this.levelLowEnd = (Double) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LevelLowEnd")));
			this.lengthM = (Double) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LengthM")));
			this.material = (String) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.Material")));
			this.diameter = (String) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.Diameter")));
			this.systemType = (String) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.SystemType")));
			this.materialPublicSpace = (String) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.MaterialPublicSpace")));
			this.networkState = (String) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.NetworkState")));
			this.observations = (String) this
					.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.Observations")));

			if (!Arrays.asList(Resources.gdb.getString("Pipeline.FeatureClassLateral").split(",")).contains(nameFeature)) {

				this.section = (String) this
						.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.Section")));
				this.base = (Double) this
						.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.Base")));
				this.height = (Double) this
						.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.Height")));
				this.bottomMean = (Double) this
						.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.BottomMean")));
				this.slope = (Double) this
						.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.Slope")));
				this.numPipelines = (Integer) this
						.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.NumPipelines")));
				this.cameraInspection = (String) this
						.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.CameraInspection")));
				this.material2 = (String) this
						.getValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.Material2")));
			}

		} catch (MissingResourceException e) {
			JOptionPane.showMessageDialog(null, "MissingResourceException\n" + e.getClassName() + "\n" + e.getLocalizedMessage() + "\n" + e.getKey()
					+ "\n" + e.getMessage() + "\n");
		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "AutomationException\n" + e.getDescription());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "IOException\n" + e.getMessage());
			e.printStackTrace();
		}

	}

	public IDataset getDatasetPipeline() {
		return pD_Pipeline;
	}

	public void setDatasetPipeline(IDataset pD_Pipeline) {
		this.pD_Pipeline = pD_Pipeline;
	}

	public String getIdPipeline() {
		return idPipeline;
	}

	public void setIdPipeline(String idPipeline) {
		this.idPipeline = idPipeline;
	}

	public Double getLevelCoverStart() {
		return levelCoverStart;
	}

	public void setLevelCoverStart(Double levelCoverStart) {
		this.levelCoverStart = levelCoverStart;
	}

	public Double getLevelCoverEnd() {
		return levelCoverEnd;
	}

	public void setLevelCoverEnd(Double levelCoverEnd) {
		this.levelCoverEnd = levelCoverEnd;
	}

	public Double getLevelHighStart() {
		return levelHighStart;
	}

	public void setLevelHighStart(Double levelHighStart) {
		this.levelHighStart = levelHighStart;
	}

	public Double getLevelHighEnd() {
		return levelHighEnd;
	}

	public void setLevelHighEnd(Double levelHighEnd) {
		this.levelHighEnd = levelHighEnd;
	}

	public Double getLevelLowStart() {
		return levelLowStart;
	}

	public void setLevelLowStart(Double levelLowStart) {
		this.levelLowStart = levelLowStart;
	}

	public Double getLevelLowEnd() {
		return levelLowEnd;
	}

	public void setLevelLowEnd(Double levelLowEnd) {
		this.levelLowEnd = levelLowEnd;
	}

	public Double getLengthM() {
		return lengthM;
	}

	public void setLengthM(Double lengthM) {
		this.lengthM = lengthM;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Double getBase() {
		return base;
	}

	public void setBase(Double object) {
		this.base = object;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getBottomMean() {
		return bottomMean;
	}

	public void setBottomMean(Double bottomMean) {
		this.bottomMean = bottomMean;
	}

	public Double getSlope() {
		return slope;
	}

	public void setSlope(Double slope) {
		this.slope = slope;
	}

	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
	}

	public String getSystemType() {
		return systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getMaterialPublicSpace() {
		return materialPublicSpace;
	}

	public void setMaterialPublicSpace(String materialPublicSpace) {
		this.materialPublicSpace = materialPublicSpace;
	}

	public String getNetworkState() {
		return networkState;
	}

	public void setNetworkState(String networkState) {
		this.networkState = networkState;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public String getMaterial2() {
		return material2;
	}

	public void setMaterial2(String material2) {
		this.material2 = material2;
	}

	public Integer getNumPipelines() {
		return numPipelines;
	}

	public void setNumPipelines(Integer numPipelines) {
		this.numPipelines = numPipelines;
	}

	public String getCameraInspection() {
		return cameraInspection;
	}

	public void setCameraInspection(String cameraInspection) {
		this.cameraInspection = cameraInspection;
	}

	public String getNameFeature() {
		return nameFeature;
	}

	public void setNameFeature(String nameFeature) {
		this.nameFeature = nameFeature;
	}

	public String getNameFieldId() {
		return nameFieldId;
	}

	public void setNameFieldId(String nameFieldId) {
		this.nameFieldId = nameFieldId;
	}

	@Override
	public IObjectClass esri_getClass() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return this.pF_Pipeline.esri_getClass();
	}

	@Override
	public void delete() throws IOException, AutomationException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getOID() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return this.pF_Pipeline.getOID();
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

		this.setValue(this.getFields().findField(this.nameFieldId), idPipeline);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LevelCoverStart")),
				levelCoverStart);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LevelCoverEnd")), levelCoverEnd);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LevelHighStart")), levelHighStart);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LevelHighEnd")), levelHighEnd);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LevelLowStart")), levelLowStart);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LevelLowEnd")), levelLowEnd);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.LengthM")), lengthM);
		this.setValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.Material")), material);

		if (!Arrays.asList(Resources.gdb.getString("Pipeline.FeatureClassLateral").split(",")).contains(nameFeature)) {
			this.setValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.Section")), section);
			this.setValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.Base")), base);
			this.setValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.Height")), height);
			this.setValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.BottomMean")), bottomMean);
			this.setValue(this.getFields().findField(Resources.gdb.getString("Pipeline.Field.Slope")), slope);
		}

		this.pF_Pipeline.store();
	}

	@Override
	public IFields getFields() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return this.pF_Pipeline.getFields();
	}

	@Override
	public Object getValue(int index) throws AutomationException, IOException {
		// TODO Auto-generated method stub
		return this.pF_Pipeline.getValue(index);
	}

	@Override
	public void setValue(int index, Object value) throws IOException, AutomationException {
		// TODO Auto-generated method stub
		this.pF_Pipeline.setValue(index, value);
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
		return this.pF_Pipeline.getShape();
	}

	@Override
	public IGeometry getShapeCopy() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShapeByRef(IGeometry shape) throws IOException, AutomationException {
		// TODO Auto-generated method stub
		this.pF_Pipeline.setShapeByRef(shape);
	}

}
