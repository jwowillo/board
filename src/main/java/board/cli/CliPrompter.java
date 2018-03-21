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

  @FunctionalInterface
  private interface Option {

    void run(Manager manager) throws Exception;

  }

  private class Pair {

    public final String label;

    public final Option option;

    public Pair(String label, Option option) {
      this.label = label;
      this.option = option;
    }

  }

  private final Scanner in = new Scanner(System.in);

  private final Map<Integer, Pair> options = new TreeMap<>();

  private Handler handler;

  public CliPrompter(Handler handler) {
    this.handler = handler;
  }

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

  private void addOptions() {
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

  private Topic promptTopic() {
    System.out.print("Topic Name: ");
    var topic =  new Topic(in.nextLine());
    System.out.println();
    return topic;
  }

  private Note promptNote() {
    System.out.print("Note Content: ");
    var note = new Note(in.nextLine());
    System.out.println();
    return note;
  }

  private Filter promptFilter() {
    System.out.print("Filter Term: ");
    Filter filter = new Filter(in.nextLine());
    System.out.println();
    return filter;
  }

}
