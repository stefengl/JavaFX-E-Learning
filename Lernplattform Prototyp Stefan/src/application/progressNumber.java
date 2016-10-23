package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;


public class progressNumber {

	private DoubleProperty progressNumber;

	public final double getProgressNumber() {
		if (progressNumber != null){
			return progressNumber.get();
		}else
		return 0;
	}

	public final void setProgressNumber(double progressNumber) {
		this.numberProperty().set(progressNumber);
	}

	public final DoubleProperty numberProperty() {
		if (progressNumber == null) {
			progressNumber = new SimpleDoubleProperty(0);
		}

		return progressNumber;

	}
}
