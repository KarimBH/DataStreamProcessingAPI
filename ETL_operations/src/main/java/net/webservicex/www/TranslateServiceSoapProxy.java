package net.webservicex.www;

public class TranslateServiceSoapProxy implements net.webservicex.www.TranslateServiceSoap {
  private String _endpoint = null;
  private net.webservicex.www.TranslateServiceSoap translateServiceSoap = null;
  
  public TranslateServiceSoapProxy() {
    _initTranslateServiceSoapProxy();
  }
  
  public TranslateServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initTranslateServiceSoapProxy();
  }
  
  private void _initTranslateServiceSoapProxy() {
    try {
      translateServiceSoap = (new net.webservicex.www.TranslateServiceLocator()).getTranslateServiceSoap();
      if (translateServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)translateServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)translateServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (translateServiceSoap != null)
      ((javax.xml.rpc.Stub)translateServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public net.webservicex.www.TranslateServiceSoap getTranslateServiceSoap() {
    if (translateServiceSoap == null)
      _initTranslateServiceSoapProxy();
    return translateServiceSoap;
  }
  
  public java.lang.String translate(net.webservicex.www.Language languageMode, java.lang.String text) throws java.rmi.RemoteException{
    if (translateServiceSoap == null)
      _initTranslateServiceSoapProxy();
    return translateServiceSoap.translate(languageMode, text);
  }
  
  
}