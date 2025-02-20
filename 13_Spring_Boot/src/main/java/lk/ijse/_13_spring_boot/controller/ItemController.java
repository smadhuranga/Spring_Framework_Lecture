package lk.ijse._13_spring_boot.controller;

import lk.ijse._13_spring_boot.dto.ItemDTO;
import lk.ijse._13_spring_boot.service.impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin(origins = "*")
public class ItemController {
    @Autowired
    private ItemServiceImpl itemService;

    @PostMapping(path = "save")
    public Boolean saveItem(@RequestBody ItemDTO ItemDTO) {
        boolean res = itemService.save(ItemDTO);
        return res;
    }

    @GetMapping("search/{id}")
    public ItemDTO getItemById(@PathVariable int id) {
        ItemDTO ItemDTO = itemService.getItemById(id);

        if (ItemDTO != null) {
            return ItemDTO;
        } else {
            return null;
        }
    }

    @PutMapping("update")
    public Boolean updateItem(@RequestBody ItemDTO ItemDTO) {
        boolean res = itemService.updateItem(ItemDTO);

        if (res) {
            return true;
        } else {
            return false;
        }
    }

    @DeleteMapping("delete/{id}")
    public Boolean deleteItem(@PathVariable int id) {
        boolean res = itemService.deleteItem(id);
        if (res) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("getAll")
    public List<ItemDTO> getAllItems() {
        List<ItemDTO> allItems = itemService.getAllItems();
        return allItems;
    }
}