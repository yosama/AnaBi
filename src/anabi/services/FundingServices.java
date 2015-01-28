package anabi.services;

import java.nio.charset.CodingErrorAction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anabi.models.Funding;
import anabi.models.Record;
import anabi.utilities.ConnectionDB;
import anabi.utilities.InitServices;

public class FundingServices {

	private Record keyRecord;
	private Funding funding;
	private Integer codFunding;
	private InitServices iniServices;
	private ConnectionDB connDB;
	private String sql ;
	private List<Funding> listFunding;


	public FundingServices (){

		iniServices  = iniServices.getInstances();
		listFunding = new ArrayList<Funding>();
		codFunding = 0;
	}


	/**
	 * Carga las organizaciones que financia el proyecto.
	 *
	 * @param record
	 */
	public void setListFunding(HashMap<String, String> record, Record key) {
		this.keyRecord = key;

		String nameFundingFU = "";
		String descriptionFundingFX = "";

		try {

			nameFundingFU = record.get("FU");
			descriptionFundingFX = record.get("FX");
			
			if ( nameFundingFU.isEmpty() && descriptionFundingFX.isEmpty() ) {
				
				System.out.println("Organizacion financiera vacia");
			} else {
				System.out.println( nameFundingFU +" ***DESCRIPTION: "+descriptionFundingFX );
				codFunding +=1;
				addFunding(codFunding, nameFundingFU, descriptionFundingFX);
				
				funding = new Funding(codFunding,nameFundingFU,descriptionFundingFX, keyRecord);
				listFunding.add(funding);
			}

		} catch (Exception e) {
		e.printStackTrace();
			System.out.println("No se encuentra los campos de organizacion financiera");
		}
	}


	public List<Funding> getListFunding(){
		return listFunding;
	}


	public Integer getCodFunding(Record keyrecord){

		Integer resultCodFunding = null;
		Integer idRow = 0;
		String idDocument = "";

		for (Funding objFunding : listFunding ){

			idRow = objFunding.getRecord().getIDRecord();
			idDocument = objFunding.getRecord().getCodDocument();

			if ( (idRow.equals(keyrecord.getIDRecord())) && (idDocument.equals(keyrecord.getCodDocument())) ) {
				resultCodFunding = objFunding.getCodFunding();
				System.out.println("Organizacion financiera encontrada");
			} 
		}
		return resultCodFunding;
	}


	public Integer countFundings(){
		return listFunding.size();
	}  

	public void addFunding (Integer idFunding,String nameFu,String descriptionFx){

		sql = "";
		sql = "INSERT INTO funding VALUES ("+idFunding+",\'"+nameFu+"\',\'"+descriptionFx+"\')";
		System.out.println(descriptionFx.length());
		connDB = iniServices.getDB();
		connDB.runSql(sql);
	}


	public void deleteAll (){
		sql = "";
		sql = "DELETE FROM funding";
		connDB = iniServices.getDB();
		connDB.runSql(sql);
	}

	public void deleteFunding(Integer idFunding){

		sql = "";
		sql = "DELETE FROM funding WHERE cod_funding="+idFunding;
		connDB = iniServices.getDB();
		connDB.runSql(sql);
	}


	public Funding findByName (String nameFunding){

		funding = null;
		nameFunding = nameFunding.trim();
		sql = "";
		sql = "SELECT * FROM funding WHERE name_fu='"+nameFunding+"'";
		connDB = iniServices.getDB();
		ResultSet rs = connDB.runSql(sql);
		if (rs != null){
			try {
				while( rs.next()){
					funding = new Funding();
					funding.setCodFunding(rs.getInt("cod_funding"));
					funding.setNameFu(rs.getString("name_fu"));
					funding.setDescriptionFx(rs.getString("description_fx"));
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
			return funding;
		}

	}
