import com.recipeapp.ui.datahandler.CSVDataHandler;
import com.recipeapp.ui.datahandler.JSONDataHandler;
import com.recipeapp.ui.datahandler.DataHandler;
import com.recipeapp.ui.RecipeUI;


import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // ユーザー入力のためのScannerを初期化
        Scanner scanner = new Scanner(System.in);
        DataHandler dataHandler;

        // ファイル形式選択メニューを表示
        System.out.println("ファイル形式選択メニュー");
        System.out.println("Choose the file format:");
        System.out.println("1. CSV");
        System.out.println("2. JSON");
        System.out.print("Select (1/2): ");

        String input = scanner.nextLine();

        // ユーザーの選択に応じてデータハンドラーを初期化
        if (input.equals("1")) {
            dataHandler = new CSVDataHandler(); // CSVDataHandlerのインスタンスを生成
        } else if (input.equals("2")) {
            dataHandler = new JSONDataHandler(); // JSONDataHandlerのインスタンスを生成
        } else {
            dataHandler = new CSVDataHandler(); // 不正な入力の場合はCSVDataHandlerを使用
        }

        // 現在のモードを表示
        System.out.println("Current mode: " + dataHandler.getMode());

        // RecipeUIを初期化し、メインメニューを表示
        RecipeUI recipeUI = new RecipeUI(dataHandler);
        recipeUI.displayMenu(); // メインメニューを表示

        // Scannerを閉じる
        scanner.close();
    }
}
