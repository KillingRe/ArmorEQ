package net.bless.armoreq.listeners;

import net.bless.armoreq.ItemTagger;
import net.bless.armoreq.NeverBreak;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
//Code used from NeverBreak
//Author thebiologist13
//License
//GNU General Public License.
public class ToolUseEvent
  implements Listener
{
  private NeverBreak plugin;

  public ToolUseEvent(NeverBreak plugin)
  {
    this.plugin = plugin;
  }

  @EventHandler
  public void onToolUse(PlayerInteractEvent ev)
  {
    Player p = ev.getPlayer();

    ItemStack stack = p.getItemInHand();
    ItemTagger tagger = new ItemTagger(this.plugin);

    if (this.plugin.getMode(p)) {
      tagger.cancelDurabilityLoss(stack);
      return;
    }

    tagger.recalculateDurability(stack);
  }
}