package fr.gohansword.detectionarrow;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utils {
    public static ArrayList<Arrow> arrows = new ArrayList<>();
    public static List<String> arrow_final_command ;
    public static  String arrow_permission = "";
    public static  String arrow_final_message = "";
    public static ArrayList<Head> heads = new ArrayList<>();
    public static List<String> head_final_command ;
    public static  String head_permission = "";
    public static  String head_final_message = "";
    private static Detectionarrow instance = Detectionarrow.getInstance();


    public static void loadHeads() {
        int headcount = 0;
        heads.clear();
        File folder = new File(instance.getDataFolder() + "/heads");
        if (folder.listFiles() == null)
            return;
        for (File file : (File[]) Objects.<File[]>requireNonNull(folder.listFiles())) {
            if (!file.isDirectory() || file.getName().contains(".yml")) {
                System.out.println(file.getName());
                heads.add(Head.getHead(file.getName().replace(".yml", "")));
                headcount++;
            }
        }
        head_final_command = Detectionarrow.getInstance().getConfig().getStringList("heads.commands");
        head_permission = Detectionarrow.getInstance().getConfig().getString("heads.permission_needed");
        head_final_message =  Detectionarrow.getInstance().getConfig().getString("heads.final_message_to_player");

    }

    public static FileConfiguration getHead(String name) {
        File file = new File(instance.getDataFolder() + "/heads", name);
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        try {
            yamlConfiguration.load(file);
        } catch (IOException |org.bukkit.configuration.InvalidConfigurationException iOException) {}
        return (FileConfiguration)yamlConfiguration;

    }

    public static void loadArrows() {
        int arrowcount = 0;
        arrows.clear();
        File folder = new File(instance.getDataFolder() + "/arrows");
        if (folder.listFiles() == null)
            return;
        for (File file : (File[]) Objects.<File[]>requireNonNull(folder.listFiles())) {
            if (!file.isDirectory() || file.getName().contains(".yml")) {
                arrows.add(Arrow.getArrow(file.getName().replace(".yml", "")));
                arrowcount++;
            }
        }
        arrow_final_command = Detectionarrow.getInstance().getConfig().getStringList("arrows.commands");
        arrow_permission = Detectionarrow.getInstance().getConfig().getString("arrows.permission_needed");
        arrow_final_message =  Detectionarrow.getInstance().getConfig().getString("arrows.final_message_to_player");

    }

    public static FileConfiguration getArrow(String name) {
        File file = new File(instance.getDataFolder() + "/arrows", name);
        YamlConfiguration yamlConfiguration = new YamlConfiguration();
        try {
            yamlConfiguration.load(file);
        } catch (IOException |org.bukkit.configuration.InvalidConfigurationException iOException) {}
        return (FileConfiguration)yamlConfiguration;

    }


}
