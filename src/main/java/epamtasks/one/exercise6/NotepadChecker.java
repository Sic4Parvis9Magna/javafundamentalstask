package epamtasks.one.exercise6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * NotepadChecker - class for technical check of functionality Notepad
 * (@see  epamtasks.one.exercise6.Notepad)
 * and NoteradRecord (@see epamtasks.one.exercise6.NotepadRecords) classes.
 * For tests( @see epamtasks.one.exercise6.NotepadTest)
 */
public class NotepadChecker {

	private static final Logger log = LogManager.getLogger(NotepadChecker.class);

	public static void main(String[] args) {
		Notepad myNote = new Notepad(3);
		myNote.addRecord("first note");
		myNote.addRecord("second record");
		myNote.addRecord("third record");
		myNote.addRecord("resize array");
		myNote.addRecord("Cheby");
		myNote.addRecord("Gen");
		myNote.printAllRecords();
		/*
		log.info("rewrite rec with id  {} works? {}",
				2,myNote.rewriteRecordwithId(" new message",2));
		log.info("rewrite rec rec with id {} works? {}",
				-1,myNote.rewriteRecordwithId(" some str2",-1));
		log.info("rewrite rec rec with id  {} works? {}",
				10,myNote.rewriteRecordwithId(" some str3",10));
		log.info("rewrite rec rec with id  {} works? {}",
				4,myNote.rewriteRecordwithId(" some str4",4));


		log.info("rewrite onto position {} works? {}",
				2,myNote.rewriteRecordOnPosition("new message",2));
		log.info("rewrite onto position {} works? {}",
				-1,myNote.rewriteRecordOnPosition("some str2",-1));
		log.info("rewrite onto position {} works? {}",
				10,myNote.rewriteRecordOnPosition("some str3",10));
		log.info("rewrite onto position {} works? {}",
				4,myNote.rewriteRecordOnPosition("some str4",4));


		/*
		log.info("append to rec with id  {} works? {}",
				2,myNote.appendToRecordWithId(" new message",2));
		log.info("append to rec with id {} works? {}",
				-1,myNote.appendToRecordWithId(" some str2",-1));
		log.info("append to rec with id  {} works? {}",
				10,myNote.appendToRecordWithId(" some str3",10));
		log.info("append to rec with id  {} works? {}",
				4,myNote.appendToRecordWithId(" some str4",4));


		log.info("append to position {} works? {}",
				2,myNote.appendToRecordOnPosition("new message",2));
		log.info("appent to position {} works? {}",
				-1,myNote.appendToRecordOnPosition("some str2",-1));
		log.info("append to position {} works? {}",
				10,myNote.appendToRecordOnPosition("some str3",10));
		log.info("append to position {} works? {}",
				4,myNote.appendToRecordOnPosition("some str4",4));

		log.info("Record with id -5 onto {} position ",myNote.findRecordWithId(-5));
		log.info("Record with id 0 onto {} position ",myNote.findRecordWithId(0));
		log.info("Record with id 3 onto {} position ",myNote.findRecordWithId(3));

		log.info("remove from position {} is {} ",-5,myNote.removeFromPosition(-5));
		log.info("remove from position {} is {} ",3,myNote.removeFromPosition(3));
		log.info("remove from position {} is {} ",10,myNote.removeFromPosition(10));
*/

		log.info("remove with id#{} is {} ",-5,myNote.removeRecordWithId(-5));
		log.info("remove with id#{} is {} ",3,myNote.removeRecordWithId(3));
		log.info("remove with id#{} is {} ",1,myNote.removeRecordWithId(1));
		log.info("remove with id#{} is {} ",100,myNote.removeRecordWithId(100));
		myNote.printAllRecords();
	}

}
