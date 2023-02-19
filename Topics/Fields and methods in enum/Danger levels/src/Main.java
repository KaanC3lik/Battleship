enum DangerLevel {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    final int level;

    DangerLevel(int i) {
        this.level = i;
    }

    public int getLevel() {
        return level;
    }
}