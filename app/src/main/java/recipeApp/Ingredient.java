package recipeApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ingredient implements StrategyInterface {
  private List<Ingredient> ingredientObjs = new ArrayList<Ingredient>();
  private String ingredientName;
  private String ingredientMeasurement;
  private int ingredientPrice;

  public Ingredient (String theName, String theMeasurement, int thePrice) {
    this.ingredientName = theName;
    this.ingredientMeasurement = theMeasurement;
    this.ingredientPrice = thePrice;
  }

  public Ingredient (List<Ingredient> TheIngredients) {
    this.ingredientObjs = TheIngredients;
  }

  public Ingredient () {
  }

  /**
   * Add an ingredient object to the List.
   */
  @Override
  public void add (Scanner input) {
    System.out.print("Enter Ingredient Name: ");
    setIngredientName(input.next());

    System.out.print("Enter Ingredient Unit Measurement(ex gram): ");
    setIngredientMeasurement(input.next());

    System.out.print("Enter Ingredient Price: ");
    setIngredientPrice(input.nextInt());

    Ingredient newIngredient = new Ingredient(getIngredientName(), getIngredientMeasurement(), getIngredientPrice());
    ingredientObjs.add(newIngredient);
  }

  /**
   * Prints all ingredients.
   */
  @Override
  public void list () {
    if (ingredientObjs.size() == 0) {
      System.out.println("No ingredients to display.");
      return;
    }

    System.out.println("\nIngredient Names: ");
    for (int i = 0; i < ingredientObjs.size(); i++) {
      Ingredient ing = ingredientObjs.get(i);

      System.out.println((i + 1) + ") " + ing.getIngredientName());
    }  
  }

  /**
   * Print the details of a specific ingredient.
   * @param selection The selected ingredient.
   */
  @Override
  public void details (Scanner input, int selection) {
    if (selection == 0) {
      list();

      if (ingredientObjs.size() == 0) {
        return;
      }

      System.out.print("Select an ingredient to view the details: ");
      selection = input.nextInt();
    }

    Ingredient ing = ingredientObjs.get(selection - 1);

    System.out.println("\nIngredient Name: " + ing.getIngredientName());
    System.out.println("Ingredient Measurement: " + ing.getIngredientMeasurement());
    System.out.println("Ingredient Price: " + ing.getIngredientPrice());
    System.out.println();
  }

  /**
   * Adjusts the details of an ingredient.
   */
  public void adjust (Scanner input) {
    this.list();

    System.out.print("Pick an ingredient to adjust: ");
    int selection = input.nextInt();

    // Removes an ingredient.
    ingredientObjs.remove(selection - 1);

    // And add the new adjusted ingredient.
    add(input);
  }

  /**
   * Deletes a ingredient object from the List.
   */
  @Override
  public void delete (Scanner input)  {
    list();

    if (ingredientObjs.size() == 0) {
      System.out.println("No ingredients to remove.");
      return;
    }

    System.out.println("Choose an ingredient to delete");
    System.out.print("0 to remove all ingredients: ");
    int selection = input.nextInt();

    if (selection == 0) {
      ingredientObjs.clear();
    } else {
      ingredientObjs.remove(selection - 1);
    } 
  }

  /**
   * Search an ingredient based on the max price.
   */
  @Override
  public void search (Scanner input) {
    System.out.println("1) Search ingredient by max price");
    System.out.print("Choice: ");
    int maxPrice = input.nextInt();

    for (int i = 0; i < ingredientObjs.size(); i++) {
      int ingredientPrice = ingredientObjs.get(i).getIngredientPrice();

      if (ingredientPrice <= maxPrice) {
        details(input, i + 1);
      }
    }
  }

  public void setIngredientName(String theIngredientName) {
    this.ingredientName = theIngredientName;
  }

  public String getIngredientName () {
    return this.ingredientName;
  }

  public void setIngredientMeasurement (String theIngredientUnit) {
    this.ingredientMeasurement = theIngredientUnit;
  }

  public String getIngredientMeasurement () {
    return ingredientMeasurement;
  }

  public void setIngredientPrice (int theIngredientPrice) {
    this.ingredientPrice = theIngredientPrice;
  }

  public int getIngredientPrice () {
    return this.ingredientPrice;
  }

  /**
   * Returns the current instance.
   * @return Ingredient.
   */
  public Ingredient getIngredient () {
    return this;
  }
}