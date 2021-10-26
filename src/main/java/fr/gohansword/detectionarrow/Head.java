package fr.gohansword.detectionarrow;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Head {
        public String name = "";
        public List<String> commands ;
        public List<String> message_to_player ;
        public Location loc;

        public Head(String command,String message_to_player,Location loc){
            command= command;
            message_to_player = message_to_player;
            loc = loc;
        }
        public static Head getHead(String name) {
            return new Head(name);
        }
        public Head(String name) {
            this.name = name;
            FileConfiguration config = Utils.getHead(name + ".yml");
            this.loc = new Location(Bukkit.getWorld(config.getString("location.world")), config.getDouble("location.x"), config.getDouble("location.y"), config.getDouble("location.z"), (float)config.getDouble("location.yaw"), (float)config.getDouble("location.pitch"));
            this.commands = config.getStringList("commands");
            this.message_to_player = config.getStringList("messages");
        }


    }
