package recipeApp;

import java.util.List;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    FileHandling ingredientFile = new FileHandling("ingredients.txt");
    FileHandling recipeFile = new FileHandling("recipes.txt");
    Scanner input = new Scanner(System.in);
    ConsoleUI ui = new ConsoleUI();
    Context context = new Context();

    List<Ingredient> ingredients = ingredientFile.getParsedIngredients();
    List<Recipe> recipes = recipeFile.getParsedRecipes(ingredients);
  
    boolean exit = false;
    while (!exit) {
      ui.mainMenu(0);
      int selection = input.nextInt();

      if (selection <= 0 || selection > 3) {
        System.out.println("Invalid selection.");
      } else {
        ui.mainMenu(selection);
      }

      // Sets strategy based on user selection.
      if (selection == 1) {
        context.setStrategy(new Ingredient(ingredients));
      } else if (selection == 2) {
        context.setStrategy(new Recipe(recipes, ingredients));
      } else {
        // Exiting without saving.
        exit = true;
        System.out.println("Exiting...");
        input.close();
        return;
      }
  
    switch (ui.subMenu(input.nextInt())) {
      case ADD:
        context.getStrategy().add(input);
        break;
      case LIST:
        context.getStrategy().list();
        break;
      case DETAILS:
        context.getStrategy().details(input, 0);
        break;
      case DELETE:
        context.getStrategy().delete(input);
        break;
      case ADJUST:
      context.getStrategy().adjust(input);
        break;
      case SEARCH:
        context.getStrategy().search(input);
        break;
      case SAVE:
        recipeFile.saveRecipes(recipes);
        ingredientFile.saveIngredients(ingredients);
        exit = true;
      default:
        break;
      }
    }
    input.close();
  }
}
