package anabi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import anabi.model.Journal;
import anabi.model.Publisher;
import anabi.model.Record;

public class PublisherServices {

	private Record keyRecord;
	private Publisher publisher;
	private JournalServices journalServi = new JournalServices();
	private List<Publisher> listPublisher = new ArrayList<Publisher>();


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

		if (!( namePublisherPU.isEmpty() && addressPublisherPA.isEmpty() && cityPublisherPI.isEmpty()) ) {

			Journal currentJournal = new Journal();
			currentJournal = journalServi.getJournal(keyRecord);

			publisher = new Publisher(namePublisherPU, cityPublisherPI, addressPublisherPA, currentJournal , keyRecord);

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


}
