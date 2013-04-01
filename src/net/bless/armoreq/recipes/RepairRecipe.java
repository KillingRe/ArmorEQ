package net.bless.armoreq.recipes;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class RepairRecipe
implements Listener
{
  //event for ArmorEQ items not to break from bukkit Durability
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent ev){
    if ((ev.getEntity() instanceof Player)){
    Player p = (Player)ev.getEntity();
    ItemStack[] armor = p.getInventory().getArmorContents();
    if (armor.length > 0){
    for (int i = 0; i < armor.length; i++){
    armor[i].setDurability((short)-armor[i].getType().getMaxDurability());}}}
	if ((ev.getDamager() instanceof Player)){
    Player p = (Player)ev.getDamager();{
	ItemStack handitem = p.getItemInHand();
	if (handitem.getDurability() != handitem.getType().getMaxDurability())
	handitem.setDurability((short)-32768);}}}
    @EventHandler
    public void onBlockBreak(BlockBreakEvent ev){
	Player p = ev.getPlayer();{
	ItemStack handitem = p.getItemInHand();
	if (handitem.getDurability() != handitem.getType().getMaxDurability())
     handitem.setDurability((short)-32768);}}
	  
	//RepairRecipe for ArmorEQ items to replace Durability lore as lore is being used for Durability now =X O Snap!
	ItemStack im = new ItemStack(Material.QUARTZ);
	ItemMeta m = im.getItemMeta();
	meta.hasDisplayName("Quartz ChestPlate");
	ArrayList<String> description = new ArrayList<String>();
	description.add("D:2500");
	meta.setLore(description);
	item.setItemMeta(meta);
	player.getInventory().addItem(item);

	ItemStack item = new ItemStack(Material.EMERALD);
	ItemMeta meta = item.getItemMeta();
	meta.hasDisplayName("Emerald Leggings");
	ArrayList<String> description = new ArrayList<String>();
	description.add("D:2000");
	meta.setLore(description);
	item.setItemMeta(meta);
	player.getInventory().addItem(item)}

    //When lore Durability hits 0 item will be removed from players Inventory aka Yo Shit be Broke!
    Player player = event.getPlayer();
    ItemStack item = player.hasLore("0");{
    if (player.hasItem.lore().hasLore("0") > max) {
    player.getInventory().clear(p.getInventory().getHeldItemSlot());
    Player.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);}}}
