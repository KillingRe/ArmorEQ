package net.bless.armoreq.recipes;

import net.bless.armoreq.Log;

import org.bukkit.Material;

public class ToolSet extends RecipeSet {
    public static boolean loadSet(String name) {
        RecipeSet set = readConfig(name, new ToolSet());
        if (set == null) {
            Log.normal("Error: couldn't read recipe ("+name+" tools)");
            return false;
        }
        
        set.itemMaterials = new Material[] {set.recipeMat, Material.STICK};
        set.items.add(new RecipeItem("Sword", Material.IRON_SWORD, new String[] { " 1 ", " 1 ", " 2 " }));
        set.items.add(new RecipeItem("Axe", Material.IRON_AXE, new String[] { "11 ", "12 ", " 2 " }));
        set.items.add(new RecipeItem("Hoe", Material.IRON_HOE, new String[] { "11 ", " 2 ", " 2 " }));
        set.items.add(new RecipeItem("Pickaxe", Material.IRON_PICKAXE, new String[] { "111", " 2 ", " 2 " }));
        set.items.add(new RecipeItem("Spade", Material.IRON_SPADE, new String[] { " 1 ", " 2 ", " 2 " }));
        
        boolean result = createRecipes(set);
        return result;
    }
}