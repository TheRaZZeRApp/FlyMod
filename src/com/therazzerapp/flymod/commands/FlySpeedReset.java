package com.therazzerapp.flymod.commands;

import com.therazzerapp.flymod.FlyMod;
import com.therazzerapp.flymod.FlyModCommand;
import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;

/**
 * Project: FlyMod
 * User: Pual
 * Date: 13/03/2015
 * Time: 15:52 PM
 * Package: com.therazzerapp.flymod.commands
 * E-Mail: rezzer101@googlemail.com
 */

public class FlySpeedReset implements FlyModCommand {

    @Override
    public void run(MessageReceiver caller, String[] args){

        if(!(caller instanceof Player)){
            if(args.length < 2){
                caller.message("No player named!");
                return;
            } else if (Canary.getServer().getPlayer(args[1]) == null){
                caller.message("No player found!");
                return;
            } else if (!Canary.getServer().getPlayer(args[1]).isOnline()){
                caller.message("Player not online!");
                return;
            }

            resetFlySpeed(Canary.getServer().getPlayer(args[1]), caller);

        } else {

            Player player = caller.asPlayer();
            if(args.length < 2){
                resetFlySpeed(player, player);
                return;
            } else if (Canary.getServer().getPlayer(args[1]) == null){
                player.message(FlyMod.getTranslator().localeTranslate("error_PlayerNotFound",player.getLocale()));
                return;
            } else if (!Canary.getServer().getPlayer(args[1]).isOnline()){
                player.message(FlyMod.getTranslator().localeTranslate("error_PlayerNotOnline",player.getLocale()));
                return;
            }

            if (player.hasPermission("fly.speed.reset.other")){
                resetFlySpeed(Canary.getServer().getPlayer(args[1]), player);
            } else {
                player.message(FlyMod.getTranslator().localeTranslate("error_SpeedResetNotEnoughPermission",player.getLocale()));
            }

        }
    }

    public void resetFlySpeed(Player player, MessageReceiver caller){
        String temp;
        if(caller instanceof Player){
            temp = FlyMod.getTranslator().localeTranslate("speedReseted", caller.asPlayer().getLocale());
        } else {
            temp = player.getName() + " 's speed reseted!";
        }
        player.getCapabilities().setFlySpeed(0.05F);
        player.updateCapabilities();
        caller.message(temp);
    }
}
