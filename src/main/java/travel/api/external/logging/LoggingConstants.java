package travel.api.external.logging;

/**
 * Created by we on 2017. 2. 24..
 */
public class LoggingConstants {
    public enum Transaction{
        Request, Response
    }
    public interface Info{}
    public interface Trace{}
    public interface Debug{}
    public interface Error{}
}
