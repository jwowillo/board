package board;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** Board connects Topics and Notes. */
public class Board {

  /** topics maps Topics to their Notes. */
  private final Map<Topic, Set<Note>> topics;

  /** Board with no Topics and Notes. */
  public Board() {
    this.topics = new HashMap<>();
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
    topics.put(topic, new HashSet<>());
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
   * removeNote removes the Note in the Topic.
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
  public Set<Topic> topics() {
    return new HashSet<>(topics.keySet());
  }

  /** notes returns a copy of the Notes for the Topic. */
  public Set<Note> notes(Topic topic) {
    if (!topics.containsKey(topic)) {
      return new HashSet<>();
    }
    return new HashSet<>(topics.get(topic));
  }

}
