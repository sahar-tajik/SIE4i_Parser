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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SieDocument {

    /**
     * #FLAGGA
     */
    private int flagga;
    private SieCompany fnamn;
    /**
     * #FORMAT
     */
    private String format;
    /**
     * #GEN
     */
    private Date genDate;
    private String genNamn;
    
    /**
     * #KONTO
     */
    private HashMap<String, SieAccount> konto;
    
    
    /**
     * #PROGRAM
     */
    private List<String> program;
    /**
     * #PROSA
     */
    private String prosa;
    
    /**
     * #SIETYP
     */
    private int sieTyp;
    
    /**
     * #VER
     */
    private List<SieVoucher> ver;

    public SieDocument() {
        setFNAMN(new SieCompany());
        setKONTO(new HashMap<>());
        setPROGRAM(new ArrayList<>());
        setVER(new ArrayList<>());

    }

    public int getFLAGGA() {
        return flagga;
    }

    public void setFLAGGA(int value) {
        flagga = value;
    }

    public SieCompany getFNAMN() {
        return fnamn;
    }

    public void setFNAMN(SieCompany value) {
        fnamn = value;
    }

    public String getFORMAT() {
        return format;
    }

    public void setFORMAT(String value) {
        format = value;
    }

    public Date getGEN_DATE() {
        return genDate;
    }

    public void setGEN_DATE(Date value) {
        genDate = value;
    }

    public String getGEN_NAMN() {
        return genNamn;
    }

    public void setGEN_NAMN(String value) {
        genNamn = value;
    }

    public HashMap<String, SieAccount> getKONTO() {
        return konto;
    }

    public void setKONTO(HashMap<String, SieAccount> value) {
        konto = value;
    }

    public List<String> getPROGRAM() {
        return program;
    }

    public void setPROGRAM(List<String> value) {
        program = value;
    }

    public String getPROSA() {
        return prosa;
    }

    public void setPROSA(String value) {
        prosa = value;
    }

    public int getSIETYP() {
        return sieTyp;
    }

    public void setSIETYP(int value) {
        sieTyp = value;
    }

    public List<SieVoucher> getVER() {
        return ver;
    }

    public void setVER(List<SieVoucher> value) {
        ver = value;
    }


}


