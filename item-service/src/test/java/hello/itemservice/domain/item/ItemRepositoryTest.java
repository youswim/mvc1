package hello.itemservice.domain.item;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void clear() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(savedItem.getId());
        Assertions.assertThat(savedItem).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        List<Item> Items = itemRepository.findAll();

        Assertions.assertThat(Items.size()).isEqualTo(2);
        Assertions.assertThat(Items).contains(item1, item2);
    }

    @Test
    void updateItem() {
        Item item1 = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item1);

        Item newItem = new Item("newItem1", 20000, 20);
        itemRepository.update(savedItem.getId(), newItem);

        Item findItem = itemRepository.findById(savedItem.getId());

        Assertions.assertThat(findItem.getItemName()).isEqualTo(newItem.getItemName());
        Assertions.assertThat(findItem.getPrice()).isEqualTo(newItem.getPrice());
        Assertions.assertThat(findItem.getQuantity()).isEqualTo(newItem.getQuantity());
    }
}