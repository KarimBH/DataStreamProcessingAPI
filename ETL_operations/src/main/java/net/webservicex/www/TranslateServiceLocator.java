/**
 * TranslateServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webservicex.www;

public class TranslateServiceLocator extends org.apache.axis.client.Service implements net.webservicex.www.TranslateService {

    public TranslateServiceLocator() {
    }


    public TranslateServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TranslateServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TranslateServiceSoap
    private java.lang.String TranslateServiceSoap_address = "http://www.webservicex.net/TranslateService.asmx";

    public java.lang.String getTranslateServiceSoapAddress() {
        return TranslateServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TranslateServiceSoapWSDDServiceName = "TranslateServiceSoap";

    public java.lang.String getTranslateServiceSoapWSDDServiceName() {
        return TranslateServiceSoapWSDDServiceName;
    }

    public void setTranslateServiceSoapWSDDServiceName(java.lang.String name) {
        TranslateServiceSoapWSDDServiceName = name;
    }

    public net.webservicex.www.TranslateServiceSoap getTranslateServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TranslateServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTranslateServiceSoap(endpoint);
    }

    public net.webservicex.www.TranslateServiceSoap getTranslateServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            net.webservicex.www.TranslateServiceSoapStub _stub = new net.webservicex.www.TranslateServiceSoapStub(portAddress, this);
            _stub.setPortName(getTranslateServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTranslateServiceSoapEndpointAddress(java.lang.String address) {
        TranslateServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (net.webservicex.www.TranslateServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                net.webservicex.www.TranslateServiceSoapStub _stub = new net.webservicex.www.TranslateServiceSoapStub(new java.net.URL(TranslateServiceSoap_address), this);
                _stub.setPortName(getTranslateServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("TranslateServiceSoap".equals(inputPortName)) {
            return getTranslateServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://www.webservicex.net", "TranslateService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://www.webservicex.net", "TranslateServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TranslateServiceSoap".equals(portName)) {
            setTranslateServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
