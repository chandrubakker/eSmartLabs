package com.sorcererpaws.eSmartLabs.core.entity.respo;

public class CustomPatient {

	private boolean reportFound;
	private Long patientId;
	private String patientName;
	private String refBy;
	private String clinicName;
	private String regNum;
	private String phone;
	private String labName;
	
	public boolean isReportFound() {
		return reportFound;
	}
	public void setReportFound(boolean reportFound) {
		this.reportFound = reportFound;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getRefBy() {
		return refBy;
	}
	public void setRefBy(String refBy) {
		this.refBy = refBy;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	public String getRegNum() {
		return regNum;
	}
	public void setRegNum(String regNum) {
		this.regNum = regNum;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
}
