package net.bless.armoreq;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import net.bless.armoreq.recipes.DiamondArmorSet;
import net.bless.armoreq.recipes.LeatherArmorSet;
import net.bless.armoreq.recipes.ToolSet;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
public class ArmorEQ extends JavaPlugin
implements Listener
{
    public static Plugin plugin;        // for storing a global link to this plugin (used in ArmorP)
    public static String pluginName;    // for Log messages
    public static String pluginVersion; // for Log messages

    @Override
    public void onEnable() {
        // Getting ready! (prepare some variables for later use)
        ArmorEQ.plugin = this;    
        pluginName = this.getDescription().getName();
        pluginVersion = this.getDescription().getVersion();
        Log.setConfigVerbosity(getConfig());
        
        // Using this "copy" code rather than "getConfig().options().copyDefaults(true); saveConfig();"
        // as this code preserves comments and exact structure of the config.yml saved in the plugin jar
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            copy(getResource("config.yml"), file);
        }
        
        // Load up them recipes!
        addRecipes();
    }
    
    private void addRecipes() {
        LeatherArmorSet.loadSet("Quartz");
        LeatherArmorSet.loadSet("Emerald");
        DiamondArmorSet.loadSet("Legendary Diamond");
        ToolSet.loadSet("Quartz");
    }
    
    /** 
     *  getColorFrom(string) - obtain "Rich" colors from org.bukkit.Color
     *  otherwise match using DyeColor.  (needed as there is no way to 
     *  go from the string directly to a "Color").  Will eventually support
     *  "R/G/B" format.
     *  
     * @author zarius
     * @param sub
     * @return
     */
    public static Color getColorFrom(String sub) {
        Color color = null;
        if (sub.matches("(?i)RICH.*")) {
            if (sub.equalsIgnoreCase("RICHGREEN")) {
                color = Color.GREEN;
            } else if (sub.equalsIgnoreCase("RICHRED")) {
                color = Color.RED;
            } else if (sub.equalsIgnoreCase("RICHBLUE")) {
                color = Color.BLUE;
            } else if (sub.equalsIgnoreCase("RICHLIME")) {
                color = Color.LIME;
            } else if (sub.equalsIgnoreCase("RICHYELLOW")) {
                color = Color.YELLOW;
            }
        } else {
            // FIXME: add ability to use Color values too - they are
            // richer/stronger colors
            color = DyeColor.valueOf(sub.toUpperCase()).getColor();
        }
        return color;
    }

    private void copy(InputStream in, File file) {
        try {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}