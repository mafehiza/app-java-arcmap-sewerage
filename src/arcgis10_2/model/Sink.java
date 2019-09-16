package arcgis10_2.model;

import java.io.IOException;

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
public class Sink implements IFeature {

	// Fields
	private String idSink;
	private Double turnSymbol;

	private IFeature pF_Sink;
	private IDataset pD_Sink;

	public Boolean isSink;

	public Sink(IFeature pF_Sink) {

		this.isSink = true;

		this.pF_Sink = pF_Sink;

		try {
			// Sink Dataset
			this.pD_Sink = new IDatasetProxy(pF_Sink.esri_getClass());

			// Sink Fields
			this.idSink = (String) pF_Sink
					.getValue(pF_Sink.getFields().findField(Resources.gdb.getString("Sink.Field.Id")));
			this.turnSymbol = (Double) pF_Sink
					.getValue(pF_Sink.getFields().findField(Resources.gdb.getString("Sink.Field.TurnSymbol")));

		} catch (AutomationException e) {
			this.isSink = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			this.isSink = false;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public IDataset getDatasetSink() {
		return pD_Sink;
	}

	public void setDatasetSink(IDataset pD_Sink) {
		this.pD_Sink = pD_Sink;
	}

	public String getIdSink() {
		return idSink;
	}

	public void setIdSink(String idSink) {
		this.idSink = idSink;
	}

	public Double getTurnSymbol() {
		return turnSymbol;
	}

	public void setTurnSymbol(Double turnSymbol) {
		this.turnSymbol = turnSymbol;
	}

	@Override
	public IObjectClass esri_getClass() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getOID() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return this.pF_Sink.getOID();
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
		
		int index = pF_Sink.getFields().findField(Resources.gdb.getString("Sink.Field.TurnSymbol"));
		Double value = this.turnSymbol;
		
		this.pF_Sink.setValue(index, value);
		this.pF_Sink.store();
	}

	@Override
	public IFields getFields() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getValue(int index) throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(int index, Object value) throws IOException, AutomationException {
		// TODO Auto-generated method stub
		
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
		return this.pF_Sink.getShape();
	}

	@Override
	public IGeometry getShapeCopy() throws IOException, AutomationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setShapeByRef(IGeometry arg0) throws IOException, AutomationException {
		// TODO Auto-generated method stub
		
	}
	
}
