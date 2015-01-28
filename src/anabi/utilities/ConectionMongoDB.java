package anabi.utilities;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;


public class ConectionMongoDB {

	private MongoClient conection = null;

	private static ConectionMongoDB instance = null;

	protected ConectionMongoDB(){

	}

	public static ConectionMongoDB getInstances(){

		if (instance == null){
			instance = new ConectionMongoDB();
		}
		return instance;
	}

	public void startConectionDB() throws UnknownHostException{

			conection = new MongoClient("localhost",27017);
	}

	public void startConectionDB(String host){
		
		try {
			conection = new MongoClient(host);
		} catch (UnknownHostException uke) {
			
			System.out.println("Error el host"+ host+" no es valido"); 
		}	
	}
	
	
	public MongoClient getConection(){
		
		return conection;
	}
	
	
	public void closeConectionDB(){
		conection.close();
	}
	
}
