package lk.ijse.service.impl;

import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Item;
import lk.ijse.repository.ItemRepo;
import lk.ijse.service.ItemService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void saveItem(ItemDTO itemDTO) {
//        Item item = new Item(
//                itemDTO.getCode(),
//                itemDTO.getName(),
//                itemDTO.getQtyOnHand(),
//                itemDTO.getUnitPrice());

        // use model mapper to map
        if (itemRepo.existsById(itemDTO.getItemCode())) {
            throw new RuntimeException("Item already exists..!");
        }

        itemRepo.save(modelMapper.map(itemDTO, Item.class));
    }

    @Override
    public ItemDTO getItemById(String id) {
        Optional<Item> itemOptional = itemRepo.findById(id);

        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            return new ItemDTO(
                    item.getItemCode(),
                    item.getDescription(),
                    item.getQtyOnHand(),
                    item.getUnitPrice());
        }
        return null;
    }

    @Override
    public void updateItem(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getItemCode())) {
//            Item item = new Item(
//                    itemDTO.getCode(),
//                    itemDTO.getName(),
//                    itemDTO.getQtyOnHand(),
//                    itemDTO.getUnitPrice());

            // use model mapper to map
            itemRepo.save(modelMapper.map(itemDTO, Item.class));
        }

        throw new RuntimeException("No such item for update..!");
    }

    @Override
    public void deleteItem(String id) {
        if (itemRepo.existsById(id)) {
            itemRepo.deleteById(id);;
        }

        throw new RuntimeException("No such item for delete..!");
    }

    @Override
    public List<ItemDTO> getAllItems() {
//        List<Item> items = itemRepo.findAll();
//        List<ItemDTO> itemDTOS = new ArrayList<>();
//
//        for (Item item : items) {
//            itemDTOS.add(new ItemDTO(
//                    item.getCode(),
//                    item.getName(),
//                    item.getQtyOnHand(),
//                    item.getUnitPrice()));
//        }

        // use model mapper to map
        return modelMapper.map(itemRepo.findAll(), new TypeToken<List<ItemDTO>>() {}.getType());
    }
}
