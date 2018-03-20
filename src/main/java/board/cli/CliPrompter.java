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
public class CliPrompter implements Prompter {

  /** Scanner to read input from. */
  private final Scanner in;

  /** Handler for errors. */
  private Handler handler;

  /** Option that can be run. */
  @FunctionalInterface
  private interface Option {

    /**
     * run the Option on the Manager.
     *
     * @throws Exception that may have occurred while running.
     */
    void run(Manager manager) throws Exception;

  }

  /**
   * options that correspond to actions that can be performed on the Manager.
   */
  private final Map<Integer, Pair> options;

  /** Pair of a label and a Option. */
  private class Pair {

    /** label of the Pair. */
    public final String label;

    /** Option ran when the label is selected. */
    public final Option option;

    /** Pair with label and Option. */
    public Pair(String label, Option option) {
      this.label = label;
      this.option = option;
    }

  }

  /** CliPrompter constructed with options. */
  public CliPrompter(Handler handler) {
    this.in = new Scanner(System.in);
    this.handler = handler;
    this.options = new TreeMap<>();
    Option option1 = m -> m.addTopic(promptTopic());
    Option option2 = m -> m.removeTopic(promptTopic());
    Option option3 = m -> m.addNote(promptTopic(), promptNote());
    Option option4 = m -> m.removeNote(promptTopic(), promptNote());
    Option option5 = m -> m.addFilter(promptFilter());
    Option option6 = m -> m.removeFilter(promptFilter());
    options.put(1, new Pair("Add Topic", option1));
    options.put(2, new Pair("Remove Topic", option2));
    options.put(3, new Pair("Add Note", option3));
    options.put(4, new Pair("Remove Note", option4));
    options.put(5, new Pair("Add Filter", option5));
    options.put(6, new Pair("Remove Filter", option6));
  }

  /**
   * prompt actions to be performed on from the Manager from the command-line.
   */
  @Override
  public void prompt(Manager manager) {
    while (true) {
      System.out.println("Options:");
      for (var pair : options.entrySet()) {
        System.out.println(pair.getKey() + ". " + pair.getValue().label);
      }
      System.out.println(String.format("%d. Exit", options.size() + 1));
      System.out.println();
      System.out.print("Choose Option: ");
      int choice = Integer.parseInt(in.nextLine());
      if (choice == options.size() + 1) {
        return;
      }
      System.out.println();
      if (!options.containsKey(choice)) {
        handler.handle(new ChoiceException());
      }
      try {
        options.get(choice).option.run(manager);
      } catch (Exception exception) {
        handler.handle(new ChoiceException());
      }
    }
  }

  /** promptTopic from the command-line. */
  private Topic promptTopic() {
    System.out.print("Topic Name: ");
    var topic =  new Topic(in.nextLine());
    System.out.println();
    return topic;
  }

  /** promptNote from the command-line. */
  private Note promptNote() {
    System.out.print("Note Content: ");
    var note = new Note(in.nextLine());
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
