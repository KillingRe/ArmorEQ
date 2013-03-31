package net.bless.armoreq;
 
import java.util.List;
 
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
 
public class ArmorP {
public String name;
public Color color;
public Short durability;
public List<String> lore;
public Enchantment ench;
public int enchLevel;
public Material mat;
public Material recipeMat;
 
public static ArmorP getArmor(String name) {
                ArmorP armor = readConfigValues(name);
                return armor;
        }
        private static ArmorP readConfigValues(String name) {
                ArmorP armor = new ArmorP();
 
                Integer enchLevel  = ArmorEQ.plugin.getConfig().getInt("armor."+name.toLowerCase()+".enchantment.level");
 
                if (enchLevel == null) return null;
                armor.enchLevel = enchLevel;
 
 
                // etc....
        }

}