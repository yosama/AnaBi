package anabi.models;

import java.util.List;



public class Record {
	
	private Integer idRecord;
	private String codDocument;
	private List<Object> listObject;
	
	
	
	
	
	public Record(Integer idRecord, String codDocument) {
		super();
		this.idRecord = idRecord;
		this.codDocument = codDocument;
	}

	public Integer getIDRecord(){
		return idRecord;
	}
	
	public void setIDRecord(Integer idRecord){
		this.idRecord = idRecord;
		
	}
	
	public String getCodDocument(){
		return codDocument;
	}
	
	public void setCodDocument(String codDocument){
		this.codDocument = codDocument;
		
	}
	
	public boolean searchElement (String element){
		
		if ( listObject.contains(element) ){
			return true;
		} else{
			return false;
		}
	}

}
