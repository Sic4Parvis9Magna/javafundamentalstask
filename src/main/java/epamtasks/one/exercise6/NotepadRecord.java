package epamtasks.one.exercise6;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.LogManager.*;

/**
 * NotepadRecord - class contains text strings named "record",
 * all records have own unique identifying number("Id").
 * Record's id starts with 1, because 0 is reserved for EMPTY .
 *
 */

public class NotepadRecord {
	private static final Logger log = getLogger(NotepadRecord.class);
	private String record;
	private final int id;
	private final static  NotepadRecord EMPTY;
	private static int lastId;
	
	 NotepadRecord() {
		record = "";
		id = lastId;
		 lastId++;

	}
	 NotepadRecord(String record) {
		this();
	 	this.record = record;

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
