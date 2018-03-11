package whiteboard;

import whiteboard.NoteDoesntExistException;
import whiteboard.NoteExistsException;
import whiteboard.TopicDoesntExistException;
import whiteboard.TopicExistsException;
import whiteboard.WhiteboardException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** Whiteboard connects Topics and Notes. */
public class Whiteboard {

  /** topics maps Topics to their Notes. */
  private final Map<Topic, Set<Note>> topics;

  /** Whiteboard with no Topics and Notes. */
  public Whiteboard() {
    this.topics = new HashMap<>();
  }

  /**
   * addTopic to the Whiteboard.
   *
   * @throws WhiteboardException If the Topic is already on the Whiteboard.
   */
  public void addTopic(Topic topic) throws WhiteboardException {
    if (topics.containsKey(topic)) {
      throw new TopicExistsException();
    }
    topics.put(topic, new HashSet<>());
  }

  /**
   * addNote to the Topic.
   *
   * @throws WhiteboardException If the Topic isn't on the Whiteboard or the
   *                             Topic has the Note.
   */
  public void addNote(Topic topic, Note note) throws WhiteboardException {
    if (!topics.containsKey(topic)) {
      throw new TopicDoesntExistException();
    }
    if (topics.get(topic).contains(note)) {
      throw new NoteExistsException();
    }
    topics.get(topic).add(note);
  }

  /**
   * removeTopic removes the Topic from the Whiteboard along with all associated
   * Notes.
   *
   * @throws WhiteboardException If the Topic isn't on the Whiteboard.
   */
  public void removeTopic(Topic topic) throws WhiteboardException {
    if (!topics.containsKey(topic)) {
      throw new TopicDoesntExistException();
    }
    topics.remove(topic);
  }

  /**
   * removeNote removes the Note in the Topic.
   *
   * @throws WhiteboardException If the Topic isn't on the Whiteboard or the
   *                             Topic doesn't have the Note.
   */
  public void removeNote(Topic topic, Note note) throws WhiteboardException {
    if (!topics.containsKey(topic)) {
      throw new TopicDoesntExistException();
    }
    if (!topics.get(topic).contains(note)) {
      throw new NoteDoesntExistException();
    }
    topics.get(topic).remove(note);
  }

  /**
   * topics returns the Topics on the Whiteboard.
   *
   * <p>Mutating the returned Set won't mutate the Whiteboard.
   */
  public Set<Topic> topics() {
    return new HashSet<>(topics.keySet());
  }

  /**
   * notes returns the Notes for the Topic.
   *
   * <p>Mutating the returned Set won't mutate the Whiteboard.
   */
  public Set<Note> notes(Topic topic) {
    if (!topics.containsKey(topic)) {
      return new HashSet<>();
    }
    return new HashSet<>(topics.get(topic));
  }

}
