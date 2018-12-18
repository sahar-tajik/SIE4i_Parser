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

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import sieparser.SIE;

public class SieDocumentReader {
    /**
     * This is where all the callbacks to client code happens.
     */
    public SieCallbacks callbacks = new SieCallbacks();
    public boolean ignoreBTRANS = false;
    public boolean ignoreMissingOMFATTNING = false;
    public boolean ignoreRTRANS = false;
    private SieDocument sieDocument;
    /**
     * Will contain all validation errors after doing a ValidateDocument
     */
    private List<Exception> validationExceptions;
    /**
     * If this is set to true in ReadFile no period values, balances or transactions will be saved in memory.
     * Use this in combination with callbacks to stream through a file.
     */
    public boolean streamValues = false;
    
    /**
     * If this is set to true in ReadFile each error will be thrown otherwise they will just be callbacked.
     */
    public boolean throwErrors = true;
    /**
     * This is the file currently being read.
     */
    private String fileName;

    /**
     * Does a fast scan of the file to get the Sie version it adheres to.
     *
     * @param fileName the file name to parse
     * @throws Exception if a parsing problem occurred
     * @return -1 if no SIE version was found in the file else SIETYPE is returned.
     */
    public static int getSieVersion(String fileName) throws Exception {
        int ret = -1;
        BufferedReader reader = IoUtil.getReader(fileName);

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {

            if (line.startsWith(SIE.SIETYP)) {
                SieDataItem di = new SieDataItem(line, null, null);
                ret = di.getInt(0);
                break;
            }

        }
        reader.close();
        return ret;
    }

    public SieDocumentReader() {
        sieDocument = new SieDocument();
        setValidationExceptions(new ArrayList<>());
    }

    public SieDocumentReader(boolean ignoreBTRANS, boolean ignoreMissingOMFATTNING, boolean streamValues, boolean
            throwErrors) {
        this();
        this.ignoreBTRANS = ignoreBTRANS;
        this.ignoreMissingOMFATTNING = ignoreMissingOMFATTNING;
        this.streamValues = streamValues;
        this.throwErrors = throwErrors;
    }

    public SieDocument readDocument(String fileName) throws Exception {
        this.fileName = fileName;
        if (throwErrors)
            callbacks.SieException = MultiAction.Combine(callbacks.SieException, new Action<Exception>() {
                @Override
                public void Invoke(Exception obj) throws Exception {
                    throw obj;
                }

                @Override
                public List<Action<Exception>> getInvocationList() throws Exception {
                    List<Action<Exception>> ret = new ArrayList<>();
                    ret.add(this);
                    return ret;
                }
            });


        SieVoucher curVoucher = null;
        boolean firstLine = true;
        BufferedReader reader = IoUtil.getReader(fileName);

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            callbacks.callbackLine(line);
            SieDataItem di = new SieDataItem(line, this, sieDocument);
            if (firstLine) {
                firstLine = false;
                if (!SIE.FLAGGA.equals(di.getItemType())) {
                    callbacks.callbackException(new Exception(this.fileName));
                    return null;
                }

            }

            String itemType = di.getItemType();
            if (SIE.ADRESS.equals(itemType)) {
                sieDocument.getFNAMN().setContact(di.getString(0));
                sieDocument.getFNAMN().setStreet(di.getString(1));
                sieDocument.getFNAMN().setZipCity(di.getString(2));
                sieDocument.getFNAMN().setPhone(di.getString(3));
            } else if (SIE.BTRANS.equals(itemType)) {
                if (!ignoreBTRANS)
                    parseTRANS(di, curVoucher);

            } else if (SIE.FLAGGA.equals(itemType)) {
                sieDocument.setFLAGGA(di.getInt(0));
            } else if (SIE.FNAMN.equals(itemType)) {
                sieDocument.getFNAMN().setName(di.getString(0));
            } else if (SIE.FORMAT.equals(itemType)) {
                sieDocument.setFORMAT(di.getString(0));
            } else if (SIE.GEN.equals(itemType)) {
                sieDocument.setGEN_DATE(di.getDate(0));
                sieDocument.setGEN_NAMN(di.getString(1));
            } else if (SIE.KONTO.equals(itemType)) {
                parseKONTO(di);
            } else if (SIE.ORGNR.equals(itemType)) {
                sieDocument.getFNAMN().setOrgIdentifier(di.getString(0));
            } else if (SIE.PROGRAM.equals(itemType)) {
                sieDocument.setPROGRAM(di.getData());
            } else if (SIE.PROSA.equals(itemType)) {
                sieDocument.setPROSA(di.getString(0));
            } else if (SIE.RTRANS.equals(itemType)) {
                if (!ignoreBTRANS) parseTRANS(di, curVoucher);
            } else if (SIE.SIETYP.equals(itemType)) {
                sieDocument.setSIETYP(di.getInt(0));
            } else if (SIE.TRANS.equals(itemType)) {
                parseTRANS(di, curVoucher);
            } else if (SIE.VER.equals(itemType)) {
                curVoucher = parseVER(di);
            } else if (SIE.FNR.equals(itemType)) {
            } else if (SIE.RAR.equals(itemType)) {
            } else if (SIE.OMFATTN.equals(itemType)) {
            } else if (SIE.SRU.equals(itemType)) {
            } else if (SIE.BKOD.equals(itemType)) {
            } else if (SIE.DIM.equals(itemType)) {
            } else if (SIE.ENHET.equals(itemType)) {
            } else if (SIE.IB.equals(itemType)) {
            } else if (SIE.KSUMMA.equals(itemType)) {
            } else if (SIE.KTYP.equals(itemType)) {
            } else if (SIE.KPTYP.equals(itemType)) {
            } else if (SIE.OBJEKT.equals(itemType)) {
            } else if (SIE.OIB.equals(itemType)) {
            } else if (SIE.OUB.equals(itemType)) {
            } else if (SIE.PBUDGET.equals(itemType)) {
            } else if (SIE.PSALDO.equals(itemType)) {
            } else if (SIE.TAXAR.equals(itemType)) {
            } else if (SIE.UB.equals(itemType)) {
            } else if (SIE.RES.equals(itemType)) {
            } else if (SIE.VALUTA.equals(itemType)) {
            } else if ("".equals(itemType)) {
            } else if ("{".equals(itemType)) {
            } else if ("}".equals(itemType)) {
                if (curVoucher != null) closeVoucher(curVoucher);
                curVoucher = null;
            } else {
                callbacks.callbackException(new Exception(di.getItemType()));
            }
        }
        validateDocument();
        reader.close();
        return sieDocument;
    }

    private void parseENHET(SieDataItem di) throws Exception {
        if (!sieDocument.getKONTO().containsKey(di.getString(0))) {
            SieAccount account = new SieAccount();
            account.setNumber(di.getString(0));
            sieDocument.getKONTO().put(di.getString(0),account );
        }

        sieDocument.getKONTO().get(di.getString(0)).setUnit(di.getString(1));
    }

    private void parseKONTO(SieDataItem di) throws Exception {
        String number = di.getString(0);
        String name = di.getString(1);
        if (sieDocument.getKONTO().containsKey(number)) {
            sieDocument.getKONTO().get(number).setName(name);
        } else {
            sieDocument.getKONTO().put(number, new SieAccount(number,name ));
        }
    }

    
    private void parseTRANS(SieDataItem di, SieVoucher v) throws Exception {
        String number = di.getString(0);
        if (!sieDocument.getKONTO().containsKey(number)) {
            sieDocument.getKONTO().put(number, new SieAccount(number));
        }

        int objOffset = 0;
        if (di.getRawData().contains("{")) objOffset = 1;

        SieVoucherRow vr = new SieVoucherRow();
        vr.setAccount(sieDocument.getKONTO().get(number));
        vr.setAmount(di.getDecimal(1 + objOffset));
        if (di.getDate(2 + objOffset) != null) vr.setRowDate(di.getDate(2 + objOffset));
        else vr.setRowDate(v.getVoucherDate());
        vr.setText(di.getString(3 + objOffset));
        vr.setQuantity(di.getIntNull(4 + objOffset));
        vr.setCreatedBy(di.getString(5 + objOffset));
        vr.setToken(di.getItemType());

        v.getRows().add(vr);
    }

    private SieVoucher parseVER(SieDataItem di) throws Exception {
        if (di.getDate(2) == null)
            callbacks.callbackException(new Exception("Voucher date"));

        SieVoucher v = new SieVoucher();
        v.setSeries(di.getString(0));
        v.setNumber(di.getString(1));
        v.setVoucherDate(di.getDate(2) != null ? di.getDate(2) : new Date());
        v.setText(di.getString(3));
        v.setCreatedDate(di.getInt(4));
        v.setCreatedBy(di.getString(5));
        v.setToken(di.getItemType());
        return v;
    }

    private void addValidationException(boolean isException, Exception ex) throws Exception {
        if (isException) {
            getValidationExceptions().add(ex);
            callbacks.callbackException(ex);
        }

    }
    public List<Exception> getValidationExceptions() {
        return validationExceptions;
    }

    public void setValidationExceptions(List<Exception> value) {
        validationExceptions = value;
    }

    private void closeVoucher(SieVoucher v) throws Exception {
        //Check sum of rows
        BigDecimal check = new BigDecimal(0);
        for (SieVoucherRow r : v.getRows()) {
            check = check.add(r.getAmount());
        }
        if (check.compareTo(new BigDecimal(0)) != 0)
            callbacks.callbackException(new Exception(v.getSeries() + "." + v.getNumber() + " Sum is not zero."));

        callbacks.callbackVER(v);
        if (!streamValues) sieDocument.getVER().add(v);
    }


    private void validateDocument() throws Exception {
        addValidationException(sieDocument.getGEN_DATE() == null,
                new Exception("#GEN Date is missing in " + fileName));
        
    }
}
