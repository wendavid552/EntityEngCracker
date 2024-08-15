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

package club.mcams.entityrngcracker.utils.compat;

import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Objects;

//#if MC >= 11600
//$$ import net.minecraft.util.registry.RegistryKey;
//#else
import net.minecraft.world.dimension.DimensionType;
//#endif

/*
 * A wrapper class to deal with dimension type class differences between minecraft version:
 * - DimensionType in 1.15-
 * - RegistryKey<World> in 1.16+
 */
public class DimensionWrapper
{
	public static final DimensionWrapper OVERWORLD = of(
			//#if MC >= 11600
			//$$ World.OVERWORLD
			//#else
			DimensionType.OVERWORLD
			//#endif
	);
	public static final DimensionWrapper THE_NETHER = of(
			//#if MC >= 11600
			//$$ World.NETHER
			//#else
			DimensionType.THE_NETHER
			//#endif
	);
	public static final DimensionWrapper THE_END = of(
			//#if MC >= 11600
			//$$ World.END
			//#else
			DimensionType.THE_END
			//#endif
	);

	//#if MC >= 11600
	//$$ private final RegistryKey<World>
	//#else
	private final DimensionType
	//#endif
			dimensionType;

	private DimensionWrapper(
			//#if MC >= 11600
			//$$ RegistryKey<World> dimensionType
			//#else
			DimensionType dimensionType
			//#endif
	)
	{
		this.dimensionType = dimensionType;
	}

	/**
	 * Warning: mc version dependent
	 */
	public static DimensionWrapper of(
			//#if MC >= 11600
			//$$ RegistryKey<World> dimensionType
			//#else
			DimensionType dimensionType
			//#endif
	)
	{
		return new DimensionWrapper(dimensionType);
	}

	public static DimensionWrapper of(World world)
	{
		return new DimensionWrapper(
				//#if MC >= 11600
				//$$ world.getRegistryKey()
				//#else
				world.getDimension().getType()
				//#endif
		);
	}

	public static DimensionWrapper of(Entity entity)
	{
		return of(entity.getEntityWorld());
	}

	/**
	 * Warning: mc version dependent
	 */
	//#if MC >= 11600
	//$$ public RegistryKey<World> getValue()
	//#else
	public DimensionType getValue()
	//#endif
	{
		return this.dimensionType;
	}

	public Identifier getIdentifier()
	{
		//#if MC >= 11600
		//$$ return this.dimensionType.getValue();
		//#else
		return DimensionType.getId(this.dimensionType);
		//#endif
	}

	public String getIdentifierString()
	{
		//#if MC >= 11600
		//$$ return this.getIdentifier().toString();
		//#else
		return this.dimensionType.toString();
		//#endif
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		DimensionWrapper that = (DimensionWrapper) o;
		return Objects.equals(dimensionType, that.dimensionType);
	}

	@Override
	public int hashCode()
	{
		return this.dimensionType.hashCode();
	}

	@Deprecated
	@Override
	public String toString()
	{
		return this.getIdentifierString();
	}
}

