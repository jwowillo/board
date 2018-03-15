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

class CliPrompter implements Prompter {

  private final Scanner in;

  private final Map<Integer, Pair> options;

  private class Pair {

    public final String label;

    public final Prompter prompter;

    public Pair(String label, Prompter prompter) {
      this.label = label;
      this.prompter = prompter;
    }

  }

  public CliPrompter() {
    this.in = new Scanner(System.in);
    this.options = new TreeMap<>();
    options.put(1, new Pair(
          "Add Topic",
          (Manager manager) -> manager.addTopic(promptTopic())));
    options.put(2, new Pair(
          "Remove Topic",
          (Manager manager) -> manager.removeTopic(promptTopic())));
    options.put(3, new Pair(
          "Add Note",
          (Manager manager) -> manager.addNote(promptTopic(), promptNote())));
    options.put(4, new Pair(
          "Remove Note",
          (Manager manager) -> manager.removeNote(promptTopic(), promptNote())));
    options.put(5, new Pair(
          "Add Filter",
          (Manager manager) -> manager.addFilter(promptFilter())));
    options.put(6, new Pair(
          "Remove Filter",
          (Manager manager) -> manager.removeFilter(promptFilter())));
  }

  @Override
  public void prompt(Manager manager) throws Exception {
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
    options.get(choice).prompter.prompt(manager);
  }

  private Topic promptTopic() {
    System.out.print("Topic Name: ");
    Topic topic =  new Topic(in.nextLine());
    System.out.println();
    return topic;
  }

  private Note promptNote() {
    System.out.print("Note Content: ");
    Note note = new Note(in.nextLine());
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
