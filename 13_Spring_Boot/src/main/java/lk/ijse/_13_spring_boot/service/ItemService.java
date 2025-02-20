package lk.ijse._13_spring_boot.service;

import lk.ijse._13_spring_boot.dto.ItemDTO;
import lk.ijse._13_spring_boot.entity.Item;
import org.modelmapper.TypeToken;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    public boolean save(ItemDTO itemDTO);

    public ItemDTO getItemById(int id);

    public boolean updateItem(ItemDTO itemDTO);

    public boolean deleteItem(int id);

    public List<ItemDTO> getAllItems();
}
