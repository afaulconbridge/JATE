package jate.util;

import java.util.Objects;

public class XY implements Comparable<XY> {
	public final float x;
	public final float y;

	public XY(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (other == null || !(other instanceof XY)) {
			return false;
		}
		XY otherXY = (XY) other;
		return Objects.equals(this.x, otherXY.x) && Objects.equals(this.y, otherXY.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public int compareTo(XY other) {
		float thisSum = this.x + this.y;
		float otherSum = other.x + other.y;
		if (thisSum < otherSum) {
			return -1;
		} else if (thisSum > otherSum) {
			return 1;
		} else {
			if (this.x < other.x) {
				return -1;
			} else if (this.x > other.x) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}