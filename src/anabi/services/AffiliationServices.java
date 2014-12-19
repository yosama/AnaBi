package anabi.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import anabi.model.Affiliation;
import anabi.model.Author;
import anabi.model.Document;
import anabi.model.Record;

public class AffiliationServices {

	private Record keyRecord;
	private Affiliation affiliation;
	private Integer codAffiliation = 0;
	private AuthorServices authorServi;
	private List<Affiliation> listAffiliation = new ArrayList<Affiliation>();
	private List<Integer> lisAuthor = new ArrayList<Integer>();


	public AffiliationServices(){}

	/** Extraer la afiliacion del record y adicionar a lista de afiliacion. 
	 * 
	 * @param record
	 */
	public void setListAffiliation(HashMap<String, String> record, Record key) {

		this.keyRecord = key;

		String nameAffiliation;
		String cityAffiliation;
		String countryAffiliation;

		String line = record.get("C1");

		try {
			BufferedReader br = new BufferedReader(new StringReader(line));
			InitServices iniServices = InitServices.getInstances();


			while ((line = br.readLine()) != null) {
				String[] dataCOne;
				List<String> listAuthorXAffiliation = new ArrayList<>();

				if (line.length() > 1) {
					if ( !(line.contains("[")) ){
						line = line.substring(0,0) + "]"+line.substring(0,1) + line.substring(1);
					}

					if (line.contains("]")) {

						String[] dataAffiliation = new String[line.split("]").length];
						dataCOne = line.split("]");
						String nameAuthorsAF = dataCOne[0].trim();
						if (nameAuthorsAF.length() > 0 && nameAuthorsAF.contains(";"))
							listAuthorXAffiliation = Arrays.asList(nameAuthorsAF.substring(1).split(";"));
						dataAffiliation = new String[dataCOne.length];
						dataAffiliation = dataCOne[1].split(",");

						if ( dataAffiliation.length > 2) {

							nameAffiliation = dataAffiliation[0];
							cityAffiliation = dataAffiliation[dataAffiliation.length - 2];
							countryAffiliation = dataAffiliation[dataAffiliation.length - 1];
						} else {
							dataAffiliation = dataCOne[1].split(" ");
							nameAffiliation = dataAffiliation[0];
							cityAffiliation = dataAffiliation[dataAffiliation.length - 2];
							countryAffiliation = dataAffiliation[dataAffiliation.length - 1];
						}


						if (listAffiliation.size() >= 1){
							affiliation = findByName(nameAffiliation);
						}

						// Adiciona los autores a la filiacion encontrada
						if ( affiliation != null ){
							List<Integer> listTempCodAuthors = affiliation.getCodAuthorList();						
							authorServi = iniServices.getAuthorServices();
							lisAuthor = authorServi.buildCodAuthorsList(listAuthorXAffiliation);
							listTempCodAuthors.addAll(lisAuthor);
							affiliation.setCodAuthorList(listTempCodAuthors);

							List<Record> listTempRecords = affiliation.getListRecord();
							listTempRecords.add(keyRecord);
							affiliation.setListRecord(listTempRecords);


						} else{
							// Crea una nueva filiacion
							codAffiliation +=1 ;
							affiliation = new Affiliation(codAffiliation, nameAffiliation, cityAffiliation,countryAffiliation, keyRecord);
							authorServi = iniServices.getAuthorServices();;
							lisAuthor = authorServi.buildCodAuthorsList(listAuthorXAffiliation);

							affiliation.setCodAuthorList(lisAuthor);
							listAffiliation.add(affiliation);
						}
					} 
				}
			}


		} catch (IOException e) {
			System.out.println("Problemas al cargar el ficheros");
		}catch (NullPointerException npe){
			System.out.println("Valor de linea: " + line);
		}

	}

	public List<Affiliation> getListAll(){
		return listAffiliation;
	}


	public Integer countAffiliations(){
		return listAffiliation.size();
	}

	public List<String> getListNameAffiliation(){
		List<String> listResult = new ArrayList<String>();

		for(Affiliation objAffiliation : listAffiliation){
			listResult.add(objAffiliation.getNameAffiliation());
		}
		return listResult;
	} 

	public List<Integer> getListAffiliation (Record keyrecord){

		List<Integer> result = new ArrayList<Integer>();

		Integer idRow = 0;
		String idDocument = "";

		for (Affiliation objAffiliation : listAffiliation ){

			List<Record> listRecord = objAffiliation.getListRecord();

			for (int i=0; i < listRecord.size(); i++){
				idRow = listRecord.get(i).getIDRecord();
				idDocument = listRecord.get(i).getCodDocument();

				if ( (idRow.equals(keyrecord.getIDRecord())) && (idDocument.equals(keyrecord.getCodDocument())) ) {
					result.add(objAffiliation.getCodAffiliation());
				} 
			}
		}
		return result;
	}




	public Affiliation findByName(String name){

		name = name.trim();
		Affiliation result = null;
		boolean founded = false;

		for ( int i = 0; i < listAffiliation.size() && !founded ; i++ ){
			String nameAffiliation = listAffiliation.get(i).getNameAffiliation().trim();

			if ( nameAffiliation.equals(name) ){
				result = listAffiliation.get(i);
				founded = true;

			}
		}
		return result;
	}

	public Affiliation findByIdAffiliation(Integer codAffiliation){

		Affiliation result = null;
		boolean founded = false;

		for ( int i = 0; i < listAffiliation.size() && !founded ; i++ ){
			Integer idAffiliation = listAffiliation.get(i).getCodAffiliation();

			if ( idAffiliation == codAffiliation ){
				result = listAffiliation.get(i);
				founded = true;
			}
		}
		return result;
	}


	public Integer getNumberAuthors (Integer codAffiliation){

		int result = 0;
		Affiliation objAffiliation = findByIdAffiliation(codAffiliation);

		if (objAffiliation != null){
			result = objAffiliation.getCodAuthorList().size();	
		}

		return result;
	}

	public List<Author> getListAuthor (Integer codAffiliation){

		List<Author> result = new ArrayList<Author>();
		Affiliation objAffiliation = findByIdAffiliation(codAffiliation);
		
		if (objAffiliation != null){
			 List<Integer> listIdAuthor = objAffiliation.getCodAuthorList();
			 result = authorServi.getAuthorList(listIdAuthor);
		}
		return result;
	} 


	public List<Author> getListAuthor (String nameAffiliation){

		List<Author> result = new ArrayList<Author>();
		nameAffiliation = nameAffiliation.trim();

		Affiliation objAffiliation = findByName(nameAffiliation);
		
		if (objAffiliation != null){
			 List<Integer> listIdAuthor = objAffiliation.getCodAuthorList();
			 result = authorServi.getAuthorList(listIdAuthor);
		}
		
		return result;
	}	


	public List<Affiliation> getListAffiliation (List<Integer> listCodAffiliations){
		List<Affiliation> result = new ArrayList<Affiliation>();

		for (Integer codAffiliation : listCodAffiliations){
			Affiliation objAffiliation = findByIdAffiliation(codAffiliation);
			result.add(objAffiliation);	
		}
		return result;
	}

	public List<String> getListName(List<Affiliation> listAffiliations){
		List<String> result = new ArrayList<String>();

		for (Affiliation objAffiliation : listAffiliations){
			result.add(objAffiliation.getNameAffiliation());
		}
		return result;
	}


}
