package net.bless.armoreq.recipes;

import net.bless.armoreq.Log;

import org.bukkit.Material;

public class LeatherArmorSet extends RecipeSet {
    public static boolean loadSet(String name) {
        RecipeSet set = readConfig(name, new LeatherArmorSet());
        if (set == null) {
            Log.normal("Error: couldn't read recipe ("+name+" leather armor)");
            return false;
        }

        set.itemMaterials = new Material[] {set.recipeMat, Material.LEATHER};
        
        set.items.add(new RecipeItem("Helm", Material.LEATHER_HELMET, new String[] { "   ", "121", "1 1" }));
        set.items.add(new RecipeItem("Leggings", Material.LEATHER_LEGGINGS, new String[] { "111", "2 2", "1 1" }));
        set.items.add(new RecipeItem("Boots", Material.LEATHER_BOOTS, new String[] { "   ", "1 1", "2 2" }));
        set.items.add(new RecipeItem("ChestPlate", Material.LEATHER_CHESTPLATE, new String[] { "1 1", "121", "111" }));

        return createRecipes(set);
    }    
}
