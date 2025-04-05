package com.gildedrose;

import static com.gildedrose.Items.AGED_BRIE;
import static com.gildedrose.Items.BACKSTAGE_PASSES;
import static com.gildedrose.Items.SULFURAS;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateQualityItem(item);
        }
    }

    private void updateQualityItem(Item item) {
        if (isAgedBrie(item)) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        } else if (isBackstage(item)) {
            if (item.quality < 50) {
                item.quality = item.quality + 1;

                if (item.sellIn < 11) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }

                if (item.sellIn < 6) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                item.quality = 0;
            }
        } else if (!isSulfuras(item)) {
            if (item.quality > 0) {
                item.quality = item.quality - 1;
            }

            item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                if (item.quality > 0) {
                    item.quality = item.quality - 1;
                }
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

}
