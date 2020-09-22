//316418300
package general;

/**
 * Counter class.
 */
public class Counter {
    private int value = 0;

    /**
     * add number to current count.
     *
     * @param number the given number.
     */
    public void increase(int number) {
        this.value += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the given number.
     */

    public void decrease(int number) {
        this.value -= number;
    }

    /**
     * get current count.
     *
     * @return this number.
     */
    public int getValue() {
        return this.value;
    }
}
