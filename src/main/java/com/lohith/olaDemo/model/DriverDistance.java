package com.lohith.olaDemo.model;

public class DriverDistance implements Comparable<DriverDistance> {
    private long key;
    private float value;

    public DriverDistance(long key, float value) {
        this.key = key;
        this.value = value;
    }

    // getters

    public long getKey() {
		return key;
	}

	public float getValue() {
		return value;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Override
    public int compareTo(DriverDistance other) {
		return this.value < other.value ? -1 
			     : this.value > other.value ? 1 
			     : 0;
    }


}

