package anabi.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import anabi.models.TypePublication;
import anabi.utilities.ConnectionDB;
import anabi.utilities.InitServices;

public class PublicationTypeServices {

	private InitServices iniServices;
	private ConnectionDB connDB;
	private String sql ;
	private TypePublication publicationType;


	public PublicationTypeServices(){
		iniServices = InitServices.getInstances();
		connDB  = iniServices.getDB();

	}


	public void addTypePublication(String name, String description){

		sql = "";
		sql = "INSERT INTO publication_type (name_publication_type,description) VALUES ("+name+","+description+")";
		connDB.runSql(sql);
	}


	public void deleteAllTypePublication (){
		sql = "";
		sql = "DELETE FROM publication_type";
		connDB.runSql(sql);
	}

	public void deleteTypePublication(Integer codTypePublication){

		sql = "";
		sql = "DELETE FROM publication_type WHERE cod_publication_type="+codTypePublication;
		connDB.runSql(sql);
	}


	public TypePublication findByName (String nameType){

		publicationType = null;
		nameType = nameType.trim();
		sql = "";
		sql = "SELECT * FROM publication_type WHERE name_document_type="+nameType;

		ResultSet rs = connDB.runSql(sql);

		try {
			while( rs.next()){
				publicationType = new TypePublication();
				publicationType.setCodCDocument(rs.getInt("cod_publication_type"));
				publicationType.setNameCDocument(rs.getString("name_publication_type"));
				publicationType.setDescription(rs.getString("description"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return publicationType;
	}
}
