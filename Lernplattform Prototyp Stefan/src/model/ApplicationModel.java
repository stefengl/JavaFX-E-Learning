package model;

import java.sql.*;
import java.util.ArrayList;

public class ApplicationModel {

	private Connection connection;

	public ApplicationModel() {
		SQLiteConnector connector = new SQLiteConnector();
		connection = connector.connector();
	}

	public boolean isConnected() {
		try {
			if (!connection.isClosed()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.print("No valid database connection");
		}
		return false;
	}
	
	
	// gibt eine ArrayListe aller Fächer zurück
	public ArrayList<String> getSubjects() { 
		System.out.println("getSubjects Methode wird gestartet");
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement preparedStatement;
		ResultSet resultSet;
		try {
			preparedStatement = connection.prepareStatement("Select fach from fach;");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("getSubjects Methode gescheitert");
			e.printStackTrace();
		}
		System.out.println("ArrayListe wird an Controller übergeben");
		return list;
	}

	// Gibt die erste Fragenart der Aufgabe des ersten Kapitels zurück
	public String getFirstFragenArt(String chosenSubject) { 
		String fragenart = "";
		PreparedStatement prepStmt;
		ResultSet resultSet;
		try {
			prepStmt = connection.prepareStatement("SELECT fragenart FROM aufgabe WHERE fach = ? AND kapitel_nr = 1 AND aufgabe_nr = 1;");
			prepStmt.setString(1, chosenSubject);
			resultSet = prepStmt.executeQuery();
			fragenart = resultSet.getString(1);
		} catch (SQLException e) {
			System.out.print("getFirstFragenArt Methode gescheitert");
		}
		System.out.println("Fragenart: " + fragenart);
		return fragenart;
	}
	
	
	//Gibt Liste aller Kapitel eines Fachs zurück
	public ArrayList<String> getChaptersToSubject(String subject) {	
		ArrayList<String> list = new ArrayList<String>();
		PreparedStatement stm;
		ResultSet rs;
		try {
			stm = connection.prepareStatement("SELECT kapitel_name FROM kapitel WHERE fach = ?;");
			stm.setString(1, subject);
			rs = stm.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			System.out.println("getChaptersToSubject Methode gescheitert");
			e.printStackTrace();
		}
		System.out.println("Kapitel des Faches: " + list);
		return list;
	}
	
	
	//Gibt eine bestimmte Frage zurück
	public String getQuestion(String subject, int chapterNr, int taskNr) {
		String frage = "";
		PreparedStatement stm;
		ResultSet rs;
		try{
			stm = connection.prepareStatement("SELECT frage_wortlaut FROM frage WHERE fach = ? AND kapitel_nr = ? AND aufgabe_nr = ?;");
			stm.setString(1, subject);
			stm.setInt(2, chapterNr);
			stm.setInt(3, taskNr);
			rs = stm.executeQuery();
			frage = rs.getString(1);
		} catch (SQLException e){
			System.out.println ("getQuestion Methode gescheitert");
		}
		
		System.out.println("Frage: " + frage);
		return frage;
	}

	
	//Gibt eine Liste aller Antwortmöglichkeiten einer Aufgabe
	public ArrayList<String> getAnswers(String subject, int chapterNr, int taskNr) { 
		ArrayList <String> answers = new ArrayList<String> ();
		PreparedStatement stm;
		ResultSet rs;
		try{
			stm = connection.prepareStatement("SELECT antwort_wortlaut FROM antwort WHERE fach = ? AND kapitel_nr = ? AND aufgabe_nr = ?");
			stm.setString(1, subject);
			stm.setInt(2, chapterNr);
			stm.setInt(3, taskNr);
			rs = stm.executeQuery();
			while (rs.next()){
				answers.add(rs.getString(1));
			}
		} catch (SQLException e){
			System.out.println("getAnswers Methode Methode gescheitert");
			e.printStackTrace();
		}
		System.out.println("Antworten: "+ answers);
		return answers;
	}
	
	
	//Gibt Anzahl der Antwortmöglichkeiten einer Aufgabe
	public int getAnswersCount(String subject, int chapterNr, int taskNr) {	
		int answersCount = 0;
		PreparedStatement stm;
		ResultSet rs;
		try {
			stm = connection.prepareStatement("SELECT anzahl_antworten FROM aufgabe WHERE fach = ? AND kapitel_nr = ? AND aufgabe_nr = ?");
			stm.setString(1, subject);
			stm.setInt(2, chapterNr);
			stm.setInt(3, taskNr);
			rs = stm.executeQuery();
			answersCount= rs.getInt(1);
		}catch (SQLException e){
			System.out.println("getAnswersCount Methode gescheitert");
		}
		System.out.println("Antwortenanzahl: " + answersCount);
		return answersCount;
	}
	
	
	//Gibt Anzahl der Fragen einer Aufgabe
	public int getQuestionsCount(String subject, int chapterNr, int taskNr) {	
		int questionsCount = 0;
		PreparedStatement stm;
		ResultSet rs;
		try {
			stm = connection.prepareStatement("SELECT anzahl_fragen FROM aufgabe WHERE fach = ? AND kapitel_nr = ? AND aufgabe_nr = ?");
			stm.setString(1, subject);
			stm.setInt(2, chapterNr);
			stm.setInt(3, taskNr);
			rs = stm.executeQuery();
			questionsCount= rs.getInt(1);
		}catch (SQLException e){
			System.out.println("getQuestionsCount Methode gescheitert");
			e.printStackTrace();
		}
		System.out.println("Fragenanzahl: " + questionsCount);
		return questionsCount;
	}

	//Ermittelt die Summe aller Aufgaben eines Kapitels, wichtig für Vorherige/NächsteButton
	public int getTaskCountOfAChapter(String subject, int chapterNr){	
		int anzahl = 0;
		try {
			PreparedStatement stm = connection.prepareStatement("SELECT anzahl_aufgaben FROM kapitel WHERE fach = ? AND kapitel_nr = ?;");
			stm.setString(1, subject);
			stm.setInt(2, chapterNr);
			ResultSet rs = stm.executeQuery();
			anzahl = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("getTaskCountOfAChapter Methode gescheitert");
			e.printStackTrace();
		} 
		System.out.println("Kapitel enthält " + anzahl + " Aufgaben.");
		return anzahl;
	}
	
	//Gibt einen bestimmten Kapitelnamen zurück
	public String getChapter(String currentSubject, int currentChapterNumber) {	
		String chapter = "";
		try {
			PreparedStatement stm = connection.prepareStatement("SELECT kapitel_name FROM kapitel WHERE fach = ? AND kapitel_nr = ?");
			stm.setString(1, currentSubject);
			stm.setInt(2, currentChapterNumber);
			ResultSet rs = stm.executeQuery();
			chapter = rs.getString(1);
		} catch (SQLException e) {
			System.out.println("getChapter Methode gescheitert");
			e.printStackTrace();
		}
		return chapter;
	}

	public int getChapterNr(String currentSubject, String chapterName) { //Gibt die KapitelNr zurück
		int chapterNr = 0;
		try {
			PreparedStatement stm = connection.prepareStatement("SELECT kapitel_nr FROM kapitel WHERE fach = ? AND kapitel_name = ?;");
			stm.setString(1, currentSubject);
			stm.setString(2, chapterName);
			ResultSet rs = stm.executeQuery();
			chapterNr = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("getChapterNr Methode gescheitert");
			e.printStackTrace();
		}
		System.out.println("Kapitelnummer: "+ chapterNr);
		return chapterNr;
	}

	public ArrayList<String> getQuestionList(String currentSubject, int currentChapterNumber, int currentTaskNumber) { 	//Returnt Liste mit Fragen, wichtig für Lückentext, Reihenfolge
		ArrayList <String> questionList = new ArrayList<String>();
		PreparedStatement stm; 
		ResultSet rs;
		try{
			stm = connection.prepareStatement("SELECT frage_wortlaut FROM frage WHERE fach = ? AND kapitel_nr = ? AND aufgabe_nr = ? ORDER BY frage_nr ASC;");
			stm.setString(1, currentSubject);
			stm.setInt(2, currentChapterNumber);
			stm.setInt(3, currentTaskNumber);
			rs = stm.executeQuery();
			while (rs.next()){
				questionList.add(rs.getString(1));
			}
		} catch (SQLException e){
			System.out.println("getQuestionList Methode gescheitert");
		}
		System.out.println("Fragenliste: "+ questionList);
		return questionList;
	}

	public String getTaskFragenart(String currentSubject, int currentChapterNumber, int currentTaskNumber) {
		String fragenart = "";
		PreparedStatement stm;
		ResultSet rs;
		try{
			stm = connection.prepareStatement("SELECT fragenart FROM aufgabe WHERE fach = ? AND kapitel_nr = ? AND aufgabe_nr = ?;");
			stm.setString(1, currentSubject);
			stm.setInt(2, currentChapterNumber);
			stm.setInt(3, currentTaskNumber);
			rs = stm.executeQuery();
			fragenart = rs.getString(1);
		} catch (SQLException e ){
			System.out.println("getTaskFragenart Methode gescheitert");
			e.printStackTrace();
		}
		System.out.println("Nächste Frage ist vom Typ: "+fragenart);
		return fragenart;
	}
//Gibt den Pfad einer Datei an mit Bezug Lösung oder Frage
	public String getPfad(String currentSubject, int currentChapterNumber, int currentTaskNumber, String bezug) {
		String pfad = "";
		PreparedStatement prep;
		ResultSet rs;
		try {
			prep = connection.prepareStatement("SELECT pfad FROM datei WHERE fach = ? AND kapitel_nr = ? AND aufgabe_nr = ? AND bezug = ?;");
			prep.setString(1, currentSubject);
			prep.setInt(2, currentChapterNumber);
			prep.setInt(3, currentTaskNumber);
			prep.setString(4, bezug);
			rs = prep.executeQuery();
			pfad = rs.getString(1);
		}catch (SQLException e){
			System.out.println("getPfad Methode gescheitert");
			e.printStackTrace();
		}
		System.out.println("Pfad: " + pfad);
		return pfad;
	}

	public String getAnswer(String currentSubject, int currentChapterNumber, int currentTaskNumber) {
		String answer = "";
		PreparedStatement prep;
		ResultSet rs;
		try {
			prep = connection.prepareStatement("SELECT antwort_wortlaut FROM antwort WHERE fach = ? AND kapitel_nr = ? AND aufgabe_nr = ? AND antwort_nr = 1");
			prep.setString(1, currentSubject);
			prep.setInt(2, currentChapterNumber);
			prep.setInt(3, currentTaskNumber);
			rs = prep.executeQuery();
			answer = rs.getString(1);
		}catch (SQLException e){
			System.out.println("getAnswer Methode gescheitert");
			e.printStackTrace();
		}
		System.out.println("Antwort lautet: " + answer);
		return answer;
	}
//SVENS EXTRAS
		public ArrayList<String> getTrueAnswers(String subject, int chapterNr, int taskNr) {
		ArrayList<String> answers = new ArrayList<String>();
		PreparedStatement stm;
		ResultSet rs;
		try {
			stm = connection.prepareStatement("SELECT antwort_wortlaut FROM antwort WHERE fach = ? AND kapitel_nr = ? AND aufgabe_nr = ? AND wahrheit = 1");
			stm.setString(1, subject);
			stm.setInt(2, chapterNr);
			stm.setInt(3, taskNr);
			rs = stm.executeQuery();
			while (rs.next()) {
				answers.add(rs.getString(1));
				System.out.println("TrueAntwort: " + rs.getString(1) + " wurde hinzugefügt.");
			}
		} catch (SQLException e) {
			e.printStackTrace();//
		}
		return answers;
	}

	public Boolean getAnswersBoolean(String subject, int chapterNr, int taskNr) {
		Boolean answers = null;
		PreparedStatement stm;
		ResultSet rs;
		try {
			stm = connection.prepareStatement(
					"SELECT wahrheit FROM antwort WHERE fach = ? AND kapitel_nr = ? AND aufgabe_nr = ?");
			stm.setString(1, subject);
			stm.setInt(2, chapterNr);
			stm.setInt(3, taskNr);
			rs = stm.executeQuery();
			System.out.println("getAnswersBoolean Methode wurde gestartet und Statement ausgeführt");
			answers = rs.getBoolean(1);
		} catch (SQLException e) {
			e.printStackTrace();//
		}
		return answers;
	}

	public String[][] getExtraHelp(String currentSubject) {
		ResultSet result;
		ResultSet result2;
		PreparedStatement stm;
		PreparedStatement stm2;
		String extraHelp [][] = null; 
		int i = 0, rowCount, columnCount = 0;
		try {
			stm = connection.prepareStatement("SELECT * FROM links WHERE fach = ?");
			stm.setString(1, currentSubject);
			result = stm.executeQuery();
			if (result.isClosed()){
				return null;
			}
			//Anzahl der Spalten für mehrdimensionales Array ermitteln
			ResultSetMetaData rsmd = result.getMetaData();
			columnCount = rsmd.getColumnCount();
			
			//Anzahl der Reihen für mehrdimensionales Array ermitteln
			stm2 = connection.prepareStatement("SELECT COUNT(*) FROM links WHERE fach = ?");
			stm2.setString(1, currentSubject);
			result2 = stm2.executeQuery();
			rowCount = result2.getInt(1);
			
			extraHelp =  new String [rowCount][columnCount];
			while (result.next()){
				extraHelp[i][0] = result.getString(2);
				extraHelp[i][1] = result.getString(3);
				i++;
			}
		} catch (SQLException e){
			e.printStackTrace();
			System.out.println("getExtraHelpMethode gescheitert");
		}
		for (int counter = 0; counter < columnCount - 1; counter ++){
			System.out.println("Hilfe #" + counter + " " + extraHelp[counter][0] + ": " + extraHelp[counter][1]);
		}
		return extraHelp;
	}
}
