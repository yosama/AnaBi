package anabi.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anabi.models.Author;
import anabi.models.Record;
import anabi.utilities.ConnectionDB;
import anabi.utilities.InitServices;

public class AuthorServices {

	private Record keyRecord;
	private Author author;
	private Integer codAuthor = 0;
	private InitServices iniServices;
	private ConnectionDB connDB;
	private String sql;
	private List<Author> listAuthor = new ArrayList<Author>();

	public AuthorServices(){
		iniServices  = iniServices.getInstances();
	}


	/**
	 * Crea lista de autores.
	 *
	 * @param record registro de datos del fichero
	 * @throws SQLException 
	 */
	public void setListAuthor(HashMap<String, String> record, Record key)  {
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
				String emailRP;
				if (nameAuthorRP.equals(autorArr)) {
					codAuthorRP = "Autor Corresponsal";
					emailRP = email;
					author.setAuthorRp(codAuthorRP);
					author.setEmailEm(email);
				} else {
					codAuthorRP = "CoAutor";
					emailRP ="";
					author.setAuthorRp(codAuthorRP);
				}

				listAuthor.add(author);

				addAuthor(codAuthor, listNamesAU[i], listNameAF[i],emailRP,codAuthorRP);

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

	
	
	// Return an  IDs authors list, receive as parameter an names authors list
	public List<Integer> buildCodAuthorsList( List<String> nameFNAuthorsList){

		List <Integer> listBuildedAuthors = new ArrayList<Integer>();

		for ( String authorNameFN: nameFNAuthorsList ){

			findAuthorByName(authorNameFN);
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


	public List<Integer> getCodAuthorList (Record aKey){

		List<Integer> result = new ArrayList<Integer>();
		Integer idRow = 0;
		String idDocument = "";


		for (Author objAuthor : listAuthor ){

			idRow = objAuthor.getRecord().getIDRecord();
			idDocument = objAuthor.getRecord().getCodDocument();

			if ( (idRow.equals(aKey.getIDRecord())) && (idDocument.equals(aKey.getCodDocument())) ) {
				result.add(objAuthor.getCodAuthor());
			} 
		}
		return result;
	}

	
	// Return a list authors. Receive as parameter a the document's key  
	public List<Author> getAuthorList (Record aKey){

		List<Author> result = new ArrayList<Author>();
		Integer idRow = 0;
		String idDocument = "";

		for (Author objAuthor : listAuthor ){

			idRow = objAuthor.getRecord().getIDRecord();
			idDocument = objAuthor.getRecord().getCodDocument();

			if ( (idRow.equals(aKey.getIDRecord())) && (idDocument.equals(aKey.getCodDocument())) ) {
				result.add(objAuthor);
			} 
		}
		return result;
	}


	public Integer countAuthors(){
		return listAuthor.size();
	}

	
	// Return all authors names of the local list
	public List<String> getAuthorsNamesAllOfList() {
		List<String> listResult = new ArrayList<String>();

		for(Author objDocument : listAuthor) {
			listResult.add(objDocument.getNameAu());
		}
		return listResult;
	} 


	
	// Return all authors of local list
	public List<Author> getAuthorsList (List<Integer> codAuthorsList) {
		List<Author> result = new ArrayList<Author>();

		for (Integer idAuthor : codAuthorsList) {
			Author author = findAuthorByIdOfList(idAuthor);
			result.add(author);
		}
		return result;
	}


	
	// Find an author by your name, receive as parameter an author name
	public Author findAuthorByNameOfList (String  aNameAuthor) {

		aNameAuthor = aNameAuthor.trim();
		Author result = null;
		boolean founded = false;

		for ( int i = 0; i < listAuthor.size() && !founded ; i++ ) {
			String nameAuthor = listAuthor.get(i).getNameAu().trim();

			if ( nameAuthor.equals(aNameAuthor) ) {
				result = listAuthor.get(i);
				founded = true;
			}
		}
		return result;
	}


	
	// Find an author by your ID, receive as parameter a code author name
	public Author findAuthorByIdOfList(Integer aCodAuthor) {

		Author result = null;
		boolean founded = false;

		for ( int i = 0; i < listAuthor.size() && !founded ; i++ ) {
			Integer idAuthor = listAuthor.get(i).getCodAuthor();
			if ( idAuthor == aCodAuthor ) {
				result = listAuthor.get(i);
				founded = true;
			}
		}
		return result;
	}

	
	
	// Return an authors's names list, receive as parameter an authors list
	public List<String> getAuthorsNamesOfList(List<Author> listAuthors) {
		List<String> result = new ArrayList<String>();
		for (Author objAuthor : listAuthors) {
			Author author = findAuthorByIdOfList(objAuthor.getCodAuthor());
			result.add(author.getNameAu());
		}
		return result;
	}



	// Inserting an author in database
	public void addAuthor(Integer codAuthor, String nameAuthor, String nameFullAuthor,String email,String authorRP) {
		sql = "";
		sql = "INSERT INTO author VALUES (\""+codAuthor+"\",\""+nameAuthor+"\",\""+nameFullAuthor+"\",\""+email+"\",\""+authorRP+"\")";
		connDB = iniServices.getDB();
		connDB.runSql(sql);

	}

	
	
	// delete all authors of the database;
	public void deleteAllAuthor (){
		sql = "";
		sql = "DELETE FROM author";
		connDB = iniServices.getDB();
		connDB.runSql(sql);
	}

	
	
	// Delete an author specific, receive as parameter the author's Id of the database
	public void deleteAuthor(Integer idAuthor){

		sql = "";
		sql = "DELETE FROM author WHERE cod_author="+idAuthor;
		connDB.runSql(sql);
	}

	
	
	// Find an author by your name on the database, receive as parameter an author name 
	public Author findAuthorByName (String nameAuthor){

		author = null;
		sql = "";
		sql = "SELECT * FROM author WHERE name_au=\""+nameAuthor+"\"";

		ResultSet rs = connDB.runSql(sql);

		try {
			while( rs.next()){
				author = new Author();
				author.setCodAuthor(rs.getInt("cod_author"));
				author.setNameFn(rs.getString("name_fn"));
				author.setEmailEm(rs.getString("email_em"));
				author.setAuthorRp(rs.getString("author_rp"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return author;
	}

}
