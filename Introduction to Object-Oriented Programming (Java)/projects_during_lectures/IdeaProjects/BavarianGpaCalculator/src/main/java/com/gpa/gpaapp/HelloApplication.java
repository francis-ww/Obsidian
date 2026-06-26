package com.gpa.gpaapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import jakarta.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HelloApplication extends Application {

    private final ObservableList<Course> data = FXCollections.observableArrayList();
    private final ObservableList<String> semesterOptions = FXCollections.observableArrayList("24秋", "25春", "25秋", "26春");

    private Label totalGpaVal;
    private VBox categoryGpaBox;
    private VBox semesterGpaBox;
    private Label userLabel; // 抽取为全局变量，方便随时刷新显示

    // === 多用户核心变量 ===
    private String currentUser = "HaitianWang";
    private static final String STORAGE_DIR = "gpa_users_data/";

    @Override
    public void start(Stage stage) {
        // 1. 启动时先创建文件夹并执行初次登录/选择
        try {
            Files.createDirectories(Paths.get(STORAGE_DIR));
        } catch (IOException e) {
            System.err.println("无法创建用户数据文件夹: " + e.getMessage());
        }

        if (!showLoginDialog()) {
            System.out.println("用户取消了登录，程序退出。");
            System.exit(0);
        }

        // --- 2. 主布局 ---
        HBox mainRoot = new HBox(20);
        mainRoot.setPadding(new Insets(20));
        mainRoot.setStyle("-fx-background-color: #f4f7f6;");

        // --- 3. 左侧：管理区域 ---
        VBox leftArea = new VBox(15);
        HBox.setHgrow(leftArea, Priority.ALWAYS);

        // 【动态升级】顶部多用户控制状态条 (包含切换、改名)
        HBox userBar = new HBox(12);
        userBar.setAlignment(Pos.CENTER_LEFT);
        userBar.setPadding(new Insets(5, 10, 5, 10));
        userBar.setStyle("-fx-background-color: #eec; -fx-background-radius: 6; -fx-border-color: #ccd; -fx-border-radius: 6;");

        userLabel = new Label();
        refreshUserLabel(); // 初始化显示名字

        Button switchUserBtn = new Button("切换账户");
        switchUserBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;");

        Button renameUserBtn = new Button("修改用户名");
        renameUserBtn.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-size: 12; -fx-font-weight: bold;");

        userBar.getChildren().addAll(userLabel, new Separator(javafx.geometry.Orientation.VERTICAL), switchUserBtn, renameUserBtn);

        TableView<Course> table = new TableView<>();
        table.setEditable(true);
        table.setPlaceholder(new Label("该账户暂无历史存档，请录入第一门课程"));

        // 表格各列配置
        TableColumn<Course, String> semCol = new TableColumn<>("学期");
        semCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
        semCol.setCellFactory(ComboBoxTableCell.forTableColumn(semesterOptions));
        semCol.setOnEditCommit(e -> { e.getRowValue().setSemester(e.getNewValue()); updateAndSave(); });

        TableColumn<Course, String> nameCol = new TableColumn<>("课程名称");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(e -> { e.getRowValue().setName(e.getNewValue()); updateAndSave(); });

        TableColumn<Course, Double> scoreCol = new TableColumn<>("分数");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        scoreCol.setOnEditCommit(e -> { e.getRowValue().setScore(e.getNewValue()); updateAndSave(); });

        TableColumn<Course, Double> creditCol = new TableColumn<>("学分");
        creditCol.setCellValueFactory(new PropertyValueFactory<>("credit"));
        creditCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        creditCol.setOnEditCommit(e -> { e.getRowValue().setCredit(e.getNewValue()); updateAndSave(); });

        TableColumn<Course, CourseType> typeCol = new TableColumn<>("分类");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeCol.setCellFactory(ComboBoxTableCell.forTableColumn(CourseType.values()));
        typeCol.setOnEditCommit(e -> { e.getRowValue().setType(e.getNewValue()); updateAndSave(); });

        table.getColumns().addAll(semCol, nameCol, scoreCol, creditCol, typeCol);
        table.setItems(data);

        // --- 表单输入控件 ---
        ComboBox<String> semIn = new ComboBox<>(semesterOptions); semIn.setValue("26春");
        TextField nameIn = new TextField(); nameIn.setPromptText("输入课程名称");
        TextField scoreIn = new TextField(); scoreIn.setPromptText("0-100");
        TextField creditIn = new TextField(); creditIn.setPromptText("学分");
        ComboBox<CourseType> typeIn = new ComboBox<>(FXCollections.observableArrayList(CourseType.values())); typeIn.setValue(CourseType.COMPULSORY);

        Button addSemBtn = new Button("+ 新增学期");
        Button addBtn = new Button("添加课程");
        Button delBtn = new Button("删除选中");

        // 表单局部布局
        VBox form = new VBox(12);
        form.setPadding(new Insets(15));
        form.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 5);");

        HBox row1 = new HBox(12); row1.setAlignment(Pos.BOTTOM_LEFT);
        VBox itemSem = new VBox(5, new Label("选择学期"), semIn);
        VBox itemName = new VBox(5, new Label("课程名称"), nameIn);
        VBox itemType = new VBox(5, new Label("分类"), typeIn);
        VBox itemScore = new VBox(5, new Label("分数"), scoreIn);
        VBox itemCredit = new VBox(5, new Label("学分"), creditIn);

        semIn.setPrefWidth(90); nameIn.setPrefWidth(220); typeIn.setPrefWidth(110); scoreIn.setPrefWidth(70); creditIn.setPrefWidth(70);
        row1.getChildren().addAll(itemSem, itemName, itemType, itemScore, itemCredit);

        HBox row2 = new HBox(15); row2.setAlignment(Pos.CENTER_LEFT);
        addSemBtn.setStyle("-fx-background-color: #34495e; -fx-text-fill: white; -fx-font-weight: bold;");
        addBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold;");
        delBtn.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; -fx-font-weight: bold;");
        row2.getChildren().addAll(addSemBtn, new Separator(javafx.geometry.Orientation.VERTICAL), addBtn, delBtn);
        form.getChildren().addAll(row1, row2);

        leftArea.getChildren().addAll(userBar, table, form);

        // --- 4. 右侧：结果面板 ---
        ScrollPane rightScroll = new ScrollPane();
        rightScroll.setPrefWidth(330);
        rightScroll.setFitToWidth(true);
        rightScroll.setStyle("-fx-background-color: transparent; -fx-background: #2c3e50; -fx-background-radius: 15;");

        VBox rightArea = new VBox(15);
        rightArea.setPadding(new Insets(25, 20, 20, 20));
        rightArea.setStyle("-fx-background-color: #2c3e50;");
        rightArea.setAlignment(Pos.TOP_CENTER);

        Label title = new Label("总加权绩点 (德国巴伐利亚)"); title.setStyle("-fx-text-fill: #bdc3c7; -fx-font-size: 14;");
        totalGpaVal = new Label("N/A"); totalGpaVal.setStyle("-fx-text-fill: #2ecc71; -fx-font-size: 50; -fx-font-weight: bold;");

        Label catTitle = new Label("各类课程独立绩点明细"); catTitle.setStyle("-fx-text-fill: #95a5a6; -fx-font-size: 13; -fx-font-weight: bold;");
        categoryGpaBox = new VBox(6); categoryGpaBox.setAlignment(Pos.TOP_LEFT);

        Label semTitle = new Label("各个学期绩点明细"); semTitle.setStyle("-fx-text-fill: #95a5a6; -fx-font-size: 13; -fx-font-weight: bold;");
        semesterGpaBox = new VBox(6); semesterGpaBox.setAlignment(Pos.TOP_LEFT);

        rightArea.getChildren().addAll(title, totalGpaVal, new Separator(), catTitle, categoryGpaBox, new Separator(), semTitle, semesterGpaBox);
        rightScroll.setContent(rightArea);

        // --- 5. 交互事件绑定 ---

        // A. 切换用户按钮事件（热切换，不清空程序）
        switchUserBtn.setOnAction(e -> {
            if (showLoginDialog()) {
                refreshUserLabel();
                loadDataFromDisk();
                updateSummary();
            }
        });

        // B. 更改用户名按钮事件（包含物理改名重命名 JSON 文件）
        renameUserBtn.setOnAction(e -> {
            TextInputDialog renameDialog = new TextInputDialog(currentUser);
            renameDialog.setTitle("修改当前用户名");
            renameDialog.setHeaderText("账户重命名");
            renameDialog.setContentText("请输入全新的用户名:");
            Optional<String> result = renameDialog.showAndWait();
            result.ifPresent(newName -> {
                String cleanName = newName.trim();
                if (!cleanName.isEmpty() && !cleanName.equals(currentUser)) {
                    File oldFile = new File(getUserFilePath());
                    this.currentUser = cleanName; // 改变内存指针

                    if (oldFile.exists()) {
                        File newFile = new File(getUserFilePath());
                        oldFile.renameTo(newFile); // 物理重命名本地 JSON 存盘文件
                    } else {
                        saveDataToDisk(); // 若原本无数据则直接新建
                    }

                    refreshUserLabel();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "用户名已成功更改为: " + cleanName);
                    alert.showAndWait();
                }
            });
        });

        addSemBtn.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("新增学期选项"); dialog.setHeaderText("请输入新学期名称"); dialog.setContentText("格式建议 (如 26秋):");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(semName -> {
                String trimmed = semName.trim();
                if (!trimmed.isEmpty() && !semesterOptions.contains(trimmed)) {
                    semesterOptions.add(trimmed); semIn.setValue(trimmed); saveDataToDisk();
                }
            });
        });

        addBtn.setOnAction(e -> {
            try {
                String semText = semIn.getValue(); String nameText = nameIn.getText().trim();
                if (semText == null || nameText.isEmpty()) {
                    new Alert(Alert.AlertType.WARNING, "课程名不能为空！").show(); return;
                }
                data.add(new Course(semText, nameText, Double.parseDouble(scoreIn.getText()), Double.parseDouble(creditIn.getText()), typeIn.getValue()));
                updateAndSave();
                nameIn.clear(); scoreIn.clear(); creditIn.clear();
            } catch (Exception ex) {
                new Alert(Alert.AlertType.WARNING, "请检查分数和学分是否输入正确！").show();
            }
        });

        delBtn.setOnAction(e -> {
            Course selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) { data.remove(selected); updateAndSave(); }
        });

        // 初始数据加载
        loadDataFromDisk();
        updateSummary();

        // --- 6. 最终启动 ---
        mainRoot.getChildren().addAll(leftArea, rightScroll);
        stage.setScene(new Scene(mainRoot, 1150, 660));
        stage.setTitle("巴伐利亚算法 GPA 计算器");
        stage.show();
    }

    private void refreshUserLabel() {
        userLabel.setText("当前账户: " + currentUser);
        userLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50; -fx-font-size: 13;");
    }

    // === 多用户弹窗逻辑 ===
    private boolean showLoginDialog() {
        List<String> choices = new ArrayList<>();
        File folder = new File(STORAGE_DIR);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));
            if (files != null) {
                for (File f : files) {
                    choices.add(f.getName().substring(0, f.getName().lastIndexOf(".json")));
                }
            }
        }
        if (choices.isEmpty()) {
            choices.add("HaitianWang");
        }

        ChoiceDialog<String> dialog = new ChoiceDialog<>(currentUser, choices);
        dialog.setTitle("账户选择登录");
        dialog.setHeaderText("欢迎使用 GPA 计算器");
        dialog.setContentText("请选择登录账户:");

        ButtonType btnCreate = new ButtonType("注册新账户", ButtonBar.ButtonData.LEFT);
        dialog.getDialogPane().getButtonTypes().add(btnCreate);

        Button createBtn = (Button) dialog.getDialogPane().lookupButton(btnCreate);
        createBtn.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
            TextInputDialog newCard = new TextInputDialog("");
            newCard.setTitle("创建新账户");
            newCard.setHeaderText("输入新使用者姓名 (如 ZhangSan)");
            Optional<String> nameOpt = newCard.showAndWait();
            nameOpt.ifPresent(n -> {
                String clean = n.trim();
                if (!clean.isEmpty() && !dialog.getItems().contains(clean)) {
                    dialog.getItems().add(clean);
                    dialog.setSelectedItem(clean);
                }
            });
            event.consume();
        });

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            this.currentUser = result.get();
            return true;
        }
        return false;
    }

    private String getUserFilePath() { return STORAGE_DIR + currentUser + ".json"; }
    private void updateAndSave() { updateSummary(); saveDataToDisk(); }

    private void saveDataToDisk() {
        JsonArrayBuilder courseArrayBuilder = Json.createArrayBuilder();
        for (Course c : data) {
            courseArrayBuilder.add(Json.createObjectBuilder()
                    .add("semester", c.getSemester()).add("name", c.getName())
                    .add("score", c.getScore()).add("credit", c.getCredit()).add("type", c.getType().name()));
        }
        JsonArrayBuilder semOptionsBuilder = Json.createArrayBuilder();
        for (String opt : semesterOptions) { semOptionsBuilder.add(opt); }

        JsonObject rootObj = Json.createObjectBuilder().add("semestersPool", semOptionsBuilder).add("courses", courseArrayBuilder).build();
        try (Writer writer = Files.newBufferedWriter(Paths.get(getUserFilePath()))) {
            JsonWriter jsonWriter = Json.createWriter(writer); jsonWriter.write(rootObj); jsonWriter.close();
        } catch (IOException e) { System.err.println("用户存档失败: " + e.getMessage()); }
    }

    private void loadDataFromDisk() {
        File file = new File(getUserFilePath());
        if (!file.exists()) { data.clear(); return; }
        try (InputStream is = Files.newInputStream(file.toPath()); JsonReader jsonReader = Json.createReader(is)) {
            JsonObject rootObj = jsonReader.readObject();
            if (rootObj.containsKey("semestersPool")) {
                semesterOptions.clear();
                JsonArray pool = rootObj.getJsonArray("semestersPool");
                for (JsonValue val : pool) { semesterOptions.add(((JsonString) val).getString()); }
            }
            data.clear();
            JsonArray courses = rootObj.getJsonArray("courses");
            for (JsonValue val : courses) {
                JsonObject cObj = (JsonObject) val;
                data.add(new Course(cObj.getString("semester"), cObj.getString("name"),
                        cObj.getJsonNumber("score").doubleValue(), cObj.getJsonNumber("credit").doubleValue(),
                        CourseType.valueOf(cObj.getString("type"))));
            }
        } catch (Exception e) { System.err.println("读取用户存档失败: " + e.getMessage()); }
    }

    private void updateSummary() {
        if (data.isEmpty()) {
            totalGpaVal.setText("N/A"); categoryGpaBox.getChildren().clear(); semesterGpaBox.getChildren().clear(); return;
        }

        double totalW = 0, totalC = 0;
        Map<CourseType, double[]> categoryMap = new LinkedHashMap<>();
        Map<String, double[]> semesterMap = new LinkedHashMap<>();

        for (Course c : data) {
            double score = c.getScore();
            double crd = c.getCredit();
            if (score < 60) continue;

            double gpa = 1.0 + 3.0 * (100.0 - score) / 40.0;
            if (gpa < 1.0) gpa = 1.0;

            totalW += gpa * crd; totalC += crd;

            if (!categoryMap.containsKey(c.getType())) { categoryMap.put(c.getType(), new double[]{0.0, 0.0}); }
            categoryMap.get(c.getType())[0] += gpa * crd;
            categoryMap.get(c.getType())[1] += crd;

            String sem = c.getSemester();
            if (!semesterMap.containsKey(sem)) { semesterMap.put(sem, new double[]{0.0, 0.0}); }
            semesterMap.get(sem)[0] += gpa * crd;
            semesterMap.get(sem)[1] += crd;
        }

        totalGpaVal.setText(totalC == 0 ? "N/A" : String.format("%.2f", totalW / totalC));

        categoryGpaBox.getChildren().clear();
        for (Map.Entry<CourseType, double[]> entry : categoryMap.entrySet()) {
            double catGpa = entry.getValue()[0] / entry.getValue()[1];
            Label catLabel = new Label(String.format(" ■ %s 绩点: %.2f (共%.2f学分)", entry.getKey().getLabel(), catGpa, entry.getValue()[1]));
            if (entry.getKey() == CourseType.MINOR_COMPULSORY || entry.getKey() == CourseType.MINOR_ELECTIVE) {
                catLabel.setStyle("-fx-text-fill: #e67e22; -fx-font-size: 13; -fx-padding: 2 0; -fx-font-weight: bold;");
            } else {
                catLabel.setStyle("-fx-text-fill: #ecf0f1; -fx-font-size: 13; -fx-padding: 2 0;");
            }
            categoryGpaBox.getChildren().add(catLabel);
        }

        semesterGpaBox.getChildren().clear();
        for (Map.Entry<String, double[]> entry : semesterMap.entrySet()) {
            double semW = entry.getValue()[0];
            double semC = entry.getValue()[1];
            Label semLabel = new Label(String.format(" • %s 学期绩点: %.2f (共%.2f学分)", entry.getKey(), semW / semC, semC));
            semLabel.setStyle("-fx-text-fill: #9b59b6; -fx-font-size: 13; -fx-padding: 2 0;");
            semesterGpaBox.getChildren().add(semLabel);
        }
    }

    public static void main(String[] args) { launch(args); }
}