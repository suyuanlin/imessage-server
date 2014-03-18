package com.imessage.util.lang;

/**
 * 
 * 
 * JSONResponse
 * 2013-12-26 下午3:57:46
 * @author Tkk
 */
public class JSONResponse {

	private boolean s;

	private Object r;

	public JSONResponse() {
		this(true, "");
	}

	public JSONResponse(Object r) {
		this(true, r);
	}

	public JSONResponse(boolean s, Object r) {
		super();
		this.s = s;
		this.r = r;
	}

	public boolean isS() {
		return s;
	}

	public void setS(boolean s) {
		this.s = s;
	}

	public Object getR() {
		return r;
	}

	public void setR(Object r) {
		this.r = r;
	}

}
