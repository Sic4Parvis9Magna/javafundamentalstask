package epamtasks.one.exercise6;

public class NotepadRecord {
	private String record;
	private int id;
	
	 NotepadRecord() {
		record = "";
		id = -1;
	}
	 NotepadRecord(String record) {
		this.record = record;
		id = -1;
	}
	 NotepadRecord(int id){
		record = "";
		this.id = id;
	}
	 NotepadRecord(String record, int id){
		this.record = record;
		this.id = id;
	}

	public String getRecord() {
		return record;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	
	public void printRecord() {
		System.out.printf("Record id %d : %s%n",id,record);
	}


}
