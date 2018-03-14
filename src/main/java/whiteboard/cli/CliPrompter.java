package board.cli;

import board.Manager;
import board.Note;
import board.Topic;
import board.observer.Handler;

import java.util.Scanner;

class CliPrompter {

  private final Manager manager;

  private final Handler handler;

  public CliPrompter(Manager manager, Handler handler) {
    this.manager = manager;
    this.handler = handler;
  }

  public void prompt() {
    Scanner in = new Scanner(System.in);
    while (true) {
      System.out.println("Options:");
      System.out.println("1. Add Topic");
      System.out.println("2. Add Note");
      System.out.println("3. Remove Topic");
      System.out.println("4. Remove Note");
      System.out.println();
      System.out.print("Choose Option: ");
      try {
        int choice = Integer.parseInt(in.nextLine());
        System.out.println();
        switch (choice) {
          case 1:
            manager.addTopic(promptTopic(in));
            break;
          case 2:
            manager.addNote(promptTopic(in), promptNote(in));
            break;
          case 3:
            manager.removeTopic(promptTopic(in));
            break;
          case 4:
            manager.removeNote(promptTopic(in), promptNote(in));
            break;
          default:
            throw new ChoiceException();
        }
      } catch (Exception exception) {
        handler.handle(exception);
      }
    }
  }

  private Topic promptTopic(Scanner in) {
    System.out.print("Topic Name: ");
    Topic topic =  new Topic(in.nextLine());
    System.out.println();
    return topic;
  }

  private Note promptNote(Scanner in) {
    System.out.print("Note Content: ");
    Note note = new Note(in.nextLine());
    System.out.println();
    return note;
  }

}
