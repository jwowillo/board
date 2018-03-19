package board.store;

import board.Board;
import board.Note;
import board.Topic;
import board.observer.Store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/** DbStore is a sqlite implementation of Store. */
public class DbStore implements Store {

  /** connection to a database. */
  private final Connection connection;

  /** insertTopic inserts a Topic. */
  private final PreparedStatement insertTopic;

  /** insertNote inserts a Note. */
  private final PreparedStatement insertNote;

  /** removeTopic removes a Topic. */
  private final PreparedStatement removeTopic;

  /** removeNote removes a Note. */
  private final PreparedStatement removeNote;

  /** selectTopics selects all Topics. */
  private final PreparedStatement selectTopics;

  /** selectNotes selects all Notes for a Topic. */
  private final PreparedStatement selectNotes;

  /**
   * DbStore connects to a sqlite-database at the path.
   *
   * @throws SQLException If the database can't be connected to.
   */
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

  /**
   * addTopic to the database.
   *
   * @throws SQLException If the Topic couldn't be added.
   */
  @Override
  public void addTopic(Topic topic) throws SQLException {
    insertTopic.setString(1, topic.name());
    insertTopic.executeUpdate();
  }

  /**
   * removeTopic from the database.
   *
   * @throws SQLException If the Topic couldn't be removed.
   */
  @Override
  public void removeTopic(Topic topic) throws SQLException {
    removeTopic.setString(1, topic.name());
    removeTopic.executeUpdate();
  }

  /**
   * addNote to the Topic in the database.
   *
   * @throws SQLException If the Note couldn't be added.
   */
  @Override
  public void addNote(Topic topic, Note note) throws SQLException {
    insertNote.setString(1, topic.name());
    insertNote.setString(2, note.content());
    insertNote.executeUpdate();
  }

  /**
   * removeNote from the Topic in the database.
   *
   * @throws SQLException If the Note couldn't be removed.
   */
  @Override
  public void removeNote(Topic topic, Note note) throws SQLException {
    removeNote.setString(1, topic.name());
    removeNote.setString(2, note.content());
    removeNote.executeUpdate();
  }

  /**
   * Board from the database or a new Board if there is none.
   *
   * @throws Exception If the Board couldn't be retrieved from the database.
   */
  @Override
  public Board board() throws Exception {
    Board board = new Board();
    for (Topic topic : topics()) {
      board.addTopic(topic);
      for (Note note : notes(topic)) {
        board.addNote(topic, note);
      }
    }
    return board;
  }

  /**
   * close all connections and statements.
   *
   * @throws SQLException If anything couldn't be closed.
   */
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

  /**
   * topics in the database.
   *
   * @throws SQLException If the Topics couldn't be retrieved.
   */
  private List<Topic> topics() throws SQLException {
    List<Topic> topics = new ArrayList<>();
    try (ResultSet set = selectTopics.executeQuery()) {
      while (set.next()) {
        topics.add(new Topic(set.getString("name")));
      }
    }
    return topics;
  }

  /**
   * notes for the Topic in the database.
   *
   * @throws SQLException If the Notes couldn't be retrieved.
   */
  private List<Note> notes(Topic topic) throws SQLException {
    List<Note> notes = new ArrayList<>();
    selectNotes.setString(1, topic.name());
    try (ResultSet set = selectNotes.executeQuery()) {
      while (set.next()) {
        notes.add(new Note(set.getString("content")));
      }
    }
    return notes;
  }

  /**
   * createTables creates the necessary tables in the database.
   *
   * @throws SQLException If the tables couldn't be created.
   */
  private void createTables() throws SQLException {
    String topic = "CREATE TABLE IF NOT EXISTS topic (\n"
        + "  name text PRIMARY KEY\n"
        + ")";
    String note = "CREATE TABLE IF NOT EXISTS note (\n"
        + "  topic text,\n"
        + "  content text,\n"
        + "  PRIMARY KEY (topic, content),\n"
        + "  FOREIGN KEY (topic) REFERENCES topic(name)\n"
        + ")";
    try (Statement statement = connection.createStatement()) {
      statement.execute(topic);
      statement.execute(note);
    }
  }

  /**
   * prepareInsertTopic prepares an insert-topic PreparedStatement.
   *
   * @throws SQLException If the PreparedStatement couldn't be prepared.
   */
  private PreparedStatement prepareInsertTopic() throws SQLException {
    return prepare("INSERT INTO topic (name) VALUES (?)");
  }

  /**
   * prepareInsertNote prepares an insert-note PreparedStatement.
   *
   * @throws SQLException If the PreparedStatement couldn't be prepared.
   */
  private PreparedStatement prepareInsertNote() throws SQLException {
    return prepare("INSERT INTO note (topic, content) VALUES (?, ?)");
  }

  /**
   * prepareRemoveTopic prepares a remove-topic PreparedStatement.
   *
   * @throws SQLException If the PreparedStatement couldn't be prepared.
   */
  private PreparedStatement prepareRemoveTopic() throws SQLException {
    return prepare("DELETE FROM topic where name = ?");
  }

  /**
   * prepareRemoveNote prepares a remove-note PreparedStatement.
   *
   * @throws SQLException If the PreparedStatement couldn't be prepared.
   */
  private PreparedStatement prepareRemoveNote() throws SQLException {
    return prepare("DELETE FROM note WHERE topic = ? and content = ?");
  }

  /**
   * prepareSelectTopics prepares a select-topics PreparedStatement.
   *
   * @throws SQLException If the PreparedStatement couldn't be prepared.
   */
  private PreparedStatement prepareSelectTopics() throws SQLException {
    return prepare("SELECT name FROM topic ORDER BY rowid");
  }

  /**
   * prepareSelectNotes prepares a select-notes PreparedStatement.
   *
   * @throws SQLException If the PreparedStatement couldn't be prepared.
   */
  private PreparedStatement prepareSelectNotes() throws SQLException {
    return prepare("SELECT content FROM note WHERE topic = ? ORDER BY rowid");
  }

  /**
   * prepare a PreparedStatement with String content.
   *
   * @throws SQLException If the PreparedStatement couldn't be prepared.
   */
  private PreparedStatement prepare(String statement) throws SQLException {
    return connection.prepareStatement(statement);
  }

  /**
   * openConnection to sqlite-database at the path.
   *
   * @throws SQLException If the Connection couldn't be made.
   */
  private Connection openConnection(String path) throws SQLException {
    String url = String.format("jdbc:sqlite:%s", path);
    Connection connection = DriverManager.getConnection(url);
    try (Statement statement = connection.createStatement()) {
      statement.execute("PRAGMA foreign_keys = ON");
    }
    return connection;
  }

}
