package fr.gohansword.detectionarrow;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Playerdata {
    private File playerConfigFile;
    private FileConfiguration playerConfig;


    public FileConfiguration getplayerConfig() {
        return this.playerConfig;
    }

    public void createplayerConfig() {
        this.playerConfigFile = new File(Detectionarrow.getInstance().getDataFolder(), "playerdata.yml");
        if (!this.playerConfigFile.exists()) {
            this.playerConfigFile.getParentFile().mkdirs();
            Detectionarrow.getInstance().saveResource("playerdata.yml", false);
        }

        this.playerConfig = (FileConfiguration) new YamlConfiguration();
        try {
            this.playerConfig.load(this.playerConfigFile);
        } catch (IOException | org.bukkit.configuration.InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}

