package travel.security;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

public class XSSRequestWrapper extends HttpServletRequestWrapper {
    private byte[] rawData;
    private HttpServletRequest request;
    private ResettableServletInputStream servletStream;

    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
        this.servletStream = new ResettableServletInputStream();
    }


    public void resetInputStream(byte[] newRawData) {
        servletStream.stream = new ByteArrayInputStream(newRawData);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        if (rawData == null) {
            rawData = IOUtils.toByteArray(this.request.getReader());
            servletStream.stream = new ByteArrayInputStream(rawData);
        }
        return servletStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (rawData == null) {
            rawData = IOUtils.toByteArray(this.request.getReader());
            servletStream.stream = new ByteArrayInputStream(rawData);
        }
        return new BufferedReader(new InputStreamReader(servletStream));
    }

    @Override
    public String getParameter(String name){
        return XSSUtils.stripXSS(super.getParameter(name));
    }



    private class ResettableServletInputStream extends ServletInputStream {

        private InputStream stream;

        @Override
        public int read() throws IOException {
            return stream.read();
        }

        /**
         * Returns true when all the data from the stream has been read else
         * it returns false.
         *
         * @return <code>true</code> when all data for this particular request
         * has been read, otherwise returns <code>false</code>.
         * @since Servlet 3.1
         */
        @Override
        public boolean isFinished() {
            return false;
        }

        /**
         * Returns true if data can be read without blocking else returns
         * false.
         *
         * @return <code>true</code> if data can be obtained without blocking,
         * otherwise returns <code>false</code>.
         * @since Servlet 3.1
         */
        @Override
        public boolean isReady() {
            return false;
        }

        /**
         * Instructs the <code>ServletInputStream</code> to invoke the provided
         * {@link ReadListener} when it is possible to read
         *
         * @param readListener the {@link ReadListener} that should be notified
         *                     when it's possible to read.
         * @throws IllegalStateException if one of the following conditions is true
         *                               <ul>
         *                               <li>the associated request is neither upgraded nor the async started
         *                               <li>setReadListener is called more than once within the scope of the same request.
         *                               </ul>
         * @throws NullPointerException  if readListener is null
         * @since Servlet 3.1
         */
        @Override
        public void setReadListener(ReadListener readListener) {

        }

    }
}