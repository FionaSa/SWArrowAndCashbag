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
            Player player;
            try{
                 player = (Player) arrow.getShooter();
            }catch (Exception e) {
                return;
            }


            String name = player.getName();


            if (player.getWorld().getName().equalsIgnoreCase(Utils.world)) {
                arrow.remove();
                if (player.hasPermission(Utils.arrow_permission)) {

                    for (fr.gohansword.detectionarrow.Arrow fleche : Utils.arrows) {
                    if( (fleche.loc.getBlockX() -1 <= arrow.getLocation().getBlockX()) && (arrow.getLocation().getBlockX() <= fleche.loc.getBlockX() +1 ) ){
                        if( (fleche.loc.getBlockY() -1 <= arrow.getLocation().getBlockY()) && (arrow.getLocation().getBlockY() <= fleche.loc.getBlockY() +1 ) ){
                            if( (fleche.loc.getBlockZ() -1 <= arrow.getLocation().getBlockZ()) && (arrow.getLocation().getBlockZ() <= fleche.loc.getBlockZ() +1 ) ){

                                    for (String message : fleche.message_to_player) {
                                        player.sendMessage(message);
                                    }

                                    player.playNote(player.getLocation(), Instrument.CHIME, Note.natural(1, Note.Tone.A));

                                    for (String commande : fleche.commands) {
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commande.replace("{name}", player.getName()));
                                    }
                                    player.chat("/combienflechequete");
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