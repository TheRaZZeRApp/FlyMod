package com.therazzerapp.flymod;

import net.visualillusionsent.utils.LocaleHelper;

/**
 * Project: FlyMod
 * User: Pual
 * Date: 21/04/2015
 * Time: 13:53 PM
 * Package: com.therazzerapp.flymod
 * E-Mail: rezzer101@googlemail.com
 */

public class Translator extends LocaleHelper{
    public Translator(String locale) {
        super(locale);
    }

    public Translator(boolean useExternalFiles, String externalDirectory, String locale) {
        super(useExternalFiles, externalDirectory, locale);
    }
}
