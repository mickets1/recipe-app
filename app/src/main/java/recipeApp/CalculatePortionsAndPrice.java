package recipeApp;

import java.util.ArrayList;
import java.util.List;

/**
   * Calculates recipe price and portions.
   */
public class CalculatePortionsAndPrice {
  private List<Ingredient> ingredientObjs = new ArrayList<Ingredient>();
  private double conversionFactor;

  public CalculatePortionsAndPrice (List<Ingredient> theIngredients) {
    this.ingredientObjs = theIngredients;
  }

  /**
   * Calculates the price when changing recipe portions.
   */
  public int price (List<String> theRecipeIngredients) {
    int price = 0;

    // If method is called by search.
    if (conversionFactor == 0) {
      conversionFactor = 1;
    }
      
      // Loop for ingredients in the recipe.
      for (int j = 0; j < theRecipeIngredients.size(); j++) {
        String recipeIngredientName = theRecipeIngredients.get(j).split(",")[2].trim().toLowerCase();
        
        // Loop for ingredient objects.
        for (int k = 0; k < ingredientObjs.size(); k++) {
          String ingredientName = ingredientObjs.get(k).getIngredientName().trim().toLowerCase();

          // If recipe ingredients contains specific ingredients.
          if (ingredientName.equals(recipeIngredientName)) {
            price += ingredientObjs.get(k).getIngredientPrice() * conversionFactor;
          }
        }
      }
    return price;
  }

  /**
   * Updates the recipe based on new portions.
   */
  public void printNewRecipe (int portion, Recipe recipe) {
    List<String> recipeIngredients = recipe.getRecipeIngredients();
    int recipePortion = recipe.getRecipePortion();
    int round = 0;

    this.conversionFactor = (double) portion / recipePortion;

    System.out.println("\nRecipe Name: " + recipe.getRecipeName());
    System.out.println("\n*Updated portions: " + portion);
    System.out.println("\nRecipe Unit: " + recipe.getRecipeUnit());

    System.out.println("\n*Updated Recipe Ingredients: ");
    for (int i = 0; i < recipeIngredients.size(); i++) {
      String[] singleRecIng = recipeIngredients.get(i).split(",");
      int amount = Integer.parseInt(singleRecIng[0].trim());
        
      // Updates recipe ingredients based on portions.
      round = (int) Math.ceil(amount * conversionFactor);

      // Retreving Ingredient unit measurement.
      for (int j = 0; j < ingredientObjs.size(); j++) {
        String ingredientName = ingredientObjs.get(j).getIngredientName().trim().toLowerCase();

        if (ingredientName.equals(singleRecIng[2].trim().toLowerCase())) {
          System.out.println(round + " " + ingredientObjs.get(j).getIngredientMeasurement() + " " + ingredientName);
          // break on first match.
          break;
        }
      }
    }
    System.out.println("\nRecipe Instructions: ");
    recipe.printArrayList(recipe.getRecipeInstructions());

    int updatedPrice = price(recipe.getRecipeIngredients());
    System.out.println("*New price: " + updatedPrice);
  }
}
