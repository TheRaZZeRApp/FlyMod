package de.therazzerapp.flymod;

import com.google.gson.JsonObject;

/**
 * Project: FlyMod
 * User: Pual
 * Date: 21/04/2015
 * Time: 13:54 PM
 * Package: com.therazzerapp.flymod
 * E-Mail: rezzer101@googlemail.com
 */

public class Settings {
    private final int maxFlySpeed;

    public Settings(JsonObject root) {
        maxFlySpeed = root.getAsJsonPrimitive("maxFlySpeed").getAsInt();
    }

    public int getMaxFlySpeed() {
        return maxFlySpeed;
    }
}
