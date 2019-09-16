package arcgis10_2.model;

import java.io.IOException;
import java.net.UnknownHostException;

import com.esri.arcgis.geodatabase.IRow;
import com.esri.arcgis.geometry.IPoint;
import com.esri.arcgis.geometry.ISpatialReference;
import com.esri.arcgis.geometry.Point;
import com.esri.arcgis.interop.AutomationException;

import arcgis10_2.util.Resources;

public class SurveyPoint {

	Double x;
	Double y;
	Double levelCover;
	Double levelGround;
	
	IRow pR_Survey;

	public SurveyPoint(IRow pR_Survey) {
		
		try {
			this.x = (Double) pR_Survey.getValue(pR_Survey.getFields().findField(Resources.gdb.getString("Survey.Field.X")));
			this.y = (Double) pR_Survey.getValue(pR_Survey.getFields().findField(Resources.gdb.getString("Survey.Field.Y")));
			this.levelCover = (Double) pR_Survey.getValue(pR_Survey.getFields().findField(Resources.gdb.getString("Survey.Field.LevelCover")));
			this.levelGround = (Double) pR_Survey.getValue(pR_Survey.getFields().findField(Resources.gdb.getString("Survey.Field.LevelGround")));
		} catch (AutomationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.pR_Survey = pR_Survey;
	}

	// Create Point
	public IPoint getPoint(ISpatialReference pSR_Planas) throws UnknownHostException, IOException {
		IPoint pPointSurvey = new Point();
		pPointSurvey.setX((Double) this.getX());
		pPointSurvey.setY((Double) this.getY());
		pPointSurvey.setSpatialReferenceByRef(pSR_Planas);
		return pPointSurvey;
	}
	
	
	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
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

}
