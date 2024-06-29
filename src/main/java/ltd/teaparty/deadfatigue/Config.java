package ltd.teaparty.deadfatigue;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {
    private final DeadFatigue plugin;
    public static String count;
    public static String message;
    public static List<String> world;

    Config(DeadFatigue plugin) {
        this.plugin = plugin;
    }


    public void loadConfig(){
        plugin.saveDefaultConfig();
        FileConfiguration config = plugin.getConfig();
        count = config.getString("count","");
        message = config.getString("message","");
        world = config.getStringList("world");
    }
}
