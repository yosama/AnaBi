
package anabi.models;


/**
 *
 * @author yosamac
 */


public class Funding {

    private Record record;
    private Integer codFunding;
    private String nameFu;
    private String descriptionFx;
   
    
    public Funding() {

    }
    
    
    public Funding (Integer idFunding,String nameFu, String descriptionFx, Record record) {
		super();
		this.codFunding = idFunding ;
		this.nameFu = nameFu;
		this.descriptionFx = descriptionFx;
		this.record = record;
	}

    public Integer getCodFunding() {
        return codFunding;
    }

    public void setCodFunding(Integer codFunding) {
        this.codFunding = codFunding;
    }

    public String getNameFu() {
        return nameFu;
    }

    public void setNameFu(String nameFu) {
        this.nameFu = nameFu;
    }

    public String getDescriptionFx() {
        return descriptionFx;
    }

    public void setDescriptionFx(String descriptionFx) {
        this.descriptionFx = descriptionFx;
    }

    public Record getRecord(){
    	return record;
    }

}
