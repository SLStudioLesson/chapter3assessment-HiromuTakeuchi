package com.recipeapp.ui.datahandler;

import com.recipeapp.ui.Ingredient; // Ingredientクラスのインポート
import com.recipeapp.ui.Recipe; // Recipeクラスのインポート
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter; // FileWriterをインポート
import java.io.IOException;
import java.util.ArrayList;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    // コンストラクタ
    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv"; // デフォルトのCSVファイルパス
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath; // 引数で指定されたパス
    }

    @Override
    public String getMode() {
        return "CSV"; // モードを返す
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String recipeName = parts[0].trim();
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for (int i = 1; i < parts.length; i++) {
                    ingredients.add(new Ingredient(parts[i].trim()));
                }
                recipes.add(new Recipe(recipeName, ingredients));
            }
        }
        return recipes; // 読み込んだレシピのリストを返す
    }

    @Override
    public void writeData(Recipe recipe) throws IOException {
        // レシピ名と材料をカンマ区切りで1つの文字列にする
        StringBuilder recipeData = new StringBuilder(recipe.getName());
        for (Ingredient ingredient : recipe.getIngredients()) {
            recipeData.append(",").append(ingredient.getName()); // IngredientクラスのgetNameメソッドを使う
        }

        // CSVファイルに追記モードで書き込む
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(recipeData.toString() + "\n");
        }
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null; // 検索処理は未実装
    }
}
