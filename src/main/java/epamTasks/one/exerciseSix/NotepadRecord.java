package epamTasks.one.exerciseSix;

public class NotepadRecord {
	private String record;
	private int id;
	
	public NotepadRecord() {
		setRecord("");
		id =-1;
	}
	public NotepadRecord(String record) {
		this.setRecord(record);
		id = -1;
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
		System.out.printf("Record id%d : %s\n",record);
	}
}
