package com.recipeapp.ui;

import java.util.ArrayList;

public class Recipe {
    private String name; // レシピの名前
    private ArrayList<Ingredient> ingredients; // 材料のリスト

    // コンストラクタ
    public Recipe(String name, ArrayList<Ingredient> ingredients) {
        this.name = name;
        this.ingredients = ingredients; // フィールドに引数を設定
    }

    // レシピ名を返すメソッド
    public String getName() {
        return name;
    }

    // 材料リストを返すメソッド
    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
