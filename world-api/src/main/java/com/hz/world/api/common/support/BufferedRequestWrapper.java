package com.hz.world.api.common.support;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

public class BufferedRequestWrapper extends HttpServletRequestWrapper {
	
	public static final int BUFFER_SIZE = 8192; 

	private byte[] buffer = null;

	public BufferedRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);

		InputStream in = request.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			writeTo(in, out);
			this.buffer = out.toByteArray();
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(in);
		}
	}
	
	private void writeTo(InputStream in, OutputStream out) throws IOException { 
        int read; 
        final byte[] data = new byte[BUFFER_SIZE]; 
        while ((read = in.read(data)) != -1) 
            out.write(data, 0, read); 
    } 

	@Override
	public ServletInputStream getInputStream() {
		return new BufferedServletInputStream(new ByteArrayInputStream(this.buffer));
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(this.buffer)));
	}

	public String getBody() {
		return new String(buffer);
	}

	class BufferedServletInputStream extends ServletInputStream {
		private ByteArrayInputStream inputStream;

		public BufferedServletInputStream(ByteArrayInputStream inputStream) {
			this.inputStream = inputStream;
		}

		@Override
		public int read() throws IOException {
			return inputStream.read();
		}

		@Override
		public int read(byte[] b) throws IOException {
			return inputStream.read(b);
		}

		@Override
		public int read(byte[] b, int offset, int length) throws IOException {
			return inputStream.read(b, offset, length);
		}

		@Override
		public long skip(long n) throws IOException {
			return inputStream.skip(n);
		}

		@Override
		public int available() throws IOException {
			return inputStream.available();
		}

		@Override
		public boolean isFinished() {
			return inputStream.available() <= 0;
		}

		@Override
		public void close() throws IOException {
			inputStream.close();
		}

		@Override
		public synchronized void mark(int limit) {
			inputStream.mark(limit);
		}

		@Override
		public synchronized void reset() throws IOException {
			inputStream.reset();
		}

		@Override
		public boolean markSupported() {
			return inputStream.markSupported();
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setReadListener(ReadListener readListener) {

		}
	}
}
