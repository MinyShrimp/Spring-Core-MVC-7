# 스프링 MVC - 웹페이지 만들기
## 프로젝트 생성
### Welcome 페이지 추가
`src/main/resources/static/index.html`
```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<ul>
    <li>상품 관리
        <ul>
            <li><a href="/basic/items">상품 관리 - 기본</a></li>
        </ul>
    </li>
</ul>
</body>
</html>
```

## 요구사항 분석
### 상품 도메인 모델
* 상품 ID
* 상품명
* 가격
* 수량

### 상품 관리 기능
* 상품 목록
* 상품 상세
* 상품 등록
* 상품 수정

### 서비스 화면
![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)
![img_3.png](img_3.png)

### 서비스 제공 흐름
![img_4.png](img_4.png)

## 상품 도메인 개발
### Item - 상품 객체
```java
@Setter @Getter
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {}

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
```

### ItemRepository - 상품 저장소
```java
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
     */
    public void clearStore() {
        store.clear();
    }
}
```

### 상품 저장소 테스트
```java
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
```

## 상품 서비스 HTML

## 상품 목록 - 타임리프

## 상품 상세

## 상품 등록 폼

## 상품 등록 처리 - `@ModelAttribute`

## 상품 수정

## PRG Post/Redirect/Get

## RedirectAttribute

## 정리
