package tech.unstable.mineQuery.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Wrapper;

public class MineQuery implements Function {

  Map<String,Object> vars = new HashMap<String,Object>();
  List<Object> argv = new ArrayList<Object>();

  Scriptable prototype = null;
  Scriptable parent = null;

  @Override
  public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] args) {
    // TODO Auto-generated method stub
    String selector = args[0].toString();
    if( args[0] instanceof Wrapper ){
      selector = ((Wrapper) args[0]).unwrap().toString();
    }
    System.out.println("MineQuery selecting '" + selector + "'");
    return new MineQueryObject(selector);
  }

  @Override
  public Scriptable construct(Context cx, Scriptable scope, Object[] args) {
    System.out.println("MineQuery Constructed????"); //This probably shouldent happen, so if this get printed WTF?
    return null;
  }

  // Scriptable
  @Override
  public void delete(String name) {
    vars.remove(name);
  }

  @Override
  public String getClassName() {
    return "MineQuery";
  }

  @Override
  public Object get(String name, Scriptable start) {
    return vars.get(name);
  }

  @Override
  public Object get(int index, Scriptable start) {
    return argv.get(index);
  }

  @Override
  public boolean has(String name, Scriptable start) {
    return vars.containsKey(name);
  }

  @Override
  public boolean has(int index, Scriptable start) {
    return argv.get(index) != null;
  }

  @Override
  public void put(String name, Scriptable start, Object value) {
    vars.put(name, value);
  }

  @Override
  public void put(int index, Scriptable start, Object value) {
    argv.add(index, value);
  }

  @Override
  public void delete(int index) {
    argv.remove(index);
  }

  @Override
  public Scriptable getPrototype() {
    return prototype;
  }

  @Override
  public void setPrototype(Scriptable prototype) {
    this.prototype = prototype;
  }

  @Override
  public Scriptable getParentScope() {
    return parent;
  }

  @Override
  public void setParentScope(Scriptable parent) {
    this.parent = parent;
  }

  @Override
  public Object[] getIds() {
    List<Object> ids = Arrays.asList(vars.keySet().toArray());
    for(int i = 0; i < argv.size(); i++){
      ids.add(new Integer(i));
    }

    return ids.toArray();
  }

  @Override
  public Object getDefaultValue(Class<?> hint) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean hasInstance(Scriptable instance) {
    // TODO Auto-generated method stub
    return false;
  }

}
