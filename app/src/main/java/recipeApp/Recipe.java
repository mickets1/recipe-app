package recipeApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Recipe implements StrategyInterface {
  private List<Recipe> recipeObjs = new ArrayList<Recipe>();
  private List<Ingredient> ingredientObjs = new ArrayList<Ingredient>();
  private List<String> recipeIngredients = new ArrayList<String>();
  private List<String> recipeInstructions = new ArrayList<String>();
  private String recipeName;
  private int recipePortion;
  private String recipeUnit;
  
  public Recipe (String recipeName, int portion, String unit, List<String> recipeIngredientList, List<Ingredient> ingredients, List<String> instructions) { 
    this.recipeName = recipeName;
    this.recipeUnit = unit;
    this.recipePortion = portion;
    this.recipeIngredients = recipeIngredientList;
    this.ingredientObjs = ingredients;
    this.recipeInstructions = instructions;
  }

  public Recipe(List<Recipe> theRecipeObjs, List<Ingredient>theIngredientObjs) {
    this.recipeObjs = theRecipeObjs;
    this.ingredientObjs = theIngredientObjs;
  }

  /**
   * Adds a recipe to the recipe object List.
   */
  @Override
  public void add (Scanner input)  {
    Ingredient newIngredient = null;

    System.out.print("Recipe Name: ");
    String recipeName = input.next();
    setRecipeName(recipeName);

    System.out.print("Recipe Portion: ");
    int recipePortion = input.nextInt();
    setRecipePortion(recipePortion);

    System.out.print("Recipe Unit(ex pieces): ");
    String recipeUnit = input.next();
    setRecipeUnit(recipeUnit);

    addRecipeInstructions(input);

    boolean moreIngredients = true;
    while (moreIngredients) {
      newIngredient = new Ingredient();

      // The add method in Ingredient.
      newIngredient.add(input);

      // Add singular Ingredient to object list.
      ingredientObjs.add(newIngredient.getIngredient());

      System.out.print("Ingredient amount in recipe(int): ");
      int ingredientRecipeAmount = input.nextInt();

      recipeIngredients.add(ingredientRecipeAmount + ", " + newIngredient.getIngredientMeasurement() + ", " + newIngredient.getIngredientName());

      setRecipeIngredients(recipeIngredients);

      System.out.print("Add more ingredients? 1 = yes 2 = no: ");
        if (input.nextInt() == 2) {
          moreIngredients = false;
          }
        }

    Recipe recipe = new Recipe(getRecipeName(), getRecipePortion(), getRecipeUnit(), getRecipeIngredients(), ingredientObjs, getRecipeInstructions());
    recipeObjs.add(recipe);
  }

  private void addRecipeInstructions (Scanner input) {
    Boolean moreInstructions = true;

    while (moreInstructions) {
      System.out.print("Add recipe instructions: ");
      String instructions = input.next();
      this.recipeInstructions.add(instructions);
      
      System.out.print("Add more instructions? 1 = yes 2 = no: ");
      if (input.nextInt() == 2) {
        moreInstructions = false;
      }
    }
  }

  /**
   * Prints all recipes.
   */
  @Override
  public void list () {
    if (recipeObjs.size() == 0) {
      System.out.println("No recipes to display.");
      return;
    }

    for (int i = 0; i < recipeObjs.size(); i++) {
      System.out.println((i + 1) + ") " + recipeObjs.get(i).getRecipeName());
    }
  }

  /**
   * Print the details of a specific recipe.
   * @param selection The selected recipe.
   */
  @Override
  public void details (Scanner input, int selection) {
    if (selection == 0) {
      list();

      if (recipeObjs.size() == 0) {
        return;
      }

      System.out.print("Select a recipe to view the details: ");
      selection = input.nextInt();
    }
    
    Recipe recipe = recipeObjs.get(selection - 1);

    System.out.println("\nRecipe Name: " + recipe.getRecipeName());
    System.out.println("Recipe Portions: " + recipe.getRecipePortion());
    System.out.println("Recipe Unit: " + recipe.getRecipeUnit());

    System.out.println("Recipe Ingredients: ");
    printArrayList(recipe.getRecipeIngredients());

    System.out.println("Recipe Instructions: ");
    printArrayList(recipe.getRecipeInstructions());
  }

  /**
   * Removes a specific recipe object from the List.
   */
  @Override
  public void delete (Scanner input) {
    list();

    if (recipeObjs.size() == 0) {
      System.out.println("No recipes to remove.");
      return;
    }

    System.out.print("Choose a recipe to delete: ");
    int selection = input.nextInt() - 1;

    recipeObjs.remove(selection);
  }

  /**
   * Adjusts recipe portions and calculates the new price.
   */
  @Override
  public void adjust (Scanner input) {
    CalculatePortionsAndPrice calculate = new CalculatePortionsAndPrice(this.ingredientObjs);
    list();

    if (recipeObjs.size() == 0) {
      return;
    }

    System.out.print("Choose a recipe to adjust and calculate portions and price: ");
    int recipeChoice = input.nextInt() - 1;

    System.out.print("How many portions?: ");
    int portions = input.nextInt();

    if (portions == 0) {
      System.out.println("\nPortions can't be zero!");
      return;
    }
    
    calculate.printNewRecipe(portions, recipeObjs.get(recipeChoice));
  }

  /**
   * Search a recipe based on max price or ingredient.
   */
  @Override
  public void search (Scanner input) {
    CalculatePortionsAndPrice calculate = new CalculatePortionsAndPrice(this.ingredientObjs);

    System.out.println("1) Search recipe by max price");
    System.out.println("2) Search recipe by ingredient");
    System.out.print("Choice: ");
    int selection = input.nextInt();

    if (selection == 1) {
      System.out.print("Max price: ");
      int maxPrice = input.nextInt();

      // Calculates the price of each recipe.
      for(int i = 0; i < recipeObjs.size(); i++) {
        int price = calculate.price(recipeObjs.get(i).getRecipeIngredients());
        
        if (price <= maxPrice) {
          System.out.println("\nRecipe name: " + recipeObjs.get(i).getRecipeName());
          System.out.println("Recipe cost: " + price);
        }
      } 
    } else if (selection == 2) {
      System.out.print("Type the name of an ingredient: ");
      String ingredientName = input.next().trim().toLowerCase();

      for (int i = 0; i < recipeObjs.size(); i++) {
        List<String> recipeIngredients = recipeObjs.get(i).getRecipeIngredients();

        for (int j = 0; j < recipeIngredients.size(); j++) {
          if (recipeIngredients.get(j).toLowerCase().contains(ingredientName)) {
            // Selection of the matching recipe.
            selection = i + 1;
            details(input, selection);
          }
        }
      }
    }
  }

  private void setRecipeName(String theRecipeName) {
    this.recipeName = theRecipeName;
  }

  public String getRecipeName () {
    return this.recipeName;
  }

  private void setRecipePortion (int theRecipePortion) {
    this.recipePortion = theRecipePortion;
  }

  public int getRecipePortion () {
    return this.recipePortion;
  }

  private void setRecipeUnit (String theUnit) {
    this.recipeUnit = theUnit;
  }

  public String getRecipeUnit () {
    return recipeUnit;
  }

  /**
   * Set recipe ingredients.
   * @param theRecipeIngredients The specific recipe ingredients, amount, measurement and name.
   */
  private void setRecipeIngredients (List<String> theRecipeIngredients) {
    this.recipeIngredients = theRecipeIngredients;
  }

  public List<String> getRecipeIngredients () {
    List<String> listDeepCopy = new ArrayList<>();
    listDeepCopy.addAll(this.recipeIngredients);
    return listDeepCopy;
  }

  public List<String> getRecipeInstructions () {
    List<String> listDeepCopy = new ArrayList<>();
    listDeepCopy.addAll(this.recipeInstructions);
    return listDeepCopy;
  }

  public void printArrayList (List<String> theArray) {
    for (int i = 0; i < theArray.size(); i++) {
      System.out.println(theArray.get(i).trim()); 
    }
    System.out.println();
  }
}