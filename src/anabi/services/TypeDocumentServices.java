package anabi.services;



import java.sql.ResultSet;
import java.sql.SQLException;

import anabi.models.TypeDocument;
import anabi.utilities.ConnectionDB;
import anabi.utilities.InitServices;

public class TypeDocumentServices {

	private InitServices iniServices;
	private ConnectionDB connDB;
	private String sql ;
	private TypeDocument typeDocument;


	public TypeDocumentServices(){
		iniServices = InitServices.getInstances();
		connDB  = iniServices.getDB();

	}


	public void addDocumentType(String name, String description){

		sql = "";
		sql = "INSERT INTO document_type (name_document_type, description) VALUES ("+name+","+description+")";
		connDB.runSql(sql);
	}


	public void deleteAllTypeDocument (){
		sql = "";
		sql = "DELETE FROM document_type";
		connDB.runSql(sql);
	}

	public void deleteTypeDocument(Integer codTypeDocument){

		sql = "";
		sql = "DELETE FROM document_type WHERE cod_document_type="+codTypeDocument;
		connDB.runSql(sql);
	}


	public TypeDocument findByName (String nameType){

		typeDocument = null;
		nameType = nameType.trim();
		sql = "";
		sql = "SELECT * FROM document_type WHERE name_document_type="+nameType;

		ResultSet rs = connDB.runSql(sql);

		try {
			while( rs.next()){
				typeDocument = new TypeDocument();
				typeDocument.setCodTDocument(rs.getInt("cod_document_type"));
				typeDocument.setNameTDocument(rs.getString("name_document_type"));
				typeDocument.setDescription(rs.getString("description"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return typeDocument;
	}

}
