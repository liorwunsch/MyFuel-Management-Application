package entities;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Elroy
 */
@SuppressWarnings("serial")
public class RowInSaleCommentsReportTable implements Serializable {
	private int saleID;
	private Date startTime;
	private Date endTime;
	private double dieselDisc;
	private double gasolineDisc;
	private double motorDisc;

	public RowInSaleCommentsReportTable() {

	}

	public RowInSaleCommentsReportTable(int saleID, Date startTime, Date endTime, double dieselDisc, double gasolineDisc,
			double motorDisc) {
		super();
		this.saleID = saleID;
		this.startTime = startTime;
		this.endTime = endTime;
		this.dieselDisc = dieselDisc;
		this.gasolineDisc = gasolineDisc;
		this.motorDisc = motorDisc;
	}

	public int getSaleID() {
		return saleID;
	}

	public void setSaleID(int saleID) {
		this.saleID = saleID;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public double getDieselDisc() {
		return dieselDisc;
	}

	public void setDieselDisc(double dieselDisc) {
		this.dieselDisc = dieselDisc;
	}

	public double getGasolineDisc() {
		return gasolineDisc;
	}

	public void setGasolineDisc(double gasolineDisc) {
		this.gasolineDisc = gasolineDisc;
	}

	public double getMotorDisc() {
		return motorDisc;
	}

	public void setMotorDisc(double motorDisc) {
		this.motorDisc = motorDisc;
	}

	@Override
	public String toString() {
		return "RowInSaleCommentReportTable [saleID=" + saleID + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", dieselDisc=" + dieselDisc + ", gasolineDisc=" + gasolineDisc + ", motorDisc=" + motorDisc + "]";
	}

}
