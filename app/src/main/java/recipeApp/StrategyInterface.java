package recipeApp;

import java.util.Scanner;

public interface StrategyInterface {
  public void add(Scanner input);
  public void list();
  public void details(Scanner input, int selection);
  public void delete(Scanner input);
  public void adjust(Scanner input);
  public void search(Scanner input);
}
