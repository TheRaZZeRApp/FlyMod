package com.therazzerapp.flymod;

import net.canarymod.chat.MessageReceiver;

/**
 * Project: FlyMod
 * User: Pual
 * Date: 21/04/2015
 * Time: 13:54 PM
 * Package: com.therazzerapp.flymod
 * E-Mail: rezzer101@googlemail.com
 */

public interface FlyModCommand{
    void run(MessageReceiver caller, String[] args);
}
