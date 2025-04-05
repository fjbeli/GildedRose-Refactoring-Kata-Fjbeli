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
        int newQuality = getNewQuality(item);
        updateQuality(item, newQuality);
        if (isNotSulfuras(item)) {
            decreaseSellIn(item);
        }
    }

    private int getNewQuality(Item item) {
        switch (item.name) {
            case AGED_BRIE:
                return 1;
            case BACKSTAGE_PASSES:
                if (item.sellIn < 6) {
                    return 3;
                } else if (item.sellIn < 11) {
                    return 2;
                } else {
                    return 1;
                }
            case SULFURAS:
                return 0;
            default:
                return -1;
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

    private void updateQuality(Item item, int change) {
        // Quality of sulfuras cannot be altered
        if (isNotSulfuras(item)) {
            int newQuality = item.quality + change;
            item.quality = Math.max(0, Math.min(newQuality, 50));
        }
    }

    private void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
        if (item.sellIn < 0) {
            updateQualityForNegativeSellIn(item);
        }
    }

    private void updateQualityForNegativeSellIn(Item item) {
        if (isAgedBrie(item)) {
            updateQuality(item, 1);
        } else if (isBackstage(item)) {
            updateQuality(item, -(item.quality));
        } else if (isNotSulfuras(item)) {
            updateQuality(item, -1);
        }
    }

}
