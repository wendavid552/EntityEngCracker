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

package club.mcams.entityrngcracker.logger.common.villagerRandom;

import carpet.logging.Logger;
import carpet.logging.LoggerRegistry;
import club.mcams.entityrngcracker.CrackerMod;
import club.mcams.entityrngcracker.logger.LoggerRegistrar;
import club.mcams.entityrngcracker.utils.Messenger;
import club.mcams.entityrngcracker.utils.compat.DimensionWrapper;
import club.mcams.entityrngcracker.utils.deobfuscator.StackTracePrinter;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.BaseText;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.List;

public class VillagerRandomLogHelper {
    public final Entity entity;
    private long thisTick;
    private final List<BaseText> messagesOfThisTick = new ArrayList<>();
    private final List<BaseText> symbolsOfThisTick = new ArrayList<>();

    public VillagerRandomLogHelper(Entity entity) {
        this.entity = entity;
    }

    public void onRandomChanged(int bits, long oldSeed, long newSeed) {
        ServerWorld world = (ServerWorld) entity.getEntityWorld();

        if (world.getTime() > thisTick) {
            LoggerRegistry.getLogger("villagerRandom").log((String option) -> {
                if ("full".equals(option)) {
                    return messagesOfThisTick.toArray(new BaseText[0]);
                }
                if ("filter".equals(option)) {
                    if (messagesOfThisTick.size() > 2) {
                        return messagesOfThisTick.toArray(new BaseText[0]);
                    }
                }
                if ("brief".equals(option)) {
                    if (messagesOfThisTick.size() > 2) {
                        return new BaseText[]{
                                Messenger.c(
                                        Messenger.s(String.format("[%s] ", world.getTime()), "g"),
                                        Messenger.s(String.format("-> %d", newSeed), "w"),
                                        Messenger.s(String.format("@ %d", messagesOfThisTick.size()), "g")
                                ),
                                Messenger.join(Messenger.s(" "), symbolsOfThisTick)
                        };
                    }
                }
                return new BaseText[] {};
            });

            thisTick = world.getTime();
            messagesOfThisTick.clear();
            symbolsOfThisTick.clear();
        }

        BaseText message = Messenger.c(
            Messenger.s(String.format("[%s] ", world.getTime()), "g"),
            "g @ ",
            Messenger.coord("w", entity.getPos(), DimensionWrapper.of(entity)),
            Messenger.s(String.format("(%d) ", bits), "m"),
            Messenger.s(String.format("%d -> %d", oldSeed, newSeed), "w"),
            Messenger.s(String.format("@ %d", messagesOfThisTick.size() + 1), "g"),
            StackTracePrinter.makeSymbol(CrackerMod.class,20)
        );

        messagesOfThisTick.add(message);
        symbolsOfThisTick.add(StackTracePrinter.makeSymbol(CrackerMod.class,20));
    }
}
