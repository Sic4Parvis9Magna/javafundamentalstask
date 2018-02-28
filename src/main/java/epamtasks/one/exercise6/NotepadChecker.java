package epamtasks.one.exercise6;



public class NotepadChecker {

	public static void main(String[] args) {
		Notepad myNote = new Notepad(3);
		myNote.addRecord("first note",1);
		myNote.addRecord("second record",2);
		myNote.addRecord("third record",3);
		myNote.addRecord("resize array",34);
		myNote.addRecord("Cheby",5);
		myNote.addRecord("Gen",56);
		myNote.printAllRecords();
		/*System.out.printf("Record with id 34 onto %d position %n",myNote.findRecordWithId(34));
		System.out.printf("Record with id 56 onto %d position %n",myNote.findRecordWithId(56));
		System.out.printf("Record with id 108 onto %d position %n",myNote.findRecordWithId(108));

		System.out.printf("remove is %b%n ",myNote.removeFromPosition(-5));
		System.out.printf("remove is %b%n ",myNote.removeFromPosition(3));
		System.out.printf("remove is %b%n After removin element list is:%n",myNote.removeFromPosition(10));
		*/

		System.out.printf("remove is %b%n ",myNote.removeRecordWithId(-5));
		System.out.printf("remove is %b%n ",myNote.removeRecordWithId(34));
		System.out.printf("remove is %b%n ",myNote.removeRecordWithId(1));
		System.out.printf("remove is %b%n ",myNote.removeRecordWithId(100));
		myNote.printAllRecords();
	}

}
