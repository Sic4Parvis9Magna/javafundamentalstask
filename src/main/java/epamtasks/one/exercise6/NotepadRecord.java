package epamtasks.one.exercise6;

import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.LogManager.*;

/**
 * NotepadRecord - class contains text strings named "record",
 * all records have own unique identifying number("Id").
 *
 */
public class NotepadRecord {
	private static final Logger log = getLogger(NotepadRecord.class);
	private String record;
	private int id;
	private final static  NotepadRecord EMPTY;
	private static int lastId;
	
	 NotepadRecord() {
		record = "";
		id = lastId++;
	}
	 NotepadRecord(String record) {
		this.record = record;
		id =lastId++;
	}


	 String getRecord() {
		return record;
	}

	 int getId() {
		return id;
	}


	 void setRecord(String record) {
		this.record = record;
	}

	/**
	 * Print content of a "record" in format "Record id{id} : {content}\n",
	 */
	 void printRecord() {
		log.info("Record id {} : {}",id,record);
	}

	 static{
		EMPTY = new NotepadRecord();
		EMPTY.setRecord("EMPTY");

	}

	/**
	 * Returns  instance of static constant, this object have empty record  content.
	 * @return  instance of class with no content (in record) with default id = 0
	 */
	 static NotepadRecord getEmptyRecord(){
	 	return EMPTY;
	}

}
