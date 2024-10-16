package com.recipeapp.ui;

public class Ingredient {
    private String name; // 材料名

    // コンストラクタ
    public Ingredient(String name) {
        this.name = name; // 材料名をフィールドに設定
    }

    // nameフィールドを返すメソッド
    public String getName() {
        return name;
    }
}

