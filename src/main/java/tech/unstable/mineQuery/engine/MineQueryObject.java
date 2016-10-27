package tech.unstable.mineQuery.engine;

import org.mozilla.javascript.Function;

import tech.unstable.mineQuery.QueryMod;

public class MineQueryObject {

  private final Selector selector;

  public MineQueryObject(String selector){
    this.selector = new Selector(selector);
  }

  public MineQueryObject on(String event, Function callback){
    QueryMod.logger.warn("ON " + event);

    Registery.getSingleton().registerEvent(event, new EventSelector(selector, callback));
    return this;
  }
}
