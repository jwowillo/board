package board.store;

import board.Board;
import board.BoardException;
import board.Note;
import board.Topic;
import board.View;
import board.observer.Store;
import board.observer.StoreException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbStore implements Store {

  private final Connection connection;

  private final PreparedStatement insertTopic;

  private final PreparedStatement insertNote;

  private final PreparedStatement removeTopic;

  private final PreparedStatement removeNote;

  public DbStore(String path) throws StoreException {
    this.connection = openConnection(path);
    createTables();
    this.insertTopic = prepareInsertTopic();
    this.insertNote = prepareInsertNote();
    this.removeTopic = prepareRemoveTopic();
    this.removeNote = prepareRemoveNote();
  }

  @Override
  public void addTopic(Topic topic) throws StoreException {
    try {
      insertTopic.setString(1, topic.name());
      insertTopic.executeUpdate();
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

  @Override
  public void removeTopic(Topic topic) throws StoreException {
    try {
      insertNote.setString(1, topic.name());
      insertNote.executeUpdate();
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

  @Override
  public void addNote(Topic topic, Note note) throws StoreException {
    try {
      insertNote.setString(1, topic.name());
      insertNote.setString(2, note.content());
      insertNote.executeUpdate();
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

  @Override
  public void removeNote(Topic topic, Note note) throws StoreException {
    try {
      removeNote.setString(1, topic.name());
      removeNote.setString(2, note.content());
      removeNote.executeUpdate();
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

  @Override
  public Board board() throws StoreException {
    Board board = new Board();
    try {
      for (Topic topic : selectTopics()) {
        board.addTopic(topic);
        for (Note note : selectNotes(topic)) {
          board.addNote(topic, note);
        }
      }
    } catch (BoardException exception) {
      throw new StoreException();
    }
    return board;
  }

  @Override
  public void close() throws StoreException {
    try {
      connection.close();
      insertTopic.close();
      insertNote.close();
      removeTopic.close();
      removeNote.close();
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

  private List<Topic> selectTopics() throws StoreException {
    String select = "SELECT name FROM topic ORDER BY rowid";
    try (PreparedStatement statement = connection.prepareStatement(select)) {
      try (ResultSet set = statement.executeQuery()) {
        List<Topic> topics = new ArrayList<>();
        while (set.next()) {
          topics.add(new Topic(set.getString("name")));
        }
        return topics;
      }
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

  private List<Note> selectNotes(Topic topic) throws StoreException {
    String select = "SELECT content FROM note WHERE topic = ? ORDER BY rowid";
    try (PreparedStatement statement = connection.prepareStatement(select)) {
      statement.setString(1, topic.name());
      try (ResultSet set = statement.executeQuery()) {
        List<Note> notes = new ArrayList<>();
        while (set.next()) {
          notes.add(new Note(set.getString("content")));
        }
        return notes;
      }
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

  private void createTables() throws StoreException {
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
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

  private PreparedStatement prepareInsertTopic() throws StoreException {
    return prepare("INSERT INTO topic (name) VALUES (?)");
  }

  private PreparedStatement prepareInsertNote() throws StoreException {
    return prepare("INSERT INTO note (topic, content) VALUES (?, ?)");
  }

  private PreparedStatement prepareRemoveTopic() throws StoreException {
    return prepare("DELETE FROM topic where name = ?");
  }

  private PreparedStatement prepareRemoveNote() throws StoreException {
    return prepare("DELETE FROM note WHERE topic = ? and content = ?");
  }

  private PreparedStatement prepare(String statement) throws StoreException {
    try {
      return connection.prepareStatement(statement);
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

  private static Connection openConnection(String path) throws StoreException {
    try {
      String url = String.format("jdbc:sqlite:%s", path);
      Connection connection = DriverManager.getConnection(url);
      try (Statement statement = connection.createStatement()) {
        statement.execute("PRAGMA foreign_keys = ON");
      }
      return connection;
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

}
