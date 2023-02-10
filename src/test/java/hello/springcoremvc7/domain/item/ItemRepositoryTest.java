package hello.springcoremvc7.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 1000, 10);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item findItem = itemRepository.findById(savedItem.getId());
        Assertions.assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        Item itemA = new Item("itemA", 1000, 10);
        Item itemB = new Item("itemB", 2000, 1000);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        // when
        List<Item> findItems = itemRepository.findAll();

        // then
        Assertions.assertThat(findItems.size()).isEqualTo(2);
        Assertions.assertThat(findItems).contains(itemA, itemB);
    }

    @Test
    void update() {
        // given
        Item itemA = new Item("itemA", 1000, 10);
        Item itemB = new Item("itemB", 3000, 100);
        itemRepository.save(itemA);

        // when
        itemRepository.update(itemA.getId(), itemB);
        Item findItem = itemRepository.findById(itemA.getId());

        // then
        Assertions.assertThat(findItem).isEqualTo(itemA);
        Assertions.assertThat(findItem.getItemName()).isEqualTo(itemA.getItemName());
        Assertions.assertThat(findItem.getItemName()).isEqualTo(itemB.getItemName());
        Assertions.assertThat(itemA).isNotEqualTo(itemB);
    }
}