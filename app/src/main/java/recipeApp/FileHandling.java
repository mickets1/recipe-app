package recipeApp;

import java.io.File;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


  /**
   * Reads the recipe and ingredient file.
   */
public class FileHandling {
  private List<Ingredient> ingredientList = new ArrayList<Ingredient>();
  private List<Recipe> recipeList = new ArrayList<>();
  private String projectPath = "";
  private String filePath;

  FileHandling (String fileName) {
    // Absolute file path.
    this.projectPath = System.getProperty("user.dir") + "/app/src/main/java/recipeApp/recipes/";
    this.filePath = this.projectPath + fileName;
  }

  /**
   * constructor used for test case.
   */
  FileHandling () {
  }

  public String readFile () {
    try {
      Scanner scan = new Scanner(new File(this.filePath));

      StringBuilder str = new StringBuilder();

      while (scan.hasNextLine()) {
        str.append(scan.nextLine());
      }

      return str.toString();

    } catch (FileNotFoundException e) {
      System.out.println(e);
      e.printStackTrace();
    }
    return "File reading error.";
  }

  public List<Ingredient> getParsedIngredients () {
    String[] ingredientArr = this.readFile().split(";");

    for (int i = 0; i < ingredientArr.length; i++) {
      String[] ing = ingredientArr[i].split(":");
      
      String name = ing[0];
      String unit = ing[1];
      int price = Integer.parseInt(ing[2].trim());
      
      Ingredient ingredient = new Ingredient(name, unit, price);

      ingredientList.add(ingredient);
    }
    return ingredientList;
  }

  public List<Recipe> getParsedRecipes (List<Ingredient> ingredients) {
    List<String> recipeArr = Arrays.asList(this.readFile().split("\\s*:\\s*"));

    if (recipeArr.size() == 0 || recipeArr.get(0).equals("")) {
      System.out.println("\nThe recipe file is empty.");
      return recipeList;
    }

    for (int i = 0; i < recipeArr.size(); i++) {
      List<String> recipeInstructions = new ArrayList<String>();

      List<String> parsedRecipe = Arrays.asList(recipeArr.get(i).split(";"));
      String recipeName = parsedRecipe.get(0);
      int recipePortion = Integer.parseInt(parsedRecipe.get(1).trim());
      String recipeUnit = parsedRecipe.get(2).trim();

      List<String> recipeIngredientList = Arrays.asList(parsedRecipe.get(3).split("\\|"));
      
      for (int j = 4; j < parsedRecipe.size(); j++) {
        recipeInstructions.add(parsedRecipe.get(j));
      }

      Recipe recipe = new Recipe(recipeName, recipePortion, recipeUnit, recipeIngredientList, ingredients, recipeInstructions);
      recipeList.add(recipe);
    }
    return recipeList;
  }

  public void saveRecipes (List<Recipe> recipeObj) {
    try {
      Writer wr = new FileWriter(this.filePath, false);

      for (int i = 0; i < recipeObj.size(); i++) {
        String convertRecIng = "";
        String convertRecIns = "";
        // Single recipe.
        Recipe sr = recipeObj.get(i);
        List<String> recipeIngredients = sr.getRecipeIngredients();

        int recipeIngLength = recipeIngredients.size();

        for (int j = 0; j < recipeIngLength; j++) {
          convertRecIng += recipeIngredients.get(j).trim();

           if (j == recipeIngLength - 1) {
            break;
          }
          
          convertRecIng += "| ";
        }
        
        List<String> recipeInstructions = sr.getRecipeInstructions();

        for (int k = 0; k < recipeInstructions.size(); k++) {
          convertRecIns += recipeInstructions.get(k).trim() + ";";
        }

        wr.write(sr.getRecipeName() + "; " + sr.getRecipePortion() + "; " + sr.getRecipeUnit() + "; " + convertRecIng + "; " + convertRecIns + ":\n") ;
      }

      wr.flush();
      wr.close();

      return;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void saveIngredients (List<Ingredient> ingredientObj) {
    try {
      Writer wr = new FileWriter(this.filePath, false);

      String ingredients = "";

      for (int i = 0; i < ingredientObj.size(); i++) {
        ingredients += ingredientObj.get(i).getIngredientName() + ":" + ingredientObj.get(i).getIngredientMeasurement() + ":" + ingredientObj.get(i).getIngredientPrice() + ";";
      }
      wr.write(ingredients);

      wr.flush();
      wr.close();
      
      return;
} catch (Exception e) {
  e.printStackTrace();
    }
  }

  /**
   * Setter used for test case.
   */
  public void setFilePath (String theFilePath) {
    this.filePath = theFilePath;
  }
}
