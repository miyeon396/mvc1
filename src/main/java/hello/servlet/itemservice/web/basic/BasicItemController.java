package hello.servlet.itemservice.web.basic;

import hello.servlet.itemservice.domain.item.Item;
import hello.servlet.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    //위의 예제를 모델 어트리뷰트로 바꿈
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) { //, Model model

        itemRepository.save(item);
//        model.addAttribute("item", item); modelAttribute가 이름 지정한 item으로 알아서 넣어준다. -> 자동 추가, 생략 가능 model도 날려도됨.

        return "basic/item";
    }

    //위의 예제를 좀 더 개선
//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        //("item") 요거를 지우면 -> 첫글자를 소문자로 바꿔서 item이 이름이 된다.

        itemRepository.save(item);
//        model.addAttribute("item", item); modelAttribute가 이름 지정한 item으로 알아서 넣어준다. -> 자동 추가, 생략 가능 model도 날려도됨.

        return "basic/item";
    }

    //위의 예제를 좀 더 개선
//    @PostMapping("/add")
    public String addItemV4(Item item) { //string이나 이런 단순타입이 오면 requestParam이지만 여기선 ModelAttribute 애노테이션도 생략이 가능하다.
        //("item") 요거를 지우면 -> 첫글자를 소문자로 바꿔서 item이 이름이 된다.
        itemRepository.save(item);

        return "basic/item";
    }

    //위의 예제를 좀 더 개선
//    @PostMapping("/add")
    public String addItemV5(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items/"+item.getId();
    }

    //위의 예제를 좀 더 개선
    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        //redirectAttribute를 사용하면 아래 치환되서 들어간다. 근데 없는거는? ?status=true 이런식으로 뒤에 붙음
        return "redirect:/basic/items/{itemId}";
    }


    @GetMapping("{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";

    }

    @PostMapping("{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";

    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
