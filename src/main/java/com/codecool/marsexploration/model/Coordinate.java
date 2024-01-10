package com.codecool.marsexploration.model;

import java.util.Objects;

public record Coordinate(int x, int y) {
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordinate that = (Coordinate) obj;
        return this.x() == that.x() && this.y() == that.y();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x(), this.y());
    }
}
