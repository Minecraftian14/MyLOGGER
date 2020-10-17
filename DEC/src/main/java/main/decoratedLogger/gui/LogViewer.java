package main.decoratedLogger.gui;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import main.generalLogger.LOGGER;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class LogViewer extends Application {

    static HashMap<String, HashSet<String>> class_to_method;
    static HashMap<String, ArrayList<String>> method_to_log;

    Scene main_scene;
    Group root;

    Scene[] sub_scenes;
    Group[] sub_roots;
    Rotate rotate;
    TranslateTransition translate = new TranslateTransition(Duration.seconds(1));
    Button[] buttons;
    TabPane[] tab_panes;
    ScrollPane[] scroll_panes;

    static final double root2 = Math.sqrt(2);
    Dimension window = new Dimension(800, 600);
    int but_t_hei;
    int but_t_wid;

    @Override
    public void init() {
        root = new Group();
        main_scene = new Scene(root, window.getWidth(), window.getHeight(), Color.BLACK);
        calculate();

        sub_roots = new Group[class_to_method.size()];
        sub_scenes = new Scene[class_to_method.size()];
        for (int i = 0; i < class_to_method.size(); i++) {
            sub_scenes[i] = new Scene(sub_roots[i] = new Group(), window.getWidth(), window.getHeight());
//            sub_scenes[i].getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        }

        addButtons();
    }

    public void calculate() {
        but_t_hei = (int) ((3 * window.getWidth() + window.getHeight()) * root2 / (6 * Math.max(1, class_to_method.size() + 2)));
        but_t_wid = (int) (Math.max(window.getWidth(), window.getHeight()) * root2);
    }

    public void addButtons() {
        buttons = new Button[class_to_method.size() + 2];
        rotate = new Rotate(-45, but_t_wid / 2, but_t_hei / 2);

        for (int i = 0; i < class_to_method.size() + 2; i++) {

            if (i == 0 || i == class_to_method.size() + 1) {
                buttons[i] = new Button();
            } else
                buttons[i] = new Button(class_to_method.keySet().toArray(new String[0])[i-1]);

            CSS j = new CSS();
            buttons[i].styleProperty().bind(Bindings
                    .when(buttons[i].hoverProperty())
                    .then(j.getHover())
                    .otherwise(j.getNormal())
            );
            buttons[i].setFont(Font.font("", FontWeight.BOLD, 30));
            buttons[i].setTextFill(Color.FLORALWHITE);

            buttons[i].setPrefHeight(but_t_hei);
            buttons[i].setPrefWidth(but_t_wid);

            buttons[i].setLayoutX((window.getWidth() - but_t_wid) / 2 - (((class_to_method.size() + 1) / 2 - i) * but_t_hei * root2));
            buttons[i].setLayoutY((window.getHeight() - but_t_hei) / 2);

            buttons[i].getTransforms().add(rotate);

            root.getChildren().add(buttons[i]);
        }
    }

    public void addTabPanes() {
        tab_panes = new TabPane[class_to_method.size()];

        for (int i = 0; i < class_to_method.size(); i++) {

            String[] methods_reference = class_to_method.get(buttons[i + 1].getText()).toArray(new String[0]);
            Tab[] tabs = new Tab[methods_reference.length + 1];

            for (int j = 0; j < tabs.length - 1; j++) {
                WebView webView = new WebView();

                StringBuilder builder = new StringBuilder();
                builder.append("<HTML><HEAD><STYLE>*{overflow: visible;}</STYLE></HEAD><BODY><P>");
                method_to_log.get(methods_reference[j]).forEach(builder::append);
                builder.append("<br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /><br /></P></BODY></HTML>");

                webView.getEngine().loadContent(builder.toString());
//                webView.getEngine().load("https://www.google.com/");

                tabs[j] = new Tab(methods_reference[j]);
                tabs[j].setContent(webView);
                webView.setPrefWidth(300);
            }
            tabs[tabs.length - 1] = new Tab("Back");

            tab_panes[i] = new TabPane(tabs);
            tab_panes[i].setPrefWidth(window.getWidth());
            tab_panes[i].setPrefHeight(window.getHeight());
            tab_panes[i].setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

            sub_roots[i].getChildren().add(tab_panes[i]);
        }
    }

    @Override
    public void start(Stage stage) {
//        main_scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        addTabPanes();

        for (int i = 0; i < class_to_method.size(); i++) {
            int I = i;
            buttons[i + 1].setOnMouseClicked(mouseEvent -> {
                translate = new TranslateTransition(Duration.seconds(1), buttons[I + 1]);
                translate.setByX(1000);
                translate.setByY(-1000);
                translate.play();
                translate.setOnFinished(actionEvent -> {
                    stage.setScene(sub_scenes[I]);
                    translate = new TranslateTransition(Duration.seconds(1), buttons[I + 1]);
                    translate.setByX(-1000);
                    translate.setByY(1000);
                    translate.play();
                });
            });

            tab_panes[i].setOnMouseClicked(mouseEvent -> {
                if (tab_panes[I].getSelectionModel().getSelectedItem().getText().equals("Back"))
                    stage.setScene(main_scene);
            });
        }

        ChangeListener<Number> listener = (obsVal, oldVal, newVal) -> {
//            window.setSize(window.getWidth() + (newVal.doubleValue() - oldVal.doubleValue()), window.getHeight());
            window.setSize(stage.getWidth(), stage.getHeight());
            resize();
        };

        stage.widthProperty().addListener(listener);
        stage.heightProperty().addListener(listener);

        stage.setScene(main_scene);
        stage.show();

    }

    public void resize() {
        calculate();
        for (int i = 0; i < class_to_method.size() + 2; i++) {
            if (buttons[i].getTransforms().size() >= 1)
                buttons[i].getTransforms().retainAll();

            buttons[i].setPrefHeight(but_t_hei);
            buttons[i].setPrefWidth(but_t_wid);

            buttons[i].setLayoutX((window.getWidth() - but_t_wid) / 2 - ((((class_to_method.size() + 1) >> 1) - i) * but_t_hei * root2));
            buttons[i].setLayoutY((window.getHeight() - but_t_hei) / 2);
            buttons[i].getTransforms().add(rotate);

            if (i == 0 || i == class_to_method.size() + 1)
                continue;

            tab_panes[i - 1].setPrefWidth(window.getWidth());
            tab_panes[i - 1].setPrefHeight(window.getHeight());
        }
    }

    public static void main(String[] args) {

        String path = "";
        if (args.length >= 1)
            path = args[0];
        else {
            path = System.getProperty("user.dir");
            File[] fp = new File(path).listFiles((dir, name) -> name.matches("logs_(.+)"));
            if (fp == null || fp.length == 0) {
                LOGGER.error("No valid file found in: ", path);
                System.exit(0);
            }
            path = fp[0].getAbsolutePath();
        }

        LOGGER.general("Directory cosen at: ", path, "\n");

        try {
            FileInputStream fis = new FileInputStream(new File(path + File.separator + "class_to_method.data"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            class_to_method = new HashMap<>((HashMap<String, HashSet<String>>) ois.readObject());
            ois.close();
            fis.close();

//            System.out.println(class_to_method);
            fis = new FileInputStream(new File(path + File.separator + "method_to_log.data"));
            ois = new ObjectInputStream(fis);
            method_to_log = new HashMap<>((HashMap<String, ArrayList<String>>) ois.readObject());
            ois.close();
            fis.close();

//            System.out.println(method_to_log);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        launch();

        /*HashMap<String, HashSet<String>> ctm = new HashMap<>();
        HashMap<String, ArrayList<String>> mtl = new HashMap<>();
        ctm.putIfAbsent("ClassA", new HashSet<>());
        ctm.get("ClassA").addAll(List.of("MethodAA", "MethodAB", "MethodAC"));
        ctm.putIfAbsent("ClassB", new HashSet<>());
        ctm.get("ClassB").addAll(List.of("MethodBA", "MethodBC"));
        ctm.putIfAbsent("ClassC", new HashSet<>());
        ctm.get("ClassC").addAll(List.of("MethodCA"));
        ctm.putIfAbsent("ClassD", new HashSet<>());
        ctm.get("ClassD").addAll(List.of("MethodDA", "MethodDB", "MethodDC"));
        ctm.putIfAbsent("ClassE", new HashSet<>());
        ctm.get("ClassE").addAll(List.of("MethodEA", "MethodEC"));
        mtl.putIfAbsent("MethodAA", new ArrayList<>());
        mtl.get("MethodAA").addAll(List.of("Hello World"));
        mtl.putIfAbsent("MethodAB", new ArrayList<>());
        mtl.get("MethodAB").addAll(List.of(Html.Wrap.BOLD("Hello World")));
        mtl.putIfAbsent("MethodAC", new ArrayList<>());
        mtl.get("MethodAC").addAll(List.of("Hello World"));
        mtl.putIfAbsent("MethodBA", new ArrayList<>());
        mtl.get("MethodBA").addAll(List.of("Hello World"));
        mtl.putIfAbsent("MethodBC", new ArrayList<>());
        mtl.get("MethodBC").addAll(List.of("Hello World"));
        mtl.putIfAbsent("MethodCA", new ArrayList<>());
        mtl.get("MethodCA").addAll(List.of("Hello World"));
        mtl.putIfAbsent("MethodDA", new ArrayList<>());
        mtl.get("MethodDA").addAll(List.of("Hello World"));
        mtl.putIfAbsent("MethodDB", new ArrayList<>());
        mtl.get("MethodDB").addAll(List.of("Hello World"));
        mtl.putIfAbsent("MethodDC", new ArrayList<>());
        mtl.get("MethodDC").addAll(List.of("Hello World"));
        mtl.putIfAbsent("MethodEA", new ArrayList<>());
        mtl.get("MethodEA").addAll(List.of("Hello World"));
        mtl.putIfAbsent("MethodEC", new ArrayList<>());
        mtl.get("MethodEC").addAll(List.of("Hello World"));
        new LogViewer(ctm, mtl);*/
    }

    public LogViewer() {
    }

}
