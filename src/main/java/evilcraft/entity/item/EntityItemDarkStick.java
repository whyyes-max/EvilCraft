package evilcraft.entity.item;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import evilcraft.api.ILocation;
import evilcraft.core.algorithm.Location;
import evilcraft.core.helper.LocationHelpers;
import evilcraft.world.gen.DarkTempleGenerator;
import evilcraft.world.gen.nbt.DarkTempleData;

/**
 * Entity for the dark stick item.
 * @author rubensworks
 *
 */
public class EntityItemDarkStick extends EntityItemDefinedRotation {

	private static final int WATCHERID_ROTATION = 20;
	private static final int WATCHERID_VALID = 21;
	
	//protected boolean isValid = false;
	//protected float rotation = 0F;
	
	/**
     * New instance.
     * @param world The world.
     * @param original The original item entity
     */
	public EntityItemDarkStick(World world, EntityItem original) {
        super(world, original);
    }
	
	/**
	 * Make a new instance.
	 * @param world The world.
	 */
	public EntityItemDarkStick(World world) {
        super(world);
    }

	/**
	 * Make a new instance.
	 * @param world The world.
	 * @param x X
	 * @param y Y
	 * @param z Z
	 */
    public EntityItemDarkStick(World world, double x, double y, double z) {
        super(world, x, y, z);
    }
    
    /**
	 * Make a new instance.
	 * @param world The world.
	 * @param x X
	 * @param y Y
	 * @param z Z
     * @param itemStack The item stack.
	 */
    public EntityItemDarkStick(World world, double x, double y, double z, ItemStack itemStack) {
        super(world, x, y, z, itemStack);
    }
    
    @Override
	public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(WATCHERID_ROTATION, 0.0F);
        this.dataWatcher.addObject(WATCHERID_VALID, 0);
        if(!worldObj.isRemote) {
        	loadRotation();
        }
    }
	
	private void loadRotation() {
		DarkTempleData data = DarkTempleGenerator.getDarkTempleData(worldObj);
		if(data != null) {
			ILocation closest = data.getClosestStructure((int) posX, (int) posY, (int) posZ);
			if(closest != null) {
				closest.getCoordinates()[1] = 0;
				double d = LocationHelpers.getDistance(closest, new Location((int) posX, 0, (int) posZ));
				ILocation normalized = new Location(closest.getCoordinates()[0] - (int) posX, 0,
						closest.getCoordinates()[2] - (int) posZ);
				double angle = Math.acos(normalized.getCoordinates()[0] / d);
				double angle2 = Math.asin(normalized.getCoordinates()[2] / d);
				this.dataWatcher.updateObject(WATCHERID_VALID, 1);
				this.dataWatcher.updateObject(WATCHERID_ROTATION, (float) angle);System.out.println("ANGLE: " + angle);
			}
		}
	}
	
	@Override
	protected boolean hasCustomRotation() {
		return this.dataWatcher.getWatchableObjectInt(WATCHERID_VALID) == 1 && worldObj.provider.dimensionId == 0;
	}
	
	@Override
	protected float getRotationYaw(World worldObj, double posX,
			double posY, double posZ) {
		return this.dataWatcher.getWatchableObjectFloat(WATCHERID_ROTATION);
	}
	
}