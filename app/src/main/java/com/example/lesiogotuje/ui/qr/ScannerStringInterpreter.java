package com.example.lesiogotuje.ui.qr;

import java.util.Locale;

public class ScannerStringInterpreter {
    private String qrOutputString;
    private int qrOutputStringLength;

    ScannerStringInterpreter(String qrCodeOutputString) {
        this.qrOutputString = qrCodeOutputString;
        this.qrOutputStringLength = qrCodeOutputString.length();
    }

    public boolean isHttp() {
        String httpExample = "http://a.pl";
        if (qrOutputStringLength >= httpExample.length()) {
            String isHttp = qrOutputString.toLowerCase(Locale.ROOT).substring(0, 7);
            return isHttp.equals("http://");
        }
        return false;
    }

    public boolean isHttps() {
        String httpExample = "https://a.pl";
        if (qrOutputStringLength >= httpExample.length()) {
            String isHttps = qrOutputString.toLowerCase(Locale.ROOT).substring(0, 8);
            return isHttps.equals("https://");
        }
        return false;
    }

    public boolean isLGShortRecipe() {

        String httpLGRecipeExample = "http://lesiogotuje.pl/a";
        String httpsLGRecipeExample = "https://lesiogotuje.pl/a";

        if (isHttp() && qrOutputStringLength >= httpLGRecipeExample.length()) {
            String isHttpSHortRecipe = qrOutputString.toLowerCase(Locale.ROOT).substring(0, 22);
            return isHttpSHortRecipe.equals("http://lesiogotuje.pl/");
        } else if (isHttps() && qrOutputStringLength >= httpsLGRecipeExample.length()) {
            String isHttpsSHortRecipe = qrOutputString.toLowerCase(Locale.ROOT).substring(0, 23);
            return isHttpsSHortRecipe.equals("https://lesiogotuje.pl/");
        }
        return false;
    }

    public boolean isLGLongRecipe() {
        String httpLGRecipeExample = "http://lesiogotuje.pl/przepisy/a";
        String httpsLGRecipeExample = "https://lesiogotuje.pl/przepisy/a";

        if (isHttp() && qrOutputStringLength >= httpLGRecipeExample.length()) {
            String isHttpLongRecipe = qrOutputString.toLowerCase(Locale.ROOT).substring(0, 31);
            return isHttpLongRecipe.equals("http://lesiogotuje.pl/przepisy/");
        } else if (isHttps() && qrOutputStringLength >= httpsLGRecipeExample.length()) {
            String isHttpsLongRecipe = qrOutputString.toLowerCase(Locale.ROOT).substring(0, 32);
            return isHttpsLongRecipe.equals("https://lesiogotuje.pl/przepisy/");
        }
        return false;
    }

    public String formatRecipe() {
        if (qrOutputStringLength <= 23) {
            return "LesioGotuje.pl";
        } else {
            if (setRawRecipeName().equals("keczup z cukini marysi")) {
                return "Przepis na keczup z cukini pani Marysi";
            }
            return "Przepis na " + setRawRecipeName() + " Lesia";
        }
    }

    private String setRawRecipeName() {
        String recipeUrl = cutUrlToRecipe();
        StringBuilder rawRecipeName = new StringBuilder();

        for (int i = 0; i < recipeUrl.length(); i++) {

            if (recipeUrl.charAt(i) == '-') {
                rawRecipeName.append(" ");
            } else {
                rawRecipeName.append(recipeUrl.charAt(i));
            }
        }
        return rawRecipeName.toString();
    }

    private String cutUrlToRecipe() {
        char stringLastChar = qrOutputString.charAt(qrOutputStringLength - 1);
        int urlSize = -1;

        if (isHttps()) {
            if (isLGLongRecipe()) {
                urlSize = 32;
            } else if (isLGShortRecipe()) {
                urlSize = 23;
            }
        } else if (isHttp()) {
            if (isLGLongRecipe()) {
                urlSize = 31;
            } else if (isLGShortRecipe()) {
                urlSize = 22;
            }
        }

        if (this.isLGLongRecipe()) {
            if (stringLastChar == '/') {
                return qrOutputString.toLowerCase(Locale.ROOT).substring(urlSize, qrOutputStringLength - 1);
            } else {
                return qrOutputString.toLowerCase(Locale.ROOT).substring(urlSize, qrOutputStringLength);
            }
        } else if (this.isLGShortRecipe()) {
            if (stringLastChar == '/') {
                return qrOutputString.toLowerCase(Locale.ROOT).substring(urlSize, qrOutputStringLength - 1);
            } else {
                return qrOutputString.toLowerCase(Locale.ROOT).substring(urlSize, qrOutputStringLength);
            }
        }
        return "[Niepoprawny format adresu internetowego]";
    }


    public boolean isEmail() {
        String mailto;
        if (qrOutputStringLength >= 13) {
            mailto = qrOutputString.toLowerCase(Locale.ROOT).substring(0, 7);
            return mailto.equals("mailto:");
        }
        return false;
    }

    public String formatEmail() {
        String mailExample = "mailto:a@a.pl";
        if (qrOutputStringLength >= mailExample.length()) {
            StringBuilder stringBuilder = new StringBuilder();
            boolean isEmailText = false;
            for (int i = 0; i < qrOutputStringLength; i++) {

                if (isEmailText) {
                    stringBuilder.append(qrOutputString.charAt(i));
                }
                String sChar = "" + qrOutputString.charAt(i);
                if (sChar.equals(":")) {
                    isEmailText = true;
                }
            }
            return stringBuilder.toString();
        }
        return "Ten email jest za krótki";
    }

    public boolean isPhoneNumber() {
        String tel;
        String phone;
        if (qrOutputStringLength >= 13) {
            tel = qrOutputString.toLowerCase(Locale.ROOT).substring(0, 4);
            phone = qrOutputString.toLowerCase(Locale.ROOT).substring(0, 6);
            if (tel.equals("tel:")) {
                return true;
            } else if (phone.equals("phone:")) {
                return true;
            }
        }
        return false;
    }

    public String formatPhone() {
        String telExample = "tel:123456789";
        String phoneExample = "phone:123456789";

        if (qrOutputStringLength >= telExample.length() || qrOutputStringLength >= phoneExample.length()) {
            StringBuilder stringBuilder = new StringBuilder();
            boolean isPhoneNumberText = false;
            for (int i = 0; i < qrOutputStringLength; i++) {

                if (isPhoneNumberText) {
                    stringBuilder.append(qrOutputString.charAt(i));
                }
                String sChar = "" + qrOutputString.charAt(i);
                if (sChar.equals(":")) {
                    isPhoneNumberText = true;
                }
            }
            return stringBuilder.toString();
        }
        return "Ten numer jest za krótki";
    }

    public boolean isWifi() {
        // TODO
        return false;
    }

    public boolean isEncrypted() {
        if (qrOutputStringLength < 13) {
            return false;
        }
        String encrypted = qrOutputString.substring(0, 4);
        return encrypted.equals("enc:");
    }

    public String getQrOutputString() {
        return qrOutputString;
    }

    public void setQrOutputString(String result) {
        this.qrOutputString = result;
    }
}