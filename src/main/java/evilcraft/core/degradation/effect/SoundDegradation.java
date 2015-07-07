package evilcraft.core.degradation.effect;

import evilcraft.api.degradation.IDegradable;
import evilcraft.core.config.extendedconfig.DegradationEffectConfig;
import evilcraft.core.degradation.StochasticDegradationEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.cyclops.cyclopscore.config.extendedconfig.ExtendedConfig;

import java.util.Random;

/**
 * Client-side degradation effect that will play creepy sounds.
 * @author rubensworks
 *
 */
public class SoundDegradation extends StochasticDegradationEffect {

    private static SoundDegradation _instance = null;
    
    /**
     * Get the unique instance.
     * @return The instance.
     */
    public static SoundDegradation getInstance() {
        return _instance;
    }
    
    private static final double CHANCE = 0.1D;
    
    public SoundDegradation(ExtendedConfig<DegradationEffectConfig> eConfig) {
        super(eConfig, CHANCE);
    }

    @Override
    public void runClientSide(IDegradable degradable) {
        
    }

    @Override
    public void runServerSide(IDegradable degradable) {
        Random random = degradable.getWorld().rand;
        World world = degradable.getWorld();
        for(Entity entity : degradable.getAreaEntities()) {
            if(entity instanceof EntityPlayer) {
                world.playSoundAtEntity((EntityPlayer) entity, "mob.blaze.breathe", 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
                world.playSoundAtEntity((EntityPlayer) entity, "mob.enderdragon.wings", 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
            }
        }
    }
    
}
