package net.bless.armoreq;

import java.util.List;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class ArmorP {
    public String name;
    public Color color;
    public Short durability;
    public List<String> lore;
    public Enchantment ench;
    public int enchLevel;
    public Material recipeMat;

    static ArmorP getArmor(String name) {
        ArmorP armor = readConfigValues(name);
        return armor;
    }

    private static ArmorP readConfigValues(String name) {
        ArmorP armor = new ArmorP();

        armor.name = name;
        Integer enchLevel  = ArmorEQ.plugin.getConfig().getInt("armor."+name.toLowerCase()+".enchantment.level");

        if (enchLevel == null) return null;
        armor.enchLevel = enchLevel;

        Material mat = Material.matchMaterial(ArmorEQ.plugin.getConfig().getString("armor."+name.toLowerCase()+".material"));
        if (mat == null) return null;
        armor.recipeMat = mat;

        Integer durability = ArmorEQ.plugin.getConfig().getInt("armor."+name.toLowerCase()+".durability");
        if (durability == null) return null;
        armor.durability = durability.shortValue();

        String colorName = ArmorEQ.plugin.getConfig().getString("armor."+name.toLowerCase()+".color");
        if (colorName == null) return null;

        Color color = getColorFrom(colorName);
        if (color == null) return null;		
        armor.color = color;

        return armor;
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
}