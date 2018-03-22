package epamtasks.one.exercise6;

import lombok.extern.log4j.Log4j2;

/**
 * Notepad - class contains an array of NotepadRecords (@see  epamtasks.one.exercise6.NotepadRecord)
 * and a quick information about array like as size and last added element("lastRecord").
 *
 */
@Log4j2
public class Notepad {


	private NotepadRecord[] records;
	private int size;
	private int lastRecord;

	
	Notepad(){
		size =10;
		records = new NotepadRecord[size];
		lastRecord = -1;
	}
	Notepad(int size) {
		this.size =size;
		records = new NotepadRecord[this.size];
		lastRecord = -1;
	}
	Notepad(Notepad notepad) {
		this(notepad.size);
		for(NotepadRecord rec:notepad.records)
			this.addRecord(rec.getRecord());
	}

	/**
	 * Adds new record to this instance of Notepad.
	 * @param message a text content of new record(@see  epamtasks.one.exercise6.NotepadRecord),
	 *                   that push into Notepad container as last record
	 *                   (@see  epamtasks.one.exercise6.Notepad)
	 * @return an changed instance of Notepad
	 */
	 Notepad addRecord(String message) {
		if(lastRecord == (size-1)) {
			NotepadRecord[] newInstance = resizeNotepad(this);
			this.records = copyRecordsFromTo(this,newInstance);

		}
		
		this.records[lastRecord+1]= new NotepadRecord(message);
		lastRecord++;
		return this;
	}

	/**
	 * Copies content from "source" (@see epamtasks.one.exercise6.Notepad #field: records) to "dest"
	 * array of NotepadRecords(@see  epamtasks.one.exercise6.NotepadRecord).
	 * @param source a source container of copying data
	 * @param dest a container - destination for copying records (@see  epamtasks.one.exercise6.NotepadRecord)
	 * @return an array of NotepadRecord(@see  epamtasks.one.exercise6.NotepadRecord)
	 */
    private static NotepadRecord[] copyRecordsFromTo(Notepad source,NotepadRecord[] dest){
	    if(source.size > dest.length){return new NotepadRecord[0];}
	    else{
            for (int i=0; i<= source.lastRecord;i++) {
                dest[i] = source.records[i];
            }
            return dest;
        }
    }

	/**
	 * Returns an array of type Notepad Record which have twice size than argument's field named "records"
	 * (@see  epamtasks.one.exercise6.Notepad).
	 * @param notepad an instance of Notepad(@see epamtasks.one.exercise6.Notepad #field: size)
	 *                   for determining size of returning array
	 * @return an array of type NotepadRecord that have twice size than argument's field named "records"
	 */
	private static NotepadRecord[] resizeNotepad(Notepad notepad) {
	    int newSize = notepad.lastRecord*2;
	    notepad.size = newSize;
		return new NotepadRecord[newSize];
	}

	/**
	 * Removes record with an index "position".
	 * @param position an array's  index,about the array
	 *                    (@see epamtasks.one.exercise6.Notepad #field: records)
	 * @return true if a remove is done else  false
	 */
	boolean removeFromPosition(int position) {
		if(position > (this.size -1) || position < 0) return false;
		if(position > this.lastRecord) return true;
		
		for(int currentPosition = position; currentPosition<this.lastRecord;currentPosition++) {
			this.records[currentPosition] = this.records[currentPosition + 1];
		}

		this.records[this.lastRecord] = NotepadRecord.getEmptyRecord();
		return true;
	}

	/**
	 * Removes record with an identifying number "id" (@see epamtasks.one.exercise6.NotepadRecords #field: id).
	 * @param id an identifying number
	 * @return true if a remove is done else  false
	 */
	boolean removeRecordWithId(int id) {
		int position = this.findRecordWithId(id);

		return this.removeFromPosition(position);
	}

	/**
	 * Founds a position of record with certain identifying  number "id"
	 * (@see epamtasks.one.exercise6.NotepadRecords #field: id).
	 * @param id an identifying  number of record (@see epamtasks.one.exercise6.NotepadRecords #field: record)
	 * @return a position of record with certain identical number "id",
	 * or -1 if record with this id isn't present into this Notepad
	 * (@see  epamtasks.one.exercise6.Notepad)
	 */
	int findRecordWithId(int id) {
		//int position = -1;
		
		for(int i=0; i<=lastRecord; i++) {
			//position++;
			if(records[i].getId() == id ) return i;
		}
		return -1;
	}

	/**
	 * Appends a text from "message" argument to "record" (@see epamtasks.one.exercise6.NotepadRecords #field: record)
	 * with an index "position".
	 * @param message a text for appending
	 * @param recordPosition an array's  index,about the array
	 *                         (@see epamtasks.one.exercise6.Notepad #field: records)
	 * @return true if an appending is successful, else false
	 */
	boolean appendToRecordOnPosition(String message,int recordPosition){
		if(recordPosition < 0 || recordPosition >= size) return false;
		StringBuilder oldMessage = new StringBuilder(this.records[recordPosition].getRecord());
		oldMessage.append(message);
		this.records[recordPosition].setRecord(oldMessage.toString());
		return true;
	}

	/**
	 * Appends a text from "message" argument to "record "(@see epamtasks.one.exercise6.NotepadRecords #field: record)
     * with certain identifying  number "id".
	 * @param message a text for appending
	 * @param id an identifying  number of "record"
	 * @return true if an appending is successful, else false
	 */
	boolean appendToRecordWithId(String message,int id){
		int recordPosition = this.findRecordWithId(id);
		return this.appendToRecordOnPosition(message,recordPosition);
	}

    /**
     * Removes a previous content of "record" (@see epamtasks.one.exercise6.NotepadRecords #field: record)
     * with an index "position" and adds text from "message" argument to "record".
     * @param message a text for rewriting
     * @param recordPosition an array's  index,about the array
     *                         (@see epamtasks.one.exercise6.Notepad #field: records)
     * @return true if an rewrite is successful, else false
     */
	boolean rewriteRecordOnPosition(String message,int recordPosition){
		if(recordPosition < 0 || recordPosition >= size) return false;
		this.records[recordPosition].setRecord(message);
		return true;
	}

    /**
     * Removes previous content of "record" (@see epamtasks.one.exercise6.NotepadRecords #field: record)
     * with certain identifying number "id"and adds text from "message" argument to "record".
     * @param message a text for rewriting
     * @param id an identifying number of "record"
     * @return true if an rewrite is successful, else false
     */
	boolean rewriteRecordwithId(String message, int id){
		int recordPosition = this.findRecordWithId(id);
		return rewriteRecordOnPosition(message,recordPosition);
	}

    /**
     * Print content of all "records" in format "Record id{id} : {content}\n",
     * about array of records (@see epamtasks.one.exercise6.Notepad #field: records).
     */
	void printAllRecords() {
		for(NotepadRecord rec:this.records)
			rec.printRecord();
	}


	public int getRecordId(String s) {

		for (int i=0; i <= lastRecord; i++){

			 if(records[i].getRecord().compareTo(s) == 0){
			 	return  records[i].getId();
			 }
		}
		return -1;
	}

	public String getRecordWithId(int id) {
		int position = findRecordWithId(id);
		if(position != -1) return records[position].getRecord();
		return "NOT_FOUND";
	}

	public String getRecordOnPosition(int position){
		if(position < 0 || position > lastRecord) {
			return "EMPTY";
		}else {
			return records[position].getRecord();
		}
	}

	public int getRecordPosition(String s) {
		for (int i=0; i <= lastRecord;i++){
			if(records[i].getRecord().equalsIgnoreCase(s)) return i;
		}
		return -1;
	}
}
