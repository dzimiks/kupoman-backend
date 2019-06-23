package app.mappers;

import app.dataTransferObjects.ShopDTO;
import app.models.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 17:43
 */
@Mapper
public interface ShopMapper {
	ShopMapper INSTANCE = Mappers.getMapper(ShopMapper.class);

	ShopDTO shopToShopDTO(Shop shop);
}
