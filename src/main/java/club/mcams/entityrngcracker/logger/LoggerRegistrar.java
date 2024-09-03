/*
 * This file is part of the EntityRngCracker project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2024  WenDavid and contributors
 *
 * EntityRngCracker is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EntityRngCracker is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with EntityRngCracker.  If not, see <https://www.gnu.org/licenses/>.
 */

package club.mcams.entityrngcracker.logger;


//#if MC>=11500
import carpet.logging.LoggerRegistry;
//#else
//$$ import club.mcams.entityrngcracker.utils.compat.carpet.LoggerRegistry;
//#endif
import carpet.logging.Logger;
import club.mcams.entityrngcracker.logger.compat.ExtensionLogger;

public class LoggerRegistrar {
    public static boolean __villagerRandom = false;

    public static void registerLoggers() {
        LoggerRegistry.registerLogger("villagerRandom", stardardLogger("villagerRandom", "brief", new String[]{"brief", "filter", "full"}, true));
    }

    static Logger stardardLogger(String logName, String def, String [] options, boolean strictOptions)
    {
        try
        {
            return new
                    //#if MC>=11500
                    Logger
                    //#else
                    //$$ ExtensionLogger
                    //#endif
                    (LoggerRegistrar.class.getField("__"+logName), logName, def, options
                    //#if MC>=11700
                    //$$ , strictOptions
                    //#endif
                    );
        }
        catch (NoSuchFieldException e)
        {
            throw new RuntimeException("Failed to create logger "+logName);
        }
    }
}
