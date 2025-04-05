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

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                increaseQuality(item);
            }
        } else if (isBackstage(item)) {
            increaseQuality(item);

            if (item.sellIn < 11) {
                increaseQuality(item);
            }

            if (item.sellIn < 6) {
                increaseQuality(item);
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                item.quality = 0;
            }
        } else if (!isSulfuras(item)) {
            decreaseQuality(item);

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                decreaseQuality(item);
            }
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
            item.quality = item.quality + 1;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > 0) {
            item.quality = item.quality - 1;
        }
    }

}
