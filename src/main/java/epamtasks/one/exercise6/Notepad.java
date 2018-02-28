package epamtasks.one.exercise6;

/**
 * Notepad - class container for massive of NotepadRecords
 */

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
	
	

	
	public Notepad addRecord(String record) {
		if(lastRecord == (size-1)) {
			NotepadRecord[] newInstance = resizeNotepad(this);
			this.records = copyRecordsFromTo(this,newInstance);

		}
		
		this.records[lastRecord+1]= new NotepadRecord(record);
		lastRecord++;
		System.out.printf("Add new record without id %n");

		return this;
	}

    public Notepad addRecord(String record,int id) {
        if(lastRecord == (size-1)) {
            NotepadRecord[] newInstance = resizeNotepad(this);
            this.records = copyRecordsFromTo(this,newInstance);
        }

        this.records[lastRecord+1]= new NotepadRecord(record,id);
        lastRecord++;
        System.out.printf("Add new record without id %n");

        return this;
    }

    private static NotepadRecord[] copyRecordsFromTo(Notepad source,NotepadRecord[] dest){
	    if(source.size > dest.length){return new NotepadRecord[0];}
	    else{
            for (int i=0; i<= source.lastRecord;i++) {
                dest[i] = source.records[i];
            }
            return dest;
        }
    }
	private static NotepadRecord[] resizeNotepad(Notepad notepad) {
	    int newSize = notepad.lastRecord*2;
	    notepad.size = newSize;
		return new NotepadRecord[newSize];
	}
	
	public void printAllRecords() {
		for(NotepadRecord record:this.records)
			record.printRecord();
	}
	
	public boolean removeFromPosition(int position) {
		if(position > (this.size -1) || position < 0) return false;
		if(position > this.lastRecord) return true;
		
		for(int currentPosition = position; currentPosition<this.lastRecord;currentPosition++)
			this.records[currentPosition]=this.records[currentPosition+1];
		//System.arraycopy(this.records,position+1,this.records,position,this.lastRecord-position);
		//TODO ask about last position(links to null or new Instance[0])
		this.records[this.lastRecord] = new NotepadRecord();
		return true;
	}
	
	public boolean removeRecordWithId(int id) {
		int position = this.findRecordWithId(id);
		if(position < 0) return false;
		
		return this.removeFromPosition(position);
	}
	
	public int findRecordWithId(int id) {
		int position = -1;
		
		for(NotepadRecord record:this.records) {
			position++;
			if(record.getId() == id ) return position;
		}
		return -1;
	}
	
	//TODO addRecord(+)
    //TODO ask about void returns is better return links at itself ??
	//TODO deleteRecord(+)
    //TODO ask about returninig type (boolean is ok ?)
	//TODO editRecord() ??? rewrite???
	//TODO showAllRecords(+)
	
}
