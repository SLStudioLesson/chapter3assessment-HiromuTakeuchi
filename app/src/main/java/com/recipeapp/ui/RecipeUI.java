package com.recipeapp.ui;

import com.recipeapp.ui.datahandler.DataHandler; // インポート文を修正
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }
    
    public void displayMenu() {
        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        displayRecipes(); // displayRecipesメソッドを呼び出す
                        break;
                    case "2":
                        addNewRecipe(); // 新しいレシピを追加するメソッドを呼び出す
                        break;
                    case "3":
                        // Search recipe functionality here
                        break;
                    case "4":
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void displayRecipes() {
        try {
            ArrayList<Recipe> recipes = dataHandler.readData(); // レシピデータを取得

            if (recipes.isEmpty()) {
                System.out.println("No recipes available."); // レシピがない場合のメッセージ
            } else {
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");
                for (Recipe recipe : recipes) {
                    System.out.println("Recipe Name: " + recipe.getName());
                    System.out.println("Main Ingredients: " + getIngredientNames(recipe.getIngredients()));
                    System.out.println("-----------------------------------");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage()); // ファイル読み込みエラー
        }
    }

    private String getIngredientNames(ArrayList<Ingredient> ingredients) {
        StringBuilder ingredientNames = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            ingredientNames.append(ingredients.get(i).getName());
            if (i < ingredients.size() - 1) {
                ingredientNames.append(", "); // 材料の間にカンマを挿入
            }
        }
        return ingredientNames.toString();
    }

    private void addNewRecipe() {
        try {
            System.out.println("Adding a new recipe.");
            System.out.print("Enter recipe name: ");
            String recipeName = reader.readLine();

            ArrayList<Ingredient> ingredients = new ArrayList<>();
            System.out.println("Enter ingredients (type 'done' when finished):");
            while (true) {
                System.out.print("Ingredient: ");
                String ingredientName = reader.readLine();
                if (ingredientName.equalsIgnoreCase("done")) {
                    break; // 'done'が入力されたらループを終了
                }
                ingredients.add(new Ingredient(ingredientName.trim())); // 新しいIngredientオブジェクトを追加
            }

            Recipe newRecipe = new Recipe(recipeName, ingredients); // 新しいRecipeオブジェクトを作成
            dataHandler.writeData(newRecipe); // レシピをデータハンドラーに書き込む
            System.out.println("Recipe added successfully."); // 成功メッセージを表示
        } catch (IOException e) {
            System.out.println("Failed to add new recipe: " + e.getMessage()); // エラーメッセージを表示
        }
    }
}
