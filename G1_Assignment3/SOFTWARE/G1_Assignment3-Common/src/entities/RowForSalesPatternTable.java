package entities;

import java.io.Serializable;

/**
 * 
 * @author Elroy class to put row in sales pattern table
 */
@SuppressWarnings("serial")
public class RowForSalesPatternTable implements Serializable {
	private int salePatternID;
	private int duration;
	private double dieselDiscount;
	private double gasolineDiscount;
	private double motorBikeDiscount;

	public RowForSalesPatternTable(int salePatternID, int duration) {
		super();
		this.salePatternID = salePatternID;
		this.duration = duration;
		this.dieselDiscount = 0.0;
		this.gasolineDiscount = 0.0;
		this.motorBikeDiscount = 0.0;
	}

	public int getSalePatternID() {
		return salePatternID;
	}

	public void setSalePatternID(int salePatternID) {
		this.salePatternID = salePatternID;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getDieselDiscount() {
		return dieselDiscount;
	}

	public void setDieselDiscount(double dieselDiscount) {
		this.dieselDiscount = dieselDiscount;
	}

	public double getGasolineDiscount() {
		return gasolineDiscount;
	}

	public void setGasolineDiscount(double gasolineDiscount) {
		this.gasolineDiscount = gasolineDiscount;
	}

	public double getMotorBikeDiscount() {
		return motorBikeDiscount;
	}

	public void setMotorBikeDiscount(double motorBikeDiscount) {
		this.motorBikeDiscount = motorBikeDiscount;
	}

	@Override
	public String toString() {
		return "RowForSalesPatternTable [salePatternID=" + salePatternID + ", duration=" + duration
				+ ", dieselDiscount=" + dieselDiscount + ", gasolineDiscount=" + gasolineDiscount
				+ ", motorBikeDiscount=" + motorBikeDiscount + "]";
	}

}
