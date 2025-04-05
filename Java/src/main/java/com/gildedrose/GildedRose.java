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
            decreaseSellIn(item);
        } else if (isBackstage(item)) {
            increaseQuality(item);

            if (item.sellIn < 11) {
                increaseQuality(item);
            }

            if (item.sellIn < 6) {
                increaseQuality(item);
            }

            decreaseSellIn(item);
        } else if (!isSulfuras(item)) {
            decreaseQuality(item);
            decreaseSellIn(item);
        }
    }

    private boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    private boolean isBackstage(Item item) {
        return item.name.equals(BACKSTAGE_PASSES);
    }

    private boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS);
    }

    private void increaseQuality(Item item) {
        if (item.quality < 50) {
            updateQuality(item, 1);
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            updateQuality(item, -1);
        }
    }

    private void updateQuality(Item item, int change) {
        item.quality = item.quality + change;
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
        } else if (!isSulfuras(item)) {
            decreaseQuality(item);
        }
    }

}
