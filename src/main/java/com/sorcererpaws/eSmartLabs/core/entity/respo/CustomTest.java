package com.sorcererpaws.eSmartLabs.core.entity.respo;

public class CustomTest {

	private Long testId;
	private Long deptId;
	private Long labId;
	private String testCode;
	private String testName;
	private String deptName;
	private double price;
	
	private String normalMin;
	private String normalMax;
	private String unitType; 
	private String unitUsed;
	
	private boolean rangesSameForAll = true;
	
	private String maleMin;
	private String maleMax;
	
	private String femaleMin;
	private String femaleMax;
	
	private String childMin;
	private String childMax;
	
	private String labName;
	
	public CustomTest() {
		
	}
	
	public CustomTest(Long testId, Long deptId, Long labId, String testCode, String testName, String deptName,
			double price, String normalMin, String normalMax, String unitType, String unitUsed,
			boolean rangesSameForAll, String maleMin, String maleMax, String femaleMin, String femaleMax,
			String childMin, String childMax, String labName) {
		
		this.testId = testId;
		this.deptId = deptId;
		this.labId = labId;
		this.testCode = testCode;
		this.testName = testName;
		this.deptName = deptName;
		this.price = price;
		this.normalMin = normalMin;
		this.normalMax = normalMax;
		this.unitType = unitType;
		this.unitUsed = unitUsed;
		this.rangesSameForAll = rangesSameForAll;
		this.maleMin = maleMin;
		this.maleMax = maleMax;
		this.femaleMin = femaleMin;
		this.femaleMax = femaleMax;
		this.childMin = childMin;
		this.childMax = childMax;
		this.labName = labName;
	}
	
	public Long getTestId() {
		return testId;
	}
	public void setTestId(Long testId) {
		this.testId = testId;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getLabId() {
		return labId;
	}
	public void setLabId(Long labId) {
		this.labId = labId;
	}
	public String getTestCode() {
		return testCode;
	}
	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	
	public String getNormalMin() {
		return normalMin;
	}
	public void setNormalMin(String normalMin) {
		this.normalMin = normalMin;
	}
	public String getNormalMax() {
		return normalMax;
	}
	public void setNormalMax(String normalMax) {
		this.normalMax = normalMax;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getUnitUsed() {
		return unitUsed;
	}
	public void setUnitUsed(String unitUsed) {
		this.unitUsed = unitUsed;
	}
	public boolean isRangesSameForAll() {
		return rangesSameForAll;
	}
	public void setRangesSameForAll(boolean rangesSameForAll) {
		this.rangesSameForAll = rangesSameForAll;
	}
	public String getMaleMin() {
		return maleMin;
	}
	public void setMaleMin(String maleMin) {
		this.maleMin = maleMin;
	}
	public String getMaleMax() {
		return maleMax;
	}
	public void setMaleMax(String maleMax) {
		this.maleMax = maleMax;
	}
	public String getFemaleMin() {
		return femaleMin;
	}
	public void setFemaleMin(String femaleMin) {
		this.femaleMin = femaleMin;
	}
	public String getFemaleMax() {
		return femaleMax;
	}
	public void setFemaleMax(String femaleMax) {
		this.femaleMax = femaleMax;
	}
	public String getChildMin() {
		return childMin;
	}
	public void setChildMin(String childMin) {
		this.childMin = childMin;
	}
	public String getChildMax() {
		return childMax;
	}
	public void setChildMax(String childMax) {
		this.childMax = childMax;
	}
}
