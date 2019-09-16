package arcgis10_2.util;

import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.esri.arcgis.geodatabase.ICodedValueDomain;
import com.esri.arcgis.interop.AutomationException;

@SuppressWarnings("serial")
public class ComboBox extends JComboBox {

	public void fill(ICodedValueDomain pCodedValueDomain) {
		try {
			DefaultComboBoxModel dcm = new DefaultComboBoxModel();
			this.setModel(dcm);
			for (int i = 0; i < pCodedValueDomain.getCodeCount(); i++) {
				dcm.addElement((String) pCodedValueDomain.getName(i));
			}
		} catch (AutomationException e) {
			JOptionPane.showMessageDialog(null, "Error FillComboBox: " + e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error FillComboBox: " + e.toString());
			e.printStackTrace();
		}
	}

}
