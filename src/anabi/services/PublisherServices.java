package anabi.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anabi.models.Publisher;
import anabi.models.Record;
import anabi.utilities.ConnectionDB;
import anabi.utilities.InitServices;

public class PublisherServices {

	private Record keyRecord;
	private Publisher publisher;
	private Integer codPublisher;
	private Integer codPublisherTemp;
	private InitServices iniServices ;
	private ConnectionDB connDB;
	private ResultSet rs;
	private String sql;

	private List<Publisher> listPublisher;


	public PublisherServices(){
		iniServices  = iniServices.getInstances();
		rs = null;
		listPublisher = new ArrayList<Publisher>();
		codPublisher = 0;
		codPublisherTemp = 0;
	}

	/**
	 * Carga la editorial de la revista.
	 *
	 * @param record
	 */
	public void setListPublisher(HashMap<String, String> record, Record key) {

		this.keyRecord = key;

		String namePublisherPU = "";
		String addressPublisherPA = "";
		String cityPublisherPI = "";

		namePublisherPU = record.get("PU");
		addressPublisherPA = record.get("PA");
		cityPublisherPI = record.get("PI");

		if ( namePublisherPU.isEmpty() && addressPublisherPA.isEmpty() && cityPublisherPI.isEmpty() ) {
			System.out.println("Publisher not found");
		} else {
			
			codPublisher += 1;
			addPublisher(codPublisher, namePublisherPU, addressPublisherPA, cityPublisherPI);
			publisher = new Publisher(codPublisher,namePublisherPU, cityPublisherPI, addressPublisherPA, keyRecord);
			listPublisher.add(publisher);

		} 

	}


	public List<Publisher> getListPublisher(){
		return listPublisher;	
	}


	public void setKeyRecord (Record keyRecord){
		this.keyRecord = keyRecord;
	}

	public Integer countPublishers(){

		return listPublisher.size();
	}


	public Integer getCodPublisher (Record keyrecord){

		Integer resultCodPublisher = null;
		Integer idRow = 0;
		String idDocument = "";

		for (Publisher objPublisher : listPublisher){

			idRow = objPublisher.getRecord().getIDRecord();
			idDocument = objPublisher.getRecord().getCodDocument();

			if ( (idRow.equals(keyrecord.getIDRecord())) && (idDocument.equals(keyrecord.getCodDocument())) ) {
				resultCodPublisher = objPublisher.getCodPublisher();
				System.out.println("Publisher finded");
			} else {
				//System.out.println("Revista no encontrada");
			}
		}
		return resultCodPublisher;
	}

	public void addPublisher(Integer aCodPublisher,String aNamePU, String addressPA, String cityPI){

		publisher = findByName(aNamePU,cityPI);
		sql = "";
		if (publisher == null){
			sql = "INSERT INTO publisher VALUES ("+aCodPublisher+",'"+aNamePU+"','"+addressPA+"','"+cityPI+"')";
			connDB.runSql(sql);
			codPublisherTemp = aCodPublisher;
		} else {
			codPublisherTemp = publisher.getCodPublisher();
		}
	}


	public Publisher findByName (String namePU,String cityPI){

		publisher = null;
		namePU = namePU.trim();
		cityPI = cityPI.trim();
		sql = "";
		sql = "SELECT * FROM publisher WHERE name_pu='"+namePU+"' AND city_pi='"+cityPI+"'";
		connDB = iniServices.getDB();
		rs = connDB.runSql(sql);
		if (rs != null){
			System.out.println("Editorial encontrada");
			try {
				while( rs.next()){
					publisher = new Publisher();
					publisher.setCodPublisher(rs.getInt("cod_publisher"));
					publisher.setNamePu(rs.getString("name_pu"));
					publisher.setAddressPa(rs.getString("address_pa"));
					publisher.setCityPi(rs.getString("city_pi"));
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

		return publisher;
	}

	public Integer getCodPublisher(){
		return codPublisherTemp;
	}

	public void deleteAllPublisher (){
		sql = "";
		sql = "DELETE FROM publisher";
		connDB = iniServices.getDB();
		connDB.runSql(sql);
	}

	public void deletePublisher(Integer idPublisher){

		sql = "";
		sql = "DELETE FROM publisher WHERE cod_publisher="+idPublisher;
		connDB = iniServices.getDB();
		connDB.runSql(sql);
	}

}
