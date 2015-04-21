package com.therazzerapp.flymod.commands;

import com.therazzerapp.flymod.FlyMod;
import com.therazzerapp.flymod.FlyModCommand;
import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;

/**
 * Project: FlyMod
 * User: TheRaZZeRApp
 * Date: 12/03/2015
 * Time: 21:11 PM
 * Package: com.therazzerapp.flymod.commands
 * E-Mail: rezzer101@googlemail.com
 */

public class Fly implements FlyModCommand {

    @Override
    public void run(MessageReceiver caller, String[] args){

        if(!(caller instanceof Player)){

            if(args.length < 2){
                caller.message("No player named!");
                return;
            } else if (Canary.getServer().getPlayer(args[1]) == null){
                caller.message("Player not found!");
                return;
            } else if (!Canary.getServer().getPlayer(args[1]).isOnline()){
                caller.message("Player not online!");
                return;
            }

            toggleFly(Canary.getServer().getPlayer(args[1]), caller);

        } else {

            Player player = caller.asPlayer();
            if(args.length < 2){
                toggleFly(player,player);
                return;
            } else if (Canary.getServer().getPlayer(args[1]) == null){
                player.message(FlyMod.getTranslator().localeTranslate("error_PlayerNotFound",player.getLocale()));
                return;
            } else if (!Canary.getServer().getPlayer(args[1]).isOnline()){
                player.message(FlyMod.getTranslator().localeTranslate("error_PlayerNotOnline",player.getLocale()));
                return;
            }

            toggleFly(Canary.getServer().getPlayer(args[1]), caller);

        }
    }

    public void enableFly(Player player){
        player.getCapabilities().setMayFly(true);
        player.getCapabilities().setFlying(true);
        player.updateCapabilities();
    }

    public void disableFly(Player player){
        player.getCapabilities().setMayFly(false);
        player.getCapabilities().setFlying(false);
        player.updateCapabilities();
    }

    public void toggleFly(Player player, MessageReceiver caller){
        String temp;
        if (player.getCapabilities().isFlying() || player.getCapabilities().mayFly()){
            if(caller instanceof Player){
                temp = FlyMod.getTranslator().localeTranslate("takeFly",caller.asPlayer().getLocale()).replaceAll("%p",player.getName());
            } else {
                temp = player.getName() + " can't fly anymore!";
            }
            disableFly(player);
            caller.message(temp);
        } else {
            if(caller instanceof Player){
                temp = FlyMod.getTranslator().localeTranslate("giveFly",caller.asPlayer().getLocale()).replaceAll("%p",player.getName());
            } else {
                temp = player.getName() + " can now fly!";
            }
            enableFly(player);
            caller.message(temp);
        }
    }
}
