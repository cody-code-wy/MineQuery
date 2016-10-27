package tech.unstable.mineQuery.jsHelers.events.world;

import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.CreateFluidSourceEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.event.world.BlockEvent.MultiPlaceEvent;
import net.minecraftforge.event.world.BlockEvent.NeighborNotifyEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;

import tech.unstable.mineQuery.jsHelers.JsBlock;
import tech.unstable.mineQuery.jsHelers.events.JsEvent;

public class JsBlockEvent extends JsEvent {

  public final JsBlock block;

  public JsBlockEvent(BlockEvent event){
    super("block", event);
    block = new JsBlock(event.getState(), event.getWorld(), event.getPos());
  }

  private JsBlockEvent(BlockEvent event, String eventName){
    super(eventName, event);
    block = new JsBlock(event.getState(), event.getWorld(), event.getPos());
  }

  private int getOffsetPos(int x1, String x2){
    if ( x2.lastIndexOf("~") >= 0){
      x2 = x2.replaceAll("~","");
      if (x2.length() == 0) {
        return x1;
      }
      int dif = Integer.parseInt(x2);
      return x1 + dif;
    } else {
      return Integer.parseInt(x2);
    }
  }

  private boolean between(int x1, int x2, int check){
    return Math.min(x1, x2) <= check && check <= Math.max(x1, x2);
  }

  @Override
  public boolean evalSelector(String selector){
    if (selector.matches("\\[" + block.x + "," + block.y + "," + block.z + "]")){ // Chords selector
      return true;
    }

    if (selector.matches("\\[[\\d\\-]*,[\\d\\-]*,[\\d\\-]*\\]:to\\[[\\d\\+~\\-]*,[\\d\\+~\\-]*,[\\d\\+~\\-]*\\]")) { // Choords range selector
      String[] chords = selector.substring(1,selector.length()-1).split("[\\[\\],:to]+", 6);
      int[] pos = new int[6];
      pos[0] = Integer.parseInt(chords[0]);
      pos[1] = Integer.parseInt(chords[1]);
      pos[2] = Integer.parseInt(chords[2]);
      //Other side
      pos[3] = getOffsetPos(pos[0], chords[3]);
      pos[4] = getOffsetPos(pos[1], chords[4]);
      pos[5] = getOffsetPos(pos[2], chords[5]);

      return between(pos[0], pos[3], block.x) && between(pos[1], pos[4], block.y) && between(pos[2], pos[5], block.z);
    }

    if (("@" + block.registryName + ":" + block.meta).contains(selector)){ // Mod + Block? + meta? selector
      return true;
    }

    return super.evalSelector(selector);
  }

  @Override
  public Object getValidSelector(){
    return block;
  }

  public class JsHarvestDropsEvent extends JsBlockEvent{

    public JsHarvestDropsEvent(HarvestDropsEvent event){
      super(event, "harvent");
    }

  }

  public class JsBreakEvent extends JsBlockEvent{

    public JsBreakEvent(BreakEvent event){
      super(event, "break");
    }

    @Override
    public boolean evalSelector(String selector){
      return super.evalSelector(selector);
    }

  }

  public class JsPlaceEvent extends JsBlockEvent{

    public JsPlaceEvent(PlaceEvent event){
      super(event, "place");
    }

  }

  public class JsMultiPlaceEvent extends JsBlockEvent{

    public JsMultiPlaceEvent(MultiPlaceEvent event){
      super(event, "multiplace");
    }

  }

  public class JsNeighborNotifyEvent extends JsBlockEvent{

    public JsNeighborNotifyEvent(NeighborNotifyEvent event){
      super(event, "notify");
    }

  }

  public class JsCreateFluidSourceEvent extends JsBlockEvent{

    public JsCreateFluidSourceEvent(CreateFluidSourceEvent event){
      super(event, "createsource");
    }

  }
}
