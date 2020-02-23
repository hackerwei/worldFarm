package com.hz.world.api.common.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.output.TeeOutputStream;

public class BufferedResponseWrapper extends HttpServletResponseWrapper {

	private HttpServletResponse response;
	private ServletOutputStream outputStream;
	private PrintWriter writer;
	private ByteArrayOutputStream content = new ByteArrayOutputStream();
	private int respBodyLength = 0;

	public BufferedResponseWrapper(HttpServletResponse response) {
		super(response);
		this.response = response;
	}

	public String getBody() {
		return this.content.toString();
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (null == this.outputStream) {
			this.outputStream = new ServletTeeOutputStream(response.getOutputStream(), content);
		}
		return this.outputStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (null == this.writer) {
			this.writer = new PrintWriter(new OutputStreamWriter(getOutputStream()));
		}
		return this.writer;
	}

	@Override
	public void resetBuffer() {
		flushWriter();
		this.content.reset();
		super.resetBuffer();
	}

	@Override
	public void flushBuffer() throws IOException {
		flushWriter();
		if (null != this.outputStream) {
			this.outputStream.flush();
		}
	}

	@Override
	public void reset() {
		flushWriter();
		resetBuffer();
		super.reset();
	}

	@Override
	public void addHeader(String name, String value) {
		Collection<String> headers = this.getHeaders(name);
		if (null != headers && headers.size() > 0 && null != value && headers.contains(value)) {
			return;
		}
		super.addHeader(name, value);
	}

	@Override
	public void setContentLength(int len) {
		super.setContentLength(len);
		respBodyLength = len;
	}

	public int getRespBodyLength() {
		return respBodyLength;
	}

	public void setRespBodyLength(int respBodyLength) {
		this.respBodyLength = respBodyLength;
	}

	private void flushWriter() {
		if (null != this.writer) {
			this.writer.flush();
		}
	}

	private class ServletTeeOutputStream extends ServletOutputStream {

		private final TeeOutputStream teeOutputStream;

		public ServletTeeOutputStream(OutputStream out, OutputStream branch) {
			this.teeOutputStream = new TeeOutputStream(out, branch);
		}

		@Override
		public void write(int b) throws IOException {
			this.teeOutputStream.write(b);
		}

		@Override
		public void write(byte[] b) throws IOException {
			this.teeOutputStream.write(b);
		}

		@Override
		public void write(byte[] b, int offset, int length) throws IOException {
			this.teeOutputStream.write(b, offset, length);
		}

		@Override
		public void flush() throws IOException {
			super.flush();
			this.teeOutputStream.flush();
		}

		@Override
		public void close() throws IOException {
			super.close();
			this.teeOutputStream.close();
		}

		@Override
		public boolean isReady() {
			return true;
		}

		@Override
		public void setWriteListener(WriteListener writeListener) {

		}
	}

}
