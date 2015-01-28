package anabi.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import anabi.models.TypePublication;
import anabi.utilities.ConnectionDB;
import anabi.utilities.InitServices;

public class TypePublicationServices {

	private InitServices iniServices;
	private ConnectionDB connDB;
	private String sql ;
	private TypePublication typePublication;


	public TypePublicationServices(){
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

		typePublication = null;
		nameType = nameType.trim();
		sql = "";
		sql = "SELECT * FROM publication_type WHERE name_document_type="+nameType;

		ResultSet rs = connDB.runSql(sql);

		try {
			while( rs.next()){
				typePublication = new TypePublication();
				typePublication.setCodCDocument(rs.getInt("cod_publication_type"));
				typePublication.setNameCDocument(rs.getString("name_publication_type"));
				typePublication.setDescription(rs.getString("description"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return typePublication;
	}
}
