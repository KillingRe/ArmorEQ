package net.bless.armoreq;

import java.lang.reflect.Field;
import net.minecraft.server.v1_5_R2.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_5_R2.inventory.CraftItemStack;


//Code used from NeverBreak
//Author thebiologist13
//License
//GNU General Public License

public class ItemTagger
{
  private final NeverBreak PLUGIN;

  public ItemTagger(NeverBreak plugin)
  {
    this.PLUGIN = plugin;
  }

  public void recalculateDurability(org.bukkit.inventory.ItemStack stack) {
    if ((stack == null) || (stack.getType().equals(Material.AIR))) {
      return;
    }

    int c = this.PLUGIN.getConfiguredDurability(stack);
    int m = stack.getType().getMaxDurability();
    if (c == m) {
      return;
    }
    if (c <= 0)
      return;
    try
    {
      net.minecraft.server.v1_5_R2.ItemStack handle = getHandle(stack);

      if ((!hasPreviousTag(stack)) && (!hasUsageTag(stack))) {
        handle = applyTags(stack, 0, 0);
        handle.setData(0);
        return;
      }
      int p = handle.tag.getInt("NeverBreakPrevious");
      int y = handle.getData();
      int n = handle.tag.getInt("NeverBreakUsage");

      if (p == y) {
        return;
      }
      if (p < y) {
        int x = Math.round(n * m / c + 2 * (m / c));
        handle = applyTags(stack, x, n + 1);
        handle.setData(x);
      } else if (y - p < 0) {
        n = Math.round(c * y / m);
        int x = Math.round(n * m / c + 2 * (m / c));
        handle = applyTags(stack, x, n);
        handle.setData(x);
      }

      setHandle(stack, handle);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void cancelDurabilityLoss(org.bukkit.inventory.ItemStack stack)
  {
    if ((stack == null) || (stack.getType().equals(Material.AIR))) {
      return;
    }

    try
    {
      net.minecraft.server.v1_5_R2.ItemStack handle = getHandle(stack);

      if ((!hasPreviousTag(stack)) && (!hasUsageTag(stack))) {
        handle = applyTags(stack, 0, 0);
        handle.setData(0);
        return;
      }
      int p = handle.tag.getInt("NeverBreakPrevious");
      int n = handle.tag.getInt("NeverBreakUsage");

      handle = applyTags(stack, p, n);
      handle.setData(p);

      setHandle(stack, handle);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void setDurability(org.bukkit.inventory.ItemStack stack, int durability) {
    if ((stack == null) || (stack.getType().equals(Material.AIR)))
      return;
    try
    {
      net.minecraft.server.v1_5_R2.ItemStack handle = getHandle(stack);

      int c = this.PLUGIN.getConfiguredDurability(stack);
      int m = stack.getType().getMaxDurability();
      if (c == m) {
        return;
      }
      if (c <= 0)
        return;
      int x = Math.round(durability * m / c);
      handle = applyTags(stack, x, durability);
      handle.setData(x);
      setHandle(stack, handle);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private net.minecraft.server.v1_5_R2.ItemStack getHandle(org.bukkit.inventory.ItemStack stack)
    throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
    CraftItemStack cStack = (CraftItemStack)stack;
    Field f = CraftItemStack.class.getDeclaredField("handle");
    f.setAccessible(true);
    return (net.minecraft.server.v1_5_R2.ItemStack)f.get(cStack);
  }

  private void setHandle(org.bukkit.inventory.ItemStack stack, net.minecraft.server.v1_5_R2.ItemStack handle) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
    CraftItemStack cStack = (CraftItemStack)stack;
    Field f = CraftItemStack.class.getDeclaredField("handle");
    f.setAccessible(true);
    f.set(cStack, handle);
  }

  private net.minecraft.server.v1_5_R2.ItemStack applyTags(org.bukkit.inventory.ItemStack stack, int previous, int usage) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException
  {
    net.minecraft.server.v1_5_R2.ItemStack nms = getHandle(stack);
    makeTag(nms);
    nms.tag.setInt("NeverBreakPrevious", previous);
    nms.tag.setInt("NeverBreakUsage", usage);
    return nms;
  }

  private boolean hasPreviousTag(org.bukkit.inventory.ItemStack stack) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException
  {
    net.minecraft.server.v1_5_R2.ItemStack nms = getHandle(stack);
    makeTag(nms);
    return nms.tag.hasKey("NeverBreakkPrevious");
  }

  private boolean hasUsageTag(org.bukkit.inventory.ItemStack stack) throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException
  {
    net.minecraft.server.v1_5_R2.ItemStack nms = getHandle(stack);
    makeTag(nms);
    return nms.tag.hasKey("NeverBreakUsage");
  }

  private void makeTag(net.minecraft.server.v1_5_R2.ItemStack stack) {
    if (!stack.hasTag())
      stack.tag = new NBTTagCompound();
  }
}