package fr.gohansword.detectionarrow;

import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickEvent implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();

        if (player.getWorld().getName().equalsIgnoreCase(Utils.world)) {
            if (player.hasPermission(Utils.head_permission)) {

            for (Head tete : Utils.heads) {
                    if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        if (event.getInteractionPoint().getBlockX() == tete.loc.getBlockX()) {
                            if (event.getInteractionPoint().getBlockY() == tete.loc.getBlockY()) {
                                if (event.getInteractionPoint().getBlockZ() == tete.loc.getBlockZ()) {
                                    for (String message : tete.message_to_player) {
                                        player.sendMessage(message);
                                    }
                                    player.playNote(player.getLocation(), Instrument.CHIME, Note.natural(1, Note.Tone.A));
                                    for (String commande : tete.commands) {
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commande.replace("{name}", player.getName()));
                                    }

                                    player.chat("/combienfriandisequete");
                                    return;
                                }
                            }
                        }
                    }

                }
            }
        }

    }
}
