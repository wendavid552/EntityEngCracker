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

package club.mcams.entityrngcracker;

import carpet.CarpetExtension;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.metadata.ModMetadata;

import net.minecraft.server.MinecraftServer;
import static club.mcams.entityrngcracker.CrackerMod.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class CrackerServer implements CarpetExtension {
    public static MinecraftServer minecraftServer;

    public static final Logger LOGGER = LogManager.getLogger();

    @Override
	public void onGameStarted()
	{
		ModMetadata metadata = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata();
		MOD_NAME = metadata.getName();
		MOD_VERSION = metadata.getVersion().getFriendlyString();
	}

    @Override
    public void onServerLoaded(MinecraftServer server)
    {
        minecraftServer = server;
    }
}
