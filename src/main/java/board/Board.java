package board;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** Board connects Topics and Notes. */
public class Board {

  /** topics maps Topics to their Notes. */
  private final Map<Topic, Set<Note>> topics;

  /** Board with no Topics and no Notes. */
  public Board() {
    this.topics = new LinkedHashMap<>();
  }

  /**
   * addTopic to the Board.
   *
   * @throws BoardException If the Topic is already on the Board.
   */
  public void addTopic(Topic topic) throws BoardException {
    if (topics.containsKey(topic)) {
      throw new TopicExistsException();
    }
    topics.put(topic, new LinkedHashSet<>());
  }

  /**
   * addNote to the Topic.
   *
   * @throws BoardException If the Topic isn't on the Board or the Topic has the
   *                        Note.
   */
  public void addNote(Topic topic, Note note) throws BoardException {
    if (!topics.containsKey(topic)) {
      throw new TopicDoesntExistException();
    }
    if (topics.get(topic).contains(note)) {
      throw new NoteExistsException();
    }
    topics.get(topic).add(note);
  }

  /**
   * removeTopic removes the Topic from the Board along with all associated
   * Notes.
   *
   * @throws BoardException If the Topic isn't on the Board.
   */
  public void removeTopic(Topic topic) throws BoardException {
    if (!topics.containsKey(topic)) {
      throw new TopicDoesntExistException();
    }
    topics.remove(topic);
  }

  /**
   * removeNote removes the Note from the Topic.
   *
   * @throws BoardException If the Topic isn't on the Board or the Topic doesn't
   *                        have the Note.
   */
  public void removeNote(Topic topic, Note note) throws BoardException {
    if (!topics.containsKey(topic)) {
      throw new TopicDoesntExistException();
    }
    if (!topics.get(topic).contains(note)) {
      throw new NoteDoesntExistException();
    }
    topics.get(topic).remove(note);
  }

  /** topics returns a copy of the Topics on the Board. */
  public List<Topic> topics() {
    return new ArrayList<>(topics.keySet());
  }

  /** notes returns a copy of the Notes for the Topic. */
  public List<Note> notes(Topic topic) {
    if (!topics.containsKey(topic)) {
      return new ArrayList<>();
    }
    return new ArrayList<>(topics.get(topic));
  }

}
