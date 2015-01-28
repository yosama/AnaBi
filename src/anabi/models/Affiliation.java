
package anabi.models;


import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author yosamac
 */


public class Affiliation  {

	
    private Integer codAffiliation = 0;
    private String nameAffiliation;
    private String cityAffiliation;
    private String countryAffiliation;
    private List<Integer> listCodAuthor;
    private List<Record>listRecord;
   
    
	public Affiliation() {
    }

    
    public Affiliation(Integer codAffiliation, String nameAffiliation, String cityAffiliation, String countryAffiliation, Record record) {
       
    	this.codAffiliation = codAffiliation;
        this.nameAffiliation = nameAffiliation;
        this.cityAffiliation = cityAffiliation;
        this.countryAffiliation = countryAffiliation;
        listRecord = new ArrayList<Record>();
        listRecord.add(record);
        this.listCodAuthor = new ArrayList<Integer>();
        
    }

    public Integer getCodAffiliation() {
        return codAffiliation;
    }

    public void setCodAffiliation(Integer codAffiliation) {
        this.codAffiliation = codAffiliation;
    }

    public String getNameAffiliation() {
        return nameAffiliation;
    }

    public void setNameAffiliation(String nameAffiliation) {
        this.nameAffiliation = nameAffiliation;
    }

    public String getCityAffiliation() {
        return cityAffiliation;
    }

    public void setCityAffiliation(String cityAffiliation) {
        this.cityAffiliation = cityAffiliation;
    }

    public String getCountryAffiliation() {
        return countryAffiliation;
    }

    public void setCountryAffiliation(String countryAffiliation) {
        this.countryAffiliation = countryAffiliation;
    }

    
    public List<Integer> getListCodAuthor() {
        return listCodAuthor;
    }

    public void setListCodAuthor(List<Integer> listCodAuthor) {
        this.listCodAuthor = listCodAuthor;
    }

    
    public List<Record> getListRecord() {
		return listRecord;
	}


	public void setListRecord(List<Record> keyRecords) {
		this.listRecord = keyRecords;
	}
    
    
    
}
