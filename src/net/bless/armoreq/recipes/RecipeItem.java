package net.bless.armoreq.recipes;

import org.bukkit.Material;

public class RecipeItem {
    String name;
    String[] shape;   // eg. " * ", " * ", " s "
    Material baseMat; // eg Iron_Sword

    public RecipeItem(String name, Material mat, String[] strings) {
        this.name = name;
        this.baseMat = mat;
        this.shape = strings;
    }
}