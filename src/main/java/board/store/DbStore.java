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

  private final String path;

  public DbStore(String path) {
    this.path = path;
  }

  @Override
  public void addTopic(Topic topic) throws StoreException {
    try (Connection connection = connection()) {
      createTables(connection);
      String insert = "INSERT INTO topic (name) VALUES (?)";
      try (PreparedStatement statement = connection.prepareStatement(insert)) {
        statement.setString(1, topic.name());
        statement.executeUpdate();
      } catch (SQLException exception) {
        throw new StoreException();
      }
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

  @Override
  public void removeTopic(Topic topic) throws StoreException {
    try (Connection connection = connection()) {
      createTables(connection);
      String delete = "DELETE FROM topic where name = ?";
      try (PreparedStatement statement = connection.prepareStatement(delete)) {
        statement.setString(1, topic.name());
        statement.executeUpdate();
      } catch (SQLException exception) {
        throw new StoreException();
      }
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

  @Override
  public void addNote(Topic topic, Note note) throws StoreException {
    try (Connection connection = connection()) {
      createTables(connection);
      String insert = "INSERT INTO name (topic, content) VALUES (?, ?)";
      try (PreparedStatement statement = connection.prepareStatement(insert)) {
        statement.setString(1, topic.name());
        statement.setString(2, note.content());
        statement.executeUpdate();
      } catch (SQLException exception) {
        throw new StoreException();
      }
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

  @Override
  public void removeNote(Topic topic, Note note) throws StoreException {
    try (Connection connection = connection()) {
      createTables(connection);
      String delete = "DELETE FROM note WHERE topic = ? and name = ?";
      try (PreparedStatement statement = connection.prepareStatement(delete)) {
        statement.setString(1, topic.name());
        statement.setString(2, note.content());
        statement.executeUpdate();
      } catch (SQLException exception) {
        throw new StoreException();
      }
    } catch (SQLException exception) {
      throw new StoreException();
    }
  }

  @Override
  public Board board() throws StoreException {
    Board board = new Board();
    try (Connection connection = connection()) {
      createTables(connection);
      for (Topic topic : selectTopics(connection)) {
        board.addTopic(topic);
        for (Note note : selectNotes(connection, topic)) {
          board.addNote(topic, note);
        }
      }
    } catch (BoardException | SQLException exception) {
      throw new StoreException();
    }
    return board;
  }

  private List<Topic> selectTopics(
      Connection connection) throws StoreException {
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

  private List<Note> selectNotes(Connection connection,
      Topic topic) throws StoreException {
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

  private void createTables(Connection connection) throws StoreException {
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

  private Connection connection() throws StoreException {
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
