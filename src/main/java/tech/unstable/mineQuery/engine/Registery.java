package tech.unstable.mineQuery.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tech.unstable.mineQuery.jsHelers.events.JsEvent;

public class Registery {

  private static Registery singleton;

  private Map<String, List<EventSelector>> registery = new HashMap<String, List<EventSelector>>();

  private Registery(){

  }

  public static Registery getSingleton(){
    if(singleton == null){
      singleton = new Registery();
    }

    return singleton;
  }

  public void registerEvent(String event,EventSelector selector){
    registery.putIfAbsent(event, new ArrayList<EventSelector>());

    registery.get(event).add(selector);
  }

  public void fireEvent(JsEvent event){
    if (!registery.containsKey(event._eventName)){
      return;
    }
    EventSelector[] checkall = registery.get(event._eventName).toArray(new EventSelector[0]);
    for(EventSelector s : checkall){
      s.eval(event);
    }
  }

}
