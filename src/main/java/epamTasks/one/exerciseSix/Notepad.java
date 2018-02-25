package epamTasks.one.exerciseSix;

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
	
	
	//TODO ask about void returns is better return links at itself ??
	
	public Notepad addRecord(String record) {
		if(lastRecord == (size-1)) {
			Notepad newInstance = resizeNotepad(this); 
			for(NotepadRecord rec:this.records)
				newInstance.addRecord(rec.getRecord());
		}
		
		this.records[lastRecord+1]=new NotepadRecord(record);
		lastRecord++;
		return this;
	}

	private Notepad resizeNotepad(Notepad notepad) {
		return new Notepad(notepad.lastRecord*2);
	}
	
	public void printAllRecords() {
		for(NotepadRecord record:this.records)
			record.printRecord();
	}
	
	public boolean removeFromPosition(int position) {
		if(position > (this.size -1) || position < 0) return false;
		else if(position > this.lastRecord) return true;
		
		for(int currentPosition = position; currentPosition<this.lastRecord;currentPosition++)
			this.records[currentPosition]=this.records[currentPosition+1];
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
	
	//TODO addRecord()
	//TODO deleteRecord()
	//TODO editRecord() ??? rewrite???
	//TODO showAllRecords()
	
}
