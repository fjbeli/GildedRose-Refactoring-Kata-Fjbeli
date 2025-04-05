package com.gildedrose;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @ParameterizedTest
    @MethodSource("provideItemsForTest")
    void testUpdate(Item item, String expected) {
        Item[] items = new Item[]{item};
        GildedRose app = new GildedRose(items);
        app.update();
        assertEquals(expected, app.items[0].toString());
    }

    private static Stream<Arguments> provideItemsForTest() {
        return Stream.of(
            Arguments.of(new Item("foo", 0, 0), "foo, -1, 0"),
            Arguments.of(new Item("Conjured Mana Cake", 3, 6), "Conjured Mana Cake, 2, 5"),
            Arguments.of(new Item("Aged Brie", 2, 0), "Aged Brie, 1, 1"),
            Arguments.of(new Item("Aged Brie", 0, 0), "Aged Brie, -1, 2"),
            Arguments.of(new Item("Sulfuras, Hand of Ragnaros", 0, 80), "Sulfuras, Hand of Ragnaros, 0, 80"),
            Arguments.of(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20), "Backstage passes to a TAFKAL80ETC concert, 14, 21"),
            Arguments.of(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49), "Backstage passes to a TAFKAL80ETC concert, 9, 50"),
            Arguments.of(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49), "Backstage passes to a TAFKAL80ETC concert, 4, 50"),
            Arguments.of(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 49), "Backstage passes to a TAFKAL80ETC concert, -1, 0")
        );
    }

}
