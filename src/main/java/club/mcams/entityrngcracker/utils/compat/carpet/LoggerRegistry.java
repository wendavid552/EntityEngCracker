/*
 * This file is part of the Carpet TIS Addition project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2023  Fallen_Breath and contributors
 *
 * Carpet TIS Addition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Carpet TIS Addition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Carpet TIS Addition.  If not, see <https://www.gnu.org/licenses/>.
 */
package club.mcams.entityrngcracker.utils.compat.carpet;

import carpet.logging.Logger;

//#if MC < 11500
//$$ import club.mcams.entityrngcracker.mixins.carpet.access.LoggerRegistryInvoker;
//#endif

/**
 * Used in mc 1.14.4 where carpet doesn't provide logging support for carpet extensions
 */
public class LoggerRegistry
{
    // a wrapped method to reduce merge conflicts
    public static void registerLogger(String name, Logger logger)
    {
        //#if MC < 11500
        //$$ LoggerRegistryInvoker.callRegisterLogger(name, logger);
        //#endif
    }
}