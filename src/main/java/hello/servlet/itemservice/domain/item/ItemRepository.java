package hello.servlet.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static //동시에 멀티쓰레드에서 접근하고 싶으면 concurrentHashMap
    private static long sequence = 0L; //static //동시에 멀티쓰레드에서 접근하고 싶으면 atomicLong나 다른거 사용

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) { //보통이라면 updateParamDto라던가 클래스 하나를 더 만들어서 사용하는 애들만 모델 만드는게 맞음. Item경우에는 id가 있고 set도 할 수 있는데 그럼 다른사람 입장에선 어 이건 뭐지 할 수 있음
        //중복이냐 명확성이냐 -> 명확성 그렇기 떄문에 중복 같아도 두개를 써라!!
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
