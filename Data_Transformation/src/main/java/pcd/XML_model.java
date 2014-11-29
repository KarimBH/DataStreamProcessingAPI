package pcd;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement
public class XML_model {
 
    private Integer id;
    private Double Heure;
    private Double Date;
    private Double latitude;
    private Double longitude;
    private Float vitesse;
    private Double CAP;
    private String X;
    private String Y;
    private String Z;
    private Float Z_Signé;
    private Float Lat_deg;
    private Float lon_deg;
    
    
    public XML_model(){}
   
    public XML_model(Integer id, Double Heure, Double date, Double latitude, Double longitude, Float vitesse, Double CAP, String X, String Y, String Z, Float Z_Signé, Float Lat_deg, Float lon_deg) {
        this.id = id;
        this.Heure = Heure;
        this.Date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.vitesse = vitesse;
        this.CAP = CAP;
        this.X = X;
        this.Y = Y;
        this.Z = Z;
        this.Z_Signé = Z_Signé;
        this.Lat_deg = Lat_deg;
        this.lon_deg = lon_deg;
    }
 
    public Integer getId() {
        return id;
    }
 
    @XmlElement
    public void setId(Integer id) {
        this.id = id;
    }
 
    public Double getHeure() {
        return Heure;
    }
 
    @XmlElement
    public void setHeure(Double Heure) {
        this.Heure = Heure;
    }
 
    public Double getDate() {
        return Date;
    }
 
    @XmlElement
    public void setDate(Double Date) {
        this.Date = Date;
    }
 
    public Double getlatitude() {
        return latitude;
    }
 
    @XmlElement
    public void setlatitude(Double latitude) {
        this.latitude = latitude;
    }
 
    public Double getlongitude() {
        return longitude;
    }
 
    @XmlElement
    public void setlongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    public Float getvitesse() {
        return vitesse;
    }
 
    @XmlElement
    public void setvitesse(Float vitesse) {
        this.vitesse = vitesse;
    }
 
    public Double getCAP() {
        return CAP;
    }
 
    @XmlElement
    public void setCAP(Double CAP) {
        this.CAP = CAP;
    }
    
    public String getX() {
        return X;
    }
 
    @XmlElement
    public void setX(String X) {
        this.X = X;
    }
 
    public String getY() {
        return Y;
    }
 
    @XmlElement
    public void setY(String Y) {
        this.Y = Y;
    }
    
    public String getZ() {
        return Z;
    }
 
    @XmlElement
    public void setZ(String Z) {
        this.Z = Z;
    }
    
    ///
    public Float getZ_Signé() {
        return Z_Signé;
    }
 
    @XmlElement
    public void setZ_Signé(Float Z_Signé) {
        this.Z_Signé = Z_Signé;
    }
 
    public Float getLat_deg() {
        return Lat_deg;
    }
 
    @XmlElement
    public void setLat_deg(Float Lat_deg) {
        this.Lat_deg = Lat_deg;
    }
    
    public Float getlon_deg() {
        return lon_deg;
    }
 
    @XmlElement
    public void setlon_deg(Float lon_deg) {
        this.lon_deg = lon_deg;
    }
    
    
    
    
    
 
    @Override
    public String toString() {
     
    	return 
       
        "MODEL \n " + id +"\t"+ Heure +"\t"+ Date +"\t" + latitude +"\t" + longitude +"\t" + vitesse +"\t" + CAP +"\t" + X +"\t" + Y +"\t" + Z +"\t" + Z_Signé +"\t" + Lat_deg +"\t" + lon_deg ;
        
    }
}