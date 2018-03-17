package board.cli;

import board.Filter;
import board.Manager;
import board.Note;
import board.Topic;
import board.app.Prompter;
import board.observer.Handler;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/** CliPrompter prompts for actions from the command-line. */
class CliPrompter implements Prompter {

  /** Scanner to read input from. */
  private final Scanner in;

  /**
   * options that correspond to actions that can be performed on the Manager.
   */
  private final Map<Integer, Pair> options;

  /** Pair of a label and a Prompter. */
  private class Pair {

    /** label of the Pair. */
    public final String label;

    /** Prompter performed when the label is selected. */
    public final Prompter prompter;

    /** Pair with label and Prompter. */
    public Pair(String label, Prompter prompter) {
      this.label = label;
      this.prompter = prompter;
    }

  }

  /** CliPrompter constructed with options. */
  public CliPrompter() {
    this.in = new Scanner(System.in);
    this.options = new TreeMap<>();
    Prompter option1 = m -> {
      m.addTopic(promptTopic());
      return true;
    };
    Prompter option2 = m -> {
      m.removeTopic(promptTopic());
      return true;
    };
    Prompter option3 = m -> {
      m.addNote(promptTopic(), promptNote());
      return true;
    };
    Prompter option4 = m -> {
      m.removeNote(promptTopic(), promptNote());
      return true;
    };
    Prompter option5 = m -> {
      m.addFilter(promptFilter());
      return true;
    };
    Prompter option6 = m -> {
      m.removeFilter(promptFilter());
      return true;
    };
    options.put(1, new Pair("Add Topic", option1));
    options.put(2, new Pair("Remove Topic", option2));
    options.put(3, new Pair("Add Note", option3));
    options.put(4, new Pair("Remove Note", option4));
    options.put(5, new Pair("Add Filter", option5));
    options.put(6, new Pair("Remove Filter", option6));
    options.put(7, new Pair("Exit", m -> false));
  }

  /**
   * prompt an action to be performed on from the Manager from the
   * command-line.
   *
   * @throws Exception If an invalid choice is made or an action causes an
   *                   Exception.
   */
  @Override
  public boolean prompt(Manager manager) throws Exception {
    System.out.println("Options:");
    for (Map.Entry<Integer, Pair> pair : options.entrySet()) {
      System.out.println(pair.getKey() + ". " + pair.getValue().label);
    }
    System.out.println();
    System.out.print("Choose Option: ");
    int choice = Integer.parseInt(in.nextLine());
    System.out.println();
    if (!options.containsKey(choice)) {
      throw new ChoiceException();
    }
    return options.get(choice).prompter.prompt(manager);
  }

  /** promptTopic from the command-line. */
  private Topic promptTopic() {
    System.out.print("Topic Name: ");
    Topic topic =  new Topic(in.nextLine());
    System.out.println();
    return topic;
  }

  /** promptNote from the command-line. */
  private Note promptNote() {
    System.out.print("Note Content: ");
    Note note = new Note(in.nextLine());
    System.out.println();
    return note;
  }

  /** promptFilter from the command-line. */
  private Filter promptFilter() {
    System.out.print("Filter Term: ");
    Filter filter = new Filter(in.nextLine());
    System.out.println();
    return filter;
  }

}
