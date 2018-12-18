package model;

public class Company {
	
	String companyName;
	String companyCode;
	String streetAddress;
	String zipCode;
	
	public Company(String companyName, String companyCode, String streetAddress, String zipCode) {
		super();
		this.companyName = companyName;
		this.companyCode = companyCode;
		this.streetAddress = streetAddress;
		this.zipCode = zipCode;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "company Name: " + companyName + ", company Code: " + companyCode + ", street: "
				+ streetAddress + ", zipCode: " + zipCode ;
	}
	
	
	
	

}
