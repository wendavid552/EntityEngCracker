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

package club.mcams.entityrngcracker.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

//#if MC<11904
import java.util.Random;
//#else
//$$ import net.minecraft.util.math.random.CheckedRandom;
//#endif
import java.util.concurrent.atomic.AtomicLong;

@Mixin(value =
        //#if MC<11904
        Random.class, remap = false
        //#else
        //$$ CheckedRandom.class
        //#endif
        )
public interface IRandomAccessor {
    @Accessor("seed")
    AtomicLong getSeed();

    @Invoker("next")
    int invokeNext(int bits);
}
