package anabi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anabi.model.Funding;
import anabi.model.Record;

public class FundingServices {
	
	private Record keyRecord;
	private List<Funding> listFunding = new ArrayList<Funding>();
	private Funding funding;
	
	public FundingServices (){}
	
	
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

			if ( !( nameFundingFU.isEmpty() && descriptionFundingFX.isEmpty()) ) {

				funding = new Funding(nameFundingFU,descriptionFundingFX, keyRecord);

				listFunding.add(funding);

			} else {
				System.out.println("Organizacion financiera vacia");
			}

		} catch (Exception e) {
			System.out.println("No se encuentra los campos de organizacion financiera");
		}
	}


	public List<Funding> getListFunding(){
		return listFunding;
	}
	
	
	public Funding getFunding(Record keyrecord){

		Funding result = null;
		Integer idRow = 0;
		String idDocument = "";

		for (Funding objFunding : listFunding ){

			idRow = objFunding.getRecord().getIDRecord();
			idDocument = objFunding.getRecord().getCodDocument();

			if ( (idRow.equals(keyrecord.getIDRecord())) && (idDocument.equals(keyrecord.getCodDocument())) ) {
				result = objFunding;
				System.out.println("Organizacion financiera encontrada");
			} 
		}
		return result;
	}

	
	public Integer countFundings(){
		return listFunding.size();
	}
}
