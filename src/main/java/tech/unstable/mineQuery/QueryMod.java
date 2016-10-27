package tech.unstable.mineQuery;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tech.unstable.mineQuery.engine.MineQueryLoader;
import tech.unstable.mineQuery.eventHandlers.CommonHandlers;

/**
 * Created by cody on 2016-09-21.
 */

@Mod(modid = QueryMod.MODID, version = QueryMod.VERSION, name = QueryMod.MODNAME)
public class QueryMod {

    public static final String MODNAME = "MineQuery";
    public static final String MODID = "minequery";
    public static final String VERSION = "V0.0.0 -beta1";

    public static Logger logger = null;
    public static File scripts = null;

    @EventHandler
    public void preInit(FMLPreInitializationEvent preinit){
      logger = preinit.getModLog();
      if (logger == null) {  // this SHOULD never get called (please?)
          System.out.println("Did got get logger! <Bakery - Oven>\n REPORT TO FORGE!");
      }
      logger.warn("Preheating the oven");

      MinecraftForge.EVENT_BUS.register(new CommonHandlers());


      scripts = new File(preinit.getModConfigurationDirectory().toPath()+"/minequery-scripts");
      if ( !scripts.exists() ) {
        scripts.mkdir();
      }

      for(File f : scripts.listFiles()){
        try {
          MineQueryLoader.loadScript(f);
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

    }

    @EventHandler
    public void init(FMLInitializationEvent init){

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent postinit) {

    }

}
