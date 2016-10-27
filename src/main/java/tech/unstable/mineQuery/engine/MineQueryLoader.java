package tech.unstable.mineQuery.engine;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.RhinoException;
import org.mozilla.javascript.Scriptable;

import tech.unstable.mineQuery.QueryMod;
import tech.unstable.mineQuery.jsHelers.JsConsole;

public class MineQueryLoader {

  public static void loadScript(File script) throws IOException{
    Reader src = null;
    try {
      src = new FileReader(script);
      Context cx = Context.enter();
      cx.evaluateReader(getGlobalScope(script.getName()), src, script.getName(), 0, null);
    } catch (RhinoException ex) {
      QueryMod.logger.error(ex.getMessage() + " from " + ex.sourceName());
      QueryMod.logger.error(ex.sourceName() + " : " + ex.lineNumber());
    } finally {
      src.close();
    }
  }

  public static Scriptable getGlobalScope(String scriptName) {
    Context cx = Context.enter();
    Scriptable scope = cx.initStandardObjects();

    scope.put("console", scope, new JsConsole(QueryMod.logger, scriptName));
    scope.put("$", scope, new MineQuery());
    scope.put("Minecraft", scope, null); // TODO

    return scope;
  }

}
