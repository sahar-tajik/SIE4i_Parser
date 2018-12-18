/*
MIT License

Copyright (c) 2015 Johan Idstam
Modifications by Per Nyfelt Copyright (c) 2016 Alipsa HB

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package sieparser;


public class SieCompany {
    
    private int sni;
    private String name;
    private String code;
    private String orgType;
    private String orgIdentifier;
    private String contact;
    private String street;
    private String zipCity;
    private String phone;

    public SieCompany() {
        
    }
    
    public SieCompany(String name, String code, String street, String zipCity) {
		super();
		this.name = name;
		this.code = code;
		this.street = street;
		this.zipCity = zipCity;
	}


	/**
     * #BKOD
     * @return the Sni code (#BKOD)
     */
    public int getSni() {
        return sni;
    }

    public void setSni(int value) {
        sni = value;
    }

    /**
     * #FNAMN
     * @return the company name (#FNAMN)
     */
    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    /**
     * #FNR
     * @return the code (#FNR)
     */
    public String getCode() {
        return code;
    }

    public void setCode(String value) {
        code = value;
    }

    /**
     * #FTYP
     * @return the organization type (#FTYP)
     */
    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String value) {
        orgType = value;
    }

    /**
     * #ORGNR
     * @return the organization id (#ORGNR)
     */
    public String getOrgIdentifier() {
        return orgIdentifier;
    }

    public void setOrgIdentifier(String value) {
        orgIdentifier = value;
    }

    /**
     * #ADRESS
     * @return the contact address (#ADRESS)
     */
    public String getContact() {
        return contact;
    }

    public void setContact(String value) {
        contact = value;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String value) {
        street = value;
    }

    public String getZipCity() {
        return zipCity;
    }

    public void setZipCity(String value) {
        zipCity = value;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String value) {
        phone = value;
    }


}


