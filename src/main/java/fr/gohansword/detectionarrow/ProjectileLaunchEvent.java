package fr.gohansword.detectionarrow;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileLaunchEvent implements Listener {

    String blankline = "&c";
    private Detectionarrow plugin;

    @EventHandler
    public void arrowEvent(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            Player player = (Player) arrow.getShooter();
            String name = player.getName();
            if (!(player instanceof Player)) {
                return;
            }

            for (fr.gohansword.detectionarrow.Arrow fleche : Utils.arrows) {
                if (arrow.getLocation().getWorld() == fleche.loc.getWorld()) {
                    if(arrow.getLocation().getBlockX()== fleche.loc.getBlockX()) {
                        if(arrow.getLocation().getBlockY()== fleche.loc.getBlockY()) {
                            if(arrow.getLocation().getBlockZ()== fleche.loc.getBlockZ()) {

                                if (player.hasPermission(Utils.arrow_permission)) {
                                    for (String message : fleche.message_to_player) {
                                        player.sendMessage(message);
                                    }
                                    arrow.remove();
                                    player.playNote(player.getLocation(), Instrument.CHIME, Note.natural(1, Note.Tone.A));

                                    for (String commande : fleche.commands) {
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commande.replace("{name}", player.getName()));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}