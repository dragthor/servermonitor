package com.camomedia.servermonitor;

public class MonitoringItem {
	public MonitoringItem() {}
	
	// Parse class name.
	public static final String PARSE_CLASS = "Monitoring";
	
	// Parse field names.
	public static final String PARSE_OBJECTID = "objectId";
	public static final String PARSE_PARSETYPE = "ParseType";
	public static final String PARSE_TAB = "Tab";
	public static final String PARSE_PATTERN = "Pattern";
	public static final String PARSE_URL = "Url";
	
	private int _parseType;
	private int _tab;
	private String _pattern;
	private String _url;
	private String _objectId;
	
	public String get_objectId() {
		return _objectId;
	}

	public void set_objectId(String _objectId) {
		this._objectId = _objectId;
	}

	public int get_tab() {
		return _tab;
	}

	public void set_tab(int _tab) {
		this._tab = _tab;
	}

	public String get_pattern() {
		return _pattern;
	}

	public void set_pattern(String _pattern) {
		this._pattern = _pattern;
	}

	public String get_url() {
		return _url;
	}

	public void set_url(String _url) {
		this._url = _url;
	}
	
	public int get_parseType() {
		return _parseType;
	}

	public void set_parseType(int _parseType) {
		this._parseType = _parseType;
	}
}
