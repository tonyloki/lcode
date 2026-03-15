import java.util.*;

class Fancy {

    static final long MOD = 1_000_000_007;

    List<Long> arr;
    long mul;
    long add;

    public Fancy() {
        arr = new ArrayList<>();
        mul = 1;
        add = 0;
    }

    public void append(int val) {

        long inv = modInverse(mul);

        long normalized = ((val - add) % MOD + MOD) % MOD;
        normalized = (normalized * inv) % MOD;

        arr.add(normalized);
    }

    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }

    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }

    public int getIndex(int idx) {

        if (idx >= arr.size())
            return -1;

        long val = arr.get(idx);

        long result = (val * mul + add) % MOD;

        return (int) result;
    }

    long modInverse(long x) {
        return pow(x, MOD - 2);
    }

    long pow(long a, long b) {

        long res = 1;

        while (b > 0) {

            if ((b & 1) == 1)
                res = (res * a) % MOD;

            a = (a * a) % MOD;
            b >>= 1;
        }

        return res;
    }
}
