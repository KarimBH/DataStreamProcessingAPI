/**
 * TranslateServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webservicex.www;

public interface TranslateServiceSoap extends java.rmi.Remote {

    /**
     * Convert text from one lanaguage to another language .Supported
     * languages are English to Chinese,English to French,English to German,English
     * to Italian,English to Japanese,English to Korean,English to Portuguese,English
     * to Spanish,Chinese to English,French to English,French to German,German
     * to English,German to French,Italian to English,Japanese to English,Korean
     * to English,Portuguese to English,Russian to English,Spanish to English.
     */
    public java.lang.String translate(net.webservicex.www.Language languageMode, java.lang.String text) throws java.rmi.RemoteException;
}
