package com.gildedrose;

import static com.gildedrose.Items.AGED_BRIE;
import static com.gildedrose.Items.BACKSTAGE_PASSES;
import static com.gildedrose.Items.SULFURAS;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void update() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        decreaseSellIn(item);
        int newQuality = getNewQuality(item);
        updateQuality(item, newQuality);
    }

    /**
     * Get the new change in the quality of an {@code item}.
     *
     * @param item the item
     * @return the change of the quality
     */
    private int getNewQuality(Item item) {
        switch (item.name) {
            case AGED_BRIE:
                return item.sellIn < 0 ? 2 : 1;
            case BACKSTAGE_PASSES:
                if (item.sellIn < 0) {
                    // reset the quality to 0
                    return -item.quality;
                } else if (item.sellIn < 5) {
                    return 3;
                } else if (item.sellIn < 10) {
                    return 2;
                } else {
                    return 1;
                }
            case SULFURAS:
                return 0;
            default:
                return item.sellIn < 0 ? -2 : -1;
        }
    }

    /**
     * Update the quality of the given {@code item} with the given {@code change}.
     * When change is zero then nothing to change in the quality.
     * When change is not zero the new quality is going to be between 0 and 50 as stated in the business rule.
     *
     * @param item   the item
     * @param change the change that needs to be added to the quality.
     */
    private void updateQuality(Item item, int change) {
        // The value 0 means nothing to change in the quality.
        if (change != 0) {
            int newQuality = item.quality + change;
            item.quality = Math.max(0, Math.min(newQuality, 50));
        }
    }

    /**
     * Decrease by 1 the sellIn data in the given {@code item}.
     * Sulfuras items are not decreased by this method as per the business rule.
     *
     * @param item the item that its sellIn will be decreased.
     */
    private void decreaseSellIn(Item item) {
        // Sulfuras items are not altered
        if (isNotSulfuras(item)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    /**
     * Check if the given {@code item} is not sulfuras item.
     *
     * @param item the item
     * @return the boolean
     */
    private boolean isNotSulfuras(Item item) {
        return !item.name.equals(SULFURAS);
    }

}
