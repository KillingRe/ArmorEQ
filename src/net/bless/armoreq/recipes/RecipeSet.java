package net.bless.armoreq.recipes;

import java.util.ArrayList;
import java.util.List;

import net.bless.armoreq.ArmorEQ;
import net.bless.armoreq.Log;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;


public abstract class RecipeSet {
    public String name;
    public Color color;
    public Short durability;
    public List<String> lore;
    public Enchantment ench;
    public int enchLevel;
    public Material recipeMat;
    public List<RecipeItem> items = new ArrayList<RecipeItem>();
    public Material[] itemMaterials;
    
    @SuppressWarnings("unchecked")
	public static RecipeSet readConfig(String name, RecipeSet set) {        
        String prefix = "";
        if (set instanceof ToolSet) {
            prefix = "tools.";
            Log.normal("Loading recipe ("+name+" "+prefix+")");
        }
        else if (set instanceof LeatherArmorSet || set instanceof DiamondArmorSet) {
            prefix = "armor.";
            Log.normal("Loading recipe ("+name+" armor)");
        }


        set.name = name;
        Integer enchLevel  = ArmorEQ.plugin.getConfig().getInt(prefix+name.toLowerCase()+".enchantment.level");
        // note: defaults to 0 if not found??

        if (enchLevel == null) {
            Log.normal("Cannot read enchantment level.");
            return null;
        }
        set.enchLevel = enchLevel;

        Material mat = Material.matchMaterial(ArmorEQ.plugin.getConfig().getString(prefix+name.toLowerCase()+".material").toUpperCase());
        if (mat == null) {
            Log.normal("Cannot read material.");
            return null;
        }
        set.recipeMat = mat;

        Integer durability = ArmorEQ.plugin.getConfig().getInt(prefix+name.toLowerCase()+".durability");
        if (durability == null) {
            Log.normal("Cannot read durability.");
            return null;
        }
        set.durability = durability.shortValue();

        String colorName = ArmorEQ.plugin.getConfig().getString(prefix+name.toLowerCase()+".color");
        Color color = null;
        if (colorName != null) {        
            color = ArmorEQ.getColorFrom(colorName);
        }
        // optional - don't quit out.
        set.color = color;
        
        Object loreObj = ArmorEQ.plugin.getConfig().get(prefix+name.toLowerCase()+".lore");
        List<String> lore = new ArrayList<String>();
        
        if (loreObj instanceof String) {
            lore.add((String)loreObj);
        } else if (loreObj instanceof List) {
            lore.addAll((List<String>)loreObj);
        }            
        // optional - don't quit out.
        set.lore = lore;
        
        return set;        
    }
    
    protected static boolean createRecipes(RecipeSet set) {
        for (RecipeItem item : set.items) {
            createPiece(set, item);
        }
        return true;
    }
    
    private static void createPiece(RecipeSet set, RecipeItem item) {
        Log.high("Creating armour piece ("+item.toString()+")");
        ItemStack is = new ItemStack(item.baseMat, 1);
        if (set.durability != null) is.setDurability(set.durability);
        
        if (isLeatherArmor(item)) {
            if (set.color != null) {
                LeatherArmorMeta leatherMeta = (LeatherArmorMeta)is.getItemMeta();
                leatherMeta.setColor(set.color);
                is.setItemMeta(leatherMeta);
            }
        }
        
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(set.name + " " + item.name);
        if (set.lore != null) im.setLore(set.lore);
        is.setItemMeta(im);
        ///is.addUnsafeEnchantment(armor.ench, armor.enchLevel);

        ShapedRecipe recipe = new ShapedRecipe(is);
        recipe.shape(item.shape);
        Integer count = 1;
        for (Material mat : set.itemMaterials) {
            recipe.setIngredient(count.toString().charAt(0), mat);
            count++;
        }
        
        ArmorEQ.plugin.getServer().addRecipe(recipe);
    }

    private static boolean isLeatherArmor(RecipeItem item) {
        return (item.baseMat == Material.LEATHER_BOOTS
                || item.baseMat == Material.LEATHER_CHESTPLATE
                || item.baseMat == Material.LEATHER_HELMET
                || item.baseMat == Material.LEATHER_LEGGINGS);
    }    
}
