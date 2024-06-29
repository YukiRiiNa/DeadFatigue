package ltd.teaparty.deadfatigue;

import com.handy.playerfatigue.api.PlayerFatigueApi;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;
import java.util.List;

public final class DeadFatigue extends JavaPlugin implements Listener, CommandExecutor {
    private Config config;
    @Override
    public void onLoad(){
        this.config = new Config(this);
    }
    @Override
    public void onEnable() {
        config.loadConfig();
        Bukkit.getPluginManager().registerEvents(this,this);
        Bukkit.getPluginCommand("deadfatigue").setExecutor(this);
        Bukkit.getLogger().info("死亡扣除疲劳值加载成功");
        Bukkit.getLogger().info("你设定的世界为："+Config.world);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void onPlayerDead(PlayerDeathEvent e){
        String count = Config.count;
        Player p = e.getPlayer();
        List<String> list = Config.world;
        for (String s : list) {
//            p.sendMessage(s);
            if(p.getWorld().getName().equals(s)){
                PlayerFatigueApi.take(p.getUniqueId(), Integer.valueOf(count));
                p.sendMessage(Config.message);
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
            if (command.getName().equals("deadfatigue")) {
                this.reloadConfig();
                config.loadConfig();
                Bukkit.getLogger().info("reloaded deadfatigue");
                return true;
            }
        return true;
    }
}
