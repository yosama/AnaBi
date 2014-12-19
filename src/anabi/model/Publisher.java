
package anabi.model;


/**
 *
 * @author yosamac 
 */

public class Publisher  {

    public Record record;
    private Integer codPublisher = 0;
    private String namePu;
    private String addressPa;
    private String cityPi;
    private Journal journal;

    public Publisher() {
    }

   

    public Publisher(String namePu, String addressPa, String cityPi, Journal journal, Record record) {
		super();
		this.record = record;
		this.codPublisher =+ 1;
		this.namePu = namePu;
		this.addressPa = addressPa;
		this.cityPi = cityPi;
		this.journal = journal;
	}



	public Integer getCodPublisher() {
        return codPublisher;
    }

    public void setCodPublisher(Integer codPublisher) {
        this.codPublisher = codPublisher;
    }

    public String getNamePu() {
        return namePu;
    }

    public void setNamePu(String namePu) {
        this.namePu = namePu;
    }

    public String getAddressPa() {
        return addressPa;
    }

    public void setAddressPa(String addressPa) {
        this.addressPa = addressPa;
    }

    public String getCityPi() {
        return cityPi;
    }

    public void setCityPi(String cityPi) {
        this.cityPi = cityPi;
    }

   
    public Journal getJournal() {
        return journal;
    }

    public void setJournalList(Journal journal) {
        this.journal = journal;
    }

    public Record getRecord(){
    	return record;
    }
}
