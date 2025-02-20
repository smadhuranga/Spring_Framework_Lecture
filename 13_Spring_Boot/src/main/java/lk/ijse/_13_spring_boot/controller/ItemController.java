package lk.ijse._13_spring_boot.controller;

import lk.ijse._13_spring_boot.dto.ItemDTO;
import lk.ijse._13_spring_boot.service.impl.ItemServiceImpl;
import lk.ijse._13_spring_boot.util.ResponsUtil;
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
    public ResponsUtil saveItem(@RequestBody ItemDTO ItemDTO) {
        boolean res = itemService.save(ItemDTO);

        if (res) {
            return new ResponsUtil(200, "Success", null);
        } else {
            return new ResponsUtil(404, "Failed", null);
        }
    }

    @GetMapping("search/{id}")
    public ResponsUtil getItemById(@PathVariable int id) {
        ItemDTO ItemDTO = itemService.getItemById(id);

        if (ItemDTO != null) {
            return new ResponsUtil(200, "Success", ItemDTO);
        } else {
            return new ResponsUtil(404, "Failed", null);
        }
    }

    @PutMapping("update")
    public ResponsUtil updateItem(@RequestBody ItemDTO ItemDTO) {
        boolean res = itemService.updateItem(ItemDTO);

        if (res) {
            return new ResponsUtil(200, "Success", null);
        } else {
            return new ResponsUtil(404, "Failed", null);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponsUtil deleteItem(@PathVariable int id) {
        boolean res = itemService.deleteItem(id);

        if (res) {
            return new ResponsUtil(200, "Success", null);
        } else {
            return new ResponsUtil(404, "Failed", null);
        }
    }

    @GetMapping("getAll")
    public ResponsUtil getAllItems() {
        List<ItemDTO> allItems = itemService.getAllItems();

        if (allItems != null) {
            return new ResponsUtil(200, "Success", allItems);
        } else {
            return new ResponsUtil(404, "Failed", null);
        }
    }
}