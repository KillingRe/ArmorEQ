package net.bless.armoreq;
 
import java.util.ArrayList;
import java.util.List;
 

import org.bukkit.Material;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import net.bless.armoreq.ArmorP;
public class ArmorEQ extends JavaPlugin
implements Listener
{
        public static Plugin plugin;  // for storing a global link to this plugin (used in ArmorP)
 
    @Override
    public void onEnable() {  
        ArmorEQ.plugin = this;    
    getConfig().options().copyDefaults(true);
    saveConfig();
    addRecipes();
 
        if (!getConfig().contains("armor.emerald.enchantment.level")) {
            getConfig().createSection("armor.emerald.enchantment.level");
            }
        }
 
        private void addRecipes() {
        List<String> lore = new ArrayList<String>();
        lore.add("Blah");
        ArmorP quartzArmor = ArmorP.getArmor("Quartz");
 
//new ArmorP("Quartz", Material.QUARTZ, Color.WHITE, (short) -2500, lore, );
 
 //       List<String> lore2 = new ArrayList<String>();
  //      lore2.add("Blah");
  //      ArmorP emeraldArmor = new ArmorP("Emerald", Material.EMERALD, Color.LIME, (short) -2000, //lore2, Enchantment.DURABILITY, 20);
 
        if (quartzArmor != null) {
                createLeatherArmourRecipes(quartzArmor);
        } else {
                // send message to console saying error reading armor
                // TODO: better to give a more specific message in the armorp.getarmor() function
        }
  //      createLeatherArmourRecipes(emeraldArmor);
 
        ItemStack is5 = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta im5 = is5.getItemMeta();
        im5.setDisplayName("Quartz Sword");
        is5.setDurability((short) -2000);
        is5.setItemMeta(im5);
        ShapedRecipe quartzsword = new ShapedRecipe(is5);
        quartzsword.shape(" * ", " * ", " s ");
        quartzsword.setIngredient('*', Material.QUARTZ);
        quartzsword.setIngredient('s', Material.STICK);
        this.getServer().addRecipe(quartzsword);
       
        ItemStack is11 = new ItemStack(Material.IRON_AXE, 1);
        ItemMeta im11 = is11.getItemMeta();
        im11.setDisplayName("Quartz Axe");
        is11.setDurability((short) -2000);
        is11.setItemMeta(im11);
        ShapedRecipe quartzaxe = new ShapedRecipe(is11);
        quartzaxe.shape("** ", "*s ", " s ");
        quartzaxe.setIngredient('*', Material.QUARTZ);
        quartzaxe.setIngredient('s', Material.STICK);
        this.getServer().addRecipe(quartzaxe);
       
        ItemStack is12 = new ItemStack(Material.IRON_HOE, 1);
        ItemMeta im12 = is12.getItemMeta();
        im12.setDisplayName("Quartz Hoe");
        is12.setDurability((short) -2000);
        is12.setItemMeta(im12);
        ShapedRecipe quartzhoe = new ShapedRecipe(is12);
        quartzhoe.shape("** ", " s ", " s ");
        quartzhoe.setIngredient('*', Material.QUARTZ);
        quartzhoe.setIngredient('s', Material.STICK);
        this.getServer().addRecipe(quartzhoe);
       
        ItemStack is13 = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemMeta im13 = is13.getItemMeta();
        im13.setDisplayName("Quartz PickAxe");
        is13.setDurability((short) -2000);
        is13.setItemMeta(im13);
        ShapedRecipe quartzpickaxe = new ShapedRecipe(is13);
        quartzpickaxe.shape(" * ", " * ", " s ");
        quartzpickaxe.setIngredient('*', Material.QUARTZ);
        quartzpickaxe.setIngredient('s', Material.STICK);
        this.getServer().addRecipe(quartzpickaxe);
       
        ItemStack is14 = new ItemStack(Material.IRON_SPADE, 1);
        ItemMeta im14 = is14.getItemMeta();
        im14.setDisplayName("Quartz PickAxe");
        is14.setDurability((short) -2000);
        is14.setItemMeta(im14);
        ShapedRecipe quartzspade = new ShapedRecipe(is14);
        quartzspade.shape(" * ", " s ", " s ");
        quartzspade.setIngredient('*', Material.QUARTZ);
        quartzspade.setIngredient('s', Material.STICK);
        this.getServer().addRecipe(quartzspade);
 
    }
 
    private void createLeatherArmourRecipes(ArmorP Armor) {
        System.out.println("creatuerecipes");
        createLeatherPiece("Helm", new String[] { "   ", "*%*", "* *" },
                Material.LEATHER_HELMET, Armor);
        createLeatherPiece("Leggings", new String[] { "***", "% %", "* *" },
                Material.LEATHER_LEGGINGS, Armor);
        createLeatherPiece("Boots", new String[] { "   ", "* *", "% %" },
                Material.LEATHER_BOOTS, Armor);
        createLeatherPiece("ChestPlate", new String[] { "* *", "*%*", "***" },
                Material.LEATHER_CHESTPLATE, Armor);
    }
 
    private void createLeatherPiece(String suffix, String[] shape,
            Material item, ArmorP armor) {
        System.out.println("createpiece " + armor.toString());
        ItemStack is = new ItemStack(item, 1);
        LeatherArmorMeta im = (LeatherArmorMeta) is.getItemMeta();
        im.setDisplayName(armor.name + " " + suffix);
        im.setLore(armor.lore);
        im.setColor(armor.color);
        is.setItemMeta(im);
        is.setDurability(armor.durability);
        is.addUnsafeEnchantment(armor.ench, armor.enchLevel);
 
        ShapedRecipe Armor = new ShapedRecipe(is);
        Armor.shape(shape);
        Armor.setIngredient('*', armor.recipeMat);
        Armor.setIngredient('%', Material.LEATHER);
        this.getServer().addRecipe(Armor);
    }
 
}