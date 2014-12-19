
package anabi.model;


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
    private List<Integer> listAuthors;
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
        this.listAuthors = new ArrayList<Integer>();
        
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

    
    public List<Integer> getCodAuthorList() {
        return listAuthors;
    }

    public void setCodAuthorList(List<Integer> authorList) {
        this.listAuthors = authorList;
    }

    
    public List<Record> getListRecord() {
		return listRecord;
	}


	public void setListRecord(List<Record> keyRecords) {
		this.listRecord = keyRecords;
	}
    
    
    
}
