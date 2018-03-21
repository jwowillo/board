package board.store;

import board.Board;
import board.Note;
import board.Topic;
import board.observer.Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** DbStore is a sqlite implementation of Store. */
public class DbStore implements Store {

  private final Connection connection;

  private final PreparedStatement insertTopic;

  private final PreparedStatement insertNote;

  private final PreparedStatement removeTopic;

  private final PreparedStatement removeNote;

  private final PreparedStatement selectTopics;

  private final PreparedStatement selectNotes;

  /** DbStore initializes the connection, tables, and statements. */
  public DbStore(String path) throws SQLException {
    this.connection = openConnection(path);
    createTables();
    this.insertNote = prepareInsertNote();
    this.insertTopic = prepareInsertTopic();
    this.removeTopic = prepareRemoveTopic();
    this.removeNote = prepareRemoveNote();
    this.selectTopics = prepareSelectTopics();
    this.selectNotes = prepareSelectNotes();
  }

  @Override
  public void addTopic(Topic topic) throws SQLException {
    insertTopic.setString(1, topic.name());
    insertTopic.executeUpdate();
  }

  @Override
  public void removeTopic(Topic topic) throws SQLException {
    removeTopic.setString(1, topic.name());
    removeTopic.executeUpdate();
  }

  @Override
  public void addNote(Topic topic, Note note) throws SQLException {
    insertNote.setString(1, topic.name());
    insertNote.setString(2, note.content());
    insertNote.executeUpdate();
  }

  @Override
  public void removeNote(Topic topic, Note note) throws SQLException {
    removeNote.setString(1, topic.name());
    removeNote.setString(2, note.content());
    removeNote.executeUpdate();
  }

  @Override
  public Board board() throws Exception {
    var board = new Board();
    for (var topic : topics()) {
      board.addTopic(topic);
      for (var note : notes(topic)) {
        board.addNote(topic, note);
      }
    }
    return board;
  }

  @Override
  public void close() throws SQLException {
    insertTopic.close();
    insertNote.close();
    removeTopic.close();
    removeNote.close();
    selectTopics.close();
    selectNotes.close();
    connection.close();
  }

  private List<Topic> topics() throws SQLException {
    var topics = new ArrayList<Topic>();
    try (var set = selectTopics.executeQuery()) {
      while (set.next()) {
        topics.add(new Topic(set.getString("name")));
      }
    }
    return topics;
  }

  private List<Note> notes(Topic topic) throws SQLException {
    var notes = new ArrayList<Note>();
    selectNotes.setString(1, topic.name());
    try (var set = selectNotes.executeQuery()) {
      while (set.next()) {
        notes.add(new Note(set.getString("content")));
      }
    }
    return notes;
  }

  private void createTables() throws SQLException {
    var topic = "CREATE TABLE IF NOT EXISTS topic (\n"
        + "  name text PRIMARY KEY\n"
        + ")";
    var note = "CREATE TABLE IF NOT EXISTS note (\n"
        + "  topic text,\n"
        + "  content text,\n"
        + "  PRIMARY KEY (topic, content),\n"
        + "  FOREIGN KEY (topic) REFERENCES topic(name)\n"
        + ")";
    try (var statement = connection.createStatement()) {
      statement.execute(topic);
      statement.execute(note);
    }
  }

  private PreparedStatement prepareInsertTopic() throws SQLException {
    return prepare("INSERT INTO topic (name) VALUES (?)");
  }

  private PreparedStatement prepareInsertNote() throws SQLException {
    return prepare("INSERT INTO note (topic, content) VALUES (?, ?)");
  }

  private PreparedStatement prepareRemoveTopic() throws SQLException {
    return prepare("DELETE FROM topic where name = ?");
  }

  private PreparedStatement prepareRemoveNote() throws SQLException {
    return prepare("DELETE FROM note WHERE topic = ? and content = ?");
  }

  private PreparedStatement prepareSelectTopics() throws SQLException {
    return prepare("SELECT name FROM topic ORDER BY rowid");
  }

  private PreparedStatement prepareSelectNotes() throws SQLException {
    return prepare("SELECT content FROM note WHERE topic = ? ORDER BY rowid");
  }

  private PreparedStatement prepare(String statement) throws SQLException {
    return connection.prepareStatement(statement);
  }

  private Connection openConnection(String path) throws SQLException {
    var url = String.format("jdbc:sqlite:%s", path);
    var connection = DriverManager.getConnection(url);
    try (var statement = connection.createStatement()) {
      statement.execute("PRAGMA foreign_keys = ON");
    }
    return connection;
  }

}
