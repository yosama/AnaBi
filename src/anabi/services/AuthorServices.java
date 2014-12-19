package anabi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import anabi.model.Author;
import anabi.model.Record;

public class AuthorServices {

	private Record keyRecord;
	private Author author;
	private Integer codAuthor = 0;
	private List<Author> listAuthor = new ArrayList<Author>();

	public AuthorServices(){}


	/**
	 * Crea lista de autores.
	 *
	 * @param record registro de datos del fichero
	 */
	public void setListAuthor(HashMap<String, String> record, Record key) {
		this.keyRecord = key;

		String[] listNamesAU;
		String [] listNameAF;
		String email;
		String authorRP;
		String nameAuthorRP = "";
		String[] dataAuthorRP;
		String codAuthorRP; // codAuthorRP = 1, Autor corresponsal

		listNamesAU = record.get("AU").split("\n");
		listNameAF = record.get("AF").split("\n");
		email = record.get("EM");
		authorRP = record.get("RP");

		try {
			dataAuthorRP = authorRP.split("(reprint author)");
			nameAuthorRP= dataAuthorRP[0].trim();
		} catch (NullPointerException npe){
			System.out.println("No existe  autor Corresponsal:" );
		}

		if (nameAuthorRP.length() > 0) {
			nameAuthorRP = nameAuthorRP.substring(0, nameAuthorRP.length() - 2);
		} else{
			nameAuthorRP = "No autor corresponsal";
		}

		if (listNamesAU.length >= 1) {

			for (int i = 0; i < listNamesAU.length; i++) {
				codAuthor += 1; 
				author = new Author(codAuthor,listNamesAU[i], listNameAF[i], keyRecord);
				String autorArr = listNamesAU[i].trim();

				if (nameAuthorRP.equals(autorArr)) {
					codAuthorRP = "Autor Corresponsal";
					author.setAuthorRp(codAuthorRP);
					author.setEmailEm(email);
				} else {
					codAuthorRP = "CoAutor";
					author.setAuthorRp(codAuthorRP);
				}

				listAuthor.add(author);
			}
		}

	}


	public List<Author> getAllAuthor(){
		return listAuthor;
	}


	public List<Author> buildAuthorsList( List<String> authorListNameFN){

		List <Author> listBuildedAuthors = new ArrayList<Author>();

		for (String authorNameFN: authorListNameFN){

			authorNameFN = authorNameFN.trim();

			for ( int i = 0; i < listAuthor.size(); i++ ){

				String name = listAuthor.get(i).getNameFn().trim();

				if ( name.equals(authorNameFN) ){
					author = listAuthor.get(i);
					listBuildedAuthors.add(author);
					//System.out.println("Autor encontrado");
				} else{
					author = null;
					//System.out.println("Autor no encontrado");
				}		
			}

		}

		return listBuildedAuthors;	
	}
	
	public List<Integer> buildCodAuthorsList( List<String> nameFNAuthorsList){

		List <Integer> listBuildedAuthors = new ArrayList<Integer>();

		for ( String authorNameFN: nameFNAuthorsList ){

			findByName(authorNameFN);
			authorNameFN = authorNameFN.trim();

			for ( int i = 0; i < listAuthor.size(); i++ ){

				String name = listAuthor.get(i).getNameFn().trim();

				if ( name.equals(authorNameFN) ){
					author = listAuthor.get(i);
					listBuildedAuthors.add(author.getCodAuthor());
					//System.out.println("Autor encontrado");
				} else{
					author = null;
					//System.out.println("Autor no encontrado");
				}		
			}

		}

		return listBuildedAuthors;	
	}


	public List<Integer> getAuthorList (Record keyrecord){

		List<Integer> result = new ArrayList<Integer>();
		Integer idRow = 0;
		String idDocument = "";


		for (Author objAuthor : listAuthor ){

			idRow = objAuthor.getRecord().getIDRecord();
			idDocument = objAuthor.getRecord().getCodDocument();

			if ( (idRow.equals(keyrecord.getIDRecord())) && (idDocument.equals(keyrecord.getCodDocument())) ) {
				result.add(objAuthor.getCodAuthor());
			} 
		}
		return result;
	}


	public Integer countAuthors(){
		return listAuthor.size();
	}

	public List<String> getNamesAllAuthorsList(){
		List<String> listResult = new ArrayList<String>();

		for(Author objDocument : listAuthor){
			listResult.add(objDocument.getNameAu());
		}
		return listResult;
	} 


	public List<Author> getAuthorList (List<Integer> codAuthorsList){
		List<Author> result = new ArrayList<Author>();

		for (Integer idAuthor : codAuthorsList){
			Author author = findByIdAuthor(idAuthor);
			result.add(author);
		}
		return result;
	}


	public Author findByName(String  name){

		name = name.trim();
		Author result = null;
		boolean founded = false;

		for ( int i = 0; i < listAuthor.size() && !founded ; i++ ){
			String nameAuthor = listAuthor.get(i).getNameAu().trim();

			if ( nameAuthor.equals(name) ){
				result = listAuthor.get(i);
				founded = true;
			}
		}
		return result;
	}


	public Author findByIdAuthor(Integer codAuthor){

		Author result = null;
		boolean founded = false;

		for ( int i = 0; i < listAuthor.size() && !founded ; i++ ){
			Integer idAuthor = listAuthor.get(i).getCodAuthor();
			if ( idAuthor == codAuthor ){
				result = listAuthor.get(i);
				founded = true;
			}
		}
		return result;
	}

	public List<String> getNamesList(List<Author> listAuthors){
		List<String> result = new ArrayList<String>();
		for (Author objAuthor : listAuthors){
			Author author = findByIdAuthor(objAuthor.getCodAuthor());
			result.add(author.getNameAu());
		}
		return result;
	}


}
