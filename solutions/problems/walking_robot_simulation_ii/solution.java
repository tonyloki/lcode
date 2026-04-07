class Robot {

    List<int[]> pos = new ArrayList<>();
    List<String> dir = new ArrayList<>();
    int idx = 0;
    boolean moved = false;

    public Robot(int width, int height) {

        // 1. East
        for (int x = 0; x < width; x++) {
            pos.add(new int[]{x, 0});
            dir.add("East");
        }

        // 2. North
        for (int y = 1; y < height; y++) {
            pos.add(new int[]{width - 1, y});
            dir.add("North");
        }

        // 3. West
        for (int x = width - 2; x >= 0; x--) {
            pos.add(new int[]{x, height - 1});
            dir.add("West");
        }

        // 4. South
        for (int y = height - 2; y >= 1; y--) {
            pos.add(new int[]{0, y});
            dir.add("South");
        }

        // Fix origin direction after movement
        dir.set(0, "South");
    }

    public void step(int num) {
        int n = pos.size();
        idx = (idx + num) % n;
        moved = true;
    }

    public int[] getPos() {
        return pos.get(idx);
    }

    public String getDir() {
        if (!moved) return "East";
        return dir.get(idx);
    }
}