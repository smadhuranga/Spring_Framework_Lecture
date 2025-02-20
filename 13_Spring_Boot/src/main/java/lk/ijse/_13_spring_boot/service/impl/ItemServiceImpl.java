package lk.ijse._13_spring_boot.service.impl;

import lk.ijse._13_spring_boot.dto.ItemDTO;
import lk.ijse._13_spring_boot.entity.Item;
import lk.ijse._13_spring_boot.repo.ItemRepo;
import lk.ijse._13_spring_boot.service.ItemService;
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
    private ModelMapper modelMapper; // ModelMapper

    @Override
    public boolean save(ItemDTO itemDTO) {
//        Item item = new Item(
//                itemDTO.getId(),
//                itemDTO.getName(),
//                itemDTO.getPrice(),
//                itemDTO.getQty()
//        );
        itemRepo.save(modelMapper.map(itemDTO, Item.class));
        return true;
    }

    @Override
    public ItemDTO getItemById(int id) {
        Optional<Item> itemOptional = itemRepo.findById(id);

        if (itemOptional.isPresent()) {
            Item item = itemOptional.get();
            return new ItemDTO(
                    item.getId(),
                    item.getName(),
                    item.getPrice(),
                    item.getQty()
            );
        }
        return null;
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) {
        if (itemRepo.existsById(itemDTO.getId())) {
//            Item item = new Item(
//                    itemDTO.getId(),
//                    itemDTO.getName(),
//                    itemDTO.getPrice(),
//                    itemDTO.getQty()
//            );
            itemRepo.save(modelMapper.map(itemDTO, Item.class));
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteItem(int id) {
        if (itemRepo.existsById(id)) {
            itemRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<ItemDTO> getAllItems() {
//        List<Item> items = itemRepo.findAll();
//        List<ItemDTO> itemDTOs = new ArrayList<>();
//
//        for (Item item : items) {
//            itemDTOs.add(new ItemDTO(
//                    item.getId(),
//                    item.getName(),
//                    item.getPrice(),
//                    item.getQty())
//            );
//        }
        return modelMapper.map(itemRepo.findAll(), new TypeToken<List<ItemDTO>>() {
        }.getType());
    }
}