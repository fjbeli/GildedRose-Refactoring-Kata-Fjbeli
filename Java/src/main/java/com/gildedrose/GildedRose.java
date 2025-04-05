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

    private int getNewQuality(Item item) {
        switch (item.name) {
            case AGED_BRIE:
                return item.sellIn < 0 ? 2 : 1;
            case BACKSTAGE_PASSES:
                if (item.sellIn < 0) {
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

    private void updateQuality(Item item, int change) {
        // The value 0 means nothing to change in the quality.
        if (change != 0) {
            int newQuality = item.quality + change;
            item.quality = Math.max(0, Math.min(newQuality, 50));
        }
    }

    private void decreaseSellIn(Item item) {
        if (isNotSulfuras(item)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private boolean isNotSulfuras(Item item) {
        return !item.name.equals(SULFURAS);
    }

}
