package hello.springcoremvc7.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InMemory 방식 Item 저장소
 */
@Repository
public class ItemRepository {
    private static final Map<Long, Item> store = new HashMap<>();
    private static long sequence = 0L;

    /**
     * Item 저장
     * @param item
     * @return 생성한 Item
     */
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    /**
     * Item 하나 찾기
     * @param id
     * @return 찾은 Item
     */
    public Item findById(Long id) {
        return store.get(id);
    }

    /**
     * 모든 Item 복사해서 반환
     * @return 모든 Items
     */
    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    /**
     * Item 정보 수정
     * @param itemId
     * @param updateItem
     */
    public void update(
            Long itemId, Item updateItem
    ) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateItem.getItemName());
        findItem.setPrice(updateItem.getPrice());
        findItem.setQuantity(updateItem.getQuantity());
    }

    /**
     * 메모리에 저장된 모든 Item 제거
     * - 테스트 용도로만 사용
     */
    public void clearStore() {
        store.clear();
    }
}
