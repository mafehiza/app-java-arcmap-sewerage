package arcgis10_2.model;

import java.io.IOException;

import com.esri.arcgis.geodatabase.IRow;
import com.esri.arcgis.interop.AutomationException;

import arcgis10_2.util.Resources;

public class PipelineTable {

	// Fields
	private String pipelineId;
	private String contract;
	private Double diameter;
	private String side;
	private String manholeId;
	private Double bottom;
	private String material;
	private String typeManhole;
	private String dateEdition;

	IRow PipelineTable;

	public PipelineTable(IRow pR_PipelineTable) {
		
		try {
			setPipelineId((String) pR_PipelineTable.getValue(pR_PipelineTable.getFields().findField(Resources.gdb.getString("PipelineTable.Field.Id"))));
			setContract((String) pR_PipelineTable.getValue(pR_PipelineTable.getFields().findField(Resources.gdb.getString("PipelineTable.Field.Contract"))));
			setDiameter((Double) pR_PipelineTable.getValue(pR_PipelineTable.getFields().findField(Resources.gdb.getString("PipelineTable.Field.Diameter"))));
			setSide((String) pR_PipelineTable.getValue(pR_PipelineTable.getFields().findField(Resources.gdb.getString("PipelineTable.Field.Side"))));
			setManholeId((String) pR_PipelineTable.getValue(pR_PipelineTable.getFields().findField(Resources.gdb.getString("PipelineTable.Field.ManholeId"))));
			setBottom((Double) pR_PipelineTable.getValue(pR_PipelineTable.getFields().findField(Resources.gdb.getString("PipelineTable.Field.Bottom"))));
			setMaterial((String) pR_PipelineTable.getValue(pR_PipelineTable.getFields().findField(Resources.gdb.getString("PipelineTable.Field.Material"))));
			setTypeManhole((String) pR_PipelineTable.getValue(pR_PipelineTable.getFields().findField(Resources.gdb.getString("PipelineTable.Field.TypeManhole"))));
			setDateEdition((String) pR_PipelineTable.getValue(pR_PipelineTable.getFields().findField(Resources.gdb.getString("PipelineTable.Field.DateEdition"))));
		} catch (AutomationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getPipelineId() {
		return pipelineId;
	}

	public void setPipelineId(String pipelineId) {
		this.pipelineId = pipelineId;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public Double getDiameter() {
		return diameter;
	}

	public void setDiameter(Double diameter) {
		this.diameter = diameter;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getManholeId() {
		return manholeId;
	}

	public void setManholeId(String manholeId) {
		this.manholeId = manholeId;
	}

	public Double getBottom() {
		return bottom;
	}

	public void setBottom(Double bottom) {
		this.bottom = bottom;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getTypeManhole() {
		return typeManhole;
	}

	public void setTypeManhole(String typeManhole) {
		this.typeManhole = typeManhole;
	}

	public String getDateEdition() {
		return dateEdition;
	}

	public void setDateEdition(String dateEdition) {
		this.dateEdition = dateEdition;
	}

}
