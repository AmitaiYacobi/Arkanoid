//316418300
package interfaces;

/**
 * HitNotifier class.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     *
     * @param hl a HitListener.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl a HitListener.
     */
    void removeHitListener(HitListener hl);
}
