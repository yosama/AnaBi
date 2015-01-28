
package anabi.models;


/**
 *
 * @author yosamac 
 */

public class Publisher  {

    public Record record;
    private Integer codPublisher;
    private String namePu;
    private String addressPa;
    private String cityPi;
    

    public Publisher() {
    }

   

    public Publisher(Integer codPublisher,String namePu, String addressPa, String cityPi, Record record) {
		super();
		this.codPublisher = codPublisher;
		this.record = record;
		this.namePu = namePu;
		this.addressPa = addressPa;
		this.cityPi = cityPi;
		
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
    
    public Record getRecord(){
    	return record;
    }
}
