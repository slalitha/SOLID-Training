package com.trimble.model;

public class Mark {

	private int mInternalMark;
	private int mExternalMark;
	private int mAttdanceMark;

	public int getmInternalMark() {
		return mInternalMark;
	}

	public int getmExternalMark() {
		return mExternalMark;
	}

	public int getmAttdanceMark() {
		return mAttdanceMark;
	}

	public int getTotalMark() {
		return mInternalMark + mExternalMark + mAttdanceMark;
	}

	public Mark(int internalMark, int externalMark, int attanceMark) {
		this.mInternalMark = internalMark;
		this.mExternalMark = externalMark;
		this.mAttdanceMark = attanceMark;
	}
}
