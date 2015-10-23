/**
 * Created by domen on 3/14/15.
 * Holds data for one frame of animation
 */
public class Frame {
    private int number;
    private boolean[][][] values;
    private String name;
    private static int nextn = 0;

    public Frame(int num) {
        number = num;
        values = new boolean[number][number][number];
        name = String.format("Sličica %d", nextn);
        nextn++;
    }

    public Frame(Frame c) {
        number = c.getNumber();
        values = new boolean[number][number][number];
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                for (int k = 0; k < number; k++) {
                    values[i][j][k] = c.getValue(i,j,k);
                }
            }
        }
        name = c.getName() + " copy";
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public boolean getValue(int x, int y, int z) {
        return values[x][y][z];
    }

    public boolean getValue(int[] pos) {
        return values[pos[0]][pos[1]][pos[2]];
    }

    public void setValue(int x, int y, int z, boolean v) {
        values[x][y][z] = v;
    }

    public void setValue(int[] pos, boolean v) {
        values[pos[0]][pos[1]][pos[2]] = v;
    }
}
