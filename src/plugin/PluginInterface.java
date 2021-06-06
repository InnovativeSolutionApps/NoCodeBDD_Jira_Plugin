package plugin;

import java.io.IOException;
import java.util.List;

public interface PluginInterface {
    public void callMe(List<?> list) throws IOException;
}
