package app.mappers;

import app.dataTransferObjects.CouponDTO;
import app.models.Coupon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author dzimiks
 * Date: 23-06-2019 at 17:43
 */
@Mapper
public interface CouponMapper {
	CouponMapper INSTANCE = Mappers.getMapper(CouponMapper.class);

	CouponDTO couponToCouponDTO(Coupon coupon);
}
