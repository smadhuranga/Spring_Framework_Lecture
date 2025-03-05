package lk.ijse.service;

import lk.ijse.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    public void saveItem(ItemDTO itemDTO);

    public ItemDTO getItemById(String id);

    public void updateItem(ItemDTO itemDTO);

    public void deleteItem(String id);

    public List<ItemDTO> getAllItems();

}
