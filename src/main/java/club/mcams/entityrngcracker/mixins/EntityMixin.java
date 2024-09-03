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

import club.mcams.entityrngcracker.logger.common.villagerRandom.VillagerRandomLogHelper;
import club.mcams.entityrngcracker.logger.common.villagerRandom.RandomWrapper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.VillagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


//#if MC<11904
import java.util.Random;
//#else
//$$ import net.minecraft.util.math.random.CheckedRandom;
//$$ import net.minecraft.util.math.random.Random;
//#endif

@Mixin(Entity.class)
public abstract class EntityMixin{
    @Unique
    private final Entity thisEntity = (Entity) (Object) this;

    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void onEntityInit(CallbackInfo ci) {
        if (thisEntity instanceof VillagerEntity) {
            Random random = ((IEntityInvoker)thisEntity).getRandom();
            //#if MC>=11904
            //$$ if (!(random instanceof CheckedRandom)) { return; }
            //#endif
            ((IEntityInvoker) thisEntity).setRandom(new RandomWrapper(random ,new VillagerRandomLogHelper(thisEntity)));
        }
    }
}
