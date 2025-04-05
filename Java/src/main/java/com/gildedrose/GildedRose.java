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
        if (isAgedBrie(item)) {
            increaseQuality(item);
        } else if (isBackstage(item)) {
            if (item.sellIn < 6) {
                updateQuality(item, 3);
            } else if (item.sellIn < 11) {
                updateQuality(item, 2);
            } else {
                increaseQuality(item);
            }
        } else if (isNotSulfuras(item)) {
            decreaseQuality(item);
        }

        if (isNotSulfuras(item)) {
            decreaseSellIn(item);
        }
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    private boolean isBackstage(Item item) {
        return item.name.equals(BACKSTAGE_PASSES);
    }

    private boolean isNotSulfuras(Item item) {
        return !item.name.equals(SULFURAS);
    }

    private void increaseQuality(Item item) {
        updateQuality(item, 1);
    }

    private void decreaseQuality(Item item) {
        updateQuality(item, -1);
    }

    private void updateQuality(Item item, int change) {
        int newQuality = item.quality + change;
        item.quality = Math.max(0, Math.min(newQuality, 50));
    }

    private void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            updateQualityForNegativeSellIn(item);
        }
    }

    private void updateQualityForNegativeSellIn(Item item) {
        if (isAgedBrie(item)) {
            increaseQuality(item);
        } else if (isBackstage(item)) {
            updateQuality(item, -(item.quality));
        } else if (isNotSulfuras(item)) {
            decreaseQuality(item);
        }
    }

}
