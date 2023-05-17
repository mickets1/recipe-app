package recipeApp;

public class ConsoleUI {
   public enum Action {
     ADD,
     LIST,
     DETAILS,
     DELETE,
     ADJUST,
     SEARCH,
     SAVE,
     NONE
   }
   
   public Action mainMenu (int selection) {
     // Sub menus
     if (selection == 0) {
      printMenu(0);
     } else if (selection == 1) {
      printMenu(1);
     } else if (selection == 2) {
      printMenu(2);
     }
     return Action.NONE;
  }

  public Action subMenu (int selection) {
    switch (selection) {
      case 1: return Action.ADD;
      case 2: return Action.LIST;
      case 3: return Action.DETAILS;
      case 4: return Action.DELETE;
      case 5: return Action.ADJUST;
      case 6: return Action.SEARCH;
      case 8: return Action.SAVE;
      default:
        break;
    }
    return Action.NONE;
  }

   public void printMenu (int dispMenu) {
    String[] menuArr = {};

    if (dispMenu == 0) {
      menuArr = new String[] {"Work with ingredients", "Work with recipes", "Exit (w/o saving)"};
    } else if (dispMenu == 1) {
      menuArr = new String[] {"Add Ingredient", "List Ingredients", "Ingredient Details", "Remove Ingredient", "Adjust Ingredient", "Search Ingredient", "Back", "Save All & Exit"};
    } else if (dispMenu == 2) {
      menuArr = new String[] {"Add Recipe", "List Recipes", "Recipe Details", "Remove Recipe", "Adjust Recipe", "Search Recipes", "Back", "Save All & Exit"};
    }

     System.out.println();
    for (int i = 0; i < menuArr.length; i++) {
        System.out.println((i + 1) + ") " + menuArr[i]);
       
    }
    System.out.print("Choice: ");
   }
}
