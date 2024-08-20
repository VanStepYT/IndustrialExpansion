package com.scand.ie;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs

@Mod.EventBusSubscriber(modid = IEMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<Integer> solarReactorExplosionPower;
    static final ForgeConfigSpec SPEC;



    static {
        BUILDER.push("Configs for IE");

        solarReactorExplosionPower = BUILDER.comment("Solar Reactor Explosion Power")
                        .define("Solar Reactor Explosion Power", 70);


        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
