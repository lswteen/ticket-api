package travel.api.external.web.support;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by we on 2017. 2. 24..
 */
public class HttpRequestWrapper extends HttpServletRequestWrapper {

    private final Map<String, String[]> modifiableParameters;
    private Map<String, String[]> allParameters = null;

    private byte[] bodyData;

    /**
     * Create a new request wrapper that will merge additional parameters into
     * the request object without prematurely reading parameters from the
     * original request.
     *
     * @param request
     * @param additionalParams
     */
    public HttpRequestWrapper(HttpServletRequest request, final Map<String, String[]> additionalParams) throws IOException {
        super(request);

        // parameter map set.
        modifiableParameters = new TreeMap<String, String[]>();
        modifiableParameters.putAll(additionalParams);

        // body copy
        InputStream is = super.getInputStream();
        bodyData = IOUtils.toByteArray(is);
    }


    @Override
    public String getParameter(String name) {
        String[] strings = getParameterMap().get(name);
        if (strings != null)
        {
            return strings[0];
        }
        return null;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        if (allParameters == null)
        {
            allParameters = new TreeMap<String, String[]>();
            allParameters.putAll(super.getParameterMap());
            allParameters.putAll(modifiableParameters);
        }
        //Return an unmodifiable collection because we need to uphold the interface contract.
        return Collections.unmodifiableMap(allParameters);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return Collections.enumeration(getParameterMap().keySet());
    }

    @Override
    public String[] getParameterValues(String name) {
        return getParameterMap().get(name);
    }


    public byte[] getBodyData() {
        return bodyData;
    }

    public void setBodyData(byte[] bodyData) {
        this.bodyData = bodyData;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bis = new ByteArrayInputStream(bodyData);
        return new ServletInputImpl(bis);
    }

    private class ServletInputImpl extends ServletInputStream {
        private InputStream is;

        public ServletInputImpl(InputStream bis) {
            is = bis;
        }

        @Override
        public int read() throws IOException {
            return is.read();
        }

        @Override
        public int read(byte[] b) throws IOException {
            return is.read(b);
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener readListener) {

        }
    }


}
