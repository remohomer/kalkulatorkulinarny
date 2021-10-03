package com.example.kalkulator.ui.qr;

public class GeneratorStringMaker {

    private final ScannerStringInterpreter scannerStringInterpreter;
    private final StringType stringType;
    private final String qrCodeInputString;
    private  String stringResult;

    public GeneratorStringMaker(String qrCodeInputString, StringType stringType) {
        this.qrCodeInputString = qrCodeInputString;
        this.stringType = stringType;
        this.scannerStringInterpreter = new ScannerStringInterpreter(qrCodeInputString);
        make();
    }

    public void make() {

        switch (stringType.getName()) {
            case "website": {
                if (!scannerStringInterpreter.isHttp() && !scannerStringInterpreter.isHttps()) {
                    System.out.println("tutaj");
                    stringResult = "http://" + qrCodeInputString;
                    break;
                }
                stringResult = qrCodeInputString;
                break;
            }
            case "email": {
                if (!scannerStringInterpreter.isEmail()) {
                    stringResult = "mailto:" + qrCodeInputString;
                    break;
                }
                stringResult = qrCodeInputString;
                break;
            }
            case "phone": {
                if (!scannerStringInterpreter.isPhoneNumber()) {
                    stringResult = "tel:" + qrCodeInputString;
                    break;
                }
                stringResult = qrCodeInputString;
                break;
            }
            case "wifi": {
                if (!scannerStringInterpreter.isWifi()) {
                    stringResult = "WIFI:" + "grubszy temat:" + qrCodeInputString;
                    break;
                }
                stringResult = qrCodeInputString;
                break;
            }
            case "encrypted": {
                if (!scannerStringInterpreter.isEncrypted()) {
                    stringResult = "encr:" + "grubszy temat:" + qrCodeInputString;
                    break;
                }
                stringResult = qrCodeInputString;
                break;
            }
            default: {
                stringResult = qrCodeInputString;
                break;
            }
        }
    }

    public String getQrCodeInputString() {
        return qrCodeInputString;
    }

    public StringType getStringType() {
        return stringType;
    }

    public String getStringResult() {
        return stringResult;
    }
}
